import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShopService } from 'src/app/services/shop.service';
import { switchMap, debounceTime, distinctUntilChanged, fromEvent, merge, Observable, Observer } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Shop } from '../../model/shop';
import { ProductService } from 'src/app/services/product.service';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Product } from 'src/app/model/product';
import { SelectionModel } from '@angular/cdk/collections';

@Component({
  selector: 'app-shops',
  templateUrl: './shops.component.html',
  styleUrls: ['./shops.component.scss'],
})
export class ShopsComponent implements OnInit {

  shop: Shop;
  product: Product;
  products: Product[];

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

  pageIndex: number = 0;
  pageSize: number = 8;
  length: number;
  pageSizeOptions: number[] = [8, 20, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  @Input() sProducts: Product[];
  @Input() onCategory: Boolean;



  constructor(
    private activateRoute: ActivatedRoute,
    private shopServices: ShopService,
    private productService: ProductService
  ) {}





  ngOnInit(): void {

    this.dataSource = new ProductDataSource(this.productService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      8,
      'name'
    );

    this.activateRoute.params
      .pipe(switchMap(({ id }) => this.shopServices.getShopByIdLanding(id)))
      .subscribe((res) => (this.shop = res));

  }


  ngAfterViewInit(): void {
    // server-side search
    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadProductsPage();
        })
      )
      .subscribe();
      console.log("shopId" + this.shop.id);

    // reset the paginator after sorting
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
      this.selection.clear();
    });

    // on sort or paginate events, load a new page
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => {
          this.loadProductsPage();
        })
      )
      .subscribe();
  }




  loadProductsPage() {
    this.selection.clear();
    this.error = false;
    const pageFilter = new AnyPageFilter(
      this.input.nativeElement.value,
      this.fields.map((field) => new AnyField(field)),
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
    pageFilter.order = [];
    pageFilter.order.push(
      new SortFilter(this.sort.active, this.sort.direction.toString())
    );
    this.dataSource.getProductsByShop(this.shop.id, pageFilter);
  }



  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;

    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.loadProductsPage();

    return event;
  }

}
