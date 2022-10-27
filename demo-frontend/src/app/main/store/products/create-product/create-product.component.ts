import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroupDirective, NgForm, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/product';
import { LoggerService } from 'src/app/services/logger.service';
import { ProductService } from 'src/app/services/product.service';
import { Location } from '@angular/common'


import {MatSelectModule} from '@angular/material/select';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { subscribeOn } from 'rxjs';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss'],
})

export class CreateProductComponent implements OnInit {
  productId: number;

  productForm: FormGroup = this.fb.group({
    name: [
      '',
      [Validators.required, Validators.minLength(3), Validators.maxLength(50)]
    ],
    price: [
      '',
      Validators.required
    ],
    category:[
      '',
      Validators.required
    ],
    boolBulk: false,
    file: [''],
  });
  product: Product;
  errores: string[];
  categories: Category[];
  category : Category;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute,
    private logger: LoggerService,
    private location: Location,
    private categoryService: CategoryService
  ) {
    this.product = new Product();
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      res => this.categories = res
    );
    this.createFormGroup();
    // this.productId = this.route.snapshot.params['id'];

  }

  createFormGroup() {
    this.productForm = this.fb.group({
      id: [this.product.id],
      name: [this.product.name],
      discount: [this.product.discount],
      price: [this.product.price],
      category: [this.product.category],
      boolBulk: [this.product.boolBulk],
      file: [this.product.images],
      bulk: [this.product.bulk]
    });
  }

  save() {
    console.log(this.productForm);

    if(this.productForm.value.boolBulk == true) {
      this.productForm.value.bulk = 1;
    } else {
      this.productForm.value.bulk = 0;
    }

    console.log(this.productForm.value.boolBulk);
    console.log(this.product.bulk);

    const newProduct: Product = Object.assign({}, this.productForm.value );
      this.productService.createProduct(newProduct).subscribe((response) => {
        this.redirectList(response);
      });

      // this.productService.uploadProductImg(this.product.id , this.productForm["file"])

      
  }

  // uploadImg() {
  //   this.productService.uploadProductImg(this.product.id , this.productForm["file"])
  // }

  redirectList(response: any) {
    if (response.responseCode === 'OK') {
      this.router.navigate(['/products']);
    }else{
      console.log(response);
    }
  }

  cancel() {

  }

  back(): void {
    this.location.back();
  }

}
