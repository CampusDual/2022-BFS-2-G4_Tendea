import { DataSource, SelectionModel } from '@angular/cdk/collections';
import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  AfterViewInit,
  Input,
} from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { Product } from 'src/app/model/product';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { ProductService } from 'src/app/services/product.service';
import { Category } from '../../../model/category';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss'],
})
export class GridComponent implements OnInit, AfterViewInit {
  pageEvent: PageEvent;
  dataSource: ProductDataSource;
  fields = [
    'id',
    'name',
    'price',
    'discount',
    'createAt',
    'updateAt',
    'images',
  ];

  selection = new SelectionModel<Product>(true, []);
  error = false;

  product: Product;
  products: Product[];
  @Input() sProducts: Product[];
  @Input() onCategory: boolean;

  productL: number;

  result: Product[] = [];

  pageIndex: number = 0;
  pageSize: number = 8;
  length: number;
  pageSizeOptions: number[] = [8, 20, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;
  category: Category;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.dataSource = new ProductDataSource(this.productService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      8,
      'name'
    );
    if (!this.onCategory) {
      this.dataSource.getProducts(pageFilter);

      this.productService
        .getProductsLanding(this.pageIndex, this.pageSize)
        .subscribe((res) => (this.sProducts = res.data));
    } else {
      this.dataSource.totalElements = this.sProducts.length;
    }
  }

  ngAfterViewInit(): void {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 8;
    this.productService.getProductsLanding(
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
  }

  loadProductsPage() {
    this.selection.clear();
    this.error = false;
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
    pageFilter.order = [];
    pageFilter.order.push(
      new SortFilter(this.sort?.active, this.sort?.direction.toString())
    );
    if (!this.onCategory) {
      this.dataSource.getProducts(pageFilter);
    } else {
      this.dataSource.totalElements = this.sProducts.length;
    }
  }

  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;

    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.paginator.pageSize = this.pageEvent.pageSize;
    if (!this.onCategory) {
      this.productService
        .getProductsLanding(this.paginator.pageIndex, this.paginator.pageSize)
        .subscribe((res) => (this.sProducts = res.data));
    }

    return event;
  }

  /**
   Corta el titulo del producto a los 31 caracteres
   */
  textTruncate(value: string): string {
    const limit = 31;
    const trail = '...';
    return value.length > limit ? value.substring(0, limit) + trail : value;
  }
}
