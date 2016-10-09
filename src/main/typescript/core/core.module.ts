import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {LoggerService} from "./logger.service";
import {NavComponent} from "./nav/nav.component";

@NgModule({
    imports: [
        CommonModule
    ],
    exports: [NavComponent],
    declarations: [NavComponent],
    providers: [LoggerService]
})
export class CoreModule {
}
