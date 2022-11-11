import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from '../store/admin-home/admin-home.component';
import { ShopComponent } from './shop/shop.component';
import { VendorComponent } from './vendor.component';
import { ProductComponent } from './shop/product/product.component';

const routes: Routes = [
  {
    path: '',
    component: VendorComponent,
    children: [
      { path: 'shop', component: ShopComponent },
      /** Productos */
      {
        path: 'products/add',
        component: ProductComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VendorRoutingModule {}
