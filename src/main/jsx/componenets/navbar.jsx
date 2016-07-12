import React, {Component} from 'react';
import { Link } from 'react-router';
class NavBar extends Component {
    render() {
        return (
            <nav className="navbar navbar-default navbar-fixed-top">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                                aria-expanded="false" aria-controls="navbar">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <a className="navbar-brand" href="#">{this.props.brand}</a>
                    </div>
                    <div classID="navbar" className="navbar-collapse collapse">
                        <ul className="nav navbar-nav">
                            <li>
                                <Link to="/">Dashboard</Link>
                            </li>
                            <li>
                                <Link to="/projects">Projects</Link>
                            </li>
                            <li>
                                <a>State2</a>
                            </li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            <li className="dropdown">
                                <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profile <span className="caret"></span></a>
                                <ul className="dropdown-menu">
                                    <li><a href="#">Profile</a></li>
                                    <li><a href="#">Settings</a></li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="/logout">Sign Out</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}

export default NavBar;