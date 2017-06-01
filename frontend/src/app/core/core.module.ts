import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {LoggerService} from './logger.service';

@NgModule({
    imports: [
        CommonModule
    ],
    exports: [],
    providers: [LoggerService]
})
export class CoreModule {
}
