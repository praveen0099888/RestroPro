import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagebillComponent } from './managebill.component';

describe('ManagebillComponent', () => {
  let component: ManagebillComponent;
  let fixture: ComponentFixture<ManagebillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagebillComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagebillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
