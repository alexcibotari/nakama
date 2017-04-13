import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {HttpHeadersService} from './http-headers.service';
import {LoggerService} from './logger.service';

@NgModule({
    imports: [
        CommonModule
    ],
    exports: [],
    providers: [HttpHeadersService, LoggerService]
})
export class CoreModule {
}
