import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainHomeComponent } from './main-home/main-home.component';
import { AuthGuard } from '../auth/auth.guard';
import { ContactsComponent } from './contacts/contacts.component';
import { StoreComponent } from './store/store.component';
import { RegisterComponent } from '../auth/register/register.component';
import { LandingComponent } from '../components/landing/landing.component';

const routes: Routes = [
  {
    path: 'main',
    component: MainHomeComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
  {
    path: 'contacts',
    component: ContactsComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
  {
    path: '',
    loadChildren: () =>
      import('./store/store.module').then((m) => m.StoreModule),
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CLIENTS'],
    },
  },
  // {
  //   path: 'shops',
  //   loadChildren: () =>
  //     import('./store/store.module').then((m) => m.StoreModule),
  //   canActivate: [AuthGuard],
  //   data: {
  //     allowedRoles: ['CLIENTS'],
  //   },
  // },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {}
