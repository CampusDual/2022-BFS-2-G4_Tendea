import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StoreRoutingModule } from './store-routing.module';
import { ShowProductsComponent } from './products/show-products/show-products.component';
import { MaterialModule } from 'src/app/material/material.module';
import { TranslateModule } from '@ngx-translate/core';
import { CreateProductComponent } from './products/create-product/create-product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShowShopsComponent } from './shops/show-shops/show-shops.component';
import { CreateShopsComponent } from './shops/create-shops/create-shops.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';


@NgModule({
  declarations: [
    ShowProductsComponent,
    CreateProductComponent,
    ShowShopsComponent,
    CreateShopsComponent,
    AdminHomeComponent
  ],
  imports: [
    CommonModule,
    StoreRoutingModule,
    MaterialModule,
    TranslateModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ]
})
export class StoreModule { }
