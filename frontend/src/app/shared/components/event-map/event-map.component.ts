import {
  AfterViewInit,
  Component,
  Input,
  ViewChild,
  ElementRef,
  OnDestroy,
  OnChanges,
  SimpleChanges
} from '@angular/core';
import { CommonModule } from '@angular/common';

import * as L from 'leaflet';

@Component({
  selector: 'app-event-map',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-map.component.html',
  styleUrls: ['./event-map.component.css']
})
export class EventMapComponent implements AfterViewInit, OnChanges, OnDestroy {

  @Input() latitude?: number;
  @Input() longitude?: number;
  @Input() locationName?: string;
  @Input() address?: string;
  @ViewChild('map', { static: false }) mapContainer?: ElementRef<HTMLDivElement>;
  private map?: L.Map;
  private marker?: L.Marker;
  private tileLayer?: L.TileLayer;

  private getDefaultIcon(): L.Icon {
    return L.icon({
      iconRetinaUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png',
      iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
      shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png',
      iconSize: [25, 41],
      iconAnchor: [12, 41],
      popupAnchor: [1, -34],
      shadowSize: [41, 41]
    });
  }
  private resizeHandler = () => { if (this.map) { this.map.invalidateSize(); } };

  ngAfterViewInit(): void {
    if (
      this.latitude === undefined ||
      this.longitude === undefined ||
      !this.mapContainer
    ) {
      return;
    }

    // Ensure marker icons load when using bundlers by pointing to CDN images
    try {
      L.Icon.Default.mergeOptions({
        iconRetinaUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png',
        iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
        shadowUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png'
      });
    } catch (e) {
      // ignore if merge not available
    }

    this.map = L.map(this.mapContainer.nativeElement).setView([
      this.latitude,
      this.longitude
    ], 13);

    this.tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors',
      tileSize: 256,
      detectRetina: false,
      updateWhenIdle: true,
      updateWhenZooming: false
    }).addTo(this.map);

    // Add or replace marker with explicit icon
    if (this.marker) { this.marker.remove(); }
    this.marker = L.marker([
      this.latitude,
      this.longitude
    ], { icon: this.getDefaultIcon() }).addTo(this.map);

    // show a popup with name/address when available
    const popupParts: string[] = [];
    if (this.locationName) { popupParts.push(`<strong>${this.locationName}</strong>`); }
    if (this.address) { popupParts.push(`${this.address}`); }
    if (popupParts.length > 0) { this.marker.bindPopup(popupParts.join('<br/>')); }

    // Fix tile rendering issues when container size changes or on initialization
    this.map.whenReady(() => {
      // multiple invalidations to handle layout timing
      setTimeout(() => this.map?.invalidateSize(), 50);
      setTimeout(() => this.map?.invalidateSize(), 250);
      setTimeout(() => this.map?.invalidateSize(), 600);
    });

    // Listen to zoom/move events and window resize to reflow tiles and redraw tiles
    this.map.on('zoomend moveend', () => {
      this.map?.invalidateSize();
      try { this.tileLayer?.redraw(); } catch (e) { /* ignore */ }
    });
    window.addEventListener('resize', this.resizeHandler);
  }

  ngOnDestroy(): void {
    if (this.map) {
      this.map.remove();
      this.map = undefined;
    }
    window.removeEventListener('resize', this.resizeHandler);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (!this.map) { return; }
    let needsView = false;
    if (changes['latitude'] || changes['longitude']) {
      const lat = this.latitude ?? 0;
      const lng = this.longitude ?? 0;
      // update marker position
      if (this.marker) {
        this.marker.setLatLng([lat, lng]);
        this.marker.setIcon(this.getDefaultIcon());
      } else {
        this.marker = L.marker([lat, lng], { icon: this.getDefaultIcon() }).addTo(this.map);
      }
      needsView = true;
    }
    if (needsView) {
      this.map.setView([this.latitude ?? 0, this.longitude ?? 0]);
      // reflow after moving
      setTimeout(() => this.map?.invalidateSize(), 100);
    }
  }

  hasCoordinates(): boolean {
    return (
      this.latitude !== undefined &&
      this.longitude !== undefined
    );
  }
}