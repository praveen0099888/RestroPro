import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { FormGroup,FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from 'src/services/user.service';
import { FormBuilder } from '@angular/forms';
import jwt_decode from "jwt-decode";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    
  validateform!: FormGroup;
  constructor(private fb: FormBuilder,private userserice:UserService,private notification:NzNotificationService,private router:Router) {}
  responsemessage:any;



  
  register():void{
    this.router.navigate(["register"])
  }


  ngOnInit(): void {
    this.validateform = this.fb.group({
      email: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  submitForm(): void { 
    console.log("inside method")
    if(this.validateform.valid){
        
        var formdata= this.validateform.value;
        var data = {email:formdata.email,
                    password:formdata.password}
        this.userserice.login(data).subscribe((response :any )=>{ 
          this.responsemessage=response?.message;
          localStorage.setItem('token',response.token)
          // localStorage.setItem('role',response.role)
         
    
          this.notification.success('Success',this.responsemessage,{nzDuration:5000});
          this.router.navigateByUrl('/dashboard')
          
  
        },(error)=>{
              if(error.error?.message){
                this.responsemessage= error.error?.message;
              }
              else{
                this.responsemessage="somethingwentwrong";
              }
              this.notification.error('error',this.responsemessage,{nzDuration:5000});
        })
        }else{
          for(const i in this.validateform.controls){
            this.validateform.controls[i].markAsDirty();
            this.validateform.controls[i].updateValueAndValidity();
          }
        }
      }
}
