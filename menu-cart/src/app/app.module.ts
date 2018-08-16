import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import {HttpClientModule} from '@angular/common/http';
import { ItemsComponent } from './items/items.component';
import { GroupPipe } from './items/group.pipe';

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    ItemsComponent,
    GroupPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
