import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import { switchMap } from 'rxjs';
import { Category } from '../../../model/category';
import { ProductService } from 'src/app/services/product.service';
import { Product } from '../../../model/product';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styles: [],
})
export class CategoriesComponent implements OnInit {
  constructor(
    private activateRoute: ActivatedRoute,
    private categoryService: CategoryService,
    private productService: ProductService,
    private router: Router
  ) {}

  categoryId: number;
  category: Category;
  @Output() sProducts: Product[] = [];

  ngOnInit(): void {
    this.activateRoute.params
      .pipe(switchMap(({ id }) => this.categoryService.getCategory(id)))
      .subscribe((res) => (this.category = res));

    this.activateRoute.params
      .pipe(
        switchMap(({ id }) => this.productService.getProductsByCategory(id))
      )
      .subscribe((res) => (this.sProducts = res));
  }

  onCategory(): boolean {
    if (this.router.url.includes('categoria')) {
      return true;
    }
    return false;
  }
}
