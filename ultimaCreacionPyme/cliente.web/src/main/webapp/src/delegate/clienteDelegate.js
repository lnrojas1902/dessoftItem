define(['delegate/_clienteDelegate'], function() {
    App.Delegate.ClienteDelegate = App.Delegate._ClienteDelegate.extend({
        
        searchDelegate: function(cliente, callback, callbackError) {
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
        getProductoId: function(id,callback,callbackError){
	    console.log('productosDelegate: ');
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/getProductoId',
	          type: 'POST',
	          data: JSON.stringify(id),
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
	},
        facturasDelegate: function(id,callback,callbackError){
	    console.log('facturaDelegate: '+id);
            
            $.ajax({
	          url: '/factura.service.subsystem.web/webresources/Factura/facturasCliente',
	          type: 'POST',
	          data: JSON.stringify(id),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
                  
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        loginDelegate: function(cliente, callback, callbackError) {
            console.log('Cliente Login: ');
            $.ajax({
                url: '/cliente.service.subsystem.web/webresources/Cliente/login',
                type: 'POST',
                data: JSON.stringify(cliente),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        },
        registrarDelegate: function(cliente, callback, callbackError) {
            
            console.log('Cliente Search: ');
            $.ajax({
                url: '/cliente.service.subsystem.web/webresources/Cliente/registrarCliente',
                type: 'POST',
                data: JSON.stringify(cliente),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        },
        comprarDelegate: function(id,callback,callbackError){
	    console.log('comprar: '+id);
            
            $.ajax({
	          url: '/cliente.service.subsystem.web/webresources/Cliente/comprar',
	          type: 'POST',
	          data: JSON.stringify(id),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        confirmarCompraDelegate: function(usuario, items,callback,callbackError){
	    
            
            $.ajax({
	          url: '/cliente.service.subsystem.web/webresources/Cliente/confirmar',
	          type: 'POST',
	          data: '{ "clienteEntity":' +  JSON.stringify(usuario) + ', "items":' +  JSON.stringify(items.models) + '}',
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	}
    });
});