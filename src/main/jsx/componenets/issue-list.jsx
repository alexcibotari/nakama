import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../services/client';
import ConfirmationDialog from './confirmation-dialog';

class IssueList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        this.loadIssuesByProjectKey(this.props.params.projectKey);
    }

    loadIssuesByProjectKey(projectKey) {
        client({method: 'GET', path: this.props.apiUrl.project + '/' + projectKey + '/issues'}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    componentWillReceiveProps(nextProps) {
        this.loadIssuesByProjectKey(nextProps.params.projectKey);
    }

    deleteIssue(issueId) {
        client({method: 'DELETE', path: this.props.apiUrl.issue + '/' + issueId}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.data.findIndex(function (issue) {
                    return issue.id === issueId;
                });
                this.state.data.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const issues = this.state.data.map(issue => {
            return (
                <tr key={issue.id}>
                    <th><Link to={'/issues/' + issue.id}>{issue.id}</Link></th>
                    <td>{issue.summery}</td>
                    <td>{issue.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'/issues/' + issue.id +'/edit'}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button"/>
                            <ConfirmationDialog
                                title="Delete Issue."
                                bodyText="Are you sure you want to delete the issue?"
                                lunchModalBtnClasses="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                lunchModalBtnText=""
                                lunchModalBtnStyles={{float: 'right'}}
                                actionBtnAction={this.deleteIssue.bind(this, issue.id)}
                                modalContainerStyle={{marginLeft: 34+'px'}}/>
                        </div>
                    </td>
                </tr>
            )
        });

        const IssueListHeading = (
            <div className="row">
                <h1>Issue List:<Link to={'/issues/' + this.props.params.projectKey +'/create'}
                                     className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/></h1>
            </div>
        );

        if (Array.isArray(this.state.data) && this.state.data.length > 0) {
            return (
                <div className="col-md-12">
                    <div>
                        {IssueListHeading}
                        <div className="row">
                            <div className="table-responsive">
                                <table className="table table-striped table-condensed">
                                    <thead>
                                    <tr>
                                        <th>Issue ID</th>
                                        <th>Summery</th>
                                        <th>Description</th>
                                        <th className="text-right">Options</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {issues}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="col-md-12">
                    {IssueListHeading}
                    <h4>No issues found.</h4>
                </div>
            )
        }
    }
}

export default IssueList;
IssueList.defaultProps = {apiUrl: {project: '/api/projects', issue: '/api/issues'}};
