import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroupDirective, NgForm, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/model/product';
import { LoggerService } from 'src/app/services/logger.service';
import { ProductService } from 'src/app/services/product.service';

import {MatSelectModule} from '@angular/material/select';
import {ErrorStateMatcher} from '@angular/material/core';

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
    category: this.fb.array(
      [
        ['Granel'],
        ['Unitario']
      ],
      Validators.required
    )
  });
  product: Product;
  errores: string[];
  category: any[] = [{name:'Granel'}, {name:'Unitario'}];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute,
    private logger: LoggerService
  ) {
    this.product = new Product();
  }

  ngOnInit(): void {
    this.createFormGroup();
    // this.productId = this.route.snapshot.params['id'];

  }

  createFormGroup() {
    this.productForm = this.fb.group({
      id: [this.product.id],
      name: [this.product.product_name],
      discount: [this.product.discount],
      price: [this.product.price]
    });
  }

  save() {
    /*
    const newProduct: Product = Object.assign({}, this.productForm.value);
      this.productService.createProduct(newProduct).subscribe((response) => {
        this.redirectList(response);
      });
    */
  }

  redirectList(response: any) {
    if (response.responseCode === 'OK') {
      this.router.navigate(['/products']);
    }else{
      console.log(response);
    }
  }

  cancel() {

  }

}
