import { NgClass } from '@angular/common';
import { Component, input } from '@angular/core';

@Component({
  selector: 'app-admin-tabel',
  imports: [NgClass],
  templateUrl: './admin-tabel.html',
  styleUrl: './admin-tabel.css',
})
export class AdminTabel {
  data = input<any[]>([]);
  columns = input<any[]>([]);
}
