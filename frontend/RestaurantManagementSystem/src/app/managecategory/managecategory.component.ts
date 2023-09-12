import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CategoryserviceService } from 'src/services/categoryservice.service';
import { DashboardService } from 'src/services/dashboard.service';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryComponent } from '../category/category.component';
import { UserStorageService } from '../Storage/user-storage.service';

@Component({
  selector: 'app-managecategory',
  templateUrl: './managecategory.component.html',
  styleUrls: ['./managecategory.component.scss']
})
export class ManagecategoryComponent implements OnInit{
  
  displayedColumns: string[] = ['name' , 'edit'];
  dataSource:any;
  responsemessage:any;

  constructor(private categoryService:CategoryserviceService,
    private dialog:MatDialog,private userstorageserice:UserStorageService,private notification:NzNotificationService,
  
    private router:Router) { }
    isadminloggedin:boolean = UserStorageService.isAdminloggedin();

ngOnInit(): void {
  this.tableData();
  
}
tableData() {
  this.categoryService.getcategorys().subscribe((response:any)=>{
    this.dataSource = new MatTableDataSource(response);
    console.log(this.dataSource)
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
  dialogConfog.height="350px";
  const dialogRef = this.dialog.open(CategoryComponent , dialogConfog);
  this.router.events.subscribe(()=>{
    dialogRef.close();
  }); 
  const sub = dialogRef.componentInstance.onAddCategory.subscribe((response)=>{
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
  dialogConfog.height="350px";
  const dialogRef = this.dialog.open(CategoryComponent , dialogConfog);
  this.router.events.subscribe(()=>{
    dialogRef.close();
  }); 
  const sub = dialogRef.componentInstance.onEditCatefory.subscribe((response)=>{
    this.tableData();
  })
}

}
