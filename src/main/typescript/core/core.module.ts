import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoggerService} from './logger.service';
import {HttpHeadersService} from './http-headers.service';

@NgModule({
    imports: [
        CommonModule
    ],
    exports: [],
    //declarations: [],
    providers: [HttpHeadersService, LoggerService]
})
export class CoreModule {
}
