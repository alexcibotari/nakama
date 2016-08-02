import React, {Component} from 'react';
import {Link, withRouter} from 'react-router';
import client from '../services/client';
import SelectForm from './select-form';
import ValidationForm from '../services/validation-form';

class IssueForm extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSelectChange = this.handleSelectChange.bind(this);
        this.validate = this.validate.bind(this);
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
            types: [],
            constraints: []
        };
    }

    componentWillMount() {
        if (this.props.params.issueKey) {
            client({method: 'GET', path: this.props.apiUrl.issue + '/' + this.props.params.issueKey}).then(response => {
                if (response.status.code == 200) {
                    this.setState({data: response.entity, edit: true});
                }
            });
        } else {
            this.state.data.project = this.props.params.projectKey;
        }

        client({method: 'GET', path: this.props.apiUrl.constraints}).then(response => {
            if (response.status.code == 200) {
                this.setState({constraints: response.entity});
            }
        });

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
                    this.props.router.push('/projects/' + this.state.data.project + '/issues/');
                }
            });
        } else {
            client({method: 'POST', path: this.props.apiUrl.issue, entity: this.state.data}).then(response => {
                if (response.status.code == 201) {
                    this.props.router.push('/projects/' + this.state.data.project + '/issues/');
                }
            });
        }
    }

    validate() {
        console.log(this.state.data);
        if(ValidationForm.validate(this.state.constraints, this.refs)){
            this.save();
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
                        <div className="col-md-12">
                            <h4 className="pull-left">Details </h4>
                            <hr/>
                        </div>
                        <div className="clearfix"></div>
                        <table className="table table-striped">
                            <tbody>
                            <tr>
                                <th className="col-xs-2 control-label">Project:</th>
                                <td className="col-xs-10 project-title">{this.state.data.project}</td>
                            </tr>
                            <tr>
                                <th className="col-xs-2 control-label">Priority:</th>
                                <td className="col-xs-10 form-group">
                                    <div>
                                        <SelectForm
                                            ref="priority"
                                            onChange={this.handleSelectChange('priority')}
                                            options={this.state.priorities}
                                            selected={this.state.data.priority}/>
                                        <p className="help-block"> </p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th className="col-xs-2 control-label">Type:</th>
                                <td className="col-xs-10  form-group">
                                    <div>
                                        <SelectForm
                                            ref="type"
                                            onChange={this.handleSelectChange('type')}
                                            options={this.state.types}
                                            selected={this.state.data.type}/>
                                        <p className="help-block"> </p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th className="col-xs-2 control-label">Status:</th>
                                <td className="col-xs-10  form-group">
                                    <div>
                                        <SelectForm
                                            ref="status"
                                            onChange={this.handleSelectChange('status')}
                                            options={this.state.statuses}
                                            selected={this.state.data.status}/>
                                        <p className="help-block"> </p>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div className="col-md-12">
                            <h4 className="pull-left">Description </h4>
                            <hr/>
                        </div>
                        <div className="clearfix"></div>
                        <div className="form-group">
                            <label htmlFor="inputSummery" className="col-sm-2 control-label">Summery:</label>
                            <div className="col-sm-10">
                                <input name="summery" ref="summery" type="text" className="form-control" classID="inputSummery"
                                       placeholder="Summery"
                                       value={this.state.data.summery} onChange={this.handleChange}/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputDesc" className="col-sm-2 control-label">Description:</label>
                            <div className="col-sm-10">
                            <textarea name="description" ref="description" className="form-control" rows="3" classID="inputDesc"
                                      placeholder="Description" value={this.state.data.description}
                                      onChange={this.handleChange}/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                    </div>
                    <div className="options-sidebar col-md-4 table-responsive">
                        <div className="col-md-12 clearfix">
                            <h4 className="pull-left">People </h4>
                            <hr/>
                        </div>
                        <div className="clearfix"></div>
                        <table className="table table-striped">
                            <tbody>
                            <tr>
                                <th className="col-xs-2">Assignees:</th>
                                <td className="col-xs-10">me</td>
                            </tr>
                            <tr>
                                <th className="col-xs-2">Reporter:</th>
                                <td className="col-xs-10"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div ref="formMessages" className="clearfix  col-md-12"></div>
                    <div className="btn-group col-md-12">
                        <a className="btn btn-primary" role="button" onClick={this.validate}>Save</a>
                        <Link to={'/projects/' + this.state.data.project + '/issues/'} className="btn btn-danger"
                              role="button">Cancel</Link>
                    </div>
                </form>
        )

    }
}

export default withRouter(IssueForm)
IssueForm.defaultProps = {apiUrl: {issue: '/api/issues', constraints: '/api/constraints/issue'}};
