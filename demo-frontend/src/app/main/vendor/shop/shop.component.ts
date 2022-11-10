import { Component, OnInit } from '@angular/core';
import { ProductDataSource } from 'src/app/model/datasource/products.datasource';
import { Product } from 'src/app/model/product';
import { AnyField, AnyPageFilter } from 'src/app/model/rest/filter';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {

  dataSource: ProductDataSource;
  products: Product[];

  displayedColumns: string[] = ['name', 'price'];
  fields = ['name', 'category.name', 'price', 'discount'];

  constructor(
    private productService: ProductService
    ) { }

  description: string = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

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

  }

}
