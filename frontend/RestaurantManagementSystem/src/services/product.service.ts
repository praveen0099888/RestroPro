import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient:HttpClient) { }



  add(data:any){
    return this.httpClient.post("http://localhost:9191/product/add" , data,{
      headers: new HttpHeaders().set('Content-Type' , "application/json")
    })
  }

  update(data:any){
    return this.httpClient.post("http://localhost:9191/product/update" , data,{
      headers: new HttpHeaders().set('Content-Type' , "application/json")
    })
  }

  getProducts(){
    return this.httpClient.get("http://localhost:9191/product/get");
  }

  updateStatus(data:any){
    return this.httpClient.post("http://localhost:9191/product/update-status" , data,{
      headers: new HttpHeaders().set('Content-Type' , "application/json")
    })
  }

  delete(id:any){
    return this.httpClient.delete("http://localhost:9191/product/delete/"+ id ,{
      headers: new HttpHeaders().set('Content-Type' , "application/json")
    })
  }

  getProductByCategory(id:any){
    return this.httpClient.get("http://localhost:9191/product/category/"+id);
  }

  getById(id:any){
    return this.httpClient.get("http://localhost:9191/product/get/"+id);
  }
}
