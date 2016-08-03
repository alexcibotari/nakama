import React, {Component} from 'react';
import {Link, withRouter} from 'react-router';
import client from '../services/client';
import ValidationForm from '../services/validation-form';

class IssueTypesForm extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.validate = this.validate.bind(this);
        this.state = {
            data: {id: "", name: "", description: ""},
            constraints: [],
            edit: false
        }
    }

    componentWillMount() {
        if (this.props.params.id) {
            client({method: 'GET', path: this.props.apiUrl.types + '/' + this.props.params.id}).then(response => {
                if (response.status.code == 200) {
                    this.setState({data: response.entity, edit: true});
                }
            });
        }
        client({method: 'GET', path: this.props.apiUrl.constraints}).then(response => {
            if (response.status.code == 200) {
                this.setState({constraints: response.entity});
            }
        });
    }

    save() {
        if (this.state.edit) {
            client({method: 'PUT', path: this.props.apiUrl.types, entity: this.state.data}).then(response => {
                if (response.status.code == 200) {
                    this.props.router.push('/admin/issues/types');
                }
            });
        } else {
            client({method: 'POST', path: this.props.apiUrl.types, entity: this.state.data}).then(response => {
                if (response.status.code == 201) {
                    this.props.router.push('/admin/issues/types');
                }
            });
        }
    }

    validate() {
        if(ValidationForm.validate(this.state.constraints, this.refs)){
            this.save();
        }
    }

    handleChange(e){
        this.state.data[e.target.name] = e.target.value;
        this.setState({ data: this.state.data});
    }

    render() {
        return (
            <div className="col-md-12">
                <form className="form-horizontal">
                    <div className="main-user-form col-md-8">
                        <div className="form-group">
                            <label htmlFor="inputIssueTypeName" className="col-sm-2 control-label">Name:</label>
                            <div className="col-sm-10 input-group">
                                <input name="name" ref="name" type="text" className="form-control" classID="inputIssueTypeName" placeholder="Issue type name"
                                       value={this.state.data.name} onChange={this.handleChange}/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputIssueTypeDescription" className="col-sm-2 control-label">Description:</label>
                            <div className="col-sm-10 input-group">
                                <input name="description" ref="description" type="text" className="form-control" classID="inputIssueTypeDescription" placeholder="Issue type description"
                                       value={this.state.data.description} onChange={this.handleChange}/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div ref="formMessages" className="clearfix  col-md-12"></div>
                        <div className="form-group">
                            <div className="col-sm-offset-2 col-sm-10 btn-group">
                                <a className="btn btn-primary" role="button" onClick={this.validate}>Save</a>
                                <Link to={'/admin/issues/types'} className="btn btn-danger" role="button">Cancel</Link>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        )

    }
}

export default withRouter(IssueTypesForm)

IssueTypesForm.defaultProps = {apiUrl: {types: '/api/admin/issues/types', constraints: '/api/constraints/issue/type'}};
