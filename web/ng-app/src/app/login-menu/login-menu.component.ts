import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-login-menu',
  templateUrl: './login-menu.component.html',
  styleUrls: ['./login-menu.component.css']
})
export class LoginMenuComponent implements OnInit {

  constructor(private authenService: AuthenticationService) { }

  ngOnInit() {
  }
  hasRole(role:string){
    return this.authenService.hasRole(role);
  }

}
