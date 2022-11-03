import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateShopsComponent } from './create-shops.component';

describe('CreateShopsComponent', () => {
  let component: CreateShopsComponent;
  let fixture: ComponentFixture<CreateShopsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateShopsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateShopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
