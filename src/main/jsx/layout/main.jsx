import React, {Component} from 'react';
import NavBar from '../componenets/navbar'

class MainLayout extends Component {
    render() {
        return (
            <div>
                <NavBar brand="Nakama"/>
                <div className="container">
                    {this.props.children}
                </div>
            </div>
        )
    }
}

export default MainLayout;