import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  constructor(private httpclient:HttpClient) { }


  getdetails(){
    return this.httpclient.get("http://localhost:9191/dashboard/count")
  }
}
