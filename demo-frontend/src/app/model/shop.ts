import { Category } from "./category";
import { Product } from "./product";
import { ShopImages } from "./shop-images";
import { User } from "./user";

export class Shop {
    id: number;
    name: string;
    description: string;
    categories: Category[];
    products: Product[];
    images: ShopImages[];
    address: string;
    city: string;
    phone: string;
    email: string;
    user: User;
    activeStatus: number;


}
