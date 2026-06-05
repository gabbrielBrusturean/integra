import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, ReplaySubject } from 'rxjs';
import { Event, CreateEventRequest, CreateEventResponse } from '../models/event.model';
import { RegisteredVolunteerDto } from '../models/volunteer.model';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { EventColumn } from '../models/event.model';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private baseUrl = 'http://localhost:8080/api/events';
  private registrationUrl = 'http://localhost:8080/api/registrations';
  private eventSubject = new ReplaySubject<Event[]>(1);

  readonly EVENT_COLUMNS: EventColumn[] = [
    { key: 'title', label: 'Event Title', type: 'string' },
    { key: 'category', label: 'Category', type: 'string' },
    { key: 'location', label: 'Location', type: 'string' },
    { key: 'startAt', label: 'Start Date', type: 'datetime' },
    { key: 'endAt', label: 'End Date', type: 'datetime' },
    { key: 'maxParticipants', label: 'Capacity', type: 'number' },
    { key: 'isFull', label: 'Status', type: 'boolean' }
  ];

  constructor(private http: HttpClient) {}

  getEvents(): Observable<Event[]> {
    this.http.get<Event[]>(this.baseUrl).subscribe({
      next: (data) => this.eventSubject.next(data),
      error: (err) => console.error('Service error:', err),
    });

    return this.eventSubject.asObservable();
  }

  getEventById(id: number): Observable<Event> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Event>(url);
  }

  getVolunteers(eventId: number, search: string = ''): Observable<RegisteredVolunteerDto[]> {
    const url = `${this.baseUrl}/${eventId}/volunteers`;

    let params = new HttpParams();
    if (search) {
      params = params.set('search', search);
    }

    return this.http.get<RegisteredVolunteerDto[]>(url, { params });
  }

  create(payload: any): Observable<any> {
    return this.http.post(this.baseUrl, payload);
  }

  registerForEvent(eventId: number, userId: number): Observable<any> {
    const payload = {
      eventId: eventId,
      userId: userId,
      status: 'PENDING',
    };

    return this.http.post(this.registrationUrl, payload);
  }

  updateEvent(eventId: number, payload: CreateEventRequest): Observable<Event> {
    const url = `${this.baseUrl}/${eventId}`;
    return this.http.put<Event>(url, payload);
  }
}
