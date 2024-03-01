import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddressService } from 'src/app/service/address-service.service';
import { ClientService } from 'src/app/service/client-service.service';

@Component({
  selector: 'app-cretion-client-page',
  templateUrl: './cretion-client-page.component.html',
  styleUrls: ['./cretion-client-page.component.scss'],
})
export class CretionClientPageComponent implements OnInit {
  addressId!: string;
  createClientForm!: FormGroup;

  constructor(
    private addressSrv: AddressService,
    private fb: FormBuilder,
    private clientSrv: ClientService
  ) {}

  ngOnInit(): void {
    this.createClientForm = this.fb.group({
      businessName: [null, Validators.required],
      vatNumber: [null, Validators.required],
      email: [null, Validators.required, Validators.email],
      startDate: [null, Validators.required],
      lastContact: [null, Validators.required],
      annualTurnover: [null, Validators.required],
      pec: [null, Validators.required, Validators.email],
      number: [null, Validators.required],
      emailReferee: [null, Validators.required, Validators.email],
      nameReferee: [null, Validators.required],
      surnameReferee: [null, Validators.required],
      numberReferee: [null, Validators.required],
      type: [null, Validators.required],
    });
    this.addressFormHidden = false;
    this.addressId = this.addressSrv.id;
    if (this.addressId != null) this.addressFormHidden = true;
  }

  onCreate() {
    const data = {
      businessName: this.createClientForm.controls['businessName'].value,
      vatNumber: this.createClientForm.controls['vatNumber'].value,
      email: this.createClientForm.controls['email'].value,
      startDate: this.createClientForm.controls['startDate'].value,
      lastContact: this.createClientForm.controls['lastContact'].value,
      annualTurnover: this.createClientForm.controls['annualTurnover'].value,
      pec: this.createClientForm.controls['pec'].value,
      number: this.createClientForm.controls['number'].value,
      emailReferee: this.createClientForm.controls['emailReferee'].value,
      nameReferee: this.createClientForm.controls['nameReferee'].value,
      surnameReferee: this.createClientForm.controls['surnameReferee'].value,
      numberReferee: this.createClientForm.controls['numberReferee'].value,
      type: this.createClientForm.controls['type'].value,
      addressId: this.addressId,
    };
    try {
      this.clientSrv.postNewAddress(data).subscribe();
    } catch (error) {
      console.log(error);
      alert(error);
    }
  }
}
