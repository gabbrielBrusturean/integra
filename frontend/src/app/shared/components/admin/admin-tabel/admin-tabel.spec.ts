import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTabel } from './admin-tabel';

describe('AdminTabel', () => {
  let component: AdminTabel;
  let fixture: ComponentFixture<AdminTabel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminTabel]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTabel);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
