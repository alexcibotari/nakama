import {ModuleWithProviders, NgModule} from '@angular/core';
import {AuthService} from '../core/auth.service';

@NgModule({
  imports: [/* Other nifty modules */],
  exports: [],
  declarations: [/* Login-Component, Logoff-component, UserInfoComponent, UserStatusComp */],
  providers: [],
})
export class AuthModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AuthModule,
      providers: [AuthService]
    };
  }
}
