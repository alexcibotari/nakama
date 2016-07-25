import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';
import Modal from './modal';

class ProjectList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.props.apiUrl.project}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    deleteProject(id) {
        client({method: 'DELETE', path: this.props.apiUrl.project + '/' + id}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.data.findIndex(project => {
                    return project.id === id;
                });
                this.state.data.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const projects = (Array.isArray(this.state.data) && this.state.data.length > 0) ? this.state.data.map(project => {
            return (
                <tr key={project.id}>
                    <td><b>{project.name}</b></td>
                    <td>{project.key}</td>
                    <td>{project.description}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'/admin/projects/edit/'+project.id}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button" title="Edit"/>
                            <Modal title="Delete Project."
                                   bodyText="Are you sure you want to delete the project?"
                                   lunchModalBtnClasses="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                   lunchModalBtnText=""
                                   lunchModalBtnStyles={{float: 'right'}}
                                   actionBtnAction={this.deleteProject.bind(this, project.id)}
                                   modalContainerStyle={{marginLeft: 34+'px'}}
                                   modalId={project.id}/>
                        </div>
                    </td>
                </tr>
            )
        }) : (<tr>
            <td colSpan="3"><h4>No project found.</h4></td>
        </tr>);

        return (
            <div>
                <div className="row">
                    <h1>Project List:<Link to={'/admin/projects/create'}
                                           className="btn-lg pull-right btn btn-success glyphicon glyphicon-plus"/></h1>
                </div>
                <div className="row">
                    <div className="table-responsive">
                        <table className="table table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Key</th>
                                <th>Description</th>
                                <th className="text-right">Options</th>
                            </tr>
                            </thead>
                            <tbody>
                            {projects}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProjectList;
ProjectList.defaultProps = {apiUrl: {project: '/api/projects'}};
