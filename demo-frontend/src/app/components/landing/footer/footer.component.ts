import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styles: [
    `
      .footer {
        /* margin-top: 2000px; */
        /* position: fixed; */
        left: 0;
        bottom: 0;
        width: 100%;
        /* background-color: red; */
        color: white;
        text-align: center;
      }
    `,
  ],
})
export class FooterComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
