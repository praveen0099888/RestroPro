import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { DashboardService } from 'src/services/dashboard.service';

import { UserStorageService } from '../Storage/user-storage.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';



@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})
export class DetailsComponent implements OnInit{
  responsemessage:any;
  data:any;
  isadminloggedin:boolean = UserStorageService.isAdminloggedin();
 ngOnInit(): void {
   this.dashboardData();
 }
 constructor(private router:Router,private notification:NzNotificationService,private dashboardservice:DashboardService) {}
 dashboardData(){
  this.dashboardservice.getdetails().subscribe((response:any)=>{
    this.data=response;
    console.log(response);

  },(error:any)=>{
    if(error.error?.message){
      this.responsemessage= error.error?.message;
    }
    else{
      this.responsemessage="somethingwentwrong";
    }
    this.notification.error('error',this.responsemessage,{nzDuration:5000});
    
  })


  }
  handlesubmitforcategory(){
    if(this.isadminloggedin){
      this.router.navigateByUrl('/dashboard/managecategory');
    }
    else{
      this.notification.error('error','you are not authorized for this page',{nzDuration:5000});
    }
  }
  handlesubmitforproduct(){
    if(this.isadminloggedin){
      this.router.navigateByUrl('/dashboard/manageproduct');
    }
    else{
      this.notification.error('error','you are not authorized for this page',{nzDuration:5000});
    }
  }


  handlesubmitforbill(){
    if(this.isadminloggedin){
      this.router.navigateByUrl('/dashboard/managebill');
    }
    else{
      this.notification.error('error','you are not authorized for this page',{nzDuration:5000});
    }
  }
}
