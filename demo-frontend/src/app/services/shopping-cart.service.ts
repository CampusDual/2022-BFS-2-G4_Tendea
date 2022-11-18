import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  private items: Product[] = [];
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

  constructor(private _snackBar: MatSnackBar) {
    this.items$ = new Subject();
  }

  /**
   * Carga el carrito del localStorage si existe
   */
  loadCart() {
    const cart = JSON.parse(localStorage.getItem('cart'));
    if (!cart) {
      return [];
    }
    console.log('LoadCart', cart);
    this.items = cart;
    return this.items;
  }

  loadNewCart(): Observable<Product[]> {
    this.items = JSON.parse(localStorage.getItem('cart'));
    console.log('LoadNewart', this.items);
    return this.items$.asObservable();
  }

  /**
   *
   * @param item Agrega un producto al carro
   */
  addProductToCart(item: Product) {
    console.log('Agregue un producto');
    this.items.push(item);
    this.items$.next(this.items);
    localStorage.setItem('cart', JSON.stringify(this.items));
    this.showMessage(`Producto agregado: ${item.name}`);
  }

  deleteProduct(product: Product) {
    const nCart = this.items.filter((p) => p.id !== product.id);
    localStorage.setItem('cart', JSON.stringify(nCart));
    console.log(nCart);
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
