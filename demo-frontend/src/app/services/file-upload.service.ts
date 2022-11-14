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
  public uploadImage(
    file: File,
    id,
    type: 'shops' | 'products'
  ): Observable<any> {
    const url = 'http://localhost:9999/products/upload';
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + this.authServices.getToken(),
    });
    let formData = new FormData();
    formData.append('file', file);
    formData.append('id', id);

    return this.http.post<any>(url, formData, { headers }).pipe(
      catchError((e) => {
        console.log(e.message);
        return throwError(() => e);
      })
    );
  }

  uploadNewImage() {

  }



}
