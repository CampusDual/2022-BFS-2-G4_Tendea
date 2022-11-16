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
  id: number;

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
  ) { }





  ngOnInit(): void {

    this.dataSource = new ProductDataSource(this.productService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      8,
      'name'
    );

    this.activateRoute.queryParams.subscribe( params => {
      console.log(params);
      })

    this.activateRoute.params
      .pipe(switchMap(({ id }) => this.shopServices.getShopByIdLanding(id)))
      .subscribe((res) => {
        this.shop = res,
        this.id = res.id;
        this.dataSource.getProductsByShop(this.id, pageFilter);
        this.productService
          .getProductsByShopIdPagData(this.id, this.pageIndex, this.pageSize)
          .subscribe((res) => (this.sProducts = res.data));
      });

    

  }


  ngAfterViewInit(): void {
    // server-side search
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 8;
    this.productService.getProductsByShopIdPagData(
      this.id, this.pageIndex, 
      this.pageSize);
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
    console.log("LoadProducts: " + this.paginator.pageIndex);

    // pageFilter.order = [];
    // pageFilter.order.push(
    //   new SortFilter(this.sort.active, this.sort.direction.toString())
    // );


    this.productService.getProductsByShopIdPagData(
      this.id, this.paginator.pageIndex, this.paginator.pageSize)
      .subscribe(res => (this.sProducts = res.data));
    
  }



  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;
    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.paginator.pageSize = this.pageEvent.pageSize;

    this.loadProductsPage();

    return event;
  }

}
