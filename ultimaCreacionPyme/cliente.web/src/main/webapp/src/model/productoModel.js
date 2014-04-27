define(['model/productoModel'], function() {
    App.Model.ProductoModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'costo' : ''
 ,  
		 'peso' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model.ProductoList = Backbone.Collection.extend({
        model: App.Model.ProductoModel,
        initialize: function() {
        }

    });
    return App.Model.FacturaModel;
});