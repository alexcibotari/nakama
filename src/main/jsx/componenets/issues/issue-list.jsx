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
            console.log(response.entity);
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
                <div className="panel panel-default" key={issue.id}>
                    <div className="panel-heading">
                        <h3 className="panel-title">{issue.summery}</h3>
                    </div>
                    <div className="panel-body">
                        <div className="pull-right">
                            <Link to={'/issues/edit/'+issue.id}
                                  className="btn btn-info glyphicon glyphicon-pencil"
                                  role="button"> Edit</Link>
                            <a className="btn btn-danger glyphicon glyphicon-trash" role="button"
                               onClick={this.delete.bind(this,issue.id)}> Delete</a>
                        </div>
                        <p>{issue.description}</p>
                    </div>
                </div>
            )
        }) : (<h4>No issues found.</h4>);
        return (
            <div>
                <div className="row add-btn">
                    <div className="pull-right">
                        <Link to={'/issues/create'} className="btn btn-sm btn-success glyphicon glyphicon-plus"/>
                    </div>
                </div>
                <div className="row">
                    {issues}
                </div>
            </div>
        )
    }
}

export default IssueList;