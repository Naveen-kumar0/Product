package org.com.training.productmaster.controller;

import org.com.training.productmaster.model.ProductFeatures;
import org.com.training.productmaster.service.ProductFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/features")
@CrossOrigin(origins = "*")

public class FeaturesController {

    @Autowired
    private ProductFeaturesService productFeaturesService;

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }

    // CREATE
    @PostMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> addFeature(@RequestBody ProductFeatures feature) {
        boolean exists = productFeaturesService.existsById(feature.getId());

        if (exists) {
            return buildResponse(HttpStatus.CONFLICT, "Feature with ID already exists.", null);
        }

        ProductFeatures result = productFeaturesService.addProductFeatures(feature);
        return buildResponse(HttpStatus.CREATED, "Feature added successfully.", result);
    }


    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getFeatureById(@PathVariable long id) {
        ProductFeatures feature = productFeaturesService.getProductFeaturesById(id);

        if (feature == null) {
            return buildResponse(HttpStatus.NOT_FOUND, "Feature not found.", null);
        }

        return buildResponse(HttpStatus.OK, "Feature fetched successfully.", feature);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFeatures() {
        List<ProductFeatures> features = productFeaturesService.getAllProductFeatures();
        return buildResponse(HttpStatus.OK, "All features fetched successfully.", features);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFeature(@PathVariable long id ,@RequestBody ProductFeatures feature) {
        ProductFeatures updated = productFeaturesService.updateProductFeatures(feature);

        if (updated == null) {
            return buildResponse(HttpStatus.NOT_FOUND, "Feature not found. Cannot update.", null);
        }

        return buildResponse(HttpStatus.OK, "Feature updated successfully.", updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFeature(@PathVariable long id) {
        ProductFeatures existing = productFeaturesService.getProductFeaturesById(id);
        if (existing == null) {
            return buildResponse(HttpStatus.NOT_FOUND, "Feature not found. Cannot delete.", null);
        }

        productFeaturesService.deleteProductFeatures(id);
        // Return 200 OK to show message in Postman
        return buildResponse(HttpStatus.OK, "Feature deleted successfully.", null);
    }

}
