import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {Product} from "../product";
import {ProductsDataService} from "../../service/product.data.service";

@Component({
  selector: 'product',
  templateUrl: './product.list.component.html',
  styleUrls:['./product.list.component.css']
})
export class ProductsComponent {
  products: Product[];
  search:string;
  constructor (private productsDataService:ProductsDataService, private router: Router){}
  ngOnInit(){
    this.productsDataService.getProductsData()
      .subscribe(products => this.products = products,
        (error : Error ) => {
          if (error.message === 'UnAuthorize'){
            this.router.navigate(['login'],{queryParams:{source:'product'}});
          }
        });
  }

  onSearch(){
    this.productsDataService.findProduct(this.search)
      .subscribe(products => this.products = products,
        (error : Error ) => {
          if (error.message === 'UnAuthorize'){
            this.router.navigate(['login'],{queryParams:{source:'product'}});
          }
        });
  }

  upQuantity(product:Product){
    product.ProductAmount++;
  }

  downQuantity(product:Product){
    if (product.ProductAmount >0)
      product.ProductAmount--;
  }
  showDetail(product: Product){
    this.router.navigate(['/detail',product.id]);
  }


}
