package com.assignment.service;

import com.assignment.entity.Product;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 17/4/2560.
 */

public interface ProductService {
    List<Product> getProducts();
    Product findById(long id);
    Product addProduct(Product product);
    Product addProduct(Product product, String imageFileName, BufferedImage image) throws IOException;
    List<Product> queryProduct(String query);
    Product getProductForTransfer(String name);
}
