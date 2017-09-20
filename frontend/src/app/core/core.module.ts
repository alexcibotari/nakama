import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {LoggerService} from './logger.service';
import {ObjectUtils} from './object-utils.service';

@NgModule({
    imports: [
        CommonModule
    ],
    exports: [],
    providers: [LoggerService, ObjectUtils]
})
export class CoreModule {
}
