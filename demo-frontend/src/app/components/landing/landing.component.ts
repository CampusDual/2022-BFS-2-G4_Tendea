import { Component, OnInit, Output } from '@angular/core';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent implements OnInit {
  categories: Category[];
  @Output() idCategory: Number;

  constructor(
    private categoryService: CategoryService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.categoryService
      .getCategories()
      .subscribe((res) => (this.categories = res));
  }

  obtenerCategoria() {
    this.idCategory = this.activatedRoute.snapshot.params['id'];
    console.log('landing', this.idCategory)
    return this.idCategory;
  }

  login() {}
}
