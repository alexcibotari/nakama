import React, {Component} from 'react';
import {Link} from 'react-router';
import client from '../services/client';
import ConfirmationDialog from './confirmation-dialog';

class UserList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        client({method: 'GET', path: this.props.apiUrl.users}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    deleteUser(userName) {
        client({method: 'DELETE', path: this.props.apiUrl.users + '/' + userName}).then(response => {
            if (response.status.code == 200) {
                var delIndex = this.state.data.findIndex(function (user) {
                    return user.userName === userName;
                });
                this.state.data.splice(delIndex, 1);
                this.forceUpdate();
            }
        });
    }

    render() {
        const users = this.state.data.map(user => {
            return (
                <tr key={user.userName}>
                    <td><b>{user.userName}</b></td>
                    <td>{user.email}</td>
                    <td>{user.enabled ? (<span className="glyphicon glyphicon-ok"> </span>) : (<span className="glyphicon glyphicon-remove"> </span>)}</td>
                    <td>
                        <div className="btn-group pull-right" role="group">
                            <Link to={'admin/users/edit/' + user.userName}
                                  className="btn btn-sm btn-default glyphicon glyphicon-pencil"
                                  role="button"/>
                            <ConfirmationDialog
                                title="Delete User."
                                bodyText="Are you sure you want to delete the user?"
                                lunchModalBtnClasses="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                lunchModalBtnText=""
                                lunchModalBtnStyles={{float: 'right'}}
                                actionBtnAction={this.deleteUser.bind(this, user.userName)}
                                modalContainerStyle={{marginLeft: 34+'px'}}/>
                        </div>
                    </td>
                </tr>
            )
        });
        const userListHeading = (<div className="row">
            <h1>User List:<Link to={'admin/users/create'}
                                className="pull-right btn btn-lg btn-success glyphicon glyphicon-plus"/>
            </h1>
        </div>);
        if (Array.isArray(this.state.data) && this.state.data.length > 0) {
            return (
                <div className="col-md-12">
                    <div>
                        {userListHeading}
                        <div className="row">
                            <div className="table-responsive">
                                <table className="table table-striped table-condensed">
                                    <thead>
                                    <tr>
                                        <th>User name</th>
                                        <th>Email</th>
                                        <th>enabled</th>
                                        <th className="text-right">Options</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {users}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div className="col-md-12">
                    {userListHeading}
                    <h4>No user found.</h4>
                </div>
            )
        }
    }
}

export default UserList;
UserList.defaultProps = {apiUrl: {users: '/api/users'}};
