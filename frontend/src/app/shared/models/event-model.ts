export interface EventModel {
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
  maxParticipants: number;
  category: string;
  registrationDeadline: string;
  isFull: boolean;
}

export interface EventColumn {
  key: keyof EventModel;
  label: string;
  type: 'number' | 'string' | 'datetime' | 'boolean';
}
