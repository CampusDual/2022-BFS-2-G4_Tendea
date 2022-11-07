import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../model/product';

@Pipe({
  name: 'imagen',
})
export class ImagenPipe implements PipeTransform {
  transform(product: Product): string {
    if (product.images.length > 0) {
      return `http://localhost:9999/products/uploads/img/${product.images[0].url}`;
    } else {
      return 'assets/images/ImgNoAvailable.png';
    }
  }
}

//
// http://localhost:9999/products/uploads/img/
