import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { FormsModule } from '@angular/forms';
import { FormGroup } from '@angular/forms';
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  constructor(private authSrv: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onLogin(form: NgForm) {
    console.log(form);
    const data = {
      email: form.value.email,
      password: form.value.password,
    };
    console.log(data);
    try {
      this.authSrv.login(data).subscribe();
    } catch (error) {
      alert('login errato');
      console.log(error);
      this.router.navigate(['/login-page']);
    }
  }
}
