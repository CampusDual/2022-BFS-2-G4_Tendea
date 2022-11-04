import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Observable, Observer } from 'rxjs';
import { ShopsDataSource } from 'src/app/model/datasource/shops.datasource';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { Shop } from 'src/app/model/shop';
import { CategoryService } from 'src/app/services/category.service';
import { ShopService } from 'src/app/services/shop.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-show-shops',
  templateUrl: './show-shops.component.html',
  styleUrls: ['./show-shops.component.scss']
})
export class ShowShopsComponent implements OnInit {

  dataSource: ShopsDataSource;
  shops: Shop[]; 
  error = false;

  pageIndex: number = 0;
  pageSize: number = 10;
  length: number;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageEvent: PageEvent;

  // prueba = this.shops[1].categories[1].name
  
  selection = new SelectionModel<Shop>(true,[]);
  highlightedRow: Shop;
  displayedColumns = ['select', 'name', 'categories', 'city', 'user'];
  fields=['id', 'name', 'description', 'categories[0].name', 'products', 'address', 'city',
          'phone', 'email', 'user', 'activeStatus'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  constructor(private shopService: ShopService,
    private translate: TranslateService,
    private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.dataSource = new ShopsDataSource(this.shopService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      10,
      'name'
    );
    this.dataSource.getShops(pageFilter);
  }

  masterToggle() {
    this.isAllSelected()
    ? this.selection.clear()
    : this.dataSource.shopsSubject.value.forEach(
      row => this.selection.select(row));
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.shopsSubject.value.length;
    return numSelected === numRows;
  }

  onEdit(row: Shop) {
    this.highlightedRow = row;
  }

  onAdd() {
    this.router.navigate(['/shops/add']);
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

  delete() {
    const shop = this.selection.selected[0];
    this.selection.deselect(shop);
    if (this.selection.selected && this.selection.selected.length === 0) {
      this.shopService.deleteShop(shop.id).subscribe((response) => {
        console.log(response)
        if (response.responseCode !== 'OK') {
           this.error = true;
         } else {
          this.loadShopsPage();
         }
      });
    } else {
      this.shopService.deleteShop(shop.id).subscribe((response) => {
        console.log(response);
        if (response.responseCode !== 'OK') {
           this.error = true;
        }
        this.delete();
      });
    }
  }

  loadShopsPage() {
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
    this.dataSource.getShops(pageFilter);
  }

  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;

    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.loadShopsPage();

    return event;
  }
  



}
