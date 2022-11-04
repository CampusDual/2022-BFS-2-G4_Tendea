import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingRoutingModule } from './landing-routing.module';
import { FooterComponent } from './footer/footer.component';
import { LandingComponent } from './landing.component';
import { NavSearchComponent } from './nav-search/nav-search.component';
import { GridComponent } from './grid/grid.component';
import { MaterialModule } from '../../material/material.module';
import { CategoriesComponent } from './categories/categories.component';
import { FormsModule } from '@angular/forms';
import { ProductCardComponent } from './product-card/product-card.component';

@NgModule({
  declarations: [
    LandingComponent,
    NavSearchComponent,
    FooterComponent,
    GridComponent,
    CategoriesComponent,
    ProductCardComponent,
  ],
  imports: [CommonModule, LandingRoutingModule, MaterialModule, FormsModule],
  exports: [],
})
export class LandingModule {}
