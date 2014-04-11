define(['model/facturaModel'], function() {
    App.Model.FacturaModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'valor' : ''
 ,  
		 'estado' : ''
 ,  
		 'tipoDePago' : ''
 ,  
		 'fechaDeRealizacion' : ''
 ,  
		 'fechaEsperadaEntrega' : ''
 ,  
		 'dereccionDeEntrega' : ''
 ,  
		 'clienteId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
             if(name=='fechaDeRealizacion'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaDeRealizacion'), this);
             }
             if(name=='fechaEsperadaEntrega'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fechaEsperadaEntrega'), this);
             }
			 if(name=='clienteId'){  
                 var value = App.Utils.getModelFromCache('clienteComponent',this.get('clienteId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model.FacturaList = Backbone.Collection.extend({
        model: App.Model.FacturaModel,
        initialize: function() {
        }

    });
    return App.Model.FacturaModel;
});