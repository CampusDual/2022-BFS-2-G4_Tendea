import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { ProductService } from 'src/app/services/product.service';
import { AnyPageFilter, AnyField, SortFilter } from 'src/app/model/rest/filter';
import { Product } from 'src/app/model/product';
import { SelectionModel } from '@angular/cdk/collections';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { debounceTime, distinctUntilChanged, fromEvent } from 'rxjs';
import { tap } from 'rxjs/operators';

/**
 * Roi / Adolfo B
 * Mostrar los productos paginados en la tabla
 */

@Component({
  selector: 'app-show-products',
  templateUrl: './show-products.component.html',
  styles: [],
})
export class ShowProductsComponent implements OnInit {
  dialog: any;
  dataSource: ProductDataSource;
  selection = new SelectionModel<Product>(true, []);
  error = false;
  highlightedRow: Product;
  displayedColumns = ['id', 'name'];
  fields = ['id', 'name'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  constructor(
    private translate: TranslateService, // Pipe traductor //TODO: Faltan hacer las traducciones de los productos
    private router: Router,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    /** Genera los dastaSources para mostrar la tabla de productos */
    this.dataSource = new ProductDataSource(this.productService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      10,
      'name'
    );
    this.dataSource.getProducts(pageFilter);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  // isAllSelected() {
  //   const numSelected = this.selection.selected.length;
  //   const numRows = this.dataSource.productsSubject.value.length;
  //   return numSelected === numRows;
  // }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.productsSubject.value.forEach((row) =>
          this.selection.select(row)
        );
  }

  /**
   * Redirige al formulario de creacion de producto
   */
  onAdd() {
    this.router.navigate(['/products/add']);
  }

  onDelete() {
    const dialogRef = this.dialog.open();
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

  /**
   * Selecciona un producto para editar
   * @param row Editar un producto
   */
  onEdit(row: Product) {
    this.highlightedRow = row;
  }

  /**
   * Busqueda de productos
   */
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
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.productsSubject.value.length;
    return numSelected === numRows;
  }

  /**
   * Paginator
   */

  length = 100;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  pageEvent: PageEvent;

  setPageSizeOptions(event?: PageEvent) {
    this.pageEvent = event;

    this.paginator.pageIndex = this.pageEvent.pageIndex;
    this.loadProductsPage();

    return event;
  }
}
