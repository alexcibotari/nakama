import React, {Component} from 'react';
import client from '../client';

class Profile extends Component {

    constructor(props){
        super(props);
        this.state = {
            constraints:[],
            data:{
                Pattern:""
            }
        }
    }

    componentWillMount(){
        client({method: 'GET', path: this.props.apiUrl}).then(response => {
            if (response.status.code == 200) {
                this.setState({constraints: response.entity});
                }
        });
    }

    validate(){
        'use strict';
        var formValid = true;
        for(var fieldName in this.state.constraints){
            var error = false;
            if(this.refs[fieldName]){
                this.refs[fieldName].parentElement.parentElement.className = 'form-group has-feedback';
                this.refs[fieldName].nextSibling.innerHTML = '';
                switch (this.state.constraints[fieldName][0].constraintType){
                    case 'AssertFalse' :
                        if(this.refs[fieldName].checked){
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'AssertTrue' :
                        if(!this.refs[fieldName].checked){
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'DecimalMax':
                        var valueIsNumber = parseFloat(this.refs[fieldName].value );
                        if( isNaN(valueIsNumber) ){
                            formValid = false;
                            error = true;
                        }else{
                            switch (this.state.constraints[fieldName][0].parameters.inclusive){
                                case true:
                                    if(fieldValue > parseInt(this.state.constraints[fieldName][0].parameters.value, 10)){
                                        formValid = false;
                                        error = true;
                                    }
                                    break;
                                case false:
                                    if(fieldValue >= parseInt(this.state.constraints[fieldName][0].parameters.value, 10)){
                                        formValid = false;
                                        error = true;
                                    }
                                    break;
                            }
                        }
                        break;
                    case 'DecimalMin':
                        var valueIsNumber = parseFloat(this.refs[fieldName].value );
                        if( isNaN(valueIsNumber) ){
                            formValid = false;
                            error = true;
                        }else{
                            switch (this.state.constraints[fieldName][0].parameters.inclusive){
                                case true:
                                    if(fieldValue < parseInt(this.state.constraints[fieldName][0].parameters.value, 10)){
                                        formValid = false;
                                        error = true;
                                    }
                                    break;
                                case false:
                                    if(fieldValue <= parseInt(this.state.constraints[fieldName][0].parameters.value, 10)){
                                        formValid = false;
                                        error = true;
                                    }
                                    break;
                            }
                        }
                        break;
                    case 'Digits':
                        var valueIsNumber = parseFloat(this.refs[fieldName].value );
                        var arrValue = this.refs[fieldName].value.split('.');
                        if( isNaN(valueIsNumber) ){
                            formValid = false;
                            error = true;
                        }else if(arrValue.length < 1 || arrValue.length > 2 ||
                            arrValue[0].length > this.state.constraints[fieldName][0].parameters.integer){
                            formValid = false;
                            error = true;
                        }else if(arrValue.length == 2 &&
                            arrValue[1].length > this.state.constraints[fieldName][0].parameters.fraction){
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Max':
                        if( isNaN(valueIsNumber) ){
                            formValid = false;
                            error = true;
                        }else if(this.refs[fieldName].value > this.state.constraints[fieldName][0].parameters.value){
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Min':
                        if( isNaN(valueIsNumber) ){
                            formValid = false;
                        }else if(this.refs[fieldName].value < this.state.constraints[fieldName][0].parameters.value){
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Size':
                        if(this.refs[fieldName].value.length < this.state.constraints[fieldName][0].parameters.min ||
                            this.refs[fieldName].value.length > this.state.constraints[fieldName][0].parameters.max){
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Pattern':
                        var regExpression = new RegExp(this.state.constraints[fieldName][0].parameters.regexp);
                        if (!regExpression.test(this.refs[fieldName].value)) {
                            formValid = false;
                            error = true;
                        }
                        break;
                }
                if(error){
                    this.refs[fieldName].parentElement.parentElement.className = 'has-error form-group has-feedback';
                    this.refs[fieldName].nextSibling.innerHTML = this.state.constraints[fieldName][0].parameters.message;
                }
            }
        }
        if(formValid === true) {
            alert('validated');
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
