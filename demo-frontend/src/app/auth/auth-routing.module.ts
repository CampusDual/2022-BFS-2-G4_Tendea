import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'registro',
        component: RegisterComponent,
      },
      // {
      //   path: '**',
      //   redirectTo: 'login',
      // },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
