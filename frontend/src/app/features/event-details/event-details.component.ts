import { Component, OnInit, ChangeDetectorRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BehaviorSubject, debounceTime, distinctUntilChanged, switchMap, startWith } from 'rxjs';
import { map } from 'rxjs';
import { Event } from '../../shared/models/event.model';
import { RegisteredVolunteerDto } from '../../shared/models/volunteer.model';
import { EventService } from '../../shared/services/event.service';

@Component({
  selector: 'app-event-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
})
export class EventDetailsComponent implements OnInit {
  event?: Event;
  volunteers: RegisteredVolunteerDto[] = [];
  private searchSubject = new BehaviorSubject<string>('');

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.eventService.getEventById(id).subscribe({
      next: (data) => {
        this.event = data;
      },
      error: (err) => console.error('Error fetching event details:', err),
    });
    this.getEventById(id);

    this.searchSubject
      .pipe(
        map((val) => val.trim()),
        debounceTime(500),
        distinctUntilChanged(),
        switchMap((term) => {
          const query = term.length >= 3 ? term : '';
          return this.eventService.getVolunteers(id, query);
        }),
      )
      .subscribe({
        next: (data) => {
          this.volunteers = data;
          console.log('Table updated with:', this.volunteers.length, 'volunteers');

          this.cdr.detectChanges();
        },
        error: (err) => console.error(err),
      });
  }

  getEventById(id: number) {
    this.eventService.getEventById(id).subscribe({
      next: (res: any) => {
        this.event = res;
      },
      error: (err: any) => {
        console.log(err.message);
      },
    });
  }

  onSearch(event: any): void {
    const input = event.target as HTMLInputElement;
    this.searchSubject.next(input.value);
  }

  getRegistrationDeadline(startAt: Date | string): Date {
    const date = new Date(startAt);
    return new Date(date.getTime() - 24 * 60 * 60 * 1000);
  }

  isClosed(): boolean {
    return this.event ? new Date() > this.getRegistrationDeadline(this.event.startAt) : false;
  }

  get isAlreadyRegistered(): boolean {
    const dummyUserEmail = 'andrei.popescu@example.com';
    return this.volunteers.some((volunteer) => volunteer.email === dummyUserEmail);
  }

  onRegister(): void {
    if (!this.event) return;
    if (this.volunteers.length >= this.event.maxParticipants) return;
    alert(`Registration button clicked! Backend integration coming soon`);
  }
}
