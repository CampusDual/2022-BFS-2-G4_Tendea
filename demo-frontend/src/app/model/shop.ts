import { Category } from "./category";
import { Product } from "./product";
import { User } from "./user";

export class Shop {
    id: number;
    name: string;
    description: string;
    categories: Category[];
    products: Product[];
    address: string;
    city: string;
    phone: string;
    email: string;
    user: User;
    activeStatus: number;


}
