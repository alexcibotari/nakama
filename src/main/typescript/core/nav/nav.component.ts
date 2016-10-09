import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../projects/project.service";
import {Project} from "../../projects/project.model";

@Component({
    moduleId: module.id,
    selector: 'nav-bar',
    templateUrl: 'nav.component.html',
    styleUrls: ['nav.component.css'],
    providers: [ProjectService]
})
export class NavComponent implements OnInit {
    private projects:Project[];
    ngOnInit() {
        console.log(this.projects);
    }
    constructor(private projectService:ProjectService) {
        this.projects = projectService.getAll();
    }
}
