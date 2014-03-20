define([], function() {
    App.Model._ItemModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'cantidad' : ''
 ,  
		 'estado' : ''
 ,  
		 'productoId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 if(name=='productoId'){  
                 var value = App.Utils.getModelFromCache('productoComponent',this.get('productoId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._ItemList = Backbone.Collection.extend({
        model: App.Model._ItemModel,
        initialize: function() {
        }

    });
    return App.Model._ItemModel;
});