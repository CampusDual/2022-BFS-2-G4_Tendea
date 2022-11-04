import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Product } from '../../../model/product';
import { ProductService } from 'src/app/services/product.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-nav-search',
  templateUrl: './nav-search.component.html',
  styleUrls: ['./nav-search.component.scss'],
})
export class NavSearchComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;

  products: Product[] = [];
  termino: string = '';
  productSelected: Product;
  pageIndex: number = 0;
  pageSize: number = 8;
  @Output() sProducts: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {}

  buscando() {
    this.productService
      .getProductByName(this.termino)
      .subscribe((res) => ((this.products = res), (this.sProducts = res)));
  }

  opcionSeleccionada(event: MatAutocompleteSelectedEvent) {
    const product: Product = event.option.value;
    if (!product) {
      this.productSelected = undefined;
      return;
    }

    if (this.termino.length < 3) {
      this.termino = '';
    }
    this.termino = product.name;
  }
}
