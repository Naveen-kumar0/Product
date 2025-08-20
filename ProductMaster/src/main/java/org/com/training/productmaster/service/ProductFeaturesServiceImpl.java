package org.com.training.productmaster.service;

import org.com.training.productmaster.dao.ProductFeaturesDAO;
import org.com.training.productmaster.model.ProductFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductFeaturesServiceImpl implements ProductFeaturesService {

    @Autowired
    private ProductFeaturesDAO productFeaturesDAO;

    private String convertBooleanToYN(String isEnabled) {
        if (isEnabled == null) return "N";
        return ("true".equalsIgnoreCase(isEnabled) || "y".equalsIgnoreCase(isEnabled)) ? "Y" : "N";
    }
    public boolean existsById(Long id) {
        if (id == null) return false;
        return productFeaturesDAO.existsById(id);
    }


    @Override
    public ProductFeatures addProductFeatures(ProductFeatures productFeatures) {
        productFeatures.setIsEnabled(convertBooleanToYN(productFeatures.getIsEnabled()));

        if (productFeatures.getId() != null) {
            Optional<ProductFeatures> existing = productFeaturesDAO.findById(productFeatures.getId());
            if (existing.isPresent()) {
                return existing.get();
            }
        }

        return productFeaturesDAO.save(productFeatures);
    }

    @Override
    public ProductFeatures updateProductFeatures(ProductFeatures productFeatures) {
        productFeatures.setIsEnabled(convertBooleanToYN(productFeatures.getIsEnabled()));

        if (productFeatures.getId() != null && productFeaturesDAO.existsById(productFeatures.getId())) {
            return productFeaturesDAO.save(productFeatures);
        }

        return null;
    }

    @Override
    public void deleteProductFeatures(long id) {
        productFeaturesDAO.deleteById(id);
    }

    @Override
    public ProductFeatures getProductFeaturesById(long id) {
        return productFeaturesDAO.findById(id).orElse(null);
    }

    @Override
    public List<ProductFeatures> getAllProductFeatures() {
        return productFeaturesDAO.findAll();
    }
}
