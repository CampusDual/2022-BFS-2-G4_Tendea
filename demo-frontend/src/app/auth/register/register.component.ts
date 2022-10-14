import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserServicesService } from '../../services/user-services.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  user: User;

  registerForm: FormGroup = this.fb.group({
    login: [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(24)],
    ],
    name: [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(24)],
    ],
    surname1: [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(24)],
    ],
    surname2: [
      '',
      [Validators.required, Validators.minLength(2), Validators.maxLength(24)],
    ],
    email: ['', [Validators.required, Validators.email]],
    password: [
      '',
      {
        validators: [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,24}$'),
        ],
        updateOn: 'blur',
      },
    ],
  });

  constructor(
    private fb: FormBuilder,
    private userService: UserServicesService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  /**
   * Registro de nuevo usuario con rol de cliente
   * @returns User: User
   */
  save() {
    const newUser: User = Object.assign({}, this.registerForm.value);

    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    /** Le agregamos el rol al nuevo objeto que generamos */
    let profiles = ['2'];
    let nUser = { profiles, ...newUser };
    this.userService.registerUser(nUser).subscribe((res) => {
      console.log(res);
      this.router.navigate(['./']);
    });
  }

  validField(campo: string) {
    return (
      this.registerForm.controls[campo].errors &&
      this.registerForm.controls[campo].touched
    );
  }
}
