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
  @Output() cart = this.shoppingCartService.cart;
  @Output() items = this.shoppingCartService.items;

  
  total: number = 0;

  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {
    console.log('Cart Component', this.cart);
  }

  /**
   * Limpia el carrito de la compra
   */
  cleanCart() {
    this.shoppingCartService.cleanCart();
  }
}
