import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DevelopersComponent } from './developers/developers.component';
import { HttpClientModule} from "@angular/common/http";
import { NavbarComponent } from './navbar/navbar.component';
import { TasksComponent } from './tasks/tasks.component';
import { CreateTaksComponent } from './create-taks/create-taks.component';

@NgModule({
  declarations: [
    AppComponent,
    DevelopersComponent,
    NavbarComponent,
    TasksComponent,
    CreateTaksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
