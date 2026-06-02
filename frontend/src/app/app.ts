import { Component, signal } from '@angular/core';

import { RouterOutlet, RouterLinkWithHref } from '@angular/router';
import {About} from './features/about/about';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, About, RouterLinkWithHref],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
}
