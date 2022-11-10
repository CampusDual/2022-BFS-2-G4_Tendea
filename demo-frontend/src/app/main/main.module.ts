import { CommonModule, DatePipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MainHomeComponent } from './main-home/main-home.component';
import { MainRoutingModule } from './main-routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { LightboxModule } from 'ngx-lightbox';
import { AppComponent } from '../app.component';
import { CoreModule } from '../core/core.module';
import { FilterItemDirective } from './directives/filter-item.directive';
import { MaterialModule } from '../material/material.module';
import { StoreComponent } from './store/store.component';
// import { CreateProductComponent } from './store/products/create-product/create-product.component';

@NgModule({
  declarations: [MainHomeComponent, 
    FilterItemDirective,
     StoreComponent
    ],
  imports: [
    TranslateModule,
    CommonModule,
    MainRoutingModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CoreModule,
    MatNativeDateModule,
    LightboxModule,
    NgxChartsModule,
    BrowserAnimationsModule,
    MaterialModule,
  ],
  bootstrap: [AppComponent],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'es-ES' }, DatePipe],
})
export class MainModule {}
