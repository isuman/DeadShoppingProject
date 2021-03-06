import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {FileNotFoundComponent} from "./filenotfound/file-not-found-component";
import {AppRoutingModule} from "./app-routing.module";
import {MenuComponent} from "./menu/menu.component";
import {ProductsDataService} from "./service/product.data.service";
import {ProductRoutingModule} from "./product/product-routing.module";
import {ProductsAddComponent} from "./product/add/product.add.component";
import {ProductsComponent} from "./product/list/product.list.component";
import {ProductsViewComponent} from "./product/view/product.view.component";
import {ProductDataServerService} from "./service/product-data-server.service";
import { LoginComponent } from './login/login.component';
import {AuthenticationService} from "./service/authentication.service";
import {CartComponent}from"./cart/cart.component";
import {LoginMenuComponent} from "./login-menu/login-menu.component";
import { CheckoutComponent } from './checkout/checkout.component';


@NgModule({
 declarations: [ AppComponent,
        ProductsAddComponent,
        ProductsComponent,
        ProductsViewComponent,
        MenuComponent,
        FileNotFoundComponent,
        LoginComponent,
        LoginMenuComponent,
        CartComponent,
        CheckoutComponent],
 imports: [BrowserModule,FormsModule, HttpModule,ProductRoutingModule,AppRoutingModule],
 bootstrap: [AppComponent],
  providers:[{ provide:ProductsDataService, useClass: ProductDataServerService},AuthenticationService]
})




export class AppModule {}
