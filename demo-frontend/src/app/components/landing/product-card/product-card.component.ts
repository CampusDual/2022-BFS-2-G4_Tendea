import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../../model/product';
import { ShoppingCartService } from '../../../services/shopping-cart.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss'],
})
export class ProductCardComponent implements OnInit {
  @Input() product: Product;

  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit(): void {}

  addProductToCart(item: Product) {
    this.shoppingCartService.addProductToCart(item);
  }
}
