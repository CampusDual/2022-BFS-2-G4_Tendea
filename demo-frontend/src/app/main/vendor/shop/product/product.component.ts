import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../../../model/product';
import { ShopService } from '../../../../services/shop.service';
import { AuthService } from '../../../../auth/auth.service';
import { CategoryService } from '../../../../services/category.service';
import { Category } from '../../../../model/category';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { ProductImages } from '../../../../model/product-images';

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
  imagen: ProductImages;
  imgTemp: any;

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private authService: AuthService,
    private categoryService: CategoryService,
    private fileUpload: FileUploadService
  ) {}

  productForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    price: [, Validators.required],
    discount: [],
    category: [, Validators.required],
    bulk: [false],
    description: [],
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
    this.shopService.createProduct(this.productForm.value).subscribe((res) => {
      this.uploadImage(res.id);
    });
  }

  /**
   * Subir o actualizar la imagen del producto
   */
  changeImage(event) {
    const file = event.target.files[0];
    this.imageUpload = file;
    if (!file) {
      this.imgTemp = '';
      return;
    }

    /** Si tenemos imagen */
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      this.imgTemp = reader.result;
    };
  }

  uploadImage(productId: number) {
    this.fileUpload
      .uploadImage(this.imageUpload, productId, 'products')
      .subscribe((res) => console.log(res));
  }
}
