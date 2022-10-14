import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { API_CONFIG } from '../shared/api.config';
import { catchError, throwError, Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserServicesService {
  constructor(private http: HttpClient, private _snackBar: MatSnackBar) {}

  /**
   * registerUser mensaje de alerta
   */
  showMessageError(message: string) {
    this._snackBar.open(`${message}`, 'CERRAR', {
      duration: 4000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }

  /**
   *
   * @param registerUser Registro de usuario, nos lo devuleve la peticion al backend
   * @returns user
   */
  registerUser(registerUser): Observable<User> {
    const url = API_CONFIG.createUser;
    //const body: CreateUserRequest = new CreateUserRequest(registerUser);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });

    return this.http.post<User>(url, registerUser, { headers }).pipe(
      catchError((e) => {
        if (e.error.errors!.includes('email')) {
          this.showMessageError('Este email ya está registrado');
        }
        if (e.error.errors!.includes('login')) {
          this.showMessageError('Este nombre de usuario ya está registrado');
        }
        return throwError(() => e);
      })
    );
  }
}
