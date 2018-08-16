import { Component, OnInit } from '@angular/core';
import {FoodItem} from '../config';
import {CategoryComponent} from '../category/category.component';
import {Cart} from '../cart';



@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent extends CategoryComponent implements OnInit {

  itemChinese = this.getItemsInCategory('Chinese Combos');
  public cart: Cart[] = [];
  private items: any[];
  total = 0;
  private allItems: FoodItem[] = [];
  products: FoodItem[];

  ngOnInit() {
      this.products = [];
      this.connnectionService.getConfig()
      .subscribe((data: FoodItem[]) => {
        this.products = data;
        });
  }

  getItemsInCategory(category: string) {
    this.items = [];
    this.connnectionService.getConfig()
      .subscribe((data: FoodItem[]) => {
        data.filter((value, index) => value.category === category).map((value, index) => {
          this.items.push(value);
        });
      });
    return this.items;
  }
  isNotAvailable(availableTime: string) {
    let result = true;
   const times = availableTime.split('-');
   const start = Number(times[0].slice(0, 2));
   const end = Number(times[1].slice(0, 2));
   const timeNow = new Date();
    console.log('start' + start);
   if (timeNow.getHours() < end && timeNow.getHours() > start ) {
     result = false;
   }
   else if (timeNow.getHours() === start) {
      if (timeNow.getMinutes() > Number(times[0].slice(3, 5))) {
        result = false;
      }
   }
   else if (timeNow.getHours() === end) {
      if (timeNow.getMinutes() < Number(times[1].slice(3, 5))) {
        result = false;
      }
   }
   return result;
  }

  getItems() {
    this.allItems = [];
    this.connnectionService.getConfig()
      .subscribe((data: FoodItem[]) => {
        data.map((value, index) => {
           this.allItems.push(value);
              });
      });
    return this.allItems;
  }

  totalPrice() {
    this.total = 0;
    for ( let i = 0; i < this.cart.length; i++) {
      this.total += (this.cart[i].item.price * this.cart[i].quantity);
    }
    console.log('total: ' + this.total);
  }

  add(item) {
    console.log(item.name);
    const requiredItem = this.cart.filter((value, index) => value.item === item);
    if (requiredItem.length === 0 ){
      this.cart.push({item: item, quantity: 1 });
    }
    else {
      const index = this.cart.indexOf(requiredItem[0]);
      if (index !== -1) {
        this.cart[index].quantity += 1;
      }
    }
    this.totalPrice();
    console.log(this.cart);
  }

  delete(item) {
    console.log(item.name);
    for (let i = 0 ; i < this.cart.length ; i++) {
      if (this.cart[i].item === item) {
        if (this.cart[i].quantity !== 0) {
          this.cart[i].quantity -= 1;
          if (this.cart[i].quantity === 0) {
            this.cart.splice(i, 1);
          }
        }
      }
    }
    this.totalPrice();
    console.log(this.cart);
  }
  deleteCart() {
    this.cart = [];
    this.totalPrice();
  }

}
