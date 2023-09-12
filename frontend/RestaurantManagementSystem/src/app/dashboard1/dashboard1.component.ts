import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from 'src/services/dashboard.service';

import { UserStorageService } from '../Storage/user-storage.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';


@Component({
  selector: 'app-dashboard1',
  templateUrl: './dashboard1.component.html',
  styleUrls: ['./dashboard1.component.css']
})
export class Dashboard1Component implements OnInit {
  isCollapsed = false; 
  responsemessage:any;
  data:any;
  isuserloggedin :boolean = UserStorageService.isUserloggedin();
  isadminloggedin:boolean = UserStorageService.isAdminloggedin();
  
  constructor(private router:Router,private userstorageserice:UserStorageService,private notification:NzNotificationService,private dashboardservice:DashboardService) {}

 
 
  
  ngOnInit(): void {
   this.router.navigateByUrl("/dashboard/details")
   console.log(UserStorageService.isAdminloggedin());
   console.log(UserStorageService.getuserrole())


   

  
   
}

}


 

