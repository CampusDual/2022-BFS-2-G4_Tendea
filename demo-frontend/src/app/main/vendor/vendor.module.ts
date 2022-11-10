import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopComponent } from './shop/shop.component';
import { MaterialModule } from 'src/app/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VendorComponent } from './vendor.component';
import { VendorRoutingModule } from './vendor-routing.module';



@NgModule({
  declarations: [
    ShopComponent,
    VendorComponent
  ],
  imports: [
    CommonModule,
    VendorRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class VendorModule { }
