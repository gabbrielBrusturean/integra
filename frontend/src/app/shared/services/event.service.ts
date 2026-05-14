import { Injectable } from '@angular/core';
import { Event } from '../models/event.model';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private mockEvents: Event[] = [
    {
      id: 1,
      title: 'Park Cleanup & Tree Care',
      description: 'Join our award-winning community initiative to revitalize Central Park!',
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
      description: 'A hands-on session where we help local NGOs build landing pages.',
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

  getEvents(): Event[] {
    return this.mockEvents;
  }

  getEventById(id: number): Event | undefined {
    return this.mockEvents.find((event) => event.id === id);
  }
}
