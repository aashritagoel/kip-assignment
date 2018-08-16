import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { FoodItem} from './config';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor(private httpClient: HttpClient) { }
  configUrl = 'https://gist.githubusercontent.com/Aakash06/fad46b64a5' +
    '73d4c152e899192b90b86c/raw/8bdfa0dfcce2411cb521b58916cd5556700c1ab1/menu.json';

  getConfig() {
    return this.httpClient.get<FoodItem[]>(this.configUrl);  }

}
