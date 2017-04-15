package com.alexcibotari.nakama.domain;

import javax.persistence.*;

@Entity
public class ProductVariation extends AbstractAuditingEntity{

    private String code;
    private Long quantity;

    private Integer orderIndex;

    private String option1;
    private String option2;
    private String option3;

    @Embedded
    private Price price;
    private boolean enabled;
    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
            "code='" + code + '\'' +
            ", quantity=" + quantity +
            ", orderIndex=" + orderIndex +
            ", option1='" + option1 + '\'' +
            ", option2='" + option2 + '\'' +
            ", option3='" + option3 + '\'' +
            ", price=" + price +
            ", enabled=" + enabled +
            ", weight=" + weight +
            ", product=" + product +
            '}';
    }
}
