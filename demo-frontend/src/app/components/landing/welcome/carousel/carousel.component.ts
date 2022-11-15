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
        bottom: 24rem;
      }
      h1 {
        color: #00af91;
      }
      h3 {
        color: white;
      }
    `,
  ],
})
export class CarouselComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
