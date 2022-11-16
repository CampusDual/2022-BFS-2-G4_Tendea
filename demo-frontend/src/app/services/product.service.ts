import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse } from '../model/rest/response';
import { API_CONFIG } from '../shared/api.config';
import { Buffer } from 'buffer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserServicesService } from './user-services.service';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  showMessageError(message: string) {
    this._snackBar.open(`${message}`, 'CERRAR', {
      duration: 4000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }
  constructor(
    private http: HttpClient,
    private _snackBar: MatSnackBar,
    private authService: AuthService
  ) {}

  public getProducts(
    pageFilter: AnyPageFilter
  ): Observable<DataSourceRESTResponse<Product[]>> {
    const url = `${environment.apiBaseUrl}/products/getProducts`;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    return this.http.post<DataSourceRESTResponse<Product[]>>(url, pageFilter, {
      headers,
    });
  }

  public createProduct(product: Product): Observable<any> {
    const url = API_CONFIG.createProduct;
    // const body: CreateProductRequest = new CreateProductRequest(product);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });

    return this.http.post<Product>(url, product, { headers }).pipe(
      catchError((e) => {
        return throwError(() => e);
      })
    );
  }

  /**
   * Productos con paginador personalizado para el landing
   * @param pageNumber Obtener todos los productos para el landing
   * @param pageSize
   * @returns products[]
   */
  public getProductsLanding(pageNumber: number, pageSize: number) {
    // Le enviamos un objeto con esas dos propiedades.
    const pageFilter = { pageNumber, pageSize };
    const url = `${environment.apiBaseUrl}/products/getProducts`;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });

    //!Hay que ver que devuleve esta movida o si va aqui
    return this.http.post<DataSourceRESTResponse<Product[]>>(url, pageFilter, {
      headers,
    });
  }

  /**
   * Eliminar un producto
   * @param id
   * @returns
   */
  public deleteProduct(id: number): Observable<any> {
    const url = API_CONFIG.deleteProduct;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(url, { params, headers });
  }

  /**
   * Subida de imagenes de producto
   * @param product
   * @param img
   * @returns
   */
  public uploadProductImg(product: any, img: File): Observable<any> {
    const url = API_CONFIG.uploadProductImg;
    // const body: CreateProductRequest = new CreateProductRequest(product);
    const headers = new HttpHeaders({
      'Content-type': 'multipart/form-data; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    let formData = new FormData();
    console.log(product);
    formData.append('file', img);
    formData.append('id', product.id);
    return this.http.post<Product>(url, formData, { headers }).pipe(
      catchError((e) => {
        return throwError(() => e);
      })
    );
  }

  /**
   * Listado de productos por categoria
   * @param category id
   * @returns devulve un listado de productos por categoria
   */
  public getProductsByCategory(category: number): Observable<Product[]> {
    const url = API_CONFIG.getProductsByCategory;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });
    //const params = new HttpParams().set('id', category);
    return this.http.get<Product[]>(`${url}/${category}`, { headers });
  }

  /**
   * Busqueda de productos por nombre
   * @param query Listado de productos por el termino de busquedaa
   * @returns product[]
   */
  public getProductByName(query: string): Observable<Product[]> {
    const url = API_CONFIG.getProductsByName;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });
    return this.http.get<Product[]>(`${url}/${query}`, { headers }).pipe(
      catchError((e) => {
        console.log(e.message);
        if (e.message!.includes('failure')) {
          this.showMessageError(
            'No se encuentra ningun producto con este nombre'
          );
        }
        return throwError(() => e);
      })
    );
  }

  /**
   * Obtiene un producto por id
   * @param id
   * @returns
   */
  public getProductById(id: number): Observable<Product> {
    const url = API_CONFIG.getProductById;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    return this.http.get<Product>(`${url}/${id}`, { headers }).pipe(
      catchError((e) => {
        console.log(e.message);
        if (e.message!.includes('failure')) {
          this.showMessageError('No se encuentra ningun producto con este id');
        }
        return throwError(() => e);
      })
    );
  }

  /**
   * Edicion de un producto desde una tienda
   * @returns
   */
  editProduct(product: Product) {
    const login = this.authService.getUserName();
    const url = API_CONFIG.editProduct;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    console.log(product);
    return this.http
      .put<Product>(`${url}/${product.id}/${login}`, product, { headers })
      .pipe(
        catchError((e) => {
          console.log(e);
          return throwError(() => e);
        })
      );
  }

  public getProductsByShopIdPag(
    id: number,
    pageFilter: AnyPageFilter
  ): Observable<DataSourceRESTResponse<Product[]>> {
    const url = API_CONFIG.getProductsByShopIdPag;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    return this.http.post<DataSourceRESTResponse<Product[]>>(
      `${url}/${id}`,
      pageFilter,
      { headers }
    );
  }

  // Eliminar, esta copiado como ejemplo

  // public getProducts(
  //   pageFilter: AnyPageFilter
  // ): Observable<DataSourceRESTResponse<Product[]>> {
  //   const url = `${environment.apiBaseUrl}/products/getProducts`;
  //   const headers = new HttpHeaders({
  //     'Content-type': 'application/json; charset=utf-8',
  //     // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
  //     Authorization:
  //       'Basic ' +
  //       Buffer.from(
  //         `${environment.clientName}:${environment.clientSecret}`,
  //         'utf8'
  //       ).toString('base64'),
  //   });
  //   return this.http.post<DataSourceRESTResponse<Product[]>>(url, pageFilter, {
  //     headers,
  //   });
  // }
}
