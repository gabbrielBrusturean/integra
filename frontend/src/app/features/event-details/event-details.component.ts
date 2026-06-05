import { Component, OnInit, ChangeDetectorRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
} from '@angular/forms';
import { BehaviorSubject, debounceTime, distinctUntilChanged, switchMap, startWith } from 'rxjs';
import { map } from 'rxjs';
import { Event, CreateEventRequest } from '../../shared/models/event.model';
import { RegisteredVolunteerDto } from '../../shared/models/volunteer.model';
import { EventService } from '../../shared/services/event.service';

@Component({
  selector: 'app-event-details',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
})
export class EventDetailsComponent implements OnInit {
  event?: Event;
  volunteers: RegisteredVolunteerDto[] = [];
  private searchSubject = new BehaviorSubject<string>('');

  isEditMode = false;
  isSaving = false;
  errorMessage = '';
  editForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private cdr: ChangeDetectorRef,
    private fb: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.initForm();
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.eventService.getEventById(id).subscribe({
      next: (data) => {
        this.event = data;
        this.editForm.patchValue(this.event);
      },
      error: (err) => console.error('Error fetching event details:', err),
    });

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

  initForm(): void {
    this.editForm = this.fb.group(
      {
        title: ['', Validators.required],
        description: ['', Validators.required],
        location: ['', Validators.required],
        startAt: ['', Validators.required],
        endAt: ['', Validators.required],
        maxParticipants: [1, [Validators.required, Validators.min(1)]],
      },
      { validators: this.dateValidator },
    );
  }

  dateValidator(group: AbstractControl): { [key: string]: boolean } | null {
    const start = group.get('startAt')?.value;
    const end = group.get('endAt')?.value;
    if (start && end && new Date(end) <= new Date(start)) {
      return { dateMismatch: true };
    }
    return null;
  }

  toggleEditMode(): void {
    this.isEditMode = true;
    this.errorMessage = '';
    if (this.event) this.editForm.patchValue(this.event);
  }

  cancelEdit(): void {
    this.isEditMode = false;
    this.errorMessage = '';
    if (this.event) this.editForm.patchValue(this.event);
  }

  saveEvent(): void {
    if (this.editForm.invalid || !this.event) {
      this.errorMessage = 'Please check the form for errors.';
      return;
    }

    this.isSaving = true;
    this.errorMessage = '';

    const updatePayload: CreateEventRequest = this.editForm.value;

    this.eventService.updateEvent(this.event.id, updatePayload).subscribe({
      next: (updatedEvent) => {
        this.event = updatedEvent;
        this.isEditMode = false;
        this.isSaving = false;
        this.cdr.detectChanges();
        setTimeout(() => {
          alert('Event updated successfully.');
        }, 10);
      },
      error: (err) => {
        console.error('Update failed:', err);
        this.errorMessage = 'Could not update event. Please check the form and try again.';
        this.isSaving = false;
        this.cdr.detectChanges();
      },
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
    /* ======================================================================
    COMMENTED OUT FOR GIT COMMIT (INCOMPLETE FEATURE)
    ======================================================================
    const dummyUserId = 1;

    this.eventService.registerForEvent(this.event.id, dummyUserId).subscribe({
      next: (response) => {
        alert('You have successfully registered!');

        if (!this.event) return;
        this.eventService.getVolunteers(this.event.id, this.searchSubject.value).subscribe({
          next: (freshData) => {
            this.volunteers = freshData;
            this.cdr.detectChanges();
          },
        });
      },
      error: (err) => {
        console.error('Registration failed', err);
        alert('Something went wrong. You might already be registered!');
      },
    });
    ======================================================================
    */
  }
}
