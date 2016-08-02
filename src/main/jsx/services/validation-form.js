'use strict';
import ReactDOM from 'react-dom';
import {Component} from 'react';

class ValidationForm {

    static validate(constraints, refs) {
        var formValid = true;
        // console.log(refs);
        for (var fieldName in constraints) {
            var error = false;

            if (refs[fieldName]) {
                if (refs[fieldName] instanceof Component) {
                    console.log(fieldName);
                    console.log(ReactDOM.findDOMNode(refs[fieldName]))

                    /*refs[fieldName].parentElement.parentElement.classList.remove('has-error');
                    refs[fieldName].nextSibling.innerHTML = '';*/
                }
                for (var i = 0; i < constraints[fieldName].length; i++) {
                    switch (constraints[fieldName][i].constraintType) {
                        case 'AssertFalse' :
                            if (refs[fieldName].checked) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'AssertTrue' :
                            if (!refs[fieldName].checked) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'DecimalMax':
                            var valueIsNumber = parseFloat(refs[fieldName].value);
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
                            var valueIsNumber = parseFloat(refs[fieldName].value);
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
                            var valueIsNumber = parseFloat(refs[fieldName].value);
                            var arrValue = refs[fieldName].value.split('.');
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
                            var valueIsNumber = parseInt(refs[fieldName].value);
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else if (valueIsNumber > constraints[fieldName][i].parameters.value) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Min':
                            var valueIsNumber = parseInt(refs[fieldName].value);
                            if (isNaN(valueIsNumber)) {
                                formValid = false;
                                error = true;
                            } else if (valueIsNumber < constraints[fieldName][i].parameters.value) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Size':
                            if (refs[fieldName].value.length < constraints[fieldName][i].parameters.min ||
                                refs[fieldName].value.length > constraints[fieldName][i].parameters.max) {
                                formValid = false;
                                error = true;
                            }
                            break;
                        case 'Pattern':
                            var regExpression = new RegExp(constraints[fieldName][i].parameters.regexp);
                            if (!regExpression.test(refs[fieldName].value)) {
                                formValid = false;
                                error = true;
                            }
                            break;
                    }
                    if (error) {
                        refs[fieldName].parentElement.parentElement.classList.add('has-error');
                        refs[fieldName].nextSibling.innerHTML = constraints[fieldName][i].parameters.message;
                        break;
                    }
                }

            }if(constraints[fieldName] === 'NotNull' && !refs[fieldName]){
                formValid = false;
                alert(refs[fieldName] + " should be NotNull");
            }
        }
        return formValid;
    }

}

export default ValidationForm;
