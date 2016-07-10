import React, {Component} from 'react';
import NavBar from '../navbar'

class MainLayout extends Component {
    render() {
        return (
            <div>
                <NavBar brand="Nakama"/>
                {this.props.children}
            </div>
        )
    }
}

export default MainLayout;