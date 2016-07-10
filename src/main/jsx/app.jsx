import './app.css';

import React, {Component}from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, hashHistory} from 'react-router';
import MainLayout from './componenets/layout/main-layout';
import ProjectLayout from './componenets/layout/project-layout';
import ProjectList from './componenets/project-list';
import ProjectForm from './componenets/project-form';
import Home from './componenets/home';

class App extends Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path="/" component={MainLayout}>
                    <IndexRoute component={Home}/>
                    <Route path="projects" component={ProjectLayout}>
                        <IndexRoute component={ProjectList}/>
                        <Route path="create" component={ProjectForm}/>
                        <Route path="edit/:key" component={ProjectForm}/>
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
