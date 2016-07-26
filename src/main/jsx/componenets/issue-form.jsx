import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';
import SelectForm from './select-form';

class IssueForm extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
        this.save = this.save.bind(this);
        this.state = {
            data: {
                id: '',
                createdDate: '',
                project: '',
                key: '',
                summery: '',
                description: '',
                priority: {
                    id: "",
                    name: "",
                    description: ""
                },
                status: {
                    id: "",
                    name: "",
                    description: ""
                },
                type: {
                    id: "",
                    name: "",
                    description: ""
                }
            },
            edit: false,
            priorities: [],
            statuses: [],
            types: []
        };
    }

    componentWillMount() {
        if (this.props.params.issueId) {
            client({method: 'GET', path: this.props.apiUrl.issue + '/' + this.props.params.issueId}).then(response => {
                if (response.status.code == 200) {
                    this.setState({data: response.entity, edit: true});
                }
            });
        } else {
            this.state.data.project = this.props.params.projectKey;
        }

        client({method: 'GET', path: this.props.apiUrl.issue + '/priority'}).then(response => {
            if (response.status.code == 200) {
                this.setState({priorities: response.entity});
                if(!this.state.edit && this.state.priorities.length > 0) {
                    this.state.data.priority = this.state.priorities[0];
                    this.forceUpdate();
                }

            }
        });
        client({method: 'GET', path: this.props.apiUrl.issue + '/status'}).then(response => {
            if (response.status.code == 200) {
                this.setState({statuses: response.entity});
                if(!this.state.edit && this.state.statuses.length > 0) {
                    this.state.data.status = this.state.statuses[0];
                    this.forceUpdate();
                }
            }
        });
        client({method: 'GET', path: this.props.apiUrl.issue + '/type'}).then(response => {
            if (response.status.code == 200) {
                this.setState({types: response.entity});
                if(!this.state.edit && this.state.types.length > 0) {
                    this.state.data.type = this.state.types[0];
                    this.forceUpdate();
                }
            }
        });
    }

    save() {
        if (this.state.edit) {
            client({method: 'PUT', path: this.props.apiUrl.issue, entity: this.state.data}).then(response => {
                if (response.status.code == 200) {
                    this.props.history.push('/projects/' + this.state.data.project + '/issues/');
                }
            });
        } else {
            client({method: 'POST', path: this.props.apiUrl.issue, entity: this.state.data}).then(response => {
                if (response.status.code == 201) {
                    this.props.history.push('/projects/' + this.state.data.project + '/issues/');
                }
            });
        }
    }

    handleChange(e) {
        this.state.data[e.target.name] = e.target.value;
        this.setState({data: this.state.data});
    }

    handleSelectChange(name) {
        var self = this;
        return function(e) {
            self.state.data[name] = e;
            self.setState({data: self.state.data});
        };
    }

    render() {
        return (
            <form className="form-horizontal">
                <div className="main-issue-form col-md-8">
                    <div className="form-group">
                        <label htmlFor="inputSummery" className="col-sm-2 control-label">Summery</label>
                        <div className="col-sm-10">
                            <input name="summery" type="text" className="form-control" classID="inputSummery"
                                   placeholder="Summery"
                                   value={this.state.data.summery} onChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputDesc" className="col-sm-2 control-label">Description</label>
                        <div className="col-sm-10">
                        <textarea name="description" className="form-control" rows="3" classID="inputDesc"
                                  placeholder="Description" value={this.state.data.description}
                                  onChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-offset-2 col-sm-10 btn-group">
                            <a className="btn btn-primary" role="button" onClick={this.save}>Save</a>
                            <Link to={'/projects/' + this.state.data.project + '/issues/'} className="btn btn-danger"
                                  role="button">Cancel</Link>
                        </div>
                    </div>
                </div>
                <div className="options-sidebar col-md-4 table-responsive">
                    <table className="table table-striped">
                        <tbody>
                        <tr>
                            <th className="col-xs-2">Project:</th>
                            <td className="col-xs-10">{this.state.data.project}</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Priority:</th>
                            <td className="col-xs-10">
                                <SelectForm
                                    onChange={this.handleSelectChange('priority')}
                                    options={this.state.priorities}
                                    selected={this.state.data.priority}/>
                            </td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Type:</th>
                            <td className="col-xs-10">
                                <SelectForm
                                    onChange={this.handleSelectChange('type')}
                                    options={this.state.types}
                                    selected={this.state.data.type}/>
                            </td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Status:</th>
                            <td className="col-xs-10">
                                <SelectForm
                                    onChange={this.handleSelectChange('status')}
                                    options={this.state.statuses}
                                    selected={this.state.data.status}/>
                            </td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Assignees:</th>
                            <td className="col-xs-10">me</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Created date:</th>
                            <td className="col-xs-10">get Date</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Labels:</th>
                            <td className="col-xs-10"></td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </form>
        )

    }
}

export default IssueForm;
IssueForm.defaultProps = {apiUrl: {issue: '/api/issues'}};
