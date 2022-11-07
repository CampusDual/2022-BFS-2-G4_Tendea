import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { Shop } from 'src/app/model/shop';
import { CategoryService } from 'src/app/services/category.service';
import { ShopService } from 'src/app/services/shop.service';
import { Location } from '@angular/common'
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-create-shops',
  templateUrl: './create-shops.component.html',
  styleUrls: ['./create-shops.component.scss']
})
export class CreateShopsComponent implements OnInit {

  shop: Shop;
  id: number;
  shopName: string;
  categories: Category[];
  user: User;
  shopForm: FormGroup;

  // shopForm = this.fb.group({

  //   shopName: [
  //     '', [Validators.required]
  //   ],
  //   category: [
  //     '', [Validators.required]
  //   ],

  //   userLogin: ['',
  //   [Validators.required, Validators.minLength(2), Validators.maxLength(24)],  ],
  //   userName: [
  //     '',
  //     [Validators.required, Validators.minLength(2), Validators.maxLength(24)],
  //   ],
  //   userSurname1: [
  //     '',
  //     [Validators.required, Validators.minLength(2), Validators.maxLength(24)],
  //   ],
  //   userSurname2: [
  //     '', [],
  //   ],
  //   userEmail: ['', [Validators.required, Validators.email]],
  //   userPassword: [
  //     '',
  //     {
  //       validators: [
  //         Validators.required,
  //         Validators.minLength(8),
  //         Validators.pattern('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,24}$'),
  //       ],
  //       updateOn: 'blur',
  //     },
  //   ],


  // });

  constructor(
    private fb: FormBuilder,
    private shopService: ShopService,
    private categoryService: CategoryService,
    private router: Router,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      res => this.categories = res
    );

    this.createFormGroup();

  }

  onFormChanges() {
    this.shopForm.valueChanges.subscribe((val) => {});
  }

  createFormGroup() {
    this.shopForm = this.fb.group({
      id: [this.shop.id],
      category: [this.shop.categories, Validators.required],
      shopName: [this.shop.name, Validators.required],
      userLogin: [this.user.login, Validators.required],
      userName: [this.user.name, Validators.required],
      userSurname1: [this.user.surname1, Validators.required],
      userSurname2: [this.user.surname2],
      userEmail: [this.user.email, Validators.required],
      userPassword: [this.user.password, Validators.required]
    });
  }

  save() {
    const newShop: Shop = Object.assign({}, this.shopForm.value );
    this.shopService.createShop(newShop).subscribe(
      response => {this.redirectList(response)}
    );
  }

  redirectList(response: any) {
    if (response.responseCode === 'OK') {
      this.router.navigate(['/shops']);
    }else{
      console.log(response);
    }
  }

  back(): void {
    this.location.back();
  }




}
