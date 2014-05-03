define(['model/productoModel'], function() {
    App.Model.ProductoModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'costo' : ''
 ,  
		 'peso' : ''
 ,  
		 'imagen' : ''                
 
        },
        initialize: function() {
        },
        getDisplay: function(name) {
            if(name==='costoAsNumber'){
                   var numberConverter = App.Utils.Converter.number;
                   return numberConverter.unserialize(this.get('costo'), this);
             }
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