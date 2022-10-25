import { Category } from "./category";

export class Product {
  id: number;
  name: string;
  price: number;
  discount: number;
  category: Category;
  soldOnBulk: boolean;
  createAt: string;
  updateAt: string;
  images: string[];
}
