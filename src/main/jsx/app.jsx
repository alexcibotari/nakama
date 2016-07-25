import './app.css';

import React, {Component}from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import MainLayout from './componenets/layout/main-layout';
import ProjectLayout from './componenets/layout/project-layout';
import IssueLayout from './componenets/layout/issue-layout';
import ProjectList from './componenets/project-list';
import ProjectForm from './componenets/project-form';
import IssueList from './componenets/issue-list';
import IssueForm from './componenets/issue-form';
import Home from './componenets/home';
import Profile from './componenets/profile';
import Settings from './componenets/settings';

class App extends Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path="/" component={MainLayout}>
                    <IndexRoute component={Home}/>
                    <Route path="admin/projects" component={ProjectLayout}>
                        <IndexRoute component={ProjectList}/>
                        <Route path="create" component={ProjectForm}/>
                        <Route path="edit/:projectId" component={ProjectForm}/>
                    </Route>
                    <Route path="projects/:projectKey/issues" component={IssueLayout}>
                        <IndexRoute component={IssueList}/>
                    </Route>
                    <Route path="issues" component={IssueLayout}>
                        <Route path=":projectKey/create" component={IssueForm}/>
                        <Route path=":issueId/edit" component={IssueForm}/>
                    </Route>
                    <Route path="profile" component={Profile}/>
                    <Route path="settings" component={Settings}/>
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
