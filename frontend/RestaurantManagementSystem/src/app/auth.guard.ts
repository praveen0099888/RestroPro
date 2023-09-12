import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Observable } from 'rxjs';
import { UserStorageService } from './Storage/user-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router:Router, private notification:NzNotificationService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

      if(!UserStorageService.isloggedin()){
        window.localStorage.clear();
        this.router.navigateByUrl("/login");
        this.notification
        .error(
          'Error',
          `you are not logged in log in first!!!`,
          {nzDuration:5000}
        );
        return false;
      }
    return true;
  }
  
}
