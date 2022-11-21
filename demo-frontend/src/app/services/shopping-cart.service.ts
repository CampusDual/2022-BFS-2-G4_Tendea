import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, Subject, map, catchError, throwError } from 'rxjs';
import { ShoppingCart } from '../model/shopping-cart';
import { ShoppingCartItem } from '../model/shopping-cart-item';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { API_CONFIG } from '../shared/api.config';
import { AuthService } from '../auth/auth.service';
import { environment } from 'src/environments/environment';
import { Buffer } from 'buffer';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  public cart: ShoppingCart;
  public items: ShoppingCartItem;

  private cart$: Subject<ShoppingCart>;
  private items$: Subject<ShoppingCartItem>;

  /**
   * Message notification
   * @param message Notificaciones
   */
  showMessage(message: string) {
    this._snackBar.open(`${message} `, 'CERRAR', {
      duration: 4000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }

  constructor(
    private _snackBar: MatSnackBar,
    private http: HttpClient,
    private authService: AuthService
  ) {
    console.log('construyo el carro');
    this.cart$ = new Subject();
    this.items$ = new Subject();
    this.cart = new ShoppingCart();
    this.cart.items = [];
    this.cart.total = 0;
    console.log('total', this.cart.total);
    this.loadCard();
  }

  loadCard() {
    const lCar = JSON.parse(localStorage.getItem('cart'));
    if (!lCar) {
      this.cart.items = [];
      this.cart.total = 0;
      return;
    }
    this.cart = lCar;
    console.log('Me cargo', this.cart);
  }

  addProductToCart(item: Product) {
    const itemS = {
      quantity: 1,
      product: item,
    };
    this.cart.items.push(itemS);
    localStorage.setItem('cart', JSON.stringify(this.cart));
    console.log((this.cart.total += item.price));
    this.showMessage(`Producto agregado: ${item.name}`);
  }

  grandTotal() {
    this.cart.items.map((i) => {
      this.cart.total += i.product.price;
    });
    console.log(this.cart.total);
  }

  loadCart() {
    this.cart = JSON.parse(localStorage.getItem('cart'));
  }

  /** Elimina un producto del carrito */

  deleteCartItem(item: ShoppingCartItem) {
    const nCart = this.cart.items.filter(
      (i) => i.product.id !== item.product.id
    );
    this.cart.items = nCart;
    localStorage.setItem('cart', JSON.stringify(this.cart));
  }

  /**
   * Vaciar el carrito de la compra
   */
  cleanCart() {
    localStorage.removeItem('cart');
    this.cart.items = [];
    this.cart.total = 0;
    this.showMessage(`La lista de compra se vaacio correctamente`);
  }

  /**
   * Devuelve el historial de carros de un cliente
   * @returns ShoppingCars[]
   */
  getAllMyCars(): Observable<ShoppingCart[]> {
    const url = API_CONFIG.getMyCars;
    const login = this.authService.getUserName();
    //const body: CreateUserRequest = new CreateUserRequest(registerUser);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      Authorization:
        'Basic ' +
        Buffer.from(
          `${environment.clientName}:${environment.clientSecret}`,
          'utf8'
        ).toString('base64'),
    });

    return this.http.get<ShoppingCart[]>(`${url}/${login}`, { headers });
  }
}
