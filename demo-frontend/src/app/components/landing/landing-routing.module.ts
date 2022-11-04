import { NgModule, Output } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing.component';
import { CategoriesComponent } from './categories/categories.component';
import { GridComponent } from './grid/grid.component';


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
        component: LandingComponent,
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
