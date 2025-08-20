package org.com.training.productmaster.service;


import org.com.training.productmaster.model.ProductFeatures;

import java.util.List;

public interface ProductFeaturesService {
    ProductFeatures addProductFeatures(ProductFeatures productFeatures);
    ProductFeatures updateProductFeatures(ProductFeatures productFeatures);
    void deleteProductFeatures(long id);
    ProductFeatures getProductFeaturesById(long id);
    List<ProductFeatures> getAllProductFeatures();

    boolean existsById(Long id);
}
