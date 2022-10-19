import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StoreRoutingModule } from './store-routing.module';
import { ShowProductsComponent } from './products/show-products/show-products.component';
import { MaterialModule } from 'src/app/material/material.module';
import { TranslateModule } from '@ngx-translate/core';


@NgModule({
  declarations: [
    ShowProductsComponent
  ],
  imports: [
    CommonModule,
    StoreRoutingModule,
    MaterialModule,
    TranslateModule
  ]
})
export class StoreModule { }
