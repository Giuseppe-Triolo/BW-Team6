import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Address } from '../module/address';
import { environment } from 'src/environments/environment';
import { take, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  apiUrl = environment.apiURL;
  id!: string;
  addressFormHidden!: boolean;
  constructor(private http: HttpClient) {}

  postNewAddress(data: {
    street: string;
    houseNumber: string;
    country: string;
    postalCode: number;
    city: string;
  }) {
    return this.http.post<Address>(`${this.apiUrl}/addresses`, data).pipe(
      tap((el) => {
        this.id = el.id;
        console.log(this.id);
        this.addressFormHidden = true;
      })
    );
  }

  getAddressId() {
    return this.id;
  }

  getAddressFormHidden() {
    return this.addressFormHidden;
  }

  setAddressFormHidden(state: boolean) {
    return this.addressFormHidden;
  }
}
