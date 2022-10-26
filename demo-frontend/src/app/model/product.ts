import { Category } from "./category";

export class Product {
  id: number;
  name: string;
  price: number;
  discount: number;
  category: Category;
  boolBulk: boolean;
  createAt: string;
  updateAt: string;
  images: string[];
  bulk: number;
}
