import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

class ProjectList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: '/api/projects',
            data: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.state.url}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    delete(key) {
        client({method: 'DELETE', path: this.state.url + '/' + key}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.data.findIndex(function (project) {
                    return project.key === key;
                });
                this.state.data.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const projects = (Array.isArray(this.state.data) && this.state.data.length > 0) ? this.state.data.map(project => {
            return (
                <div className="col-sm-6 col-md-4" key={project.key}>
                    <div className="thumbnail">
                        <div className="caption">
                            <h3>{project.name}</h3>
                            <p>{project.description}</p>
                            <div>
                                <div className="btn-group btn-group-justified" role="group">
                                    <Link to={'/projects/edit/'+project.key}
                                          className="btn btn-default glyphicon glyphicon-pencil"
                                          role="button"> Edit</Link>
                                    <a className="btn btn-danger glyphicon glyphicon-trash" role="button"
                                       onClick={this.delete.bind(this,project.key)}> Delete</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }) : (<h4>No project found.</h4>);
        return (
            <div>
                <div className="row">
                    <div className="pull-right">
                        <Link to={'/projects/create'} className="btn btn-sm btn-success glyphicon glyphicon-plus"/>
                    </div>
                </div>
                <div className="row">
                    {projects}
                </div>
            </div>
        )
    }
}

export default ProjectList;