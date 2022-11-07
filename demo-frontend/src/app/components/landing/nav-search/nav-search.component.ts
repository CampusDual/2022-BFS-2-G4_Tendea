import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Product } from '../../../model/product';
import { ProductService } from 'src/app/services/product.service';
import { MatPaginator } from '@angular/material/paginator';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-nav-search',
  templateUrl: './nav-search.component.html',
  styleUrls: ['./nav-search.component.scss'],
})
export class NavSearchComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;

  products: Product[] = [];
  termino: string = '';

  pageIndex: number = 0;
  pageSize: number = 8;
  productSelected: Product;

  @Output() sProducts: Product[] = [];
  @Output() product: Product;

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {}

  /** Buscando un producto */
  buscando() {
    this.productService
      .getProductByName(this.termino)
      .subscribe((res) => ((this.products = res), (this.sProducts = res)));

    console.log(this.products);
  }

  /** Al seleccionar un producto */
  opcionSeleccionada(event: MatAutocompleteSelectedEvent) {
    const product: Product = event.option.value;
    this.productSelected = product;
    this.termino = product.name;
    if (!product) {
      this.productSelected = undefined;
      return;
    }

    this.productService
      .getProductById(this.productSelected.id)
      .subscribe(
        (res) => (
          (this.product = res),
          this.router.navigate(['/producto', this.product.id])
        )
      );
  }
}
