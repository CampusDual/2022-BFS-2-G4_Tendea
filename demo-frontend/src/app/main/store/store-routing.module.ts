import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CreateProductComponent } from './products/create-product/create-product.component';
import { ShowProductsComponent } from './products/show-products/show-products.component';
import { CreateShopsComponent } from './shops/create-shops/create-shops.component';
import { ShowShopsComponent } from './shops/show-shops/show-shops.component';
import { StoreComponent } from './store.component';
import { ProductComponent } from '../vendor/shop/product/product.component';

const routes: Routes = [
  {
    path: '',
    component: StoreComponent,
    children: [
      { path: 'products', component: ShowProductsComponent },
      { path: 'products/add', component: CreateProductComponent },
      { path: 'shops', component: ShowShopsComponent },
      { path: 'shops/add', component: CreateShopsComponent },
      { path: 'home', component: AdminHomeComponent },

      //{ path: 'edit/:id', component: EditContactComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StoreRoutingModule {}
