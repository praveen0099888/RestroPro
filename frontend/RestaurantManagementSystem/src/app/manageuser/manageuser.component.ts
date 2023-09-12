import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-manageuser',
  templateUrl: './manageuser.component.html',
  styleUrls: ['./manageuser.component.scss']
})
export class ManageuserComponent implements OnInit{
  displayedColumns: string[] = ['name' , 'email' , 'contactNumber' , 'status'];
  dataSource:any;
  responsemessage:any;

  constructor(private userService:UserService,
    private dialog:MatDialog,
    private notification:NzNotificationService,
    private router:Router) { }
    ngOnInit(): void {
      this.tableData();
    }
    tableData() {
      this.userService.getUsers().subscribe((response:any)=>{
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
    onChange(status:any , id:any){
      var data = {
        status:status.toString(),
        id:id
      }
      this.userService.update(data).subscribe((response:any)=>{
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
