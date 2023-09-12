import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ProductService } from 'src/services/product.service';
import { ConfirmationComponent } from '../confirmation/confirmation.component';
import { ProductComponent } from '../product/product.component';

@Component({
  selector: 'app-manageproduct',
  templateUrl: './manageproduct.component.html',
  styleUrls: ['./manageproduct.component.scss']
})
export class ManageproductComponent implements OnInit{

  displayedColumns: string[] = ['name' , 'categoryName' , 'description' , 'price' , 'edit'];
  dataSource:any;
  length1:any;
  responsemessage:any;

  constructor(private productService:ProductService, private notification:NzNotificationService,
    private dialog:MatDialog,
    private router:Router) { }


  ngOnInit(): void {
    this.tableData();
   
  }
  tableData() {
    this.productService.getProducts().subscribe((response:any)=>{
      this.dataSource = new MatTableDataSource(response);
    },(error:any)=>{
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
     
      
    })
  }

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  handleAddAction(){
    const dialogConfog = new MatDialogConfig();
    dialogConfog.data={
      action:'Add'
    };
    dialogConfog.width = "850px";
    dialogConfog.height="500px";
    const dialogRef = this.dialog.open(ProductComponent , dialogConfog);
    this.router.events.subscribe(()=>{
      dialogRef.close();
    }); 
    const sub = dialogRef.componentInstance.onAddProduct.subscribe((response)=>{
      this.tableData();
    })
  }
  handleEditAction(values:any){
    const dialogConfog = new MatDialogConfig();
    dialogConfog.data={
      action:'Edit',
      data:values
    };
    dialogConfog.width = "850px";
    dialogConfog.height="500px";
    const dialogRef = this.dialog.open(ProductComponent , dialogConfog);
    this.router.events.subscribe(()=>{
      dialogRef.close();
    }); 
    const sub = dialogRef.componentInstance.onEditProduct.subscribe((response)=>{
      this.tableData();
    })
  }
  handleDeleteAction(values:any){
    const dialogConfog = new MatDialogConfig();
    dialogConfog.data={
      message:'delete '+ values.name + ' product ',
      confirmation:true
    };
    const dialogRef = this.dialog.open(ConfirmationComponent , dialogConfog);
    const sub = dialogRef.componentInstance.onEmistStatusChange.subscribe((response)=>{
      this.deleteProduct(values.id);
      dialogRef.close();
    })

  }
  deleteProduct(id:any){
    this.productService.delete(id).subscribe((response:any)=>{
      this.tableData();
      this.responsemessage = response?.message;
      //alert("Product is Deleted");
      this.notification.success('Success',this.responsemessage,{nzDuration:5000});
    },(error:any)=>{
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
     
    })
  }
  onChange(status:any , id:any){
    var data = {
      status:status.toString(),
      id:id
    }
    this.productService.updateStatus(data).subscribe((response:any)=>{
      this.responsemessage = response?.message;
      this.notification.success('Success',this.responsemessage,{nzDuration:5000});
    },(error:any)=>{
      //console.log(error.error?.message);
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
      
    })
  }



}
