import { Component, OnInit, Output } from '@angular/core';
import { ShoppingCartService } from '../../../services/shopping-cart.service';
import { Product } from '../../../model/product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  @Output() cartItems: Product[];
  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {
    this.cartItems = this.shoppingCartService.loadCart();
  }


  
}
