import {Component, OnInit} from '@angular/core';
import {Project} from './shared/project.model';
import {ProjectService} from './shared/project.service';

@Component({
    moduleId: module.id,
    templateUrl: 'project.component.html',
    styleUrls: ['project.component.css'],
    providers: [ProjectService]
})
export class ProjectComponent implements OnInit {
    projects: Project[];

    ngOnInit(): void {
        this.projectService.getAll().subscribe(projects => this.projects = projects);
    }

    constructor(private projectService: ProjectService) {

    }
}
