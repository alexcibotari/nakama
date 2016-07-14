import React, {Component} from 'react';
import { Link } from 'react-router';
import client from '../client';

export default class ProjectDropDown extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            url: '/api/projects',
            data: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.state.url}).then(response => {
            if (response.status.code == 200) {
                this.setState({data: response.entity});
            }
        });
    }

    render(){
        const projects = (Array.isArray(this.state.data) && this.state.data.length > 0) ? this.state.data.map(project => {
            return (
                <li key={project.id}>
                    <Link to={'/projects/'+project.id+'/issues'} role="button">{project.name}</Link>
                </li>)
        }) : (<li><p>No project found.</p></li>);
        return(
            <li className="dropdown">
                <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    Projects <span className="caret"></span>
                </a>
                <ul className="dropdown-menu">
                    {projects}
                </ul>
            </li>
        )
    }

}