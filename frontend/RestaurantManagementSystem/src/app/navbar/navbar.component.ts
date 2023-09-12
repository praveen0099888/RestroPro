import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from '../Storage/user-storage.service';
import { Observable } from 'rxjs';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  isuserloggedin :boolean = UserStorageService.isloggedin();
  constructor(private router :Router){}
  ngOnInit(): void {
    this.router.events.subscribe(event=>{
      if(event.constructor.name==='NavigationEnd'){
       var ans = UserStorageService.isloggedin();
       console.log(ans)

        this.isuserloggedin= UserStorageService.isloggedin();
      }
    })

}
   logut(){
    console.log("done")
    window.localStorage.clear();
   }
}
