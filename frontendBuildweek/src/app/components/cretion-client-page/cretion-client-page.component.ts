import { Component, DoCheck, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddressService } from 'src/app/service/address-service.service';
import { ClientService } from 'src/app/service/client-service.service';

@Component({
  selector: 'app-cretion-client-page',
  templateUrl: './cretion-client-page.component.html',
  styleUrls: ['./cretion-client-page.component.scss'],
})
export class CretionClientPageComponent implements OnInit, DoCheck {
  addressId!: string;
  createClientForm!: FormGroup;
  addressFormHidden!: boolean;

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
      numberOfCompany: [null, Validators.required],
      emailReferee: [null, Validators.required, Validators.email],
      nameReferee: [null, Validators.required],
      lastNameReferee: [null, Validators.required],
      numberOfReferee: [null, Validators.required],
      typeOfCompany: [null, Validators.required],
    });
    this.addressFormHidden = this.addressSrv.setAddressFormHidden(false);
  }

  ngDoCheck(): void {
    this.addressId = this.addressSrv.id;
    console.log(this.addressId);
    if(this.addressId != undefined){
      this.addressFormHidden = this.addressSrv.setAddressFormHidden(true);
    }
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
      numberOfCompany: this.createClientForm.controls['numberOfCompany'].value,
      emailReferee: this.createClientForm.controls['emailReferee'].value,
      nameReferee: this.createClientForm.controls['nameReferee'].value,
      lastNameReferee: this.createClientForm.controls['lastNameReferee'].value,
      numberOfReferee: this.createClientForm.controls['numberOfReferee'].value,
      typeOfCompany: this.createClientForm.controls['typeOfCompany'].value,
      addressId: this.addressId,
    };
    try {
      console.log("Questo è l'address ID",data.addressId);
      this.clientSrv.postNewClient(data).subscribe();
    } catch (error) {
      console.log(error);
      alert(error);
    }
  }
}
