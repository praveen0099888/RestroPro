import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CategoryserviceService } from 'src/services/categoryservice.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  onAddCategory = new EventEmitter();
  onEditCatefory = new EventEmitter();
  categoryForm:any = FormGroup;
  dialogAction:any = "Add";
  action:any = "Add";

responseMessage:any;


constructor(@Inject(MAT_DIALOG_DATA) public dialogData:any,
  private formBulider:FormBuilder,
  protected categoryService:CategoryserviceService,
  public dialogRef: MatDialogRef<CategoryComponent>,
  private notification:NzNotificationService
 
  ) { }

  ngOnInit(): void {
    this.categoryForm = this.formBulider.group({
      name:[null,[Validators.required]]
    });
    if(this.dialogData.action === 'Edit'){
      this.dialogAction = "Edit";
      this.action = "Update";
      this.categoryForm.patchValue(this.dialogData.data);
    }
  }

  handleSubmit(){
    if(this.dialogAction === "Edit"){
      this.edit();
    }else{
      this.add();
    }
  }

  add() {
    var formData = this.categoryForm.value;
    var data = {
      name: formData.name
    }
    this.categoryService.add(data).subscribe((response:any)=>{
      this.dialogRef.close();
      this.onAddCategory.emit();
      this.responseMessage = response.message;
     
      this.notification.success('Success',this.responseMessage,{nzDuration:5000});
     
    },(error)=>{
      this.dialogRef.close();
      console.error(error);
      if(error.error?.message){
        this.responseMessage= error.error?.message;
      }
      else{
        this.responseMessage="somethingwentwrong";
      }
      this.notification.error('error',this.responseMessage,{nzDuration:5000});
      
    });
  }
  edit() {
    var formData = this.categoryForm.value;
    var data = {
      id: this.dialogData.data.id,
      name: formData.name
    }
    this.categoryService.update(data).subscribe((response:any)=>{
      this.dialogRef.close();
      this.onEditCatefory.emit();
      this.responseMessage = response.message;
      
      this.notification.success('Success',this.responseMessage,{nzDuration:5000});
      
    },(error)=>{
      this.dialogRef.close();
      console.error(error);
      if(error.error?.message){
        this.responseMessage= error.error?.message;
      }
      else{
        this.responseMessage="somethingwentwrong";
      }
      this.notification.error('error',this.responseMessage,{nzDuration:5000});
      
    });  
  }
}
