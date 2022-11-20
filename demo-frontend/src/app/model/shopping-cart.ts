import { User } from './user';
import { Product } from './product';
import { ShoppingCartItem } from './shopping-cart-item';

/**
 * Model from a ShoppingCart
 */

export class ShoppingCart {
  id?: number;
  comment: string;
  user: User;
  items: ShoppingCartItem[] = [];
  total?: number = 0;
  createdAt?: string;
  updatedAt?: string;
}
