package com.alexcibotari.nakama.web.rest.dto;


import javax.validation.constraints.*;

public class TestDTO {

    @AssertFalse(message = "The annotated element must be false. Supported type is boolean. null elements are considered valid.")
    private Boolean checkOnFalse;

    @AssertTrue
    private Boolean checkOnTrue;

    @DecimalMax(value = "20", message = "The annotated element must be a number whose value must be lower or equal to the specified maximum. Note that float is not supported due to rounding errors (some providers might provide some approximative support).")
    private Long numberMax;

    @DecimalMin(value = "20", message = "The annotated element must be a number whose value must be higher or equal to the specified minimum. Note that float is not supported due to rounding errors (some providers might provide some approximative support).")
    private Long numberMin;

    @Digits(integer = 3, fraction = 2, message = "The annotated element must be a number within accepted range. null elements are considered valid")
    private double doubleWithFraction;

    @Digits(integer = 5, fraction = 0, message = "The annotated element must be a number within accepted range. null elements are considered valid")
    private Long intWithoutFraction;

    @Max(value = 20, message = "The annotated element must be a number whose value must be lower or equal to the specified maximum.  Note that float is not supported due to rounding errors (some providers might provide some approximative support).")
    private Long checkOnMax;

    @Min(value = 20, message = "The annotated element must be a number whose value must be lower or equal to the specified maximum.  Note that float is not supported due to rounding errors (some providers might provide some approximative support).")
    private Long checkOnMin;

    @Pattern(regexp = "^[A-Za-z]+$", message = "The annotated CharSequence must match the specified regular expression")
    private String checkOnPattern;

    @Size(min = 3, max = 10, message = "The annotated element size must be between the specified boundaries (included)")
    private String checkOnSize;


    public TestDTO() {
    }

    public Boolean getCheckOnFalse() {
        return checkOnFalse;
    }

    public void setCheckOnFalse(Boolean checkOnFalse) {
        this.checkOnFalse = checkOnFalse;
    }

    public Boolean getCheckOnTrue() {
        return checkOnTrue;
    }

    public void setCheckOnTrue(Boolean checkOnTrue) {
        this.checkOnTrue = checkOnTrue;
    }

    public Long getNumberMax() {
        return numberMax;
    }

    public void setNumberMax(Long numberMax) {
        this.numberMax = numberMax;
    }

    public Long getNumberMin() {
        return numberMin;
    }

    public void setNumberMin(Long numberMin) {
        this.numberMin = numberMin;
    }

    public double getDoubleWithFraction() {
        return doubleWithFraction;
    }

    public void setDoubleWithFraction(double doubleWithFraction) {
        this.doubleWithFraction = doubleWithFraction;
    }

    public Long getIntWithoutFraction() {
        return intWithoutFraction;
    }

    public void setIntWithoutFraction(Long intWithoutFraction) {
        this.intWithoutFraction = intWithoutFraction;
    }

    public Long getCheckOnMax() {
        return checkOnMax;
    }

    public void setCheckOnMax(Long checkOnMax) {
        this.checkOnMax = checkOnMax;
    }

    public Long getCheckOnMin() {
        return checkOnMin;
    }

    public void setCheckOnMin(Long checkOnMin) {
        this.checkOnMin = checkOnMin;
    }

    public String getCheckOnPattern() {
        return checkOnPattern;
    }

    public void setCheckOnPattern(String checkOnPattern) {
        this.checkOnPattern = checkOnPattern;
    }

    public String getCheckOnSize() {
        return checkOnSize;
    }

    public void setCheckOnSize(String checkOnSize) {
        this.checkOnSize = checkOnSize;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
            "checkOnFalse=" + checkOnFalse +
            ", checkOnTrue=" + checkOnTrue +
            ", numberMax=" + numberMax +
            ", numberMin=" + numberMin +
            ", doubleWithFraction=" + doubleWithFraction +
            ", intWithoutFraction=" + intWithoutFraction +
            ", checkOnMax=" + checkOnMax +
            ", checkOnMin=" + checkOnMin +
            ", checkOnPattern='" + checkOnPattern + '\'' +
            ", checkOnSize='" + checkOnSize + '\'' +
            '}';
    }
}
