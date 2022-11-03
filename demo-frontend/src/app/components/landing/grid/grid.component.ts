import { DataSource, SelectionModel } from '@angular/cdk/collections';
import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  AfterViewInit,
  Input,
  Output,
} from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { Product } from 'src/app/model/product';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../../model/category';
import { switchMap } from 'rxjs';
import { CategoryService } from 'src/app/services/category.service';
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

  products: Product[];
  sProducts: Product[] = [];

  result: Product[] = [];

  pageIndex: number = 0;
  pageSize: number = 8;
  length: number;
  pageSizeOptions: number[] = [8, 20, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;
  category: Category;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.category = this.activatedRoute.snapshot.params['id'];
    this.dataSource = new ProductDataSource(this.productService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      8,
      'name'
    );
    this.dataSource.getProducts(pageFilter);

    this.productService
      .getProductsLanding(this.pageIndex, this.pageSize)
      .subscribe((res) => (this.sProducts = res.data));
  }

  ngAfterViewInit(): void {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 8;
    this.productService.getProductsLanding(
      this.paginator.pageIndex,
      this.paginator.pageSize
    );

    // fromEvent(this.input.nativeElement, 'keyup')
    //   .pipe(
    //     debounceTime(150),
    //     distinctUntilChanged(),
    //     tap(() => {
    //       this.paginator.pageIndex = 0;
    //       this.loadProductsPage();
    //     })
    //   )
    //   .subscribe();

    // this.sort.sortChange.subscribe(() => {
    //   this.paginator.pageIndex = 0;
    //   this.selection.clear();
    // });

    // merge(this.sort.sortChange, this.paginator.page)
    //   .pipe(
    //     tap(() => {
    //       this.loadProductsPage();
    //     })
    //   )
    // .subscribe();
  }

  // onPageChange($event) {

  //   this.sProducts = this.products.slice(this.pageEvent.pageIndex*this.pageEvent.pageSize, this.pageEvent.pageIndex*this.pageEvent.pageSize + this.pageEvent.pageSize);
  //   // this.sProducts =  this.products.slice($event.pageIndex*$event.pageSize,
  //   // $event.pageIndex*$event.pageSize + $event.pageSize);
  // }

  // sliceProducts(products, pageEvent) : Product[] {

  //   this.sProducts = products.slice(pageEvent.pageIndex*pageEvent.pageSize, pageEvent.pageIndex*pageEvent.pageSize + pageEvent.pageSize);
  //   return this.sProducts;
  // }

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
    this.dataSource.getProducts(pageFilter);
  }

  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;

    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.paginator.pageSize = this.pageEvent.pageSize;
    this.productService
      .getProductsLanding(this.paginator.pageIndex, this.paginator.pageSize)
      .subscribe((res) => (this.sProducts = res.data));

    return event;
  }

  // loadProducts() {
  //   const pageFilter = new AnyPageFilter(
  //     this.input.nativeElement.value,
  //     this.fields.map((field) => new AnyField(field)),
  //     this.paginator.pageIndex,
  //     this.paginator.pageSize
  //   );

  //   // this.sProducts = this.products.slice(this.paginator.pageIndex*this.paginator.pageSize, this.paginator.pageIndex*this.paginator.pageSize + this.paginator.pageSize)
  //     this.sProducts = this.products.slice(1,8);

  // }

  textTruncate(value: string): string {
    const limit = 31;
    const trail = '...';
    return value.length > limit ? value.substring(0, limit) + trail : value;
  }

  onGetCategory() {
    console.log('hola');
    this.activatedRoute.params
      .pipe(switchMap(({ id }) => this.categoryService.getCategory(id)))
      .subscribe((res) => (this.category = res));

    // this.category = this.activatedRoute.snapshot.params['id'];
    // console.log(this.category);
  }
}
