import React, {Component} from 'react';

class SelectForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            edit: false
        };
    }

    editSelect() {
        this.setState({edit: true});
    }

    blurSelect() {
        this.setState({edit: false});
    }

    handleChange(e) {
        if (this.props.onChange) {
            for (var i = 0; i < this.props.options.length; i++) {
                if (this.props.options[i].id == e.target.value) {
                    this.props.onChange(this.props.options[i]);
                }
            }
        }
    }

    renderForm() {
        const options = this.props.options.map(option =>
            <option key={option.id} value={option.id}>{option.name}</option>
        );
        return (
            <select
                autoFocus={true}
                className="form-control"
                defaultValue={this.props.selected.id}
                onChange={this.handleChange.bind(this)}
                onBlur={this.blurSelect.bind(this)}>
                {options}
            </select>
        )
    }

    render() {
        if (this.state.edit) {
            return this.renderForm();
        } else if (this.props.selected) {
            return (
                <a onClick={this.editSelect.bind(this)} title={this.props.selected.description}>{this.props.selected.name} <span className="caret"></span></a>
            )
        } else {
            return this.renderForm();
        }
    }
}

export default SelectForm;
