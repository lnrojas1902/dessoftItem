define([], function() {
    App.Model.ItemModel = Backbone.Model.extend({
        defaults: {
 

		 'cantidad' : ''
 ,  
		 'estado' : ''
 ,  
		 'productoId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 
         return this.get(name);
        }
    });

    App.Model.ItemList = Backbone.Collection.extend({
        model: App.Model.ItemModel,
        initialize: function() {
        }

    });
    return App.Model.ItemModel;
});