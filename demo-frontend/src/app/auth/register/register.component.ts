import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup = this.fb.group({
    login : [, [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    name : [, [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    surname1 : [, [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    surname2 : [, [Validators.required, Validators.minLength(2), Validators.maxLength(24) ]],
    email : [, [Validators.required, Validators.email ]],
    password : ['', {validators : [Validators.required, Validators.minLength(8), Validators.pattern("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,24}$") ], updateOn : 'blur'}]
  });

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  
  }

  save() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }
    console.log("Enviado");
  }

  validField(campo : string) {
    return (
      this.registerForm.controls[campo].errors && this.registerForm.controls[campo].touched
    )
  }

}
