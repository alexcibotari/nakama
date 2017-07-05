import {Component, Inject, OnInit} from "@angular/core";
import {MD_DIALOG_DATA} from "@angular/material";

@Component({
    selector: 'confirmation-dialog',
    templateUrl: './confirmation-dialog.component.html',
    styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent implements OnInit {

    constructor(@Inject(MD_DIALOG_DATA) public data: any) {
    }

    ngOnInit() {
    }

}
