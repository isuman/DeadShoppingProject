import {Component} from '@angular/core';

import {ActivatedRoute, Params} from "@angular/router";
import 'rxjs/add/operator/switchMap';
import {Product} from "../product";
import {ProductsDataService} from "../../service/product.data.service";

@Component({
  selector: 'products-view',
  templateUrl: './product.view.component.html',
  styleUrls: ['./product.view.component.css']
})
export class ProductsViewComponent {

  constructor(private route: ActivatedRoute , private productDataService:ProductsDataService) {
  }

  product: Product;
  isNoData:boolean;
  inputCount: number;

  ngOnInit() {
    this.isNoData = false;
    this.inputCount = 15;
    this.route.params
      .switchMap((params:Params) =>  this.productDataService.getProduct(+params['id']))
      .subscribe((product:Product) => {
          if (product !== null)
            this.product = product;
          else
            this.isNoData = true;
        }
      );

  }

}

