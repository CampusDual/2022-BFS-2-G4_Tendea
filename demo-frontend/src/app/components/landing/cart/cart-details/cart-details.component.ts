import { CloseScrollStrategy } from '@angular/cdk/overlay';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Product } from '../../../../model/product';
import { ShoppingCartService } from '../../../../services/shopping-cart.service';
import { ShoppingCartItem } from '../../../../model/shopping-cart-item';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.scss'],
})
export class CartDetailsComponent implements OnInit {
  @Input() item: ShoppingCartItem;

  @Output() productDelete: EventEmitter<Number>;

  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {}

  /**
   * Elimina un producto del carrtio de compra
   * @param item ShoppingCartItem
   */
  deleteProduct(item: ShoppingCartItem) {
    this.shoppingCartService.deleteCartItem(item);
  }
}
