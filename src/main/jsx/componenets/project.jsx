import React, {Component} from 'react';

class Project extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="col-sm-6 col-md-4">
                <div className="thumbnail">
                    <div className="caption">
                        <h3>{this.props.project.name}</h3>
                        <p>{this.props.project.description}</p>
                        <div>
                            <div className="btn-group btn-group-justified" role="group">
                                <a href="#" className="btn btn-default" role="button">
                                    <span className="glyphicon glyphicon-pencil" aria-hidden="true"/> Edit</a>
                                <a href="#" className="btn btn-danger" role="button">
                                    <span className="glyphicon glyphicon-trash" aria-hidden="true"/> Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Project;