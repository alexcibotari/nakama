import React, {Component} from 'react';

import client from './../client';
import Project from './../componenets/project';

class ProjectLayout extends Component {

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
        if (Array.isArray(this.state.data) && this.state.data.length > 0) {
            return (
                <div className="row">
                    <button className="btn btn-sm btn-success glyphicon glyphicon-plus"/>
                    {this.state.data.map((project) => {
                        return (
                            <Project project={project} key={project.key}/>
                        )
                    })}
                </div>
            )
        } else {
            return (
                <h4>
                    No project found.
                </h4>
            )
        }

    }
}

export default ProjectLayout;