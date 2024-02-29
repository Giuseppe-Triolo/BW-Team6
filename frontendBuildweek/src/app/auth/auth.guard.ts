import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { AuthData } from '../module/authdata';
import { map, take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authSrv: AuthService, private router: Router  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return this.authSrv.user$.pipe(
        take(1),
        map((utente) => {
          const user = localStorage.getItem('user');
  
          if (user) {
            const userData: AuthData = JSON.parse(user);
            console.log(userData);
            console.log(utente);
          }
          console.log(utente);
          if (utente) {
            return true;
          }
          alert(
            'Per visualizzare questa risorsa devi essere loggato!\nAccedi o registrati'
          );
          return this.router.createUrlTree(['/login-page']);
        })
      );  }
  
}
