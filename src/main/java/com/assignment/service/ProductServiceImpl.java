package com.assignment.service;

import com.assignment.dao.ProductDAO;
import com.assignment.entity.Product;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Admin on 17/4/2560.
 */
@Service
@ConfigurationProperties(prefix = "server")
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDAO productDAO;


    String imageServerDir;


    public void setImageServerDir(String imageServerDir) {
        this.imageServerDir = imageServerDir;
    }


    @Transactional
    @Override
<<<<<<< HEAD
    public Product getProductForTransfer(String name) {
        Product product = productDAO.findByName(name);
=======
    public Product getProductForTransfer(String productName) {
        Product product = productDAO.findByProductName(productName);
>>>>>>> origin/master
        return product;
    }

    @Transactional
    public List<Product> getProducts(){
        List<Product> products = productDAO.getProducts();

        return products;
    }
    @Transactional
    public Product findById(long id) {

        Product product = productDAO.findById(id);
        return product;
    }
    public Product addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    @Override
    public Product addProduct(Product product, String imageFileName, BufferedImage image) throws IOException {
        int newId = productDAO.size()+1;
        String newFilename = newId +"."+ imageFileName;
        File targetFile = Files.createFile(Paths.get(imageServerDir+newFilename)).toFile();
        ImageIO.write(image, FilenameUtils.getExtension(imageFileName),targetFile);

        product.setImage(newFilename);
        productDAO.addProduct(product);
        return product;
    }
}
