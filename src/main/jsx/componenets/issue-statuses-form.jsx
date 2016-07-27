import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

class IssueStatusesForm extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.save = this.save.bind(this);
        this.state = {
            data: {id: "", name: "", description: ""},
            edit: false
        }
    }

    componentWillMount() {
        if (this.props.params.id) {
            client({method: 'GET', path: this.props.apiUrl.statuses + '/' + this.props.params.id}).then(response => {
                if (response.status.code == 200) {
                    this.setState({data: response.entity, edit: true});
                }
            });
        }
    }

    save() {
        if (this.state.edit) {
            client({method: 'PUT', path: this.props.apiUrl.statuses, entity: this.state.data}).then(response => {
                if (response.status.code == 200) {
                    this.props.history.push('/admin/issues/statuses');
                }
            });
        } else {
            client({method: 'POST', path: this.props.apiUrl.statuses, entity: this.state.data}).then(response => {
                if (response.status.code == 201) {
                    this.props.history.push('/admin/issues/statuses');
                }
            });
        }
    }

    handleChange(e){
        this.state.data[e.target.name] = e.target.value;
        this.setState({ data: this.state.data});
    }

    render() {
        return (
            <div className="col-md-12">
                <form className="form-horizontal">
                    <div className="main-user-form col-md-8">
                        <div className="form-group">
                            <label htmlFor="inputIssueStatusName" className="col-sm-2 control-label">Name:</label>
                            <div className="col-sm-10 input-group">
                                <input name="name" type="text" className="form-control" classID="inputIssueStatusName" placeholder="Issue status name"
                                       value={this.state.data.name} onChange={this.handleChange}/>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputIssueStatusDescription" className="col-sm-2 control-label">Description:</label>
                            <div className="col-sm-10 input-group">
                                <input name="description" type="text" className="form-control" classID="inputIssueStatusDescription" placeholder="Issue status description"
                                       value={this.state.data.description} onChange={this.handleChange}/>
                            </div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-offset-2 col-sm-10 btn-group">
                                <a className="btn btn-primary" role="button" onClick={this.save}>Save</a>
                                <Link to={'/admin/issues/statuses'} className="btn btn-danger" role="button">Cancel</Link>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        )

    }
}

export default IssueStatusesForm;

IssueStatusesForm.defaultProps = {apiUrl: {statuses: '/api/admin/issues/statuses'}};
