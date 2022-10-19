import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainHomeComponent } from './main-home/main-home.component';
import { AuthGuard } from '../auth/auth.guard';
import { ContactsComponent } from './contacts/contacts.component';
import { StoreComponent } from './store/store.component';

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
    path: 'products',
    loadChildren: ()=>import ('./store/store.module').then((m) => m.StoreModule),
    // el import es como una función anónima para cargar el módulo (lazy loading), y el then coge la respuesta (promesa), le pone el nombre m y le asigna el módulo.
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CLIENTS']
    }
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {}
