import React, {Component} from 'react';
import client from './../client';

class Projects extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: '/api/projects',
            data: []
        }
    }

    loadProjectsFromServer() {
        client({method: 'GET', path: this.state.url}).then(response => {
            console.log(response);
        });
    }

    componentDidMount() {
        this.loadProjectsFromServer();
    }

    render() {
        return (
            <div>
                Projects
            </div>
        )
    }
}

export default Projects;