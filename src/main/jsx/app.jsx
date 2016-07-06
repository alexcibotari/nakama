import './app.css';

import React from 'react';
import ReactDOM from 'react-dom';
import Header from './componenets/header';

class NakamaApp extends React.Component {
    render() {
        return (
            <Header/>
        )
    }
}

init();

function init() {
    var main = document.createElement('main');
    document.body.appendChild(main);
    ReactDOM.render(<NakamaApp/>, main);
}
