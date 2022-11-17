import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  /**
   *
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
    this.loadCart();
  }

  /**
   * Carga el carrito del localStorage si existe
   */
  loadCart() {
    const cart = JSON.parse(localStorage.getItem('cart'));
    this.itemsCart = cart;
    return cart;
  }

  /**
   *
   * @param item Agrega un producto al carro
   */
  addProductToCart(item: Product) {
    this.itemsCart.push(item);
    console.log(this.itemsCart);
    localStorage.setItem('cart', JSON.stringify(this.itemsCart));
    this.showMessage(`Producto agregado: ${item.name}`);
  }

  deleteProduct(product: Product) {
    this.loadCart();
    const cart = this.itemsCart.filter((p) => p.id !== product.id);
    localStorage.setItem('cart', JSON.stringify(cart));
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
