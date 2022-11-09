import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';
import {
  MatTreeFlatDataSource,
  MatTreeFlattener,
} from '@angular/material/tree';
import { FlatTreeControl } from '@angular/cdk/tree';

interface FoodNode {
  name: string;
  children?: FoodNode[];
}

const TREE_DATA = [
  {
    name: 'Categorias',
    children: [
      { id: 1, name: 'Electronica' },
      { id: 1, name: 'Banana' },
      { id: 1, name: 'Fruit loops' },
    ],
  },
];

/** Flat node with expandable and level information */
interface ExampleFlatNode {
  expandable: boolean;
  name: string;
  level: number;
}

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent implements OnInit {
  catData: [] = [];
  categories: Category[] = [];
  TREE_DATA;
  @Output() category: Category;
  @Output() onGetCategory: EventEmitter<Category> = new EventEmitter();

  constructor(private categoryService: CategoryService) {
    console.log('Constructor');
    this.dataSource.data = TREE_DATA;
  }

  ngOnInit(): void {
    /** Carga inicial de las categorias */
    // this.categoryService
    //   .getCategories()
    //   .subscribe((cat) => {this.catData.push(cat)});
    this.categoryService
      .getCategories()
      .subscribe(res => (console.log(typeof(res))));
    console.log(typeof(this.catData));
    console.log(this.categories);
    console.log(TREE_DATA);
  }

  /** Obtener productos por categorias */
  getProductForCategory(selected: any) {
    this.category = selected;
    console.log(selected);
    this.onGetCategory.emit(selected);
  }

  private _transformer = (node: FoodNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      level: level,
    };
  };

  treeControl = new FlatTreeControl<ExampleFlatNode>(
    (node) => node.level,
    (node) => node.expandable
  );

  treeFlattener = new MatTreeFlattener(
    this._transformer,
    (node) => node.level,
    (node) => node.expandable,
    (node) => node.children
  );

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  hasChild = (_: number, node: ExampleFlatNode) => node.expandable;

  login() {}
}
