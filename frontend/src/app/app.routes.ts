import { Routes } from '@angular/router';
import { EventListComponent } from './features/event-list/event-list.component';
import { EventDetailsComponent } from './features/event-details/event-details.component';

export const routes: Routes = [
  {
    path: 'events', component: EventListComponent,
  },

  {
    path: 'events/:id', component: EventDetailsComponent,
  },

  {
    path: '', redirectTo: '/events', pathMatch: 'full',
  },

  {
    path: '**', redirectTo: '/events'
  },
];
