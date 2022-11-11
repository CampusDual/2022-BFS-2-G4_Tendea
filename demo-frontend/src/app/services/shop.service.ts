import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse } from '../model/rest/response';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Shop } from '../model/shop';
import { Buffer } from 'buffer';
import { API_CONFIG } from '../shared/api.config';
import { Product } from '../model/product';
import { AuthService } from '../auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class ShopService {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private _snackBar: MatSnackBar
  ) {}

  showMessageError(message: string) {
    this._snackBar.open(`${message}`, 'CERRAR', {
      duration: 4000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }

  public getShopsPag(
    pageFilter: AnyPageFilter
  ): Observable<DataSourceRESTResponse<Shop[]>> {
    const url = `${environment.apiBaseUrl}/shops/getShopsPag`;
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
    return this.http.post<DataSourceRESTResponse<Shop[]>>(url, pageFilter, {
      headers,
    });
  }

  public deleteShop(id: number): Observable<any> {
    const url = API_CONFIG.deleteShop;
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

  public createShop(shop: Shop): Observable<any> {
    const url = API_CONFIG.createShop;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });

    return this.http.post<Shop>(url, shop, { headers }).pipe(
      catchError((e) => {
        return throwError(() => e);
      })
    );
  }

  /**
   * Get last 5 shops
   */
  getLastShop(): Observable<Shop[]> {
    const url = API_CONFIG.getLastShops;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });
    return this.http.get<Shop[]>(url, { headers }).pipe(
      catchError((e) => {
        return throwError(() => console.log(e));
      })
    );
  }

  /**
   * Registro del producto de una tienda
   * @param product Creacion de producto de una tienda
   * @returns
   */
  createProduct(product: Product): Observable<Product> {
    const login = this.authService.getUserName();
    console.log(login);
    const url = API_CONFIG.createShopProduct;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });
    return this.http.post<Product>(url, { product, login }, { headers }).pipe(
      catchError((e) => {
        return throwError(() => e);
      })
    );
  }

  public getShopById(id: number): Observable<Shop> {
    const url = API_CONFIG.getShopById;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });
    return this.http.get<Shop>(`${url}/${id}`, { headers }).pipe(
      catchError((e) => {
        console.log(e.message);
        if (e.message!.includes('failure')) {
          this.showMessageError('No se encuentra ninguna tienda con este id');
        }

        return throwError(() => e);
      })
    );
  }

  public getShopByUserId(query: number): Observable<Shop[]> {
    const url = API_CONFIG.getShopsByUserId;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });
    return this.http.get<Shop[]>(`${url}/${query}`, { headers }).pipe(
      catchError((e) => {
        console.log(e.message);
        if (e.message!.includes('failure')) {
          this.showMessageError(
            'No se encuentra ninguna tienda perteneciente al usuario.'
          );
        }
        return throwError(() => e);
      })
    );
  }


  public uploadShopImg(shop: any, img: File): Observable<any> {
    const url = API_CONFIG.uploadShopImg;
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
    console.log(shop);
    formData.append('file', img);
    formData.append('id', shop.id);

    return this.http.post<Product>(url, formData, { headers }).pipe(
      catchError((e) => {
        return throwError(() => e);
      })
    );
  }












}
