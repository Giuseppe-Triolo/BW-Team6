import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Client } from '../module/client';
import { Page } from '../module/page';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private apiUrl = environment.apiURL;

  constructor(private http: HttpClient) {}

  getAllClient() {
    return this.http.get<Page>(`${this.apiUrl}/clients`);
  }
  postNewClient(data: {
    addressId: string;
    businessName: string;
    vatNumber: number;
    email: string;
    startDate: Date;
    lastContact: Date;
    annualTurnover: number;
    pec: string;
    numberOfCompany: number;
    emailReferee: string;
    nameReferee: string;
    lastNameReferee: string;
    numberOfReferee: number;
    typeOfCompany: string;
  }) {
    return this.http.post<Client>(`${this.apiUrl}/clients`, data);
  }
}
