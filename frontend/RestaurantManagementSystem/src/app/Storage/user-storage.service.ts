import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";
const TOKEN= 'token';


@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

 
  static gettoken():any{
    return localStorage.getItem(TOKEN);
  }
  static getrole():any{
  var decoded= jwt_decode(this.gettoken());
   return decoded;
  }
 static getuserrole():any{
  let payload= this.getrole();
  let price = payload["role"]; 
    return price;
 } 
   
  
  static isloggedin():boolean{
    if(this.gettoken()===null){
      return false;
    }
   
    else{
      return true;
    }
  }

  static isUserloggedin():boolean{
    if(this.gettoken()===null){
      return false;
    }
    if(this.gettoken()!=null&&this.getuserrole()==='user'){
      return true;
    }
    else{
      return false;
    }
  
  }
  static isAdminloggedin():boolean{
    if(this.gettoken()===null){
      return false;
    }
    if(this.gettoken()!=null&&this.getuserrole()==='admin'){
      return true;
    }
    else{
      return false;
    }
  
  }

 

}
