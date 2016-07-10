import React, {Component} from 'react';
import client from '../client';

class ProjectForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: '/api/projects',
            data: {name: "", key: "", description: ""}
        }
    }

    componentWillMount() {
        if(this.props.params.key){
            client({method: 'GET', path: this.state.url + '/' + this.props.params.key}).then(response => {
                this.setState({data: response.entity});
            });
        }

    }

    render() {
        return (
            <div>
                Project Form : {this.state.data.key}
            </div>
        )
    }
}

export default ProjectForm;