'use strict';

class ValidationForm {

    static validate(constraints, refs) {
        var formValid = true;
        for (var fieldName in constraints) {
            var error = false;
            if (refs[fieldName]) {
                refs[fieldName].parentElement.parentElement.className = 'form-group has-feedback';
                refs[fieldName].nextSibling.innerHTML = '';
                switch (constraints[fieldName][0].constraintType) {
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
                            if (constraints[fieldName][0].parameters.inclusive) {
                                if (valueIsNumber > parseInt(constraints[fieldName][0].parameters.value, 10)) {
                                    formValid = false;
                                    error = true;
                                }
                            } else {
                                if (valueIsNumber >= parseInt(constraints[fieldName][0].parameters.value, 10)) {
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
                            if (constraints[fieldName][0].parameters.inclusive) {
                                if (valueIsNumber < parseInt(constraints[fieldName][0].parameters.value, 10)) {
                                    formValid = false;
                                    error = true;
                                }
                            } else {
                                if (valueIsNumber <= parseInt(constraints[fieldName][0].parameters.value, 10)) {
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
                            arrValue[0].length > constraints[fieldName][0].parameters.integer) {
                            formValid = false;
                            error = true;
                        } else if (arrValue.length == 2 &&
                            arrValue[1].length > constraints[fieldName][0].parameters.fraction) {
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Max':
                        var valueIsNumber = parseInt(refs[fieldName].value);
                        if (isNaN(valueIsNumber)) {
                            formValid = false;
                            error = true;
                        } else if (valueIsNumber > constraints[fieldName][0].parameters.value) {
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Min':
                        var valueIsNumber = parseInt(refs[fieldName].value);
                        if (isNaN(valueIsNumber)) {
                            formValid = false;
                            error = true;
                        } else if (valueIsNumber < constraints[fieldName][0].parameters.value) {
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Size':
                        if (refs[fieldName].value.length < constraints[fieldName][0].parameters.min ||
                            refs[fieldName].value.length > constraints[fieldName][0].parameters.max) {
                            formValid = false;
                            error = true;
                        }
                        break;
                    case 'Pattern':
                        var regExpression = new RegExp(constraints[fieldName][0].parameters.regexp);
                        if (!regExpression.test(refs[fieldName].value)) {
                            formValid = false;
                            error = true;
                        }
                        break;
                }
                if (error) {
                    refs[fieldName].parentElement.parentElement.className = 'has-error form-group has-feedback';
                    refs[fieldName].nextSibling.innerHTML = constraints[fieldName][0].parameters.message;
                }
            }
        }
        return formValid;
    }

}

export default ValidationForm;
