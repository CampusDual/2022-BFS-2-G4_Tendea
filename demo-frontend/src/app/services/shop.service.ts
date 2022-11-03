import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AnyPageFilter } from '../model/rest/filter';
import { DataSourceRESTResponse } from '../model/rest/response';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Shop } from '../model/shop';
import { Buffer } from 'buffer';


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









}
