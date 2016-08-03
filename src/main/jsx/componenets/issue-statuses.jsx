import React, {Component} from 'react';
import {Link } from 'react-router';
import client from '../services/client';
import ConfirmationDialog from './confirmation-dialog';

class IssueStatuses extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        client({method: 'GET', path: this.props.apiUrl.statuses}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    deleteItem(id) {
        client({method: 'DELETE', path: this.props.apiUrl.statuses + '/' + id}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.data.findIndex(function (status) {
                    return status.id === id;
                });
                this.state.data.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const statuses = this.state.data.map(status => {
            return (
                <tr key={status.id}>
                    <td><b>{status.name}</b></td>
                    <td>{status.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'admin/issues/statuses/edit/' + status.id}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button"/>
                            <ConfirmationDialog
                                title="Delete Issue Status."
                                bodyText="Are you sure you want to delete the Issue Status?"
                                lunchModalBtnClasses="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                lunchModalBtnText=""
                                lunchModalBtnStyles={{float: 'right'}}
                                actionBtnAction={this.deleteItem.bind(this, status.id)}
                                modalContainerStyle={{marginLeft: 34+'px'}}/>
                        </div>
                    </td>
                </tr>
            )
        });
        const tableHeading = (<div className="row">
            <h1>Issue Status List:
                <Link to={'admin/issues/statuses/create'} className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/>
            </h1>
        </div>);
        if (Array.isArray(this.state.data) && this.state.data.length > 0) {
            return (
                <div className="col-md-12">
                    <div>
                        {tableHeading}
                        <div className="row">
                            <div className="table-responsive">
                                <table className="table table-striped table-condensed">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th className="text-right">Options</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {statuses}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div className="col-md-12">
                    {tableHeading}
                    <h4>No status found.</h4>
                </div>
            )
        }
    }
}

export default IssueStatuses;

IssueStatuses.defaultProps = {apiUrl: {statuses: '/api/admin/issuestatuses'}};
