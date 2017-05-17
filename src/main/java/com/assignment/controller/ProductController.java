package com.assignment.controller;

import com.assignment.entity.Product;
import com.assignment.service.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Admin on 17/4/2560.
 */

@Component
@Path("/product")
@ConfigurationProperties(prefix = "server")
public class ProductController {

    ProductService productService;
    String imageServerDir;
    String imageUrl;
    String baseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageServerDir(String imageServerDir) {
        this.imageServerDir = imageServerDir;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {

        List<Product> products = productService.getProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") long id) {
        Product product = productService.findById(id);
        if (product != null)
            return Response.ok(product).build();
        else
            //http code 204
            return Response.status(Response.Status.NO_CONTENT).build();

    }
    @OPTIONS
    public Response checkOK(){
        return Response.ok().build();
    }




    @POST
    @Path("/image")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.TEXT_PLAIN})
    public Response uploadImage(@FormDataParam("file") InputStream fileInputStream,
                                @FormDataParam("file") FormDataContentDisposition cdh) throws IOException {
        try {
            BufferedImage img = ImageIO.read(fileInputStream);
            String oldFilename = cdh.getFileName();
            String ext = FilenameUtils.getExtension(oldFilename);
            String newFilename = Integer.toString(LocalTime.now().hashCode(), 16) + Integer.toString(oldFilename.hashCode(), 16) + "." + ext;
            File targetFile = Files.createFile(Paths.get(imageServerDir + newFilename)).toFile();
            ImageIO.write(img, ext, targetFile);

            return Response.ok(baseUrl + imageUrl + newFilename).build();
        }catch(NullPointerException e){
            return Response.status(202).build();
        }
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Product uploadOnlyProduct(@RequestBody Product product) {

        productService.addProduct(product);
        return product;

    }

    @GET
    @Path("/images/{fileName}")
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response getStuentImage(@PathParam("fileName") String filename) {
        File file = Paths.get(imageServerDir + filename).toFile();
        if (file.exists()) {
            Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
            responseBuilder.header("Content-Disposition", "attachment; filename=" + filename);
            return responseBuilder.build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
