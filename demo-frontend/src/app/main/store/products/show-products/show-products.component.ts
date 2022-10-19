import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-show-products',
  templateUrl: './show-products.component.html',
  styles: [
  ]
})
export class ShowProductsComponent implements OnInit {
  dialog: any;
  // dataSource: ProductsDataSource = [];
  displayedColumns = [
    'select',
    'name',
    'surname1',
    'surname2',
    'phone',
    'email',
  ];
  fields = ['name', 'surname1', 'surname2', 'phone', 'email'];

  constructor(
    private translate: TranslateService,
    private router: Router,
    private productService: ProductService,
  ) {
   }

  ngOnInit(): void {
    //this.dataSource = new ProductsDataSource(this.ProductService);
    this.productService.getProducts().subscribe(res => console.log(res));
  }


  onAdd() {
    this.router.navigate(['/products/add']);
  }

  onDelete() {
    const dialogRef = this.dialog.open();
  }

  loadProductsPage() {

  }

}
