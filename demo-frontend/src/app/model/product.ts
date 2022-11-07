import { Category } from './category';
import { ProductImages } from './product-images';

export class Product {
  id: number;
  name: string;
  price: number;
  discount: number;
  category: Category;
  boolBulk: boolean;
  createAt: string;
  updateAt: string;
  images: ProductImages[];
  bulk: number;
}
