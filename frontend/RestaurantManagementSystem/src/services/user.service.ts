import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private httpclient:HttpClient) { }


  signup(data:any){
    return this.httpclient.post("http://localhost:9191/user/signup",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  login(data:any){
    return this.httpclient.post("http://localhost:9191/user/login",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }

  checktoken(){
    return this.httpclient.get("http://localhost:8081/user/checkToken")
  }
  forgotpassword(data:any){
    return this.httpclient.post("http://localhost:9191/user/changePassword",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }


   getUsers(){
    return this.httpclient.get("http://localhost:9191/user/get",{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
   }

   update(data:any){
    return this.httpclient.post("http://localhost:9191/user/update" , data , {
      headers:new HttpHeaders().set('Content-Type' , 'application/json')
    })
  }
 



 
}

