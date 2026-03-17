package com.example.shopping.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Document
public class furniture {

    @Id
    private String id;
    private String furnitureName;
    private String categories;
    private Double price;
    private Integer inStock;
    private Integer sku;
    private String description;
    private String imageUrl;
    private ArrayList<color> color;


     public furniture() {
    }
    public furniture(Object object, String modern_Sofa, String living_Room, double d, int i, int i0, String a_comfortable_modern_sofa, String httpsexamplecommodernsofajpg, Object object0) {
        // Constructor for creating a furniture object with specific parameters
        this.id = (String) object;
        this.furnitureName = modern_Sofa;
        this.categories = living_Room;
        this.price = d;
        this.inStock = i;
        this.sku = i0;
        this.description = a_comfortable_modern_sofa;
        this.imageUrl = httpsexamplecommodernsofajpg;
        this.color = new ArrayList<>(); // Initialize color list
    }

     

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<color> getColor() {
        return color;
    }

    public void setColor(ArrayList<color> color) {
        this.color = color;
    }
    @Override
    public String toString() {
        return "furniture{" +
                "id=" + id +
                ", furnitureName='" + furnitureName + '\'' +
                ", categories='" + categories + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", sku=" + sku +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", color=" + color +
                '}';
}
}