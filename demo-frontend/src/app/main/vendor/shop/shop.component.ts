import { SelectionModel } from '@angular/cdk/collections';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import {
  debounceTime,
  distinctUntilChanged,
  fromEvent,
  merge,
  Observable,
  Observer,
} from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthService } from 'src/app/auth/auth.service';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { ShopsDataSource } from 'src/app/model/datasource/shops.datasource';
import { Product } from 'src/app/model/product';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { Shop } from 'src/app/model/shop';
import { User } from 'src/app/model/user';
import { ProductService } from 'src/app/services/product.service';
import { ShopService } from 'src/app/services/shop.service';
import { UserServicesService } from 'src/app/services/user-services.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss'],
})
export class ShopComponent implements OnInit {
  dataSource: ProductDataSource;
  products: Product[];

  length = 100;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageEvent: PageEvent;

  selection = new SelectionModel<Product>(true, []);
  error = false;
  displayedColumns: string[] = [
    'image',
    'select',
    'name',
    'price',
    'discount',
    'actions',
  ];
  fields = ['name', 'select', 'category.name', 'price', 'discount', 'images.url'];

  shop: Shop;
  shops: Shop[];
  user: User;
  users: User[];
  login: String;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild('input') input: ElementRef;
  @ViewChild(MatSort) sort: MatSort;

  @Input() product: Product;

  constructor(
    private productService: ProductService,
    private authService: AuthService,
    private router: Router,
    private shopService: ShopService,
    private userService: UserServicesService,
    private dialog: MatDialog,
    private translate: TranslateService
  ) {
    this.login = authService.getUserName();
    this.shop = new Shop();
    this.user = new User();
  }

  description: string =
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.';

  ngOnInit(): void {
    this.dataSource = new ProductDataSource(this.productService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      10,
      'name'
    );

    this.dataSource.getProducts(pageFilter);
    this.getUserAndShop(this.login);

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
    this.dataSource.getProducts(pageFilter);
  }

  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;

    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.loadProductsPage();

    return event;
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.productsSubject.value.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.productsSubject.value.forEach((row) =>
          this.selection.select(row)
        );
  }

  getUserAndShop(login) {
    this.userService.getUserByLogin(login).subscribe((res) => {
      this.users = res;
      console.log(this.users[0].login);
      this.user = this.users[0];
      this.shopService.getShopByUserId(this.users[0].id).subscribe((res2) => {
        this.shops = res2;
        console.log(this.shops[0].name);
        this.shop = this.shops[0];
      });
    });

    //Ejecuta el código antes de terminar de llenar users
    //  this.user = this.users[0];
    //  console.log("Usuario: " + this.user.login);

    //  this.shopService.getShopByUserId(this.users[0].id).subscribe(
    //   res => this.shops = res
    //  );

    //  this.shop = this.shops[0];
    //  console.log("Tienda: " + this.shops.values());
  }

  delete() {
    const product = this.selection.selected[0];
    this.selection.deselect(product);
    if (this.selection.selected && this.selection.selected.length === 0) {
      this.productService.deleteProduct(product.id).subscribe((response) => {
        console.log(response)
        if (response.responseCode !== 'OK') {
           this.error = true;
         } else {
          this.loadProductsPage();
         }
      });
    } else {
      this.productService.deleteProduct(product.id).subscribe((response) => {
        console.log(response);
        if (response.responseCode !== 'OK') {
           this.error = true;
        }
        this.delete();
      });
    }
  }

  onDelete() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: this.translate.instant('delete-element-confirmation'),
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.delete();
        return new Observable((observer: Observer<boolean>) =>
          observer.next(true)
        );
      } else {
        return new Observable((observer: Observer<boolean>) =>
          observer.next(false)
        );
      }
    });
  }


}

