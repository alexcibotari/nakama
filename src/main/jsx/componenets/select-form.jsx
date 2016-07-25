import React, {Component} from 'react';

class SelectForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            edit: false,
            selected: 0
        };
    }

    editSelect() {
        this.setState({edit: true});
    }

    blurSelect() {
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
                value={this.state.selected.id}
                onChange={this.handleChange.bind(this)}
                onBlur={this.blurSelect.bind(this)}>
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
                <p onClick={this.editSelect.bind(this)} title={this.props.selected.description}>{this.props.selected.name}</p>
            )
        } else {
            return this.renderForm();
        }
    }
}

export default SelectForm;
SelectForm.defaultProps = {name: (new Date%9e6).toString(36)};
