import {Injectable} from "@angular/core";
import {Project} from "./project.model";

@Injectable()
export class ProjectService {
    getAll():Project[] {
        return [
            new Project('1', 'TEST1', 'desc'),
            new Project('2', 'TEST2', 'desc'),
            new Project('3', 'TEST3', 'desc'),
        ];
    }
}
