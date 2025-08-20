package org.com.training.productmaster.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_features")  // Change table name if needed
public class ProductFeatures {

    @Id
    @Column(name="feature_id")
    private Long id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "feature_name")
    private String featureName;

    @Column(name = "IS_ENABLED", length = 1)
    private String isEnabled;

    @Column(name = "fee_amount")
    private Double feeAmount;

    @Column(name = "fee_frequency")
    private String feeFreq;

    public ProductFeatures() {
    }

    public ProductFeatures(Long id, Integer productId, String featureName, String isEnabled, Double feeAmount, String feeFreq) {
        this.id = id;
        this.productId = productId;
        this.featureName = featureName;
        this.isEnabled = isEnabled;
        this.feeAmount = feeAmount;
        this.feeFreq = feeFreq;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeFreq() {
        return feeFreq;
    }

    public void setFeeFreq(String feeFreq) {
        this.feeFreq = feeFreq;
    }

    @Override
    public String toString() {
        return "ProductFeatures{" +
                "id=" + id +
                ", productId=" + productId +
                ", featureName='" + featureName + '\'' +
                ", isEnabled=" + isEnabled +
                ", feeAmount=" + feeAmount +
                ", feeFreq='" + feeFreq + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFeatures)) return false;
        ProductFeatures that = (ProductFeatures) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(featureName, that.featureName) &&
                Objects.equals(isEnabled, that.isEnabled) &&
                Objects.equals(feeAmount, that.feeAmount) &&
                Objects.equals(feeFreq, that.feeFreq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, featureName, isEnabled, feeAmount, feeFreq);
    }
}
