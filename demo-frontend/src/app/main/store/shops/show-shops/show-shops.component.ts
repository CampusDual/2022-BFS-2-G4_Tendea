import { Component, OnInit } from '@angular/core';
import { ShopsDataSource } from 'src/app/model/datasource/shops.datasource';
import { AnyField, AnyPageFilter } from 'src/app/model/rest/filter';
import { Shop } from 'src/app/model/shop';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-show-shops',
  templateUrl: './show-shops.component.html',
  styleUrls: ['./show-shops.component.scss']
})
export class ShowShopsComponent implements OnInit {

  dataSource: ShopsDataSource;
  shops: Shop[];

  fields=['id', 'name', 'description', 'categories', 'products', 'address', 'city',
          'phone', 'email', 'user', 'activeStatus']

  constructor(private shopService: ShopService) { }

  ngOnInit(): void {
    this.dataSource = new ShopsDataSource(this.shopService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      10,
      'name'
    );
    this.dataSource.getShops(pageFilter);
  }

}
