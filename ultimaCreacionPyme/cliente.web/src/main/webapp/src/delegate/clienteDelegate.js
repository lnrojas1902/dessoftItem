define(['delegate/_clienteDelegate'], function() {
    App.Delegate.ClienteDelegate = App.Delegate._ClienteDelegate.extend({
        search: function(cliente, callback, callbackError) {
            console.log('Cliente Search: ');
            $.ajax({
                url: '/cliente.service.subsystem.web/webresources/Cliente/search',
                type: 'POST',
                data: JSON.stringify(cliente),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        },
        comprar: function(id,callback,callbackError){
	    console.log('comprar: '+id);
            $.ajax({
	          url: '/cliente.service.subsystem/webresources/Cliente/'+id+'/comprar',
	          type: 'PUT',
	          data: {},
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        productosDelegate: function(callback,callbackError){
	    console.log('productosDelegate: ');
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/listar',
	          type: 'POST',
	          data: '',
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        productosCarritoDelegate: function(callback,callbackError){
	    console.log('productosDelegate: ');
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/listar',
	          type: 'POST',
	          data: '',
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	}
    });
});