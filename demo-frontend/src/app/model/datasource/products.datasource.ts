import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from '../product';
import { ProductService } from '../../services/product.service';
import { AnyPageFilter } from '../rest/filter';
import { finalize } from 'rxjs/operators';
import { ShowProductsComponent } from 'src/app/main/store/products/show-products/show-products.component';

/**
 * Adolfo B.
 * DataSurce Paginator Products
 */

export class ProductDataSource extends DataSource<Product> {
  productsSubject = new BehaviorSubject<Product[]>([]);
  loadingSubject = new BehaviorSubject<boolean>(false);
  public loadinig$ = this.loadingSubject.asObservable();
  public totalElements: number;


  products : Product[];

  constructor(private productService: ProductService) {
    super();
  }

  getProducts(pageFilter: AnyPageFilter) {
    this.productsSubject.next([]);
    this.loadingSubject.next(true);
    this.productService
      .getProducts(pageFilter)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((response) => {
        this.totalElements = response.totalElements;
        this.productsSubject.next(response.data);
        this.products = response.data;
      });

  }

  // AÑADIR PAGE FILTER A getProductsByShopIdPag (service)
  // getProducts --> getProductsByShopIdPag
  getProductsByShop(id: number, pageFilter: AnyPageFilter) {
    this.productsSubject.next([]);
    this.loadingSubject.next(true);
    this.productService
      .getProductsByShopIdPag(id, pageFilter)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((response) => {
        this.totalElements = response.totalElements;
        this.productsSubject.next(response.data);
        this.products = response.data;
      });

  }


  connect(): BehaviorSubject<Product[]> {
    return this.productsSubject;
  }

  disconnect(): void {
    this.productsSubject.complete();
    this.loadingSubject.complete();
  }
}
