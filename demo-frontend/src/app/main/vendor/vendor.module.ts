import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopComponent } from './shop/shop.component';
import { MaterialModule } from 'src/app/material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VendorComponent } from './vendor.component';
import { VendorRoutingModule } from './vendor-routing.module';
import { ProductComponent } from './shop/product/product.component';
import { ImagenPipe } from 'src/app/pipes/imagen.pipe';



@NgModule({
  declarations: [
    ShopComponent,
    VendorComponent,
    ProductComponent,
    ImagenPipe
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
