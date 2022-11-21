import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsComponent } from './clients.component';
import { ListsComponent } from './lists/lists.component';
import { MaterialModule } from '../../material/material.module';

@NgModule({
  declarations: [ClientsComponent, ListsComponent],
  imports: [CommonModule, ClientsRoutingModule, MaterialModule],
})
export class ClientsModule {}
