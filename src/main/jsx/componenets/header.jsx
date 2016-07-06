import React from 'react';

class Header extends React.Component {
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
                        <a className="navbar-brand" href="#">Nakama</a>
                    </div>
                    <div classID="navbar" className="navbar-collapse collapse">
                        <ul className="nav navbar-nav">
                            <li>
                                <a>Dashboard</a>
                            </li>
                            <li>
                                <a>State1</a>
                            </li>
                            <li>
                                <a>State2</a>
                            </li>
                        </ul>
                        <form className="navbar-form navbar-right" action="/logout" method="post">
                            <button type="submit" className="btn btn-default">Sign Out</button>
                        </form>
                    </div>
                </div>
            </nav>
        )
    }
}

export default Header;