import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { API_CONFIG } from '../shared/api.config';
import { CreateUserRequest } from '../model/rest/request';
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
    this._snackBar.open(`${message}`, 'Error', {
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

    console.log(registerUser);
    return this.http.post<User>(url, registerUser, { headers }).pipe(
      catchError((e) => {
        if (e.error.errors!.includes('email')) {
          this.showMessageError('Este email ya esta registrado');
        }
        if (e.error.errors!.includes('login')) {
          this.showMessageError('Este nombre de usuario ya esta registrado');
        }
        return throwError(() => e);
      })
    );
  }
}
