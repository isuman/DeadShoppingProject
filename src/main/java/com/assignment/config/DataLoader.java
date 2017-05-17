package com.assignment.config;


import com.assignment.dao.ProductDAO;
import com.assignment.entity.Product;
import com.assignment.entity.security.Authority;
import com.assignment.entity.security.AuthorityName;
import com.assignment.entity.security.User;
import com.assignment.security.repository.AuthorityRepository;
import com.assignment.security.repository.UserRepository;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Admin on 16/4/2560.
 */
@ConfigurationProperties(prefix = "server")
@Component
public class DataLoader implements ApplicationRunner {
    ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    String baseUrl;
    String imageUrl;
    String imageBaseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    UserRepository userSecurityRepository;
    @Autowired
    public void setUserSecurityRepository(UserRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }
    AuthorityRepository authorityRepository;

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        imageBaseUrl = baseUrl + imageUrl;
        Product product1 = Product.builder().ProductId("Miku-001").name("miku").image(imageBaseUrl+"Hastune_Miku___S_589aa63ba5689.jpg").ProductAmount(5).price(20.00).description("miku").build();
        Product product2 = Product.builder().ProductId("Maki-001").name("Maki").image(imageBaseUrl+"Maki_Nishikino_A_5703e3387162a.jpg").ProductAmount(5).price(20.00).description("Maki").build();


        productDAO.addProduct(product1);
        productDAO.addProduct(product2);

        securitySetup();
    }

    User user1,user2,user3;
    private void securitySetup() {
        user1 = User.builder()
                .username("admin")
                .password("admin")
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("customer")
                .password("customer")
                .firstname("customer")
                .lastname("customer")
                .email("customer@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user3 = User.builder()
                .username("shop")
                .password("shop")
                .firstname("shop")
                .lastname("shop")
                .email("shop@shop.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2016, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        Authority auth1 = Authority.builder().name(AuthorityName.ROLE_CUSTOMER).build();
        Authority auth2 = Authority.builder().name(AuthorityName.ROLE_SHOPKEEPER).build();
        Authority auth3 = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();

        authorityRepository.save(auth1);
        authorityRepository.save(auth2);
        authorityRepository.save(auth3);

        user1.setAuthorities(new ArrayList<>());
        user1.getAuthorities().add(auth3);
        user2.setAuthorities(new ArrayList<>());
        user2.getAuthorities().add(auth1);
        user3.setAuthorities(new ArrayList<>());
        user3.getAuthorities().add(auth2);

        userSecurityRepository.save(user1);
        userSecurityRepository.save(user2);
        userSecurityRepository.save(user3);
    }
}
