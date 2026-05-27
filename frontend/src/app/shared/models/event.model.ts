export interface Event {
  id: number;
  title: string;
  description: string;
  location: string;
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
  startAt: string;
  endAt: string;
  maxParticipants: number;
}

export interface CreateEventResponse {
  id: number;
  title: string;
  description: string;
  location: string;
  startAt: string;
  endAt: string;
  createdAt: string;
  maxParticipants: number | null;
}
