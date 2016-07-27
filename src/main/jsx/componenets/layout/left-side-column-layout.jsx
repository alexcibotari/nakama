import React, {Component} from 'react';

export default class LeftSideColumnLayout extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="row">
                <div className="col-md-2">
                    {this.props.sidebar}
                </div>
                <div className="col-md-10">
                    {this.props.main}
                </div>
            </div>
        )
    }
}
