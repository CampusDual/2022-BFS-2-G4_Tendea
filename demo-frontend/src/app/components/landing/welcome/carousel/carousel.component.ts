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
        bottom: 12rem;
      }
      h2 {
        color: #00af91;
      }
      p {
        color: white;
      }
    `,
  ],
})
export class CarouselComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
