define(['ojs/ojmodel', 'models/featureModel'], function (oj, FeatureModel) {
    var FeatureCollection = oj.Collection.extend({
        url: 'http://localhost:9092/features',  // Make sure this endpoint is correct and available
        model: FeatureModel
    });

    return FeatureCollection;
});
