export interface Event {
  id: number;
  title: string;
  description: string;
  location: string;
  address?: string | null;
  latitude?: number | null;
  longitude?: number | null;
  startAt: string;
  endAt: string;
  createdAt: string;
  maxParticipants: number | null;
  category?: string;
  registrationDeadline?: string;
  isFull: boolean;
}

export interface CreateEventRequest {
  title: string;
  description: string;
  location: string;
  address?: string;
  latitude?: number;
  longitude?: number;
  startAt: string;
  endAt: string;
  maxParticipants: number;
}

export interface CreateEventResponse {
  id: number;
  title: string;
  description: string;
  location: string;
  address?: string;
  latitude?: number;
  longitude?: number;
  startAt: string;
  endAt: string;
  createdAt: string;
  maxParticipants: number | null;
}
