import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { RouterLink, Router } from '@angular/router';

import { CreateEventRequest } from '../../../shared/models/event.model';
import { EventService } from '../../../shared/services/event.service';

@Component({
  selector: 'app-create-event',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './create-event.component.html',
  styleUrl: './create-event.component.css',
})
export class CreateEventComponent {
  isSubmitting = false;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private eventService: EventService,
    private router: Router
  ) {
    this.form = this.formBuilder.group(
      {
        title: ['', [Validators.required]],
        description: ['', [Validators.required]],
        location: ['', [Validators.required]],
        startAt: ['', [Validators.required]],
        endAt: ['', [Validators.required]],
        maxParticipants: [null as number | null, [Validators.required, Validators.min(1)]],
      },
      { validators: [this.endAfterStartValidator] }
    );
  }

  onSubmit(): void {
    this.successMessage = null;
    this.errorMessage = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const raw = this.form.getRawValue();

    const payload: CreateEventRequest = {
      title: (raw.title ?? '').trim(),
      description: (raw.description ?? '').trim(),
      location: (raw.location ?? '').trim(),
      startAt: this.toBackendLocalDateTime(raw.startAt ?? ''),
      endAt: this.toBackendLocalDateTime(raw.endAt ?? ''),
      maxParticipants: Number(raw.maxParticipants ?? 0),
    };

    this.isSubmitting = true;

    this.eventService.create(payload).subscribe({
      next: (resp) => {
        this.isSubmitting = false;
        this.successMessage = 'Event created.';
        this.form.reset();
        setTimeout(() => {
          this.router.navigate(['/events']);
        }, 2000);
      },
      error: (err) => {
        this.isSubmitting = false;
        this.errorMessage = err?.error?.message ?? 'Failed to create event.';
      },
    });
  }

  isInvalid(controlName: string): boolean {
    const control = this.form.get(controlName);
    return !!control && control.invalid && (control.touched || control.dirty);
  }

  showEndAfterStartError(): boolean {
    const startTouched = this.form.get('startAt')?.touched ?? false;
    const endTouched = this.form.get('endAt')?.touched ?? false;
    return !!this.form.errors?.['endBeforeStart'] && (startTouched || endTouched);
  }

  fieldError(controlName: string): string | null {
    const control = this.form.get(controlName);
    if (!control || !this.isInvalid(controlName) || !control.errors) {
      return null;
    }

    if (control.errors['required']) {
      return this.requiredMessage(controlName);
    }

    if (controlName === 'maxParticipants' && control.errors['min']) {
      return 'Maximum participants must be greater than 0.';
    }

    return 'Invalid value.';
  }

  private requiredMessage(controlName: string): string {
    switch (controlName) {
      case 'title':
        return 'Title is required.';
      case 'description':
        return 'Description is required.';
      case 'location':
        return 'Location is required.';
      case 'startAt':
        return 'Start date/time is required.';
      case 'endAt':
        return 'End date/time is required.';
      case 'maxParticipants':
        return 'Maximum participants is required.';
      default:
        return 'This field is required.';
    }
  }

  private toBackendLocalDateTime(value: string): string {
    // input[type="datetime-local"] typically returns: YYYY-MM-DDTHH:mm
    // Backend expects: YYYY-MM-DDTHH:mm:ss
    if (/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}$/.test(value)) {
      return `${value}:00`;
    }
    return value;
  }

  private endAfterStartValidator(control: AbstractControl): ValidationErrors | null {
    const startAt = control.get('startAt')?.value as string | null;
    const endAt = control.get('endAt')?.value as string | null;

    if (!startAt || !endAt) {
      return null;
    }

    const startMs = Date.parse(startAt);
    const endMs = Date.parse(endAt);

    if (Number.isNaN(startMs) || Number.isNaN(endMs)) {
      return null;
    }

    return endMs > startMs ? null : { endBeforeStart: true };
  }
}
