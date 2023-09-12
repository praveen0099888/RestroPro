import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserStorageService } from './Storage/user-storage.service';

@Injectable({
  providedIn: 'root'
})
export class NoAuthGuard implements CanActivate {

  constructor(private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

      if(UserStorageService.isloggedin()&&UserStorageService.isUserloggedin()){
        this.router.navigateByUrl("/dashboard/details")
        return false;
      }
      if(UserStorageService.isloggedin()&&UserStorageService.isAdminloggedin()){
        this.router.navigateByUrl("/dashboard/details")
        return false;
      }
    return true;
  }
  
}
