import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Client } from '../module/client';
import { Page } from '../module/page';

@Injectable({
  providedIn: 'root',
})
export class ClientServiceService {
  private apiUrl = environment.clientUrl;

  constructor(private http: HttpClient) {}

  getAllClient() {
    return this.http.get<Page>(this.apiUrl);
  }
}
