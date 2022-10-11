import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateModule } from '@ngx-translate/core';

import { JwtModule } from '@auth0/angular-jwt';
import { CoreModule, FlexLayoutModule } from '@angular/flex-layout';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';

export function tokenGetter() {
  return localStorage.getItem('access_token');
}

@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    TranslateModule,
    CommonModule,
    AuthRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FlexLayoutModule,
    MatSelectModule,
    MatIconModule,
    ReactiveFormsModule,
    CoreModule,
    MatCardModule,
    JwtModule.forRoot({
      config: {
        tokenGetter,
      },
    }),
  ],
})
export class AuthModule {}
