import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowShopsComponent } from './show-shops.component';

describe('ShowShopsComponent', () => {
  let component: ShowShopsComponent;
  let fixture: ComponentFixture<ShowShopsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowShopsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowShopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
