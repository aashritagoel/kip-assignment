import { Component } from '@angular/core';
import {ConnectionService} from './connection.service';
import {FoodItem} from './config';
import {ItemsComponent} from './items/items.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'menu-cart';
  constructor(private configService: ConnectionService) {}
  showConfig() {
    this.configService.getConfig()
      .subscribe((data: FoodItem[]) => {
        data.map((value, index) => {
        console.log('----------->' + value.availabletime + value.category + value.description + value.name);
        });
      });
  }
}
