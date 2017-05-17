import {Component} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
@Component({
   selector: 'menu',
   templateUrl: './menu.component.html',
   styleUrls:['./menu.component.css']
})
export class MenuComponent {

  constructor(private authenService: AuthenticationService) {
  }
  hasRole(role:string){
    return this.authenService.hasRole(role);
  }
}
