import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UntypedFormControl } from '@angular/forms';
import { UserService } from 'src/services/user.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit{
  
  isSpinning= false;
  responsemessage:any;
  constructor(private fb: FormBuilder,private userserice:UserService,private notification:NzNotificationService,private router:Router) {}
  validateForm!: FormGroup;

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
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      name: [null, [Validators.required]],
      contactnumber: [null, [Validators.required]]
    });
  }


  submitForm(): void { 
    console.log("inside method")
    if(this.validateForm.valid){
        this.isSpinning=true;
        var formdata= this.validateForm.value;
        var data = {name: formdata.name,
                    email:formdata.email,
                    contactNumber:formdata.contactnumber,
                    password:formdata.password}
        this.userserice.signup(data).subscribe((response :any )=>{ 
          this.responsemessage=response?.message;
          this.notification.success('Success',this.responsemessage,{nzDuration:5000});
          this.router.navigateByUrl('/login')
  
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


  

  
  