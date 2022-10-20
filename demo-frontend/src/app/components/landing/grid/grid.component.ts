import { Component, OnInit } from '@angular/core';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { Product } from 'src/app/model/product';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {
  dataSource: ProductDataSource;
  fields = ['name', 'price', 'discount'];

  products: Product[];
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
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

  // loadProductsPage() {
  //   const pageFilter = new AnyPageFilter(
  //     this.input.nativeElement.value,
  //     this.fields.map((field) => new AnyField(field)),
  //     this.paginator.pageIndex,
  //     this.paginator.pageSize
  //   );
  //   pageFilter.order = [];
  //   pageFilter.order.push(
  //     new SortFilter(this.sort.active, this.sort.direction.toString())
  //   );
  //   this.dataSource.getProducts(pageFilter);
  // }

}
