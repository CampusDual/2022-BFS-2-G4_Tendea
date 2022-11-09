import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/services/category.service';
import {
  MatTreeFlatDataSource,
  MatTreeFlattener,
} from '@angular/material/tree';
import { FlatTreeControl } from '@angular/cdk/tree';
import { ProductService } from '../../services/product.service';
import { Product } from '../../model/product';

interface CatNode {
  id?: number;
  name: string;
  children?: CatNode[];
}

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
  products: Product[];
  catData: CatNode;
  categories;
  TREE_DATA;

  @Output() category: Category;
  @Output() onGetCategory: EventEmitter<Category> = new EventEmitter();

  constructor(
    private categoryService: CategoryService,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.productService.getProductsLanding(1, 5).subscribe((res) => {
      this.products = res.data
    });

    this.categoryService.getCategories().subscribe((cat) => {
      const TREE_DATA: CatNode[] = [
        {
          name: 'Categorias',
          children: cat,
        },
      ];
      this.dataSource.data = TREE_DATA;
    });
  }

  /** Obtener productos por categorias */
  getProductForCategory(selected: any) {
    this.category = selected;
    console.log(selected);
    this.onGetCategory.emit(selected);
  }

  private _transformer = (node: CatNode, level: number) => {
    return {
      expandable: !!node.children && node.children.length > 0,
      name: node.name,
      id: node.id,
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
