import {Routes, RouterModule} from "@angular/router";
import {FileNotFoundComponent} from "./filenotfound/file-not-found-component";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./login/login.component";

const appRoutes: Routes = [

  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    redirectTo: '/add',
    pathMatch: 'full'
  },
  {path: '**', component: FileNotFoundComponent}


];

@NgModule({
   imports: [
   RouterModule.forRoot(appRoutes)
   ],
   exports: [
   RouterModule
   ]
})
export class AppRoutingModule {

}
