import React, {Component} from 'react';
import {Link} from 'react-router';


export default class IssueSidebar extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="panel panel-default">
                <div className="panel-heading">Issues:</div>
                <div className="panel-body list-group">
                    <Link className="list-group-item" to="/admin/issues/types" activeClassName="active">Types</Link>
                    <Link className="list-group-item" to="/admin/issues/priorities" activeClassName="active">Priorities</Link>
                    <Link className="list-group-item" to="/admin/issues/statuses" activeClassName="active">Statuses</Link>
                </div>
            </div>
        )
    }
}

