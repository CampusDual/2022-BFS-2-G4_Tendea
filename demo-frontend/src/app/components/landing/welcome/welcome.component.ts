import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styles: [],
})
export class WelcomeComponent implements OnInit {
  welcome: boolean = true;
  constructor() {}

  ngOnInit(): void {
  }
}
