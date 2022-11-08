import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse } from '../model/rest/response';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Shop } from '../model/shop';
import { Buffer } from 'buffer';
import { API_CONFIG } from '../shared/api.config';


@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private http: HttpClient) { }

  public getShopsPag(pageFilter: AnyPageFilter):
   Observable<DataSourceRESTResponse<Shop[]>> {

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
    return this.http.delete<any>(url, {params, headers});
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






}
