import { EventColumn, Event } from "../../shared/models/event.model";
import { UserColumn, UserModel } from "../../shared/models/user-model";


export const adminMockData = {
    EVENT_COLUMNS: <EventColumn[]>[
        { key:'id',label:'ID', type:'number' },
        { key:'title',label:'Title', type:'string' },
        { key:'description',label:'Description', type:'string' },
        { key:'location',label:'Location', type:'string' },
        { key:'startAt',label:'Start At', type:'datetime' },
        { key:'endAt',label:'End At', type:'datetime' },
        { key:'createdAt',label:'Created At', type:'datetime' },
        { key:'maxParticipants',label:'Max Participants', type:'number' },
        { key:'category',label:'Category', type:'string' },
        { key:'registrationDeadline',label:'Registration Deadline', type:'datetime' },
        { key:'isFull',label:'Is Full', type:'boolean' }
    ],

    EVENTS_DATA: <Event[]> [
        {
            id: 1,
            title: 'Angular Workshop',
            description: 'A workshop on Angular framework.',
            location: 'Online',
            startAt: '2024-07-01T10:00:00Z',
            endAt: '2024-07-01T16:00:00Z',
            createdAt: '2024-06-01T12:00:00Z',
            maxParticipants: 100,
            category: 'Workshop',
            registrationDeadline: '2024-06-25T23:59:59Z',
            isFull: false
        },
        {
            id: 2,
            title: 'Food Donation Drive',
            description: 'Help collect and distribute food packages for families in need.',
            location: 'Community Center, Cluj-Napoca',
            startAt: '2026-06-02T09:00:00',
            endAt: '2026-06-02T13:00:00',
            createdAt: '2026-05-10T11:20:00',
            maxParticipants: 20,
            category: 'Social',
            registrationDeadline: '2026-05-30T23:59:00',
            isFull: true,
        },
        {
            id: 3,
            title: 'Animal Shelter Support',
            description: 'Support the local animal shelter with cleaning and feeding activities.',
            location: 'Happy Paws Shelter, Cluj-Napoca',
            startAt: '2026-06-15T08:30:00',
            endAt: '2026-06-15T12:30:00',
            createdAt: '2026-05-12T16:45:00',
            maxParticipants: 15,
            category: 'Animals',
            registrationDeadline: '2026-06-12T23:59:00',
            isFull: false,
        },
    ],

    USER_COLUMNS: <UserColumn[]>[
        { key:'id',label:'ID', type:'number' },
        { key:'firstName',label:'First Name', type:'string' },
        { key:'lastName',label:'Last Name', type:'string' },
        { key:'email',label:'Email', type:'string' },
        { key:'createdAt',label:'Created At', type:'datetime' }
    ],

    USERS_DATA: <UserModel[]> [
        {
            id: 1,
            firstName: 'John',
            lastName: 'Doe',
            email: 'john.doe@example.com',
            createdAt: '2024-06-01T12:00:00Z',
        },
        {
            id: 2,
            firstName: 'Mihai',
            lastName: 'Ionescu',
            email: 'mihai.ionescu@example.com',
            createdAt: '2026-05-03T14:30:00',
        },
        {
            id: 3,
            firstName: 'Elena',
            lastName: 'Dumitrescu',
            email: 'elena.dumitrescu@example.com',
            createdAt: '2026-05-05T09:15:00',
        },
    ],
}
