import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';


@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent implements OnInit {
  categories: Category[];
  @Output() category: Category;
  @Output() onGetCategory: EventEmitter<Category> = new EventEmitter();

  constructor(
    private categoryService: CategoryService,
  ) {}

  ngOnInit(): void {
    this.categoryService
      .getCategories()
      .subscribe((res) => (this.categories = res));
  }

  getProductForCategory(selected: any) {
    this.category = selected;
    console.log(selected);
    this.onGetCategory.emit(selected);
  }


  login() {}
}
