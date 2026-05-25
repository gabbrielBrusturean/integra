import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserRegistrationResponse } from './registration.model';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl = 'http://localhost:8080/api/me/registrations';

  constructor(private http: HttpClient) {}

  getMyRegistrations(): Observable<UserRegistrationResponse[]> {
    return this.http.get<UserRegistrationResponse[]>(this.apiUrl, { withCredentials: true });
  }
}