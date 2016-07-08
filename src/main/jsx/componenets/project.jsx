import React, {Component} from 'react';

class Project extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="project">
                {this.props.project.key}
            </div>
        )
    }
}

export default Project;