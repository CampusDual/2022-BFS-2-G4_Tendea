import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { User } from './user';
import { UserServicesService } from '../../services/user-services.service';

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
  ) {
    this.user = new User();
  }

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
    let profile = 2;
    let nUser = { profile, ...newUser };
    this.userService.registerUser(newUser).subscribe((res) => {
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
