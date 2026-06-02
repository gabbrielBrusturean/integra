import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTableHeader } from './admin-table-header';

describe('AdminTableHeader', () => {
  let component: AdminTableHeader;
  let fixture: ComponentFixture<AdminTableHeader>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminTableHeader]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTableHeader);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
