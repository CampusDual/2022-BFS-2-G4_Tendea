import { Component, OnInit, Output } from '@angular/core';
import { ShoppingCartService } from '../../../services/shopping-cart.service';
import { Product } from '../../../model/product';
import { ShoppingCart } from '../../../model/shopping-cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  @Output() cartItems: Product[] = this.shoppingCartService.items;
  @Output() shoppingCart: ShoppingCart = this.shoppingCartService.cart;
  
  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {
    console.log('Cart Component');
  }

  /**
   * Limpia el carrito de la compra
   */
  cleanCart() {
    this.shoppingCartService.cleanCart();
    this.cartItems = [];
    console.log('limpie', this.cartItems);
  }
}
