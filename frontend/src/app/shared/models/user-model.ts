export interface UserModel {
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    createdAt: string
}

export interface UserColumn {
    key: keyof UserModel;
    label: string;
    type: 'number' | 'string' | 'datetime' | 'boolean';
}