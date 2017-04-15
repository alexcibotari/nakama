package com.alexcibotari.nakama.domain;

import javax.persistence.Entity;

@Entity
public class Image extends AbstractAuditingEntity{

    private String altText;
    private Integer width;
    private Integer high;
    private String name;
    private String mimeType;
    private String realFileName;
    private String url;

    @Override
    public String toString() {
        return null;
    }

}
