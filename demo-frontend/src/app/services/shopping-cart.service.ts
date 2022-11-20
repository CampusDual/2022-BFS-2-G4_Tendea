import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, Subject } from 'rxjs';
import { ShoppingCart } from '../model/shopping-cart';
import { ShoppingCartItem } from '../model/shopping-cart-item';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  public cart: ShoppingCart;
  private cart$: Subject<ShoppingCart>;

  public items: Product[] = [];
  public total: number;
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
    this.loadNewCart();
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
    if (!this.items) {
      this.items = [];
    }

    console.log('LoadNewart', this.items);
    return this.items$.asObservable();
  }

  /**
   *
   * @param item Agrega un producto al carro
   */
  addProductToCart(item: Product) {
    console.log('Agregue un producto', item);
    this.items.push(item);
    this.items$.next(this.items);
    localStorage.setItem('cart', JSON.stringify(this.items));
    this.showMessage(`Producto agregado: ${item.name}`);
  }

  calCulateGrandTotal() {}

  deleteProduct(product: Product) {
    const nCart = this.items.filter((p) => p.id !== product.id);
    localStorage.setItem('cart', JSON.stringify(nCart));
    this.items$.next(nCart);
    this.showMessage(`Producto eliminado: ${product.name}`);
  }

  deleteCartItem(item: ShoppingCartItem) {
    const nCart = this.cart.items.filter(
      (item) => item.product.id !== item.product.id
    );
    localStorage.setItem('cart', JSON.stringify(nCart));
    this.cart.items = nCart;
    console.log(this.cart.items);
  }

  /**
   * Limpia el carrito
   */
  cleanCart() {
    localStorage.removeItem('cart');
    this.showMessage(`Se vacio el carrito`);
  }
}
