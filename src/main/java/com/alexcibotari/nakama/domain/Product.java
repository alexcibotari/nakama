package com.alexcibotari.nakama.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product extends AbstractAuditingEntity {

    private String sku;
    private String name;
    private String description;
    private String subtitle;
    @Embedded
    private Price price;
    private boolean enabled;

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "product_gallery_images",
        joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "id")})
    private Set<Image> galleryImages = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "primary_image_id")
    private Image primaryImage;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Override
    public String toString() {
        return null;
    }
}
