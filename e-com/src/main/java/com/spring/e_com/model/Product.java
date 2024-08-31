package com.spring.e_com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String brand;
    private String category;
    private int quantity;
    private BigDecimal price;
    private boolean available;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    private Date releaseDate;
}
