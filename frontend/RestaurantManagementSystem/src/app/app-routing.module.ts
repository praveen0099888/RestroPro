import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { Dashboard1Component } from './dashboard1/dashboard1.component';
import { DetailsComponent } from './details/details.component';
import { LoginComponent } from './login/login.component';
import { ManagebillComponent } from './managebill/managebill.component';
import { ManagecategoryComponent } from './managecategory/managecategory.component';
import { ManageorderComponent } from './manageorder/manageorder.component';
import { ManageproductComponent } from './manageproduct/manageproduct.component';
import { ManageuserComponent } from './manageuser/manageuser.component';
import { NoAuthGuard } from './no-auth.guard';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [{path: 'login',component:LoginComponent,canActivate:[NoAuthGuard]},
                        {path: 'register',component:SignupComponent,canActivate:[NoAuthGuard]},
                        {path: 'dashboard',component:Dashboard1Component,canActivate:[AuthGuard],
                        children: 
                        [
                          {
                            path:'changepassword', component: ChangepasswordComponent
                          },{
                            path:'details', component: DetailsComponent
                          },
                          {
                            path:'managecategory', component: ManagecategoryComponent
                          },
                          {
                            path:'manageproduct', component: ManageproductComponent
                          },
                          {
                            path:'manageorder', component: ManageorderComponent
                          },
                          {
                            path:'managebill', component: ManagebillComponent
                          },
                          {
                            path:'manageusers', component: ManageuserComponent
                          }
                        ]   
                        } ];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
