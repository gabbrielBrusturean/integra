import {Routes} from '@angular/router';
import {Home} from './features/home/home';
import { MyRegistrationsComponent } from './features/my-registrations/my-registrations';
import { EventListComponent } from './features/event-list/event-list.component';
import { EventDetailsComponent } from './features/event-details/event-details.component';
import { CreateEventComponent } from './features/events/create-event/create-event.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'events',
    pathMatch: 'full'
  },
  {
    path: 'events',
    component: Home,
  },

  {
    path: 'events/create', component: CreateEventComponent,
  },

  {
    path: 'events/:id', component: EventDetailsComponent,
  },

  {
    path: 'my-registrations',
    component: MyRegistrationsComponent,
  },
  {
    path: '**',
    redirectTo: 'events'
  }
];