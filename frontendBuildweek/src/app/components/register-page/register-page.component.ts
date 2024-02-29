import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss'],
})
export class RegisterPageComponent implements OnInit {
  subscribeForm!: FormGroup;
  confirmPass$: BehaviorSubject<boolean> = new BehaviorSubject(true);
  constructor(private fb: FormBuilder, private authSrv: AuthService) {}

  confirmPasswordCorrected() {
    this.confirmPass$.next(false);

    console.log(this.confirmPass$.getValue());
    if (
      this.subscribeForm.controls['password'].value ===
      this.subscribeForm.controls['confirmPassword'].value
    ) {
      this.subscribeForm.controls['confirmPassword'].setErrors(null);
      this.confirmPass$.next(true);
      console.log(this.confirmPass$.getValue());
    } else {
      this.subscribeForm.controls['confirmPassword'].setErrors({
        notEqual: true,
      });
      this.confirmPass$.next(false);
      console.log(this.confirmPass$.getValue());
    }
  }

  ngOnInit(): void {
    this.subscribeForm = this.fb.group({
      username: [null, Validators.required],
      name: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      surname: [null, Validators.required],
      confirmPassword: [null, [Validators.required, Validators.minLength(8)]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      acceptTerms: [null, Validators.required],
    });
  }

  onRegister() {
    const data = {
      username: this.subscribeForm.controls['username'].value,
      name: this.subscribeForm.controls['name'].value,
      surname: this.subscribeForm.controls['surname'].value,
      email: this.subscribeForm.controls['email'].value,
      password: this.subscribeForm.controls['password'].value,
    };
    try {
      this.authSrv.register(data).subscribe();
    } catch (error) {
      console.log(error);
      alert(error);
    }
  }
}
