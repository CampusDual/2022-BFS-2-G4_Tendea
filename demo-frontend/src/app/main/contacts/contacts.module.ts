import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ContactsRoutingModule } from './contacts-routing.module';
import { ContactsComponent } from './contacts.component';
import { EditContactComponent } from './edit-contact/edit-contact.component';


import { ContactsLayoutComponent } from './contacts-layout.component';
import { MaterialModule } from '../../material/material.module';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        ContactsRoutingModule,
        MaterialModule
    ],
    declarations: [
        ContactsComponent,
        EditContactComponent,
        ContactsLayoutComponent
    ]
})
export class ContactsModule { }
