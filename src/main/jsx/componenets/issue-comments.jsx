import React, {Component} from 'react';
import {Link, withRouter} from 'react-router';
import client from '../services/client';
import ValidationForm from '../services/validation-form';

class IssueComments extends Component{

    constructor(props){
        super(props);
        this.state={

        }
    }

    render(){
        return(
            <div className="comments">
                here will go comments. cool! ha?!
            </div>
        )
    }
}

export default withRouter(IssueComments)

IssueComments.defaultProps = {apiUrl: {issue: '/api/issue/', constraints: '/api/constraints/issue/comment'}};
