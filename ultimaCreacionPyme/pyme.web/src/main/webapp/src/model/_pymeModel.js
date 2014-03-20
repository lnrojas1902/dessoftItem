define([], function() {
    App.Model._PymeModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'descripcion' : ''
 ,  
		 'idCatalogo' : ''
 ,  
		 'facturaId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 if(name=='facturaId'){  
                 var value = App.Utils.getModelFromCache('facturaComponent',this.get('facturaId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._PymeList = Backbone.Collection.extend({
        model: App.Model._PymeModel,
        initialize: function() {
        }

    });
    return App.Model._PymeModel;
});