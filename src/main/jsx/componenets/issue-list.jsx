import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

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
        const issues = (Array.isArray(this.state.data) && this.state.data.length > 0) ? this.state.data.map(issue => {
            return (
                <tr key={issue.id}>
                    <td><b>{issue.id}</b></td>
                    <td>{issue.summery}</td>
                    <td>{issue.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'/issues/' + issue.id +'/edit'}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button"/>
                            <a className="btn btn-sm btn-danger glyphicon glyphicon-trash" role="button"
                               onClick={this.deleteIssue.bind(this, issue.id)}/>
                        </div>
                    </td>
                </tr>
            )
        }) : (<tr>
            <td colSpan="3"><h4>No issues found.</h4></td>
        </tr>);
        return (
            <div>
                <div className="row">
                    <h1>Issue List:<Link to={'/issues/' + this.props.params.projectKey +'/create'}
                                         className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/></h1>
                </div>
                <div className="row">
                    <div className="table-responsive">
                        <table className="table table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>ID</th>
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
        )
    }
}

export default IssueList;
IssueList.defaultProps = {apiUrl: {project: '/api/projects', issue: '/api/issues'}};