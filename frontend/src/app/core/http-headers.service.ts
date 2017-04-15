import {Injectable} from '@angular/core';
import {Headers} from '@angular/http';

@Injectable()
export class HttpHeadersService {

    constructor() {
    }

    public getHeaders(): Headers {
        return new Headers({
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8',
        });
    }
}
