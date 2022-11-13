import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../../../model/product';
import { ShopService } from '../../../../services/shop.service';
import { AuthService } from '../../../../auth/auth.service';
import { CategoryService } from '../../../../services/category.service';
import { Category } from '../../../../model/category';
import { FileUploadService } from 'src/app/services/file-upload.service';

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
  imageUpload: File; // Imagen a subir

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private authService: AuthService,
    private categoryService: CategoryService,
    private fileUpload: FileUploadService
  ) {}

  productForm: FormGroup = this.fb.group({
    name: ['Un Producto'],
    price: [100],
    discount: [20],
    category: [, Validators.required],
    bulk: [],
    description: ['Esta es una descipcion'],
  });
  ngOnInit(): void {
    this.userName = this.authService.getUserName();
    this.categoryService
      .getCategories()
      .subscribe((res) => (this.categories = res));
  }

  save() {
    /** Cambio de bult to integer */
    this.productForm.controls['bulk'].value
      ? this.productForm.get('bulk').setValue(1)
      : this.productForm.get('bulk').setValue(0);

    /** Send product to backend */
    // this.shopService
    //   .createProduct(this.productForm.value)
    //   .subscribe((res) => console.log(res));
  }

  /**
   * Subir o actualizar la imagen del producto
   */
  changeImage(event) {
    const file = event.target.files[0];
    this.imageUpload = file;
  }

  uploadImage() {
    console.log(this.imageUpload);
    this.fileUpload
      .uploadImage(236, this.imageUpload)
      .subscribe((res) => console.log(res));
  }
}
