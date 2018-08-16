import {Component, OnInit, ɵEMPTY_ARRAY} from '@angular/core';
import {ConnectionService} from '../connection.service';
import {FoodItem} from '../config';

@Component({
  selector: '[app-category]',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories = this.getCategories();
  private category: any[];

  ngOnInit() {
  }
  constructor(public connnectionService: ConnectionService) {}
  getCategories() {
    this.category = [];
    this.connnectionService.getConfig()
      .subscribe((data: FoodItem[]) => {
        data.map((value, index) => {
         if (this.category.indexOf(value.category) < 0) {
           this.category.push(value.category);
         }
        });
      });
    return this.category;
  }
}
