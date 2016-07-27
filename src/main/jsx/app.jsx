import './app.css';

import React, {Component}from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import MainLayout from './componenets/layout/main-layout';
import OneColumnLayout from './componenets/layout/one-column-layout';
import LeftSideColumnLayout from './componenets/layout/left-side-column-layout'
import ProjectList from './componenets/project-list';
import ProjectForm from './componenets/project-form';
import IssueList from './componenets/issue-list';
import IssueForm from './componenets/issue-form';
import IssueView from './componenets/issue-view';
import UserList from './componenets/user-list';
import UserForm from './componenets/user-form';
import Home from './componenets/home';
import Profile from './componenets/profile';
import Settings from './componenets/settings';
import IssueTypes from './componenets/issue-types';
import IssuePriorities from "./componenets/issue-priorities";
import IssueStatuses from "./componenets/issue-statuses";
import IssueSidebar from "./componenets/issue-sidebar";
import UserSidebar from "./componenets/user-sidebar";
import IssueTypesForm from "./componenets/issue-types-form";
import IssuePrioritiesForm from "./componenets/issue-priorities-form";
import IssueStatusesForm from "./componenets/issue-statuses-form";

class App extends Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path="/" component={MainLayout}>
                    <IndexRoute component={Home}/>
                    <Route path="projects/:projectKey/issues" component={OneColumnLayout}>
                        <IndexRoute component={IssueList}/>
                    </Route>
                    <Route path="issues" component={OneColumnLayout}>
                        <Route path=":projectKey/create" component={IssueForm}/>
                        <Route path=":issueId/edit" component={IssueForm}/>
                        <Route path=":issueId" component={IssueView}/>
                    </Route>
                    <Route path="profile" component={Profile}/>
                    <Route path="settings" component={Settings}/>
                    <Route path="admin" component={LeftSideColumnLayout}>
                        <Route path="projects">
                            <IndexRoute components={{main:ProjectList}}/>
                            <Route path="create" components={{main:ProjectForm}}/>
                            <Route path="edit/:projectId" components={{main:ProjectForm}}/>
                        </Route>
                        <Route path="users">
                            <IndexRoute components={{main:UserList, sidebar: UserSidebar}}/>
                            <Route path="create" components={{main:UserForm, sidebar: UserSidebar}}/>
                            <Route path="edit/:userName" components={{main:UserForm, sidebar: UserSidebar}}/>
                        </Route>
                        <Route path="issues">
                            <Route path="types">
                                <IndexRoute components={{ main: IssueTypes, sidebar: IssueSidebar}}/>
                                <Route path="create" components={{main: IssueTypesForm, sidebar: IssueSidebar}}/>
                                <Route path="edit/:id" components={{main: IssueTypesForm, sidebar: IssueSidebar}}/>
                            </Route>
                            <Route path="priorities">
                                <IndexRoute components={{ main: IssuePriorities, sidebar: IssueSidebar}}/>
                                <Route path="create" components={{main: IssuePrioritiesForm, sidebar: IssueSidebar}}/>
                                <Route path="edit/:id" components={{main: IssuePrioritiesForm, sidebar: IssueSidebar}}/>
                            </Route>
                            <Route path="statuses">
                                <IndexRoute components={{ main: IssueStatuses, sidebar: IssueSidebar}}/>
                                <Route path="create" components={{main: IssueStatusesForm, sidebar: IssueSidebar}}/>
                                <Route path="edit/:id" components={{main: IssueStatusesForm, sidebar: IssueSidebar}}/>
                            </Route>
                        </Route>
                    </Route>
                </Route>
            </Router>
        )
    }
}

init();

function init() {
    var main = document.createElement('main');
    document.body.appendChild(main);
    ReactDOM.render(<App/>, main);
}
