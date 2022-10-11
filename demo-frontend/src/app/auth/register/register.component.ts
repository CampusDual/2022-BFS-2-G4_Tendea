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
    password : [, [Validators.required, Validators.pattern('(?=.[a-zA-Z]*) (?=.*[0-9]) [a-zA-Z0-9].{8,24}') ]],
  });

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  
  }

  save() {
    console.log("Enviado");
  }

  validField(campo : string) {
    return (
      this.registerForm.controls[campo].errors && this.registerForm.controls[campo].touched
    )
  }

}
