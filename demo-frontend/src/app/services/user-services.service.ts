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

  getUsers(): Observable<User[]> {
    const url = API_CONFIG.getUsers;
    //const body: CreateUserRequest = new CreateUserRequest(registerUser);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });

    return this.http.get<User[]>(url, { headers });
  }

  public getUserByLogin(query: string): Observable<User[]> {
    const url = API_CONFIG.getUsersByLogin;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
    });
    return this.http.get<User[]>(`${url}/${query}`, { headers }).pipe(
      catchError((e) => {
        console.log(e.message);
        if (e.message!.includes('failure')) {
          this.showMessageError(
            'No se encuentra ningun usuario con este nombre'
          );
        }
        return throwError(() => e);
      })
    );
  }

  getToken() {
    const token = localStorage.getItem('token');
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
    if (token === null) {
      console.log('Token es nulo o invalido');
    }
    return httpHeaders;
  }


  
}
