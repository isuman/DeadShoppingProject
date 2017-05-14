package com.assignment.entity;

import com.assignment.config.json.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Admin on 17/4/2560.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Login.class)
    long id;
    @JsonView(View.Login.class)
    String productId;
    @JsonView(View.Login.class)
    String name;
    @JsonView(View.Login.class)
    String image;
    @JsonView(View.Login.class)
    int productAmount;
    @JsonView(View.Login.class)
    double price;
    @JsonView(View.Login.class)
    String description;



}
