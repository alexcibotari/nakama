import React, {Component} from 'react';
import NavBar from '../navbar';
import client from '../../client';

class MainLayout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            url: '/api/profile',
            profile: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.state.url}).then(response => {
            if (response.status.code === 200) {
                this.setState({profile: response.entity});
            }
        });
    }

    render() {
        return (
            <div>
                <NavBar brand="Nakama" profile={this.state.profile} />
                {this.props.children}
            </div>
        )
    }
}

export default MainLayout;