import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

class IssueForm extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.save = this.save.bind(this);
        this.state = {
            data: {id: '', project: '', key: '', summery: '', description: ''},
            edit: false,
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
                <div className="options-sidebar col-md-4">
                    <table className="table table-striped">
                        <tbody>
                        <tr>
                            <td>Project:</td>
                            <td>{this.state.data.project}</td>
                        </tr>
                        <tr>
                            <td>Assignees:</td>
                            <td>me</td>
                        </tr>
                        <tr>
                            <td>Type:</td>
                            <td>bug</td>
                        </tr>
                        <tr>
                            <td>Due date:</td>
                            <td>yesterday</td>
                        </tr>
                        <tr>
                            <td>Labels:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Priority:</td>
                            <td>Do when you have time</td>
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
