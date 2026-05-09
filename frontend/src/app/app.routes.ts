import { Routes } from '@angular/router';
import { EventListComponent } from './features/event-list/event-list.component';
import { EventDetailsComponent } from './features/event-details/event-details.component';

export const routes: Routes = [
  {
    path: 'list-of-events',
    component: EventListComponent,
  },

  {
    path: 'detailed-event/:id',
    component: EventDetailsComponent,
  },

  {
    path: '',
    redirectTo: '/list-of-events',
    pathMatch: 'full',
  },

  {
    path: '**',
    redirectTo: '/list-of-events',
  },
];
