'use strict';
import ReactDOM from 'react-dom';
import {Component} from 'react';

class ValidationForm {

    static validate(constraints, refs) {
        var formValid = true;
        for (var fieldName in constraints) {
            var error = false;
            for (var i = 0; i < constraints[fieldName].length; i++) {
                var formField = refs[fieldName];
                if (formField) {
                    if (formField instanceof Component) {
                        formField = ReactDOM.findDOMNode(formField);
                    }
                    formField.parentElement.parentElement.classList.remove('has-error');
                    formField.nextSibling.innerHTML = '';
                    switch (constraints[fieldName][i].constraintType) {
                        case 'AssertFalse' :
                            if (formField.checked) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'AssertTrue' :
                            if (!formField.checked) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'DecimalMax':
                            var valueIsNumber = parseFloat(formField.value);
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else {
                                if (constraints[fieldName][i].parameters.inclusive) {
                                    if (valueIsNumber > parseInt(constraints[fieldName][i].parameters.value, 10)) {
                                        formValid = false;
                                        error = true;
                                    }
                                } else {
                                    if (valueIsNumber >= parseInt(constraints[fieldName][i].parameters.value, 10)) {
                                        formValid = false;
                                        error = true;
                                    }
                                }
                            }
                            break;
                        case 'DecimalMin':
                            var valueIsNumber = parseFloat(formField.value);
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else {
                                if (constraints[fieldName][i].parameters.inclusive) {
                                    if (valueIsNumber < parseInt(constraints[fieldName][i].parameters.value, 10)) {
                                        formValid = false;
                                        error = true;
                                    }
                                } else {
                                    if (valueIsNumber <= parseInt(constraints[fieldName][i].parameters.value, 10)) {
                                        formValid = false;
                                        error = true;
                                    }
                                }
                            }
                            break;
                        case 'Digits':
                            var valueIsNumber = parseFloat(formField.value);
                            var arrValue = formField.value.split('.');
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else if (arrValue.length < 1 || arrValue.length > 2 ||
                                arrValue[i].length > constraints[fieldName][i].parameters.integer) {
                                formValid = false;
                                error = true;
                            } else if (arrValue.length == 2 &&
                                arrValue[1].length > constraints[fieldName][i].parameters.fraction) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Max':
                            var valueIsNumber = parseInt(formField.value);
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else if (valueIsNumber > constraints[fieldName][i].parameters.value) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Min':
                            var valueIsNumber = parseInt(formField.value);
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else if (valueIsNumber < constraints[fieldName][i].parameters.value) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Size':
                            if (formField.value.length < constraints[fieldName][i].parameters.min ||
                                formField.value.length > constraints[fieldName][i].parameters.max) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Pattern':
                            var regExpression = new RegExp(constraints[fieldName][i].parameters.regexp);
                            if (!regExpression.test(formField.value)) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'NotNull':
                            if(!formField.value) {
                                console.log(3);
                                formValid = false;
                                error = true;
                            }
                            break;
                    }
                    if (error) {
                        formField.parentElement.parentElement.classList.add('has-error');
                        formField.nextSibling.innerHTML = constraints[fieldName][i].parameters.message;
                        break;
                    }
                }else if(constraints[fieldName][i].constraintType === 'NotNull'){
                    formValid = false;
                    var section = document.querySelector("form");
                    var errorMessage = document.createElement("div");
                    errorMessage.innerHTML = "<p class='help-block'>"+[fieldName] + " should exist</p>";
                    section.insertBefore( errorMessage, section.firstChild );
                    errorMessage.classList.add('has-error');
                }
            }
        }
        return formValid;
    }
}

export default ValidationForm;
