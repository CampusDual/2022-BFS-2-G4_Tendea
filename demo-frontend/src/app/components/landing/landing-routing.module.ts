import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing.component';
import { CategoriesComponent } from './categories/categories.component';
import { NavSearchComponent } from './nav-search/nav-search.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ShopsComponent } from '../shops/shops.component';

const routes: Routes = [
  {
    path: '',
    component: LandingComponent,
    children: [
      {
        path: 'categoria/:id',
        component: CategoriesComponent,
      },
      {
        path: '',
        component: NavSearchComponent,
      },
      {
        path: 'producto/:id',
        component: ProductDetailComponent,
      },
      {
        path: 'welcome',
        component: WelcomeComponent,
      },
      {
        path: 'tienda/:id',
        component: ShopsComponent,
        loadChildren: () =>
          import('../shops/shops.module').then((s) => s.ShopsModule),
      },
      // {
      //   path: '**',
      //   redirectTo: '',
      // },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LandingRoutingModule {}
