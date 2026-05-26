import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { UserColumn, UserModel } from '../models/user-model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  http = inject(HttpClient)
  userUrl = 'http://localhost:8080/api/users'

  USER_COLUMNS = <UserColumn[]>[
    { key:'id',label:'ID', type:'number' },
    { key:'firstName',label:'First Name', type:'string' },
    { key:'lastName',label:'Last Name', type:'string' },
    { key:'email',label:'Email', type:'string' },
    { key:'createdAt',label:'Created At', type:'datetime' }
  ]

  getUsers(){
    return this.http.get(this.userUrl);
  }

  getUserById(id:number){
    return this.http.get(this.userUrl+ '/' + id);
  }

  createUser(user:UserModel){
    return this.http.post(this.userUrl,user)
  }

  getdeleteById(id:number){
    return this.http.delete(this.userUrl+ '/' + id);
  }

  updateUser(id:number,user:UserModel){
    return this.http.put(this.userUrl+ '/' + id,user);
  }
}
