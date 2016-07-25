import React, {Component} from 'react';
import client from '../client';

class SelectForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            edit: false,
            selected: -1
        };
    }

    editProject() {
        this.setState({edit: true});
    }

    blurProject() {
        this.setState({edit: false});
    }

    handleChange(e) {
        this.setState({ selected: e.currentTarget.value });
        if (this.props.onChange) {
            this.props.onChange(e);
        }
    }

    renderForm() {
        const options = this.props.options.map(option =>
            <option key={option.id} value={option.id}>{option.name}</option>
        );
        return (
            <select
                className="form-control"
                name={this.props.name}
                value={this.state.selected}
                onChange={this.handleChange.bind(this)}
                onBlur={this.blurProject.bind(this)}>
                <option value="">Please select a value</option>
                {options}
            </select>
        )
    }

    render() {
        if (this.state.edit) {
            return this.renderForm();
        } else if (this.props.selected) {
            return (
                <p onClick={this.editProject.bind(this)}>{this.props.selected}</p>
            )
        } else {
            return this.renderForm();
        }
    }
}

export default SelectForm;
