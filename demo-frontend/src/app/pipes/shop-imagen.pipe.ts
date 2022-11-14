import { Pipe, PipeTransform } from '@angular/core';
import { Shop } from '../model/shop';

@Pipe({
  name: 'shopImagen',
})
export class ShopImagenPipe implements PipeTransform {
  transform(shop: Shop): string {
    if (shop.images) {
      return `http://localhost:9999/shops/uploads/img/${shop.images[0].url}`;
    } else {
      return 'assets/images/ImgNoAvailable.png';
    }
  }
}