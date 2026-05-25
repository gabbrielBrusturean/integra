import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RegistrationService } from '../../shared/models/registration.service';
import { UserRegistrationResponse } from '../../shared/models/registration.model';

@Component({
  selector: 'app-my-registrations',
  imports: [CommonModule, RouterLink],
  templateUrl: './my-registrations.html',
  styleUrls: ['./my-registrations.css']
})
export class MyRegistrationsComponent implements OnInit {
  registrations: UserRegistrationResponse[] = [];
  isLoading = true; 

  constructor(private registrationService: RegistrationService) {}

  ngOnInit(): void {
    this.registrationService.getMyRegistrations().subscribe({
      next: (data) => {
        this.registrations = data;
        this.isLoading = false; 
      },
      error: (err) => {
        this.isLoading = false; 
        console.error('Failed to load registrations', err);
      }
    });
  }
}