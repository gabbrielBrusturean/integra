import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs'; 
import { Event } from '../../shared/models/event.model';
import { EventService } from '../../shared/services/event.service';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css'],
})

export class EventListComponent implements OnInit {
  eventService = inject(EventService);
  events= signal<Event[]>([]);

  ngOnInit(): void {
    this.getEvents();
  }

  getEvents(){
    this.eventService.getEvents().subscribe({
      next:(res:any)=>{
        this.events.set(res);
        console.log(res)
      },
      error:(err)=>{
        console.log(err.message)
      }
    })
  }
} 
