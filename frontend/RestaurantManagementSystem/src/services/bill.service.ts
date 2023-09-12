import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private httpClient:HttpClient) { }

  generateReport(data:any){
    return this.httpClient.post("http://localhost:9191/bill/generate-report" , data,{
      headers: new HttpHeaders().set('Content-Type' , "application/json")
    })
  }

  getPdf(data:any):Observable<Blob>{
    return this.httpClient.post("http://localhost:9191/bill/get-pdf" , data,{responseType:'blob'});
  }

  getBills(){
    return this.httpClient.get("http://localhost:9191/bill/getbills");
    headers: new HttpHeaders().set('Content-Type' , "application/json")
  }
  delete(id:any){
    return this.httpClient.delete("http://localhost:9191/bill/"+id,{
      headers: new HttpHeaders().set('Content-Type' , "application/json")
    });
  }

}
