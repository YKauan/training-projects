import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output } from '@angular/core';
import { faPen, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Item } from 'src/app/interfaces/iItem';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit, OnChanges, OnDestroy {

  @Input() item!: Item;
  @Output() editarItemEvent = new EventEmitter();
  @Output() deletarItemEvent = new EventEmitter();

  faPen = faPen;
  faTrash = faTrash

  constructor() { }

  ngOnInit(): void { }

  ngOnChanges(): void { }

  ngOnDestroy() {
    console.log('ItemComponent foi destruído');
  }

  editarItem() {
    this.editarItemEvent.emit(this.item);
  }

  deletarItem() {
    this.deletarItemEvent.emit(this.item.id);
  }
}
