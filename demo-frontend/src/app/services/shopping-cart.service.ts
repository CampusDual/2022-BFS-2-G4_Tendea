import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  private items$: Subject<Product[]>;

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

  itemsCart: Product[] = [];
  constructor(private _snackBar: MatSnackBar) {
    this.items$ = new Subject();
    this.loadCart();
  }

  /**
   * Carga el carrito del localStorage si existe
   */
  loadCart() {
    const cart = JSON.parse(localStorage.getItem('cart'));
    if (!cart) {
      return [];
    }
    this.itemsCart = cart;
    return cart;
  }

  loadNewCart(): Observable<Product[]> {
    return this.items$.asObservable();
    return JSON.parse(localStorage.getItem('cart'));
  }

  /**
   *
   * @param item Agrega un producto al carro
   */
  addProductToCart(item: Product) {
    this.itemsCart.push(item);
    localStorage.setItem('cart', JSON.stringify(this.itemsCart));
    this.showMessage(`Producto agregado: ${item.name}`);
  }

  deleteProduct(product: Product) {
    const nCart = this.itemsCart.filter((p) => p.id !== product.id);
    localStorage.setItem('cart', JSON.stringify(nCart));
    //this.loadCart();
    this.items$.next(nCart);
    this.showMessage(`Producto eliminado: ${product.name}`);
  }

  /**
   * Limpia el carrito
   */
  cleanCart() {
    localStorage.removeItem('cart');
    this.showMessage(`Se vacio el carrito`);
  }
}
