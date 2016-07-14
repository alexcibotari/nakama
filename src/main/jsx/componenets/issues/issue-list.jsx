import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../../client';

class IssueList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: '/api/issues',
            data: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.state.url}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    delete(id) {
        client({method: 'DELETE', path: this.state.url + '/' + id}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.data.findIndex(function (issue) {
                    return issue.id === id;
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
                    <td><b>{issue.summery}</b></td>
                    <td>{issue.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'/issues/edit/' + issue.id}
                                  className="btn btn-info glyphicon glyphicon-pencil"
                                  role="button"/>
                            <a className="btn btn-danger glyphicon glyphicon-trash" role="button"
                               onClick={this.delete.bind(this, issue.id)}/>
                        </div>
                    </td>
                </tr>
            )
        }) : (<tr><td colSpan="3"><h4>No issues found.</h4></td></tr>);
        return (
            <div>
                <div className="row">
                    <h1>Issue List:<Link to={'/issues/create'}
                                         className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/></h1>
                </div>
                <div className="row">
                    <div className="table-responsive">
                        <table className="table table-striped table-condensed">
                            <thead>
                            <tr>
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