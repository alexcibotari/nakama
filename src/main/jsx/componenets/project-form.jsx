import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

class ProjectForm extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.validate = this.validate.bind(this);
        this.state = {
            data: {name: "", key: "", description: ""},
            edit: false
        }
    }

    componentWillMount() {
        if (this.props.params.id) {
            client({method: 'GET', path: this.props.apiUrl.project + '/' + this.props.params.id}).then(response => {
                if (response.status.code == 200) {
                    this.setState({data: response.entity, edit: true});
                }
            });
        }
    }

    save() {
        if (this.state.edit) {
            client({method: 'PUT', path: this.props.apiUrl.project, entity: this.state.data}).then(response => {
                if (response.status.code == 200) {
                    this.props.history.push('admin/projects');
                }
            });
        } else {
            client({method: 'POST', path: this.props.apiUrl.project, entity: this.state.data}).then(response => {
                if (response.status.code == 201) {
                    this.props.history.push('admin/projects');
                }
            });
        }
    }

    validate() {
        var validForm = true;
        for(var name in this.refs) {
            this.refs[name].parentElement.parentElement.className = 'form-group has-feedback';
            this.refs[name].nextSibling.innerHTML = '';
        }

        if (!/^[A-Za-z][A-Za-z\d\s]*[A-Za-z\d]$/.test(this.state.data.name)) {
            this.refs.name.parentElement.parentElement.className = 'has-error form-group has-feedback';
            this.refs.name.nextSibling.innerHTML = 'Name may contain only letters and numbers, should start with a letter, and should not end with a space!';
            validForm =false;
        }
        if (!/^[A-Z][A-Z\d]+$/.test(this.state.data.key)) {
            this.refs.key.parentElement.parentElement.className = 'has-error form-group has-feedback';
            this.refs.key.nextSibling.innerHTML = 'Key may contain only letters and numbers, and should start with a letter!';
            validForm =false;
        }
        if(validForm) {
            this.save();
        }
    }

    handleChange(e) {
        this.state.data[e.target.name] = e.target.value;
        var generatedVal = '';
        if (e.target.name == 'name') {
            var arr = e.target.value.split(" ");
            if (arr.length > 1) {
                for (var i = 0; i < arr.length; i++) {
                    var uppercaseletters = '';
                    for (var j = 1; j < arr[i].length; j++) {
                        if (arr[i].charAt(j) == arr[i].charAt(j).toUpperCase()) {
                            uppercaseletters += arr[i].charAt(j);
                        }
                    }
                    generatedVal += (arr[i].charAt(0).toUpperCase() + uppercaseletters);
                }
            } else {
                generatedVal = arr[0].toUpperCase();
            }
            if (generatedVal.indexOf(this.state.data.key) == 0 || this.state.data.key == '' || this.state.data.key == arr[0].toUpperCase()) {
                this.state.data.key = generatedVal;
            }
        }
        if(e.target.name == 'key'){
            this.state.data.key = e.target.value.toUpperCase();
        }
        this.setState({data: this.state.data});
    }


    render() {
        return (
            <form className="form-horizontal">
                <div className="form-group has-feedback">
                    <label htmlFor="inputName" className="col-sm-2 control-label">Name</label>
                    <div className="col-sm-10">
                        <input name="name" ref="name" type="text" className="form-control" classID="inputName"
                               placeholder="Name"
                               value={this.state.data.name} onChange={this.handleChange}/>
                        <p className="help-block"> </p>
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="inputKey" className="col-sm-2 control-label">Key</label>
                    <div className="col-sm-10">
                        <input name="key" ref="key" type="text" className="form-control" classID="inputKey" placeholder="Key"
                               value={this.state.data.key} onChange={this.handleChange}/>
                        <p className="help-block"> </p>
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="inputDesc" className="col-sm-2 control-label">Description</label>
                    <div className="col-sm-10">
                        <textarea name="description" className="form-control" rows="3" classID="inputDesc"
                                  placeholder="Description" value={this.state.data.description}
                                  onChange={this.handleChange}/>
                    </div>
                </div>
                <div className="form-group">
                    <div className="col-sm-offset-2 col-sm-10">
                        <a className="btn btn-primary" role="button" onClick={this.validate}>Save</a>
                        <Link to={'admin/projects'} className="btn btn-danger" role="button">Cancel</Link>
                    </div>
                </div>
            </form>
        )
    }
}

export default ProjectForm;
ProjectForm.defaultProps = {apiUrl: {project: '/api/projects'}};