import { Component, inject, signal } from '@angular/core';
import { AdminTabel } from '../../shared/components/admin/admin-tabel/admin-tabel';
import { adminMockData } from '../../core/admin/admin.mock-data';
import { EventService } from '../../shared/services/event.service';
import { UserModel } from '../../shared/models/user-model';
import { UserService } from '../../shared/services/user.service';
import { EventModel } from '../../shared/models/event-model';


@Component({
  selector: 'app-admin',
  imports: [AdminTabel],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin {
  userService = inject(UserService);
  EventService = inject(EventService);

  users = signal<UserModel[]>([]);
  userColumns = this.userService.USER_COLUMNS;

  events = signal<EventModel[]>([]);
  eventColumns = this.EventService.EVENT_COLUMNS;

  ngOnInit(): void {
    this.getUsers();
    this.getEvents();
  }

  getUsers(){
    this.userService.getUsers().subscribe({
      next:(res: any)=>{
        this.users.set(res)
        console.log('aaaaa')
      },
      error:(err: any)=>{
        console.log(err.message)
      }
    })
  }

  getEvents(){
    this.EventService.getEvents().subscribe({
      next:(res: any)=>{
        this.events.set(res)
      },
      error:(err: any)=>{
        console.log(err.message)
      }
    })
  }
}
