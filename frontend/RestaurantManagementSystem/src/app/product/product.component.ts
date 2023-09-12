import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CategoryserviceService } from 'src/services/categoryservice.service';
import { ProductService } from 'src/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  onAddProduct = new EventEmitter();
  onEditProduct = new EventEmitter();
  productForm: any = FormGroup;
  dialogAction: any = "Add";
  action: any = "Add";
  responsemessage: any;
  categorys: any = [];
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBulider: FormBuilder,
    protected productService: ProductService,
    public dialogRef: MatDialogRef<ProductComponent>,
    private notification:NzNotificationService,
    private categoryService: CategoryserviceService
  ) { }


  getCategorys() {
    this.categoryService.getcategorys().subscribe((response: any) => {
      this.categorys = response;
      console.log(this.categorys)
      
    }, (error) => {
      console.error(error);
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
     
    });
  }
  ngOnInit(): void {
    this.productForm = this.formBulider.group({
      name: [null, Validators.required],
      categoryId: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required]
    });
    if (this.dialogData.action === 'Edit') {
      this.dialogAction = "Edit";
      this.action = "Update";
      this.productForm.patchValue(this.dialogData.data);
    }
    this.getCategorys();
    
  }



  
  handleSubmit() {
    if (this.dialogAction === "Edit") {
      this.edit();
    } else {
      this.add();
    }
  }
  
  add() {
    var formData = this.productForm.value;
    console.log(formData)
    var data = {
      name: formData.description,
      categoryId: formData.categoryId,
      price: formData.price,
      description: formData.name
    }
    console.log(data)

    this.productService.add(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.onAddProduct.emit();
      this.responsemessage = response.message;
      
      this.notification.success('Success',this.responsemessage,{nzDuration:5000});
    }, (error) => {
      this.dialogRef.close();
      console.error(error);
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
    });
  }

  edit() {
    var formData = this.productForm.value;
    var data = {
      id: this.dialogData.data.id,
      name: formData.name,
      categoryId : formData.categoryId,
      price: formData.price,
      description:formData.description
    }
    console.log(data)
    this.productService.update(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.onEditProduct.emit();
      this.responsemessage = response.message;
     
      this.notification.success('Success',this.responsemessage,{nzDuration:5000});
    }, (error) => {
      this.dialogRef.close();
      console.error(error);
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
    });
  }

  

}
