import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup = this.fb.group({
    login : ['', [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    name : ['aaaa', [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    surname1 : ['aaaa', [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    surname2 : ['aaaa', [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    email : ['@gmail.com', [Validators.required, Validators.email ]],
    password : ['Nonames1234', {validators : [Validators.required, Validators.minLength(8), Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,24}$") ], updateOn : 'blur'}]
  });

  constructor(private fb: FormBuilder, private authServices: AuthService) { }

  ngOnInit(): void {
  
  }

  save() {
    const newUser = Object.assign({}, this.registerForm.value);
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }
    console.log("Enviado");
    newUser.profiles.id = 2;
    this.authServices.registerUser(newUser).subscribe((res) => {
      console.log(res);
    })
  }

  validField(campo : string) {
    return (
      this.registerForm.controls[campo].errors && this.registerForm.controls[campo].touched
    )
  }

}
