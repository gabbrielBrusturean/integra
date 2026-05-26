import { Routes } from '@angular/router';
import { EventListComponent } from './features/event-list/event-list.component';
import { EventDetailsComponent } from './features/event-details/event-details.component';
import {Home} from './features/home/home';
import { Admin } from './features/admin/admin';

export const routes: Routes = [
  {
    path: '',
    component: Home,
  },
  {
    path: 'admin',
    component: Admin,
  },
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
