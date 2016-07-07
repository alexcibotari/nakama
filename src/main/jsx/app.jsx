import './app.css';

import React, {Component}from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, hashHistory } from 'react-router';
import MainLayout from './componenets/main-layout';
import Home from './componenets/home';
import Projects from './componenets/projects';

class App extends Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route component={MainLayout}>
                    <Route path="/" component={Home}/>
                    <Route path="/projects" component={Projects}/>
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
