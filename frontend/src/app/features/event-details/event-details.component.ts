import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Event } from '../../shared/models/event.model';

@Component({
  selector: 'app-event-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
})
export class EventDetailsComponent implements OnInit {
  event?: Event;

  private mockDatabase: Event[] = [
    {
      id: 1,
      title: 'Park Cleanup & Tree Care',
      description:
        'Join our award-winning community initiative to revitalize Central Park! We will be planting new saplings and clearing pathways.',
      location: 'Central Park Entrance, Cluj-Napoca',
      startAt: '2026-05-20T10:00:00',
      endAt: '2026-05-20T14:00:00',
      createdAt: '2026-05-01T12:00:00',
      maxParticipants: 30,
      category: 'Environment',
      registrationDeadline: '2026-05-18T23:59:00',
      isFull: false, 
    },
    {
      id: 2,
      title: 'Code for Charity: Frontend Workshop',
      description:
        'A hands-on session where we help local NGOs build landing pages. Bring your laptop and a passion for social impact!',
      location: 'The Tech Hub, 2nd Floor, Cluj-Napoca',
      startAt: '2026-06-15T18:00:00',
      endAt: '2026-06-15T21:00:00',
      createdAt: '2026-05-05T09:00:00',
      maxParticipants: 15,
      category: 'Education',
      registrationDeadline: '2026-06-10T23:59:00',
      isFull: true, 
    },
  ];

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const eventId = Number(this.route.snapshot.paramMap.get('id'));

    this.event = this.mockDatabase.find((e) => e.id === eventId);
  }

  
  onRegister(): void {
    if (this.event && !this.event.isFull) {
      alert(`Successfully registered for: ${this.event.title}`);
    } else {
      console.warn('Registration attempt blocked: Event is full.');
    }
  }
}
