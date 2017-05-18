import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(private authenService: AuthenticationService) { }

  ngOnInit() {
  }
  hasRole(role:string){
    return this.authenService.hasRole(role);
  }
}
