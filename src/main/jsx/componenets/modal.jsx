import React, {Component} from 'react';

export default class Modal extends React.Component{
    render(){
        return(
            <div className="modal-container" style={this.props.modalContainerStyle}>
                <div className="modal fade" tabIndex="-1" role="dialog" id={this.props.modalId}>
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 className="modal-title">{this.props.title}</h4>
                            </div>
                            <div className="modal-body">
                                <p>{this.props.bodyText}</p>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" className="btn btn-primary" data-dismiss="modal" onClick={this.props.actionBtnAction}>Yes</button>
                            </div>
                        </div>
                    </div>
                </div>
                <button role="button"
                        type="button"
                        className={this.props.lunchModalBtnClasses}
                        data-toggle="modal"
                        data-target={'#'+this.props.modalId}
                        style={this.props.lunchModalBtnStyles}>{this.props.lunchModalBtnText}</button>
            </div>
        )
    }
}
