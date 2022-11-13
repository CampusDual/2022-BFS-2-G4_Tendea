import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth/auth.service';
import { catchError, throwError, Observable } from 'rxjs';
import { Buffer } from 'buffer';
import { API_CONFIG } from '../shared/api.config';

@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  constructor(private http: HttpClient, private authServices: AuthService) {}

  /**
   * Actualizar Imagen
   */
  public uploadImage(id: any, img: File): Observable<any> {
    const url = API_CONFIG.uploadProductImage;
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
    console.log(id);
    formData.append('file', img);
    formData.append('id', id);

    return this.http.post<any>(url, formData, { headers }).pipe(
      catchError((e) => {
        return throwError(() => e);
      })
    );
  }
}
