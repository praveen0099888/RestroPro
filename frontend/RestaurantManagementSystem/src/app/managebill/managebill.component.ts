import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { BillService } from 'src/services/bill.service';
import { ConfirmationComponent } from '../confirmation/confirmation.component';
import { ViewBillComponent } from '../view-bill/view-bill.component';
import * as saveAs from 'file-saver';

@Component({
  selector: 'app-managebill',
  templateUrl: './managebill.component.html',
  styleUrls: ['./managebill.component.scss']
})
export class ManagebillComponent implements OnInit{

  displayedColumns: string[] = ['name', 'email', 'contactNumber', 'paymentMethod', 'total', 'view'];
  dataSource: any;
  responsemessage: any;
  bills:any;


  constructor(private billservice: BillService,
    private dialog: MatDialog,
    private notification:NzNotificationService,
    
    private router: Router) { }
  ngOnInit(): void {
    this.tableData();
    console.log(this.dataSource)
  }
 
  tableData() {
    console.log("fromtabel")
      this.billservice.getBills().subscribe((response: any) => {
      this.dataSource = new MatTableDataSource(response);
      
      // console.log(this.dataSource)
    }, (error: any) => {
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
      
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  handleViewAction(values: any) {
    const dialogConfog = new MatDialogConfig();
    dialogConfog.data = {
      data: values
    };
    dialogConfog.width = "850px";
    dialogConfog.height="500px";
    const dialogRef = this.dialog.open(ViewBillComponent, dialogConfog);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
  
  }


  handleDeleteAction(values: any) {
    const dialogConfog = new MatDialogConfig;
    dialogConfog.data = {
      message: 'delete ' + values.name + ' bill',
      confirmation: true
    };
    const dialogRef = this.dialog.open(ConfirmationComponent, dialogConfog);
    const sub = dialogRef.componentInstance.onEmistStatusChange.subscribe((response) => {
      this.deleteBill(values.id);
      dialogRef.close();
    })
  }



  deleteBill(id: any) {
    this.billservice.delete(id).subscribe((response: any)=>{
      this.tableData();
      this.responsemessage = response?.message;
      this.notification.success('Success',"product added successffully",{nzDuration:5000});
    }, (error: any) => {
      if(error.error?.message){
        this.responsemessage= error.error?.message;
      }
      else{
        this.responsemessage="somethingwentwrong";
      }
      this.notification.error('error',this.responsemessage,{nzDuration:5000});
      
    })
  }
  

  downloadReportAction(values: any) {
    var data = {
      name: values.name,
      email: values.email,
      uuid: values.uuid,
      contactNumber: values.contactNumber,
      paymentMethod: values.paymentMethod,
      totalAmount: values.total.toString(),
      productDetails: values.productDetails
    }
    this.downloadFile(values.uuid, data);
  }

  downloadFile(fileName: string, data: any) {

    this.billservice.getPdf(data).subscribe((response: any) => {
      saveAs(response, fileName + '.pdf');
    })
  }


}
