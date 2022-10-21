import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse } from '../model/rest/response';
import { API_CONFIG } from '../shared/api.config';
import { Buffer } from 'buffer';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  // TODO: Es necesario hacer un PageFilter, comprobar funcionamiento en contacts.component.ts

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

  /*
public createProduct(product: Product): Observable<any> {
  const url = API_CONFIG.createProduct;
  const body: CreateProductRequest = new CreateProductRequest(product);
  const headers = new HttpHeaders({
    'Content-type': 'application/json; charset=utf-8',
    Authorization:
      'Basic ' +
      Buffer.from(
        `${environment.clientName}:${environment.clientSecret}`,
        'utf8'
      ).toString('base64'),
  });
  return this.http.post<Product>(url, body, { headers }).pipe(
    catchError((e) => {
      return throwError(() => e);
    })
  );
}
*/

}
