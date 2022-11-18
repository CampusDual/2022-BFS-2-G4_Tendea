import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../../../services/product.service';
import { switchMap } from 'rxjs';
import { Product } from '../../../model/product';
import { CategoryService } from '../../../services/category.service';
import { ShoppingCartService } from '../../../services/shopping-cart.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styles: [
    `
      .discounted-price {
        color: #00af91;
      }

      .original-price {
        color: darkgrey;
        text-decoration: line-through;
      }
      .card {
        cursor: pointer;
      }
    `,
  ],
})
export class ProductDetailComponent implements OnInit {
  product: Product;
  relatedProducts: Product[];

  constructor(
    private activateRoute: ActivatedRoute,
    private productService: ProductService,
    private shoppingCartService: ShoppingCartService
  ) {}

  ngOnInit(): void {
    /** Carga el producto que viene en la url */
    this.activateRoute.params
      .pipe(switchMap(({ id }) => this.productService.getProductById(id)))
      .subscribe(
        (res) => (
          (this.product = res),
          /** Muestra los productos relacionados por categoria */
          this.productService
            .getProductsByCategory(this.product.category.id)
            .subscribe((res) => {
              const pr = res.filter((p) => p.id !== this.product.id);
              this.relatedProducts = pr;
            })
        )
      );
  }

  addProductToCart(product: Product) {
    this.shoppingCartService.addProductToCart(product);
  }
}
