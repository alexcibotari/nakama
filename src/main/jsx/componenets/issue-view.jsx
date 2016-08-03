import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../services/client';
import DateUtils from '../services/date-utils';
import IssueComments from "./issue-comments";

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
        if (this.props.params.issueKey) {
            client({method: 'GET', path: this.props.apiUrl.issue + '/' + this.props.params.issueKey}).then(response => {
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

    render() {
        return (
            <div>
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
                                <td className="col-xs-10">
                                    <p title={this.state.data.description}>{this.state.data.project}</p>
                                </td>
                            </tr>
                            <tr>
                                <th className="col-xs-2">Priority:</th>
                                <td className="col-xs-10">
                                    <p title={this.state.data.priority.description}>{this.state.data.priority.name}</p>
                                </td>
                            </tr>
                            <tr>
                                <th className="col-xs-2">Type:</th>
                                <td className="col-xs-10">
                                    <p title={this.state.data.type.description}>{this.state.data.type.name}</p>
                                </td>
                            </tr>
                            <tr>
                                <th className="col-xs-2">Status:</th>
                                <td className="col-xs-10">
                                    <p title={this.state.data.status.description}>{this.state.data.status.name}</p>
                                </td>
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
                                <td className="col-xs-10">{DateUtils.timeDifference(this.state.data.createdDate)}</td>
                            </tr>
                            <tr>
                                <th className="col-xs-2">Updated:</th>
                                <td className="col-xs-10">{DateUtils.timeDifference(this.state.data.lastModifiedDate)}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-12">
                            <Link to={'/projects/' + this.state.data.project + '/issues/'} className="btn btn-primary"
                                  role="button">Back</Link>
                        </div>
                    </div>
                </form>
                <div className="clearfix col-md-12">
                    <ul className="nav nav-tabs" role="tablist" id="issueTabs">
                        <li role="presentation" className="active"><a href="#all" data-toggle="tab" aria-controls="all" role="tab" data-toggle="tab">All</a></li>
                        <li role="presentation"><a href="#comments" data-toggle="tab" aria-controls="comments" role="tab" data-toggle="tab">Comments</a></li>
                        <li role="presentation"><a href="#worklog" data-toggle="tab" aria-controls="worklog" role="tab" data-toggle="tab">Work Log</a></li>
                    </ul>
                    <div className="tab-content">
                        <div role="tabpanel" className="tab-pane active" id="all">
                            <div className="panel-body">
                                all
                                <IssueComments issue={this.state.data}/>
                            </div>
                        </div>
                        <div role="tabpanel" className="tab-pane" id="comments">
                            <div className="panel-body">
                                <IssueComments issue={this.state.data}/>
                            </div>
                        </div>
                        <div role="tabpanel" className="tab-pane" id="worklog">
                            <div className="panel-body">
                                worklog
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )

    }
}

export default IssueView;
IssueView.defaultProps = {apiUrl: {issue: '/api/issues'}};
