import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {
  HttpClient,
  HttpClientModule,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule } from '@angular/forms';
import { MatPaginatorIntl } from '@angular/material/paginator';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import {
  TranslateLoader,
  TranslateModule,
  TranslateService,
} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { NgxSpinnerModule } from 'ngx-spinner';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthService } from './auth/auth.service';
import { MatSnackBarComponent } from './components/mat-snack-bar/mat-snack-bar.component';
import { CoreModule } from './core/core.module';
import { MainModule } from './main/main.module';
import { MaterialModule } from './material/material.module';
import { CustomMatPaginatorIntl } from './model/custom-mat-paginator';
import { InterceptService } from './services/intercept.service';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { ConfirmationDialogComponent } from './shared/confirmation-dialog/confirmation-dialog.component';
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import { JwtModule } from '@auth0/angular-jwt';
import { WelcomeComponent } from './components/landing/welcome/welcome.component';

registerLocaleData(localeEs);
export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [
    AppComponent,
    ConfirmationDialogComponent,
    MatSnackBarComponent,
    WelcomeComponent,
  ],
  imports: [
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter,
      },
    }),
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FlexLayoutModule,
    MainModule,
    CoreModule,
    NgxSpinnerModule,
    MaterialModule,
    NgIdleKeepaliveModule.forRoot(),
  ],
  entryComponents: [ConfirmationDialogComponent],
  exports: [TranslateModule],
  providers: [
    AuthService,
    MatSnackBarComponent,
    InterceptService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptService,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true,
    },
    {
      provide: MatPaginatorIntl,
      useFactory: (translate) => {
        const service = new CustomMatPaginatorIntl();
        service.injectTranslateService(translate);
        return service;
      },
      deps: [TranslateService],
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}
