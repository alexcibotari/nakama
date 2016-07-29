import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../services/client';
import ConfirmationDialog from './confirmation-dialog';

export default class IssuePriorities extends Component {

    constructor(props) {
        super(props);
        this.state = {
            issuePriorityList: []
        }
    }

    componentDidMount() {
        client({method: 'GET', path: this.props.apiUrl.priorities}).then(response => {
            if (response.status.code === 200) {
                this.setState({issuePriorityList: response.entity});
            }
        });
    }

    deleteIssuePriority(id) {
        client({method: 'DELETE', path: this.props.apiUrl.priorities + '/' + id}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.issuePriorityList.findIndex(function (priority) {
                    return priority.id === id;
                });
                this.state.issuePriorityList.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const priorities = this.state.issuePriorityList.map(priority => {
            return (
                <tr key={priority.id}>
                    <td><b>{priority.name}</b></td>
                    <td>{priority.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'admin/issues/priorities/edit/' + priority.id}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button"/>
                            <ConfirmationDialog
                                title="Delete Issue Priority."
                                bodyText="Are you sure you want to delete the Issue Priority?"
                                lunchModalBtnClasses="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                lunchModalBtnText=""
                                lunchModalBtnStyles={{float: 'right'}}
                                actionBtnAction={this.deleteIssuePriority.bind(this, priority.id)}
                                modalContainerStyle={{marginLeft: 34+'px'}}/>
                        </div>
                    </td>
                </tr>
            )
        });
        const tableHeading = (<div className="row">
            <h1>Issue Priority List:<Link to={'admin/issues/priorities/create'}
                                      className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/>
            </h1>
        </div>);
        if (Array.isArray(this.state.issuePriorityList) && this.state.issuePriorityList.length > 0) {
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
                                    {priorities}
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
                    <h4>No type found.</h4>
                </div>
            )
        }
    }
}

IssuePriorities.defaultProps = {apiUrl: {priorities: '/api/admin/issues/priorities'}};
