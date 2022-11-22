import { Component, OnInit, OnDestroy, ViewChild, EventEmitter, Output } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { LoggerService } from 'src/app/services/logger.service';
import { SidenavService } from 'src/app/services/sidenav.service';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { Router } from '@angular/router';
import { ThisReceiver } from '@angular/compiler';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { ShopService } from 'src/app/services/shop.service';
import { Product } from 'src/app/model/product';
import { Shop } from 'src/app/model/shop';
import { MatTreeFlatDataSource, MatTreeFlattener } from '@angular/material/tree';
import { FlatTreeControl } from '@angular/cdk/tree';
import { Category } from 'src/app/model/category';

interface ROUTE {
  icon?: string;
  route?: string;
  title?: string;
  allowedRoles?: Array<string>;
}

interface CategoryFlatNode {
  expandable: boolean;
  name: string;
  level: number;
}

interface CatNode {
  id?: number;
  name: string;
  children?: CatNode[];
}

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent implements OnInit, OnDestroy {

  // Variables búsqueda

  /** Flat node with expandable and level information */

  shops: Shop[];
  products: Product[];
  TREE_DATA;  
  catData: CatNode;
  categories;


  private _transformer = (node: CatNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      id: node.id,
      level: level,
    };
  };

  treeFlattener = new MatTreeFlattener(
    this._transformer,
    (node) => node.level,
    (node) => node.expandable,
    (node) => node.children
  );

  treeControl = new FlatTreeControl<CategoryFlatNode>(
    (node) => node.level,
    (node) => node.expandable
  );

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);


  @Output() category: Category;
  @Output() onGetCategory: EventEmitter<Category> = new EventEmitter();








  //Variables permisos
  userName?: string = '';
  @ViewChild('commandbarSidenav') public sidenav: MatSidenav;

  sidenavRoutes: ROUTE[] = [
    {
      icon: 'home',
      route: '/home',
      title: 'menu.home',
      allowedRoles: ['ADMIN'],
    },
    {
      icon: 'store',
      route: 'vendors/shop',
      title: 'Mi tienda',
      allowedRoles: ['SHOPS'],
    },
    {
      icon: 'people',
      route: 'products',
      title: 'menu.products', // TODO añadir a translate GAL/ENG, decidir nombre
      allowedRoles: ['ADMIN'],
    },
    {
      icon: 'store', //Buscar icono
      route: 'shops',
      title: 'menu.shops', // TODO añadir a translate GAL/ENG, decidir nombre
      allowedRoles: ['ADMIN'],
    },
  ];

  protected subscription: Subscription;

  constructor(
    private commandBarSidenavService: SidenavService,
    private authService: AuthService,
    private logger: LoggerService,
    private authGuard: AuthGuard,
    private router: Router,
    private categoryService: CategoryService,
    private productService: ProductService,
    private shopService: ShopService
  ) {}

  ngOnInit() {
    if(this.authService.isLoggedIn) {
      this.userName = this.authService.getUserName();
    }
    this.logger.info('NavComponent: ngOnInit()');
    this.commandBarSidenavService.setSidenav(this.sidenav);


  }

  ngAfterViewInit() {
            /** Llamda a la bbd ultimas tiendas registradas */
            this.shopService.getLastShop().subscribe((res) => {
              this.shops = res;
            });
        
            /** LLamada a la bd de productos, obtenemos los ultimos 5 */
            this.productService.getProductsLanding(1, 5).subscribe((res) => {
              this.products = res.data;
            });
        
            /** LLamada a la bd de categorias, obtenemos los ultimos 5 */
            this.categoryService.getCategories().subscribe((cat) => {
              const TREE_DATA: CatNode[] = [
                {
                  name: 'Categorias',
                  children: cat,
                },
              ];
              this.dataSource.data = TREE_DATA;
              console.log(this.categories);
            });

  }

  public isAuthenticated() {
    if (
      !this.authService.isLoggedIn() &&
      !(
        this.router.url === `/categoria/${Number}` ||
        this.router.url === '/welcome' ||
        this.router.url === `/producto/${Number}` ||
        this.router.url === `/tienda/${Number}` ||
        this.router.url === '/auth/login' ||
        this.router.url === '/auth/registro' ||
        this.router.url === '/'
      )
    ) {
      /** Si lo activo entro en un bucle infinito */
      //this.authService.redirectLoginSessionExpiration();
    }
    return this.authService.isLoggedIn();
  }

  public ngOnDestroy() {
    this.logger.info('NavComponent: ngOnDestroy()');
  }

  get allowedRoutes() {
    const allowedRoutes: Array<ROUTE> = [];
    if (this.isAuthenticated()) {
      this.sidenavRoutes.forEach((route) => {
        if (this.authGuard.isAllowed(route.allowedRoles)) {
          allowedRoutes.push(route);
        }
      });
      if(!this.authService.getRoles().includes('ADMIN') && (
        this.router.url === '/products' ||
        this.router.url === '/products/add' ||
        this.router.url === '/home' ||
        this.router.url === '/shops' ||
        this.router.url === '/shops/add'
      ) ) {
        this.router.navigate(['/']);
      }

      if( (!this.authService.getRoles().includes('SHOPS')) && (
        this.router.url === '/vendors/shop' ||
        this.router.url === '/vendors/shop' ||
        this.router.url === '/vendors/shop/products/add' ||
        this.router.url.includes('/vendors/shop/products/edit')
      )
        
         ) {
          this.router.navigate(['/']);
         }



    }
    return allowedRoutes;
  }


  // Barra de búsqueda

    /** Obtener productos por categorias */
    getProductForCategory(selected: any) {
      this.category = selected;
      console.log(selected);
      this.onGetCategory.emit(selected);
    }

    hasChild = (_: number, node: CategoryFlatNode) => node.expandable;








}
