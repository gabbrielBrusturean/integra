import { Component, input } from '@angular/core';

@Component({
  selector: 'app-admin-table-header',
  imports: [],
  templateUrl: './admin-table-header.html',
  styleUrl: './admin-table-header.css',
})
export class AdminTableHeader {
  columns = input<any[]>([]);
}
