import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styles: [
    `
      .carousel-innerimg {
        width: 960px;
        max-height: 625px;
      }
      .carousel-caption {
        bottom: 18rem;
      }
      h1 {
        color: #00af91;
      }
      h3 {
        color: white;
      }
      #entrance-button{
        margin-right: 1vw;
        margin-up: 20vw;
        color: white;
        font-size: 25px;
        line-height: 18px;
        height: 40px;
        width: 300px;
    }
    `,
  ],
})
export class CarouselComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
