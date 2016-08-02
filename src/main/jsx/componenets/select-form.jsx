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

    render() {
        const options = this.props.options.map(option =>
            <option key={option.id} value={option.id}>{option.name}</option>
        );
        return (
            <select
                className="form-control"
                defaultValue={this.props.selected.id}
                onChange={this.handleChange.bind(this)}
                onBlur={this.blurSelect.bind(this)}>
                {options}
            </select>
        )
    }
}

export default SelectForm;
