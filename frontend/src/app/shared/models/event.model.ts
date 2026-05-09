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
