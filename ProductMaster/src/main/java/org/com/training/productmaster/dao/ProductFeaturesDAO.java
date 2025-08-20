package org.com.training.productmaster.dao;

import org.com.training.productmaster.model.ProductFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeaturesDAO extends JpaRepository<ProductFeatures, Long> {
    // No need to declare CRUD methods here; JpaRepository provides them
}