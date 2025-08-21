define([
  'knockout',
  'ojs/ojtable',
  'ojs/ojmodel',
  'models/FeatureCollection',
  'ojs/ojcollectiondataprovider',
  'ojs/ojbutton',
  'ojs/ojformlayout',
  'ojs/ojinputtext',
  'ojs/ojdialog'
], function (ko, ojtable, ojmodel, FeatureCollection, CollectionDataProvider) {

  function FeaturesViewModel() {
    var self = this;

    // Collection and data provider
    self.collection = new FeatureCollection();
    self.dataProvider = ko.observable();

    // Observables for form fields
    self.id = ko.observable();
    self.productId = ko.observable();
    self.featureName = ko.observable();
    self.isEnabled = ko.observable();
    self.feeAmount = ko.observable();
    self.feeFreq = ko.observable();

    // Fetch features
    self.fetchFeatures = function () {
      self.collection.fetch({
        success: function () {
          self.dataProvider(new CollectionDataProvider(self.collection));
          console.log("Features loaded");
        },
        error: function (jqXHR, textStatus) {
          console.error("Fetch error:", textStatus);
        }
      });
    };

    // Clear all input fields
    self.clearFields = function () {
      self.id('');
      self.productId('');
      self.featureName('');
      self.isEnabled('');
      self.feeAmount('');
      self.feeFreq('');
    };

    // Validation function
    self.validateInputs = function () {
      if (!self.productId() || isNaN(Number(self.productId()))) {
        alert("Product ID is required and must be numeric.");
        return false;
      }
      if (!self.featureName() || self.featureName().length > 100) {
        alert("Feature Name is required and maximum 100 characters allowed.");
        return false;
      }
      if (!self.isEnabled() || !["Y", "N"].includes(self.isEnabled().toUpperCase())) {
        alert("Is Enabled must be 'Y' or 'N'.");
        return false;
      }
      if (self.feeAmount() !== '' && (isNaN(Number(self.feeAmount())) || Number(self.feeAmount()) < 0)) {
        alert("Fee Amount must be a positive number or zero.");
        return false;
      }
      if (self.feeFreq() && self.feeFreq().length > 50) {
        alert("Fee Frequency maximum length is 50 characters.");
        return false;
      }
      return true;
    };

    // Dialog open functions
    self.openAddDialog = function () {
      self.clearFields();
      document.getElementById('addFeatureDialog').open();
    };

    self.openEditDialog = function () {
      // For editing, populate fields from selected row
      var selection = self.collection.at(0); // or get selected row in your way
      if (selection) {
        self.id(selection.id);
        self.productId(selection.get('productId'));
        self.featureName(selection.get('featureName'));
        self.isEnabled(selection.get('isEnabled'));
        self.feeAmount(selection.get('feeAmount'));
        self.feeFreq(selection.get('feeFreq'));
        document.getElementById('editFeatureDialog').open();
      } else {
        alert("Please select a feature to edit.");
      }
    };

    self.openDeleteDialog = function () {
      // For deleting, just set ID to delete
      var selection = self.collection.at(0); // or get selected row in your way
      if (selection) {
        self.id(selection.id);
        document.getElementById('deleteFeatureDialog').open();
      } else {
        alert("Please select a feature to delete.");
      }
    };

    // Add feature
    self.addFeature = function () {
      if (!self.validateInputs()) return;

      var newFeature = {
        id: self.id() ? Number(self.id()) : undefined, // undefined to let backend generate ID
        productId: Number(self.productId()),
        featureName: self.featureName(),
        isEnabled: self.isEnabled().toUpperCase(),
        feeAmount: self.feeAmount() !== '' ? Number(self.feeAmount()) : 0,
        feeFreq: self.feeFreq() || "ONE_TIME"
      };

      self.collection.create(newFeature, {
        wait: true,
        success: function () {
          alert("Feature added successfully.");
          document.getElementById("addFeatureDialog").close();
          self.fetchFeatures();
        },
        error: function () {
          alert("Failed to add feature.");
        }
      });
    };

    // Edit feature
    self.editFeature = function () {
      if (!self.validateInputs()) return;

      var feature = self.collection.get(self.id());
      if (feature) {
        feature.set({
          productId: Number(self.productId()),
          featureName: self.featureName(),
          isEnabled: self.isEnabled().toUpperCase(),
          feeAmount: self.feeAmount() !== '' ? Number(self.feeAmount()) : 0,
          feeFreq: self.feeFreq() || "ONE_TIME"
        });

        feature.save(null, {
          wait: true,
          success: function () {
            alert("Feature updated successfully.");
            document.getElementById("editFeatureDialog").close();
            self.fetchFeatures();
          },
          error: function () {
            alert("Failed to update feature.");
          }
        });
      } else {
        alert("Feature not found.");
      }
    };

    // Delete feature
    self.deleteFeature = function () {
      var feature = self.collection.get(self.id());
      if (feature) {
        feature.destroy({
          wait: true,
          success: function () {
            alert("Feature deleted successfully.");
            document.getElementById("deleteFeatureDialog").close();
            self.fetchFeatures();
          },
          error: function () {
            alert("Failed to delete feature.");
          }
        });
      } else {
        alert("Feature not found.");
      }
    };

    // Initial load
    self.fetchFeatures();
  }

  return new FeaturesViewModel();
});
