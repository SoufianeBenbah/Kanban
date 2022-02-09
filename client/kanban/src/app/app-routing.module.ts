import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DevelopersComponent} from "./developers/developers.component";
import {TasksComponent} from "./tasks/tasks.component";
import {CreateTaksComponent} from "./create-taks/create-taks.component";

const routes: Routes = [
  {path: "home", component: TasksComponent},
  {path: "developers", component: DevelopersComponent},
  {path: "createTask", component: CreateTaksComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
