import { Pipe, PipeTransform } from '@angular/core';
import {FoodItem} from '../config';

@Pipe({
  name: 'group'
})
export class GroupPipe implements PipeTransform {

  transform(foodItems: FoodItem[], category?: string): any {
    const foodInCategories = foodItems.filter((value, index) => value.category === category);
    return foodInCategories;
  }

}
