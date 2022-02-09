import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {Developer} from "../../model/developer.interface";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DeveloperService {
  api: string = "http://localhost:8080/developers";

  developers : BehaviorSubject<Developer[]> = new BehaviorSubject<Developer[]>([])

  constructor(private http: HttpClient) {
  }

  loadDevelopers(): Observable<Developer[]> {
    return this.http.get<Developer[]>(this.api);
  }

}
