import { NgModule, Output } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing.component';
import { CategoriesComponent } from './categories/categories.component';
import { GridComponent } from './grid/grid.component';
import { NavSearchComponent } from './nav-search/nav-search.component';


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
        component: CategoriesComponent
      },
      {
        path: '**',
        redirectTo: '',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LandingRoutingModule {}
