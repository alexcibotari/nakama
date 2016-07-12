import React, {Component} from 'react';
import NavBar from '../navbar';
import client from '../../client';

class MainLayout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            url: '/api/profile',
            data: []
        }
    }

    componentWillMount() {
        client({method: 'GET', path: this.state.url}).then(response => {
            if (response.status.code === 200) {
                this.setState({data: response.entity});
            }
        });
    }

    render() {
        return (
            <div>
                <NavBar brand="Nakama" profileData={this.state.data} />
                {this.props.children}
            </div>
        )
    }
}

export default MainLayout;