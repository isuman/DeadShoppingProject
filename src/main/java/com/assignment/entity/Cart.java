package com.assignment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Cart {
    ArrayList<Product> ListofProduct;
}
