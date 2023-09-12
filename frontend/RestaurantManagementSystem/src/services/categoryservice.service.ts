import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryserviceService {

  constructor(private httpclient:HttpClient) { }
  
  add(data:any){
    return this.httpclient.post("http://localhost:9191/category/add",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
  update(data:any){
    return this.httpclient.post("http://localhost:9191/category/update",data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    })
  }
 

  getcategorys(){
    return this.httpclient.get("http://localhost:9191/category/get");
  }

  getFilteredCategorys(){
    return this.httpclient.get("http://localhost:9191/category/get?filterValue=true");
  }

}
