import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddressService } from 'src/app/service/address-service.service';
import { Address } from 'src/app/module/address';

@Component({
  selector: 'app-adress-form',
  templateUrl: './adress-form.component.html',
  styleUrls: ['./adress-form.component.scss'],
})
export class AdressFormComponent implements OnInit {
  createAdressForm!: FormGroup;
  res!: Address;
  constructor(private adressSrv: AddressService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.createAdressForm = this.fb.group({
      street: [null, Validators.required],
      houseNumber: [null, Validators.required],
      country: [null, [Validators.required]],
      postalCode: [null, Validators.required],
      city: [null, [Validators.required]],
    });
  }

  onCreate() {
    const data = {
      street: this.createAdressForm.controls['street'].value,
      houseNumber: this.createAdressForm.controls['houseNumber'].value,
      country: this.createAdressForm.controls['country'].value,
      postalCode: this.createAdressForm.controls['postalCode'].value,
      city: this.createAdressForm.controls['city'].value,
    };
    try {
      this.adressSrv.postNewAddress(data).subscribe((el) => {
        this.res = el;
        console.log(this.res);
      });
    } catch (error) {
      console.log(error);
      alert(error);
    }
  }
}
