import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/module/client';
import { Page } from 'src/app/module/page';
import { ClientServiceService } from 'src/app/service/client-service.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss'],
})
export class MainPageComponent implements OnInit {
  page!: Page;
  clients: Client[] = [];

  constructor(
    private clientSrv: ClientServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getClient();
  }

  getClient() {
    this.clientSrv.getAllClient().subscribe((page: Page) => {
      this.clients = page.content;
      console.log(this.clients);
    });
  }

  changePage(id: string) {
    this.router.navigate([`/details/${id}`]);
  }
}
