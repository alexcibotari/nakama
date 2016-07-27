import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

class IssueView extends Component {

    constructor(props) {
        super(props);
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
            types: []
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

    timeDifference(time) {
        var initialTime = moment(time);

        var diff = moment().diff(initialTime, 'days');
        if(diff > 14) {
            return initialTime.format("DD/MM/YYYY HH:mm:ss");
        } else {
            return initialTime.fromNow();
        }
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
                            <th className="col-xs-2">Project:</th>
                            <td className="col-xs-10">{this.state.data.project}</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Priority:</th>
                            <td className="col-xs-10">{this.state.data.priority.name}</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Type:</th>
                            <td className="col-xs-10">{this.state.data.type.name}</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Status:</th>
                            <td className="col-xs-10">{this.state.data.status.name}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div className="col-md-12">
                        <h4 className="pull-left">Description </h4>
                        <hr/>
                    </div>
                    <div className="clearfix"></div>
                    <div className="form-group">
                        <label htmlFor="inputSummery" className="col-sm-2 control-label">Summery</label>
                        <div className="col-sm-10">
                            <h4>{this.state.data.summery}</h4>
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputDesc" className="col-sm-2 control-label">Description</label>
                        <div className="col-sm-10">
                            <p>{this.state.data.description}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-12">
                            <Link to={'/projects/' + this.state.data.project + '/issues/'} className="btn btn-primary"
                                  role="button">Back</Link>
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
                    <div className="col-md-12 clearfix">
                        <h4 className="pull-left">Dates </h4>
                        <hr/>
                    </div>
                    <div className="clearfix"></div>
                    <table className="table table-striped">
                        <tbody>
                        <tr>
                            <th className="col-xs-2">Created:</th>
                            <td className="col-xs-10">{this.timeDifference(this.state.data.createdDate)}</td>
                        </tr>
                        <tr>
                            <th className="col-xs-2">Updated:</th>
                            <td className="col-xs-10">{this.timeDifference(this.state.data.lastModifiedDate)}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        )

    }
}

export default IssueView;
IssueView.defaultProps = {apiUrl: {issue: '/api/issues'}};
