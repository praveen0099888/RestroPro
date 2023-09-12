import { forwardRef, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, NG_VALUE_ACCESSOR } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { DemoNgZorroAntdModule } from './DemoNgZorroAntdModule';
import { ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './signup/signup.component';
import { NavbarComponent } from './navbar/navbar.component';
import { Dashboard1Component } from './dashboard1/dashboard1.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { DetailsComponent } from './details/details.component';

import { TokenInterceptorService } from '../services/token-interceptor.service';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ManagecategoryComponent } from './managecategory/managecategory.component';
import { MatTableModule } from '@angular/material/table'  
import { MatInputModule } from '@angular/material/input';
import { CategoryComponent } from './category/category.component';
import { ProductComponent } from './product/product.component';
import { MatOptionModule } from '@angular/material/core';
import { ManageproductComponent } from './manageproduct/manageproduct.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import {MatSelectModule} from '@angular/material/select';
import { ManageorderComponent } from './manageorder/manageorder.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { ManagebillComponent } from './managebill/managebill.component';
import { ManageuserComponent } from './manageuser/manageuser.component';
registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    NavbarComponent,
    Dashboard1Component,
    ChangepasswordComponent,
    DetailsComponent,
   ManagecategoryComponent,
   CategoryComponent,
   ProductComponent,
   ManageproductComponent,
   ConfirmationComponent,
   ManageorderComponent,
   ViewBillComponent,
   ManagebillComponent,
   ManageuserComponent
   
   
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    MatCardModule,
    MatToolbarModule,
    FormsModule, 
    MatButtonModule,
    MatIconModule, 
    MatFormFieldModule,
    MatDialogModule,
    MatTableModule,
    MatInputModule,
    MatOptionModule,
    MatSlideToggleModule,
    MatSelectModule
   
  ],

  providers: [
    { provide: NZ_I18N, useValue: en_US },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService , multi: true},
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => ManageproductComponent),  // replace name as appropriate
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
