import { CollectionViewer, DataSource } from "@angular/cdk/collections";
import { Shop } from "../shop";
import { BehaviorSubject } from 'rxjs';
import { AnyPageFilter } from "../rest/filter";
import { ShopService } from "src/app/services/shop.service";
import { finalize } from 'rxjs/operators';

export class ShopsDataSource extends DataSource<Shop> {

    shopsSubject = new BehaviorSubject<Shop[]>([]);
    loadingSubject = new BehaviorSubject<boolean>(false);
    public loading$ = this.loadingSubject.asObservable();


    shops: Shop[];
    totalElements: number;

    constructor(private shopService: ShopService) {
        super();
    }


    getShops(pageFilter: AnyPageFilter) {
        this.shopsSubject.next([]);
        this.loadingSubject.next(true);
        this.shopService
          .getShopsPag(pageFilter)
          .pipe(finalize(() => this.loadingSubject.next(false)))
          .subscribe((response) => {
            this.totalElements = response.totalElements;
            this.shopsSubject.next(response.data);
            this.shops = response.data;
          });
    
      }




    connect(): BehaviorSubject<Shop[]> {
        return this.shopsSubject;
    }

    disconnect(): void {
        this.shopsSubject.complete();
        this.loadingSubject.complete();
    }


}