import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { ProductService } from 'src/app/services/product.service';
import { AnyPageFilter, AnyField, SortFilter } from 'src/app/model/rest/filter';
import { Product } from 'src/app/model/product';
import { SelectionModel } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

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
      20,
      'name'
    );
    this.dataSource.getProducts(pageFilter);
  }

  /** Whether the number of selected elements matches the total number of rows. */
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

  /**
   * Redirige al formulario de creacion de producto
   */
  onAdd() {
    this.router.navigate(['/products/add']);
  }

  onDelete() {
    const dialogRef = this.dialog.open();
  }

  loadProductsPage() {}

  /**
   * Selecciona un producto para editar
   * @param row Editar un producto
   */
  onEdit(row: Product) {
    this.highlightedRow = row;
  }
}
