import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { MyRegistrationsComponent } from './features/my-registrations/my-registrations';

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
    path: 'events/:id',
    loadComponent: () => import('./features/event-details/event-details.component').then(m => m.EventDetailsComponent)
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