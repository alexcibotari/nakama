import {Injectable} from "@angular/core";

@Injectable()
export class ObjectUtils {

    public clone(source: any): any {
        let target: any = Object.assign({}, source);
        Object.getOwnPropertyNames(target)
            .forEach(value => {
                if(target[value] instanceof Object){
                    target[value] = this.clone(target[value]);
                }
            });
        return target;
    }
}
