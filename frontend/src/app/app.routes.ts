import {Routes} from '@angular/router';
import {Home} from './features/home/home';
import { MyRegistrationsComponent } from './features/my-registrations/my-registrations';

export const routes: Routes = [
  {
    path: '',
    component: Home,
  },
  {
    path: 'my-registrations',
    component: MyRegistrationsComponent,
  }
];
