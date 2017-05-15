import {Component, ViewChild,Input, ElementRef} from '@angular/core';
import {Router} from "@angular/router";
import {Product} from "../product";
import {ProductsDataService} from "../../service/product.data.service";

@Component({
  selector: 'products-add',
  templateUrl: './product.add.component.html',
  styleUrls: ['./product.add.component.css']
})
export class ProductsAddComponent {
  product: any = {};
  constructor(private productsDataService: ProductsDataService, private router: Router) {
  };

  ngOnInit() {
    this.product = new Product();
  }
  upQuantity(product: Product) {
    product.ProductAmount++;
  }

  downQuantity(product: Product) {
    if ( product.ProductAmount > 0)
      product.ProductAmount--;
  }
  @ViewChild('fileInput') inputEl: ElementRef;
  addProduct(product: Product) {
    let result: Product;
    console.log(product)
    let inputEl: HTMLInputElement = this.inputEl.nativeElement;

    this.productsDataService.addProduct(product,inputEl.files.item(0))
      .subscribe(resultStudent => {
        result = resultStudent
        if (result != null){
          this.router.navigate(['/list']);
        }else{
          alert("Error in adding a product");
        }
      });
  }

  onFileChange(event, product: any) {

    var filename = event.target.files[0].name;
    console.log(filename);
    product.image = filename;
    product.file = event.target.files[0];
  }
}
