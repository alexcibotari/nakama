import React, {Component} from 'react';

class SelectForm extends Component {

    handleChange(e) {
        if (this.props.onChange) {
            for (var i = 0; i < this.props.options.length; i++) {
                if (this.props.options[i].id == e.target.value) {
                    this.props.onChange(this.props.options[i]);
                }
            }
        }
    }

    render() {
        console.log("default:" + this.props.selected.id);
        const options = this.props.options.map(option =>
            <option key={option.id} value={option.id}>{option.name}</option>
        );
        return (
            <select
                className="form-control"
                value={this.props.selected.id}
                onChange={this.handleChange.bind(this)}
                title={this.props.selected.description}>
                {options}
            </select>
        )
    }
}

export default SelectForm;
