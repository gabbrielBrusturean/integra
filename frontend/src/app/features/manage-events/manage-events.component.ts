import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EventService } from '../../shared/services/event.service';
import { CompanyEvent } from '../../shared/models/event.model';

@Component({
  selector: 'app-manage-events',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './manage-events.component.html',
  styleUrls: ['./manage-events.component.css']
})
export class ManageEventsComponent implements OnInit {
  events: CompanyEvent[] = [];

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.eventService.getMyEvents().subscribe({
      next: (data) => this.events = data,
      error: (err) => console.error('Eroare la incarcarea evenimentelor companiei', err)
    });
  }

  getEventStatus(event: CompanyEvent): 'Upcoming' | 'Ongoing' | 'Past' {
    const now = new Date().getTime();
    const start = new Date(event.startAt).getTime();
    const end = new Date(event.endAt).getTime();

    if (now < start) return 'Upcoming';
    if (now >= start && now <= end) return 'Ongoing';
    return 'Past';
  }
}