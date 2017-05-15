import { Component, OnInit } from '@angular/core';
import {Product} from "../product/product";
import {ProductsDataService} from "../service/product.data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  products: Product[];
  constructor(private productsDataService: ProductsDataService, private router: Router) { }

  ngOnInit() {
    this.productsDataService.getProductsData()
      .subscribe(products => this.products= products);
  }

  upQuantity(product: Product) {
    product.ProductAmount++;
  }

  downQuantity(product: Product) {
    if ( product.ProductAmount > 0)
      product.ProductAmount--;
  }

}
