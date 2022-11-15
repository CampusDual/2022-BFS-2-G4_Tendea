import { Category } from './category';
import { ProductImages } from './product-images';
import { Shop } from './shop';

export class Product {
  id?: number;
  name: string;
  price: number;
  discount: number;
  category: Category;
  boolBulk: boolean;
  createAt: string;
  updateAt: string;
  images: ProductImages[];
  description: string;
  bulk: number;
  login?: string;
  shop?: Shop;
}
