package com.alexcibotari.nakama.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product extends AbstractAuditingEntity {

    private String code;
    private String name;
    private String title;
    private String subtitle;
    private String description;

    private String seoKeywords;

    private boolean enabled;

    @OneToMany(mappedBy = "product")
    @OrderBy("orderIndex")
    private Set<ProductVariation> variations;

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "product_images",
        joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "id")})
    private Set<Image> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "primary_image_id")
    private Image primaryImage;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<ProductVariation> getVariations() {
        return variations;
    }

    public void setVariations(Set<ProductVariation> variations) {
        this.variations = variations;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Image getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(Image primaryImage) {
        this.primaryImage = primaryImage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", title='" + title + '\'' +
            ", subtitle='" + subtitle + '\'' +
            ", description='" + description + '\'' +
            ", seoKeywords='" + seoKeywords + '\'' +
            ", enabled=" + enabled +
            ", variations=" + variations +
            ", categories=" + categories +
            ", images=" + images +
            ", primaryImage=" + primaryImage +
            ", brand=" + brand +
            '}';
    }
}
