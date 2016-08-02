import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../services/client';
import ConfirmationDialog from './confirmation-dialog';

export default class IssueTypes extends Component {

    constructor(props) {
        super(props);
        this.state = {
            issueTypeList: []
        }
    }

    componentDidMount() {
        client({method: 'GET', path: this.props.apiUrl.types}).then(response => {
            if (response.status.code === 200) {
                this.setState({issueTypeList: response.entity});
            }
        });
    }

    deleteIssueType(id) {
        client({method: 'DELETE', path: this.props.apiUrl.types + '/' + id}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.issueTypeList.findIndex(function (type) {
                    return type.id === id;
                });
                this.state.issueTypeList.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const types = this.state.issueTypeList.map(type => {
            return (
                <tr key={type.id}>
                    <td><b>{type.name}</b></td>
                    <td>{type.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'admin/issues/types/edit/' + type.id}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button"/>
                            <ConfirmationDialog
                                title="Delete Issue Type."
                                bodyText="Are you sure you want to delete the Issue Type?"
                                lunchModalBtnClasses="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                lunchModalBtnText=""
                                lunchModalBtnStyles={{float: 'right'}}
                                actionBtnAction={this.deleteIssueType.bind(this, type.id)}
                                modalContainerStyle={{marginLeft: 34+'px'}}/>
                        </div>
                    </td>
                </tr>
            )
        });
        const IssueTypeListHeading = (<div className="row">
            <h1>Issue Type List:<Link to={'admin/issues/types/create'}
                                className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/>
            </h1>
        </div>);
        if (Array.isArray(this.state.issueTypeList) && this.state.issueTypeList.length > 0) {
            return (
                <div className="col-md-12">
                    <div>
                        {IssueTypeListHeading}
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
                                    {types}
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
                    {IssueTypeListHeading}
                    <h4>No type found.</h4>
                </div>
            )
        }
    }
}

IssueTypes.defaultProps = {apiUrl: {types: '/api/admin/issues/types'}};
