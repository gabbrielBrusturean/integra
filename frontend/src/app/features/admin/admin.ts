import { Component } from '@angular/core';
import { AdminTabel } from '../../shared/components/admin/admin-tabel/admin-tabel';
import { adminMockData } from '../../core/admin/admin.mock-data';


@Component({
  selector: 'app-admin',
  imports: [AdminTabel],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin {
  users = adminMockData.USERS_DATA;
  userColumns = adminMockData.USER_COLUMNS;

  events = adminMockData.EVENTS_DATA;
  eventColumns = adminMockData.EVENT_COLUMNS;
}
