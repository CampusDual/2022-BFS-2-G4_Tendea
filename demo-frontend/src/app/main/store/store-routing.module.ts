import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateProductComponent } from './products/create-product/create-product.component';
import { ShowProductsComponent } from './products/show-products/show-products.component';
import { StoreComponent } from './store.component';

const routes: Routes = [
  {
    path: '',
    component: StoreComponent,
    children: [
      { path: "", component: ShowProductsComponent, },
      { path: "add", component: CreateProductComponent },
      //{ path: 'edit/:id', component: EditContactComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StoreRoutingModule {

}
