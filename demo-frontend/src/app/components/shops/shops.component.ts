import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShopService } from 'src/app/services/shop.service';
import { switchMap } from 'rxjs';
import { Shop } from '../../model/shop';

@Component({
  selector: 'app-shops',
  templateUrl: './shops.component.html',
  styleUrls: ['./shops.component.scss'],
})
export class ShopsComponent implements OnInit {
  shop: Shop;
  constructor(
    private activateRoute: ActivatedRoute,
    private shopServices: ShopService
  ) {}

  ngOnInit(): void {
    this.activateRoute.params
      .pipe(switchMap(({ id }) => this.shopServices.getShopByIdLanding(id)))
      .subscribe((res) => (this.shop = res));
  }
}
