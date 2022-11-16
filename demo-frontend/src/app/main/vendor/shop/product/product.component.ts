import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../../../model/product';
import { ShopService } from '../../../../services/shop.service';
import { AuthService } from '../../../../auth/auth.service';
import { CategoryService } from '../../../../services/category.service';
import { Category } from '../../../../model/category';
import { FileUploadService } from 'src/app/services/file-upload.service';
import { ProductImages } from '../../../../model/product-images';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  idProduct: number;
  product: Product;
  category!: Category;
  userName: string;
  categories: Category[];
  imageUpload: File; // Imagen a subir
  imagen: ProductImages;
  imgTemp: any;
  productForm: FormGroup;
  formName!: string;

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private authService: AuthService,
    private categoryService: CategoryService,
    private fileUpload: FileUploadService,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private productService: ProductService
  ) {
    this.product = new Product();
    this.category = new Category();
  }

  ngOnInit(): void {
    /** Si viene el ide en la url */
    this.createForm();
    this.idProduct = this.activateRoute.snapshot.params['id'];
    if (this.idProduct) {
      this.formName = 'Edición de producto';
      this.productService.getProductById(this.idProduct).subscribe((res) => {
        this.product = res;
        this.productForm.patchValue(this.product, {
          emitEvent: false,
          onlySelf: false,
        });
      });
    } else {
      this.formName = 'Nuevo producto';
    }

    this.userName = this.authService.getUserName();
    this.categoryService
      .getCategories()
      .subscribe((res) => (this.categories = res));
  }

  onFormChanges() {
    this.productForm.valueChanges.subscribe((val) => {
      console.log(val);
    });
  }

  setCategory(category) {
    console.log(category);
  }

  /**
   * Creacion del formulario
   */
  createForm() {
    this.productForm = this.fb.group({
      id: [this.product.id],
      name: [this.product.name, Validators.required],
      price: [this.product.price, Validators.required],
      discount: [this.product.discount],
      category: [this.product.category, Validators.required],
      bulk: [this.product.bulk],
      description: [this.product.description],
      shop: [this.product.shop],
    });
  }

  /**
   * Guardar producto
   */
  save() {
    /** Cambio de bult to integer */
    this.productForm.controls['bulk'].value
      ? this.productForm.get('bulk').setValue(1)
      : this.productForm.get('bulk').setValue(0);

    if (this.product.id) {
      this.productService
        .editProduct(this.productForm.value)
        .subscribe((res) => {
          if (this.imgTemp) {
            console.log('Tiene imagen');
            this.uploadImage(this.product.id);
          }
          console.log('No tiene imagen');
        });
    } else {
      /** Send product to backend */
      console.log('aqui estamos')
      this.shopService
        .createProduct(this.productForm.value)
        .subscribe((res) => {
          this.uploadImage(res.id);
          this.productForm.reset();
        });
    }
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

    /** Si tenemos imagen la mostramos
     */

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      this.imgTemp = reader.result;
    };
  }

  /**
   * Subida de imagen
   * @param productId Subida de imagen
   */
  uploadImage(productId: number) {
    this.fileUpload
      .uploadImage(this.imageUpload, productId, 'products')
      .subscribe();
  }

  cancel() {
    this.router.navigate(['/vendors/shop']);
  }
}
