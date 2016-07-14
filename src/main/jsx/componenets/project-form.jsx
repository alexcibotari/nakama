import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../client';

class ProjectForm extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.validate = this.validate.bind(this);
        this.state = {
            url: '/api/projects',
            data: {name: "", key: "", description: ""},
            edit: false
        }
    }

    componentWillMount() {
        if (this.props.params.id) {
            client({method: 'GET', path: this.state.url + '/' + this.props.params.id}).then(response => {
                if (response.status.code == 200) {
                    this.refs.key.setAttribute('readonly', null);
                    this.setState({data: response.entity, edit: true});
                }
            });
        }
    }

    save() {
        if (this.state.edit) {
            client({method: 'PUT', path: this.state.url, entity: this.state.data}).then(response => {
                if (response.status.code == 200) {
                    this.props.history.push('/projects');
                }
            });
        } else {
            client({method: 'POST', path: this.state.url, entity: this.state.data}).then(response => {
                if (response.status.code == 201) {
                    this.props.history.push('/projects');
                }
            });
        }
    }

    validate() {
        if (!/^[A-Za-z0-9\s]+$/.test(this.state.data.name)) {
            this.refs.name.parentElement.parentElement.className = 'has-error form-group has-feedback';
            console.log(this);
            return;
        }
        this.save();
    }

    handleChange(e) {
        this.state.data[e.target.name] = e.target.value;
        var val = '';
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
                    val += (arr[i].charAt(0).toUpperCase() + uppercaseletters);
                }
            } else {
                val = arr[0].toUpperCase();
            }
            if (val.indexOf(this.state.data.key) == 0 || this.state.data.key == '' || this.state.data.key == arr[0].toUpperCase()) {
                this.state.data.key = val;
            }
        }
        this.setState({data: this.state.data});
    }


    render() {
        return (
            <form className="form-horizontal">
                <div className="form-group has-feedback">
                    <label htmlFor="inputName" className="col-sm-2 control-label">Name</label>
                    <div className="col-sm-10">
                        <input name="name" ref="name" type="text" className="form-control" id="inputName"
                               placeholder="Name"
                               value={this.state.data.name} onChange={this.handleChange}/>
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="inputKey" className="col-sm-2 control-label">Key</label>
                    <div className="col-sm-10">
                        <input name="key" ref="key" type="text" className="form-control" classID="inputKey" placeholder="Key"
                               value={this.state.data.key} onChange={this.handleChange}/>
                        <p className="help-block">This is a key of the project.</p>
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
                        <Link to={'admin/projects/'} className="btn btn-danger" role="button">Cancel</Link>
                    </div>
                </div>
            </form>
        )
    }
}

export default ProjectForm;