import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../../../model/product';
import { ShopService } from '../../../../services/shop.service';
import { AuthService } from '../../../../auth/auth.service';
import { CategoryService } from '../../../../services/category.service';
import { Category } from '../../../../model/category';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  product: Product;
  category: Category;
  userName: string;
  categories: Category[];

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private authService: AuthService,
    private categoryService: CategoryService
  ) {}

  productForm: FormGroup = this.fb.group({
    name: ['Un Producto'],
    price: [100],
    discount: [20],
    category: [],
    description: [],
    bulk: [],
  });
  ngOnInit(): void {
    this.userName = this.authService.getUserName();
    this.categoryService
      .getCategories()
      .subscribe((res) => (this.categories = res));
  }

  save() {
    this.productForm.controls['bulk'].value
      ? this.productForm.get('bulk').setValue(1)
      : this.productForm.get('bulk').setValue(0);

    console.log(this.productForm.value);
    this.shopService
      .createProduct(this.productForm.value)
      .subscribe((res) => console.log('se creo correctamente'));
  }
}
