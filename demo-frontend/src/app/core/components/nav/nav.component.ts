import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { LoggerService } from 'src/app/services/logger.service';
import { SidenavService } from 'src/app/services/sidenav.service';
import { AuthGuard } from 'src/app/auth/auth.guard';
import { Router } from '@angular/router';
import { ThisReceiver } from '@angular/compiler';

interface ROUTE {
  icon?: string;
  route?: string;
  title?: string;
  allowedRoles?: Array<string>;
}

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent implements OnInit, OnDestroy {
  userName?: string = '';
  @ViewChild('commandbarSidenav') public sidenav: MatSidenav;

  sidenavRoutes: ROUTE[] = [
    {
      icon: 'home',
      route: '/home',
      title: 'menu.home',
      allowedRoles: ['CLIENTS'],
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
      allowedRoles: ['SHOPS'],
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
    private router: Router
  ) {}

  ngOnInit() {
    //this.userName = this.authService.getUserName();
    this.logger.info('NavComponent: ngOnInit()');
    this.commandBarSidenavService.setSidenav(this.sidenav);
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
    }
    return allowedRoutes;
  }
}
