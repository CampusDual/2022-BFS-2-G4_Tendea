import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShopsRoutingModule } from './shops-routing.module';
import { ShopsComponent } from './shops.component';

@NgModule({
  declarations: [ShopsComponent],
  imports: [CommonModule, ShopsRoutingModule],
})
export class ShopsModule {}
