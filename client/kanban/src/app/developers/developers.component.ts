import { Component, OnInit } from '@angular/core';
import { Observable} from "rxjs";
import {Developer} from "../../model/developer.interface";
import {DeveloperService} from "../../services/developer/developer.service";

@Component({
  selector: 'app-developers',
  templateUrl: './developers.component.html',
  styleUrls: ['./developers.component.css']
})
export class DevelopersComponent implements OnInit {

  constructor(private developerService: DeveloperService) { }

  developers : Observable<Developer[]> | any;


  ngOnInit(): void {
    this.developers = this.developerService.loadDevelopers();
  }

}
