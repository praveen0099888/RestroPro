import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.scss']
})
export class ChangepasswordComponent implements OnInit{
  constructor(private fb:FormBuilder,private userserice:UserService,private notification:NzNotificationService,private router:Router){}

  validateForm!:FormGroup;
  responsemessage:any;


  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls['password'].value) {
      return { confirm: true, error: true };
    }
    return {};
  };
  ngOnInit(): void {
    this.validateForm = this.fb.group({
      oldpassword: [null, [Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null,[Validators.required,this.confirmationValidator]],
    });
  }

  submitForm(){
    if(this.validateForm.valid){
        
      var formdata= this.validateForm.value;
      var data = {oldPassword:formdata.oldpassword,
                  newPassword:formdata.password}
                  console.log(data);
      this.userserice.forgotpassword(data).subscribe((response :any )=>{ 
        this.responsemessage=response?.message;
        this.notification.success('Success',this.responsemessage,{nzDuration:5000});

        

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
        for(const i in this.validateForm.controls){
          this.validateForm.controls[i].markAsDirty();
          this.validateForm.controls[i].updateValueAndValidity();
        }
      }
  }
  

}
