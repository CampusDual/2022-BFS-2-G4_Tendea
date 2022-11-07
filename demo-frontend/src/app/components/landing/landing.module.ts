import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingRoutingModule } from './landing-routing.module';
import { FooterComponent } from './footer/footer.component';
import { LandingComponent } from './landing.component';
import { GridComponent } from './grid/grid.component';
import { MaterialModule } from '../../material/material.module';
import { CategoriesComponent } from './categories/categories.component';
import { FormsModule } from '@angular/forms';
import { ProductCardComponent } from './product-card/product-card.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { NavSearchComponent } from './nav-search/nav-search.component';
import { ImagenPipe } from '../../pipes/imagen.pipe';
import { CarouselComponent } from './welcome/carousel/carousel.component';
import { WelcomeComponent } from './welcome/welcome.component';

@NgModule({
  declarations: [
    LandingComponent,
    NavSearchComponent,
    FooterComponent,
    GridComponent,
    CategoriesComponent,
    ProductCardComponent,
    ProductDetailComponent,
    ImagenPipe,
    CarouselComponent,
    WelcomeComponent
  ],
  imports: [CommonModule, LandingRoutingModule, MaterialModule, FormsModule],
  exports: [],
})
export class LandingModule {}
