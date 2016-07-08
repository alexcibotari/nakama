import React, {Component} from 'react';
import client from './../client';
import Project from './project'

class Projects extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: '/api/projects',
            data: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.state.url}).then(response => {
            this.setState({data: response.entity});
        });
    }

    render() {
        return (
            <div className="projects">
                {this.state.data.map((project) => {
                    return (
                        <Project project={project} key={project.key}/>
                    )
                })}
            </div>
        )
    }
}

export default Projects;