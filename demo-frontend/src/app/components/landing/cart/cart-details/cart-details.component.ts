import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../../../model/product';
import { ShoppingCartService } from '../../../../services/shopping-cart.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.scss'],
})
export class CartDetailsComponent implements OnInit {
  @Input() product: Product;
  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {}

  deleteProductFromCart(product: Product) {
    this.shoppingCartService.deleteProduct(product);
  }
}
