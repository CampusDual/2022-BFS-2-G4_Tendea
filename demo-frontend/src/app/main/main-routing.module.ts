import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainHomeComponent } from './main-home/main-home.component';
import { AuthGuard } from '../auth/auth.guard';
import { ContactsComponent } from './contacts/contacts.component';
import { StoreComponent } from './store/store.component';
import { RegisterComponent } from '../auth/register/register.component';
import { LandingComponent } from '../components/landing/landing.component';
import { ShopComponent } from './vendor/shop/shop.component';
import { VendorComponent } from './vendor/vendor.component';

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
    path: 'vendors',
    // component: VendorComponent,
    loadChildren: () =>
      import('././vendor/vendor.module').then((m) => m.VendorModule),
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['SHOPS'],
    },
  },
  {
    path: '',
    loadChildren: () =>
      import('./store/store.module').then((m) => m.StoreModule),
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['ADMIN'],
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {}
