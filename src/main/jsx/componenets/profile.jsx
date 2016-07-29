import React, {Component} from 'react';
import client from '../services/client';
import ValidationForm from '../services/validation-form';

class Profile extends Component {

    constructor(props){
        super(props);
        this.state = {
            constraints:[]
        }
    }

    componentWillMount(){
        client({method: 'GET', path: this.props.apiUrl}).then(response => {
            if (response.status.code == 200) {
                this.setState({constraints: response.entity});
            }
        });
    }

    validate() {
        if(ValidationForm.validate(this.state.constraints, this.refs)){
            alert('valid');
        }
    }

    render(){
        return (
            <div className="col-md-12">
                <h3>Profile Page</h3>
                <form className="form-horizontal">
                    <div className="col-md-8">
                        <div className="form-group has-feedback">
                            <label htmlFor="inputName" className="col-sm-2 control-label">Pattern:</label>
                            <div className="col-sm-10">
                                <input name="checkOnPattern" ref="checkOnPattern" type="text" className="form-control" classID="inputName" placeholder="Pattern"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="enableControll" className="col-sm-2 control-label">checkOnTrue:</label>
                            <div className="col-sm-10">
                                <div className="checkbox">
                                    <input name="checkOnTrue" ref="checkOnTrue" type="checkbox"  />
                                    <p className="help-block"> </p>
                                </div>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="checkOnFalse" className="col-sm-2 control-label">checkOnFalse:</label>
                            <div className="col-sm-10">
                                <div className="checkbox">
                                    <input name="checkOnFalse" ref="checkOnFalse"  type="checkbox" />
                                    <p className="help-block"> </p>
                                </div>

                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="doubleWithFraction" className="col-sm-2 control-label">doubleWithFraction:</label>
                            <div className="col-sm-10">
                                <input name="doubleWithFraction" ref="doubleWithFraction" type="text" className="form-control" classID="inputAge" placeholder="doubleWithFraction"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="checkOnSize" className="col-sm-2 control-label">checkOnSize:</label>
                            <div className="col-sm-10">
                                <input name="checkOnSize" ref="checkOnSize" type="text" className="form-control" classID="checkOnSize" placeholder="checkOnSize"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="intWithoutFraction" className="col-sm-2 control-label">intWithoutFraction:</label>
                            <div className="col-sm-10">
                                <input name="intWithoutFraction" ref="intWithoutFraction" type="number" className="form-control" classID="intWithoutFraction" placeholder="intWithoutFraction"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="checkOnMax" className="col-sm-2 control-label">checkOnMax:</label>
                            <div className="col-sm-10">
                                <input name="checkOnMax" ref="checkOnMax" type="number" className="form-control" classID="checkOnMax" placeholder="checkOnMax"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="numberMax" className="col-sm-2 control-label">numberMax:</label>
                            <div className="col-sm-10">
                                <input name="numberMax" ref="numberMax" type="number" className="form-control" classID="numberMax" placeholder="numberMax"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="checkOnMin" className="col-sm-2 control-label">checkOnMin:</label>
                            <div className="col-sm-10">
                                <input name="checkOnMin" ref="checkOnMin" type="number" className="form-control" classID="checkOnMin" placeholder="checkOnMin"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="numberMin" className="col-sm-2 control-label">numberMin:</label>
                            <div className="col-sm-10">
                                <input name="numberMin" ref="numberMin" type="number" className="form-control" classID="numberMin" placeholder="numberMin"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label htmlFor="checkOnRange" className="col-sm-2 control-label">checkOnRange:</label>
                            <div className="col-sm-10">
                                <input name="checkOnRange" ref="checkOnRange" type="number" className="form-control" classID="checkOnRange" placeholder="checkOnRange"/>
                                <p className="help-block"> </p>
                            </div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-offset-2 col-sm-10">
                                <a className="btn btn-primary" role="button" onClick={this.validate.bind(this)}>Save</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

export default Profile;

Profile.defaultProps = {apiUrl: "/api/constraints/test"};
