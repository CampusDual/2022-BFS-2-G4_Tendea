import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  categories: Category[];
  category : Category;

  constructor(private categoryService: CategoryService) {

   }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(res => this.categories = res);
  }

  login() {

  }

}
