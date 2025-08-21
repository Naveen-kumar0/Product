define(['ojs/ojmodel'], function (oj) {
    var FeatureModel = oj.Model.extend({
        idAttribute: 'id',  // Primary key from the Java class
        urlRoot: 'http://localhost:9092/features'  // Update this if your API uses a different base URL
    });

    return FeatureModel;
});
