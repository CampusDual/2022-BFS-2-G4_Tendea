import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import { switchMap } from 'rxjs';
import { Category } from '../../../model/category';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styles: [],
})
export class CategoriesComponent implements OnInit {
  constructor(
    private activateRoute: ActivatedRoute,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  category: Category;

  ngOnInit(): void {
    this.activateRoute.params
      .pipe(switchMap(({ id }) => this.categoryService.getCategory(id)))
      .subscribe((res) => (this.category = res, console.log(res)));

    console.log(this.category);
  }
}
