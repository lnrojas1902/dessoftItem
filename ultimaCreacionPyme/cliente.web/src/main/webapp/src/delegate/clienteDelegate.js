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
        buscarProductoDelegate: function(producto,callback,callbackError){
	    console.log('productosDelegate: ');
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/buscarProducto',
	          type: 'POST',
	          data: JSON.stringify(producto),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        buscarProductosCostoDelegate: function(producto,callback,callbackError){
	    console.log('productosDelegate: ');
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/buscarProductosCosto',
	          type: 'POST',
	          data: JSON.stringify(producto),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        buscarProductosPesoDelegate: function(producto,callback,callbackError){
	    console.log('productosDelegate: peso ');
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/buscarProductosPeso',
	          type: 'POST',
	          data: JSON.stringify(producto),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        getProductoId: function(id,callback,callbackError){
	    console.log('getProductoId: ');
            
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
        getFacturaId: function(id,callback,callbackError){
	    console.log('facturaDelegate: ');
            
            $.ajax({
	          url: '/factura.service.subsystem.web/webresources/Factura/getFacturaId',
	          type: 'POST',
	          data: JSON.stringify(id),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        getItemsFactura: function(id,callback,callbackError){
	    console.log('facturaDelegate: ');
            
            $.ajax({
	          url: '/factura.master.service.subsystem/webresources/FacturaMaster/getItemsFactura',
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
	          url: '/producto.service.subsystem.web/webresources/Producto/comprar',
	          type: 'POST',
	          data: JSON.stringify(id),
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        confirmarCompraDelegate: function(usuario, items, datosDeEntrega, callback,callbackError){
	    
            
            $.ajax({
	          url: '/producto.service.subsystem.web/webresources/Producto/comprar',
	          type: 'POST',
	          data: '{ "clienteEntity":' +  JSON.stringify(usuario) 
                          + ', "items":' +  JSON.stringify(items.models) 
                          + ', "direccion":' +  JSON.stringify(datosDeEntrega.getDisplay('direccion')) 
                          + ', "metodoPago":' +  JSON.stringify(datosDeEntrega.getDisplay('pago')) 
                          + '}',
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	},
        
        agregarCalificacion: function(factura, callback, callbackError){
	    console.log('facturaCalificar: Estamos en Delegate');
            console.log("DatosFactura"+JSON.stringify(factura));
            $.ajax({
	          url: '/factura.service.subsystem.web/webresources/Factura/calificar',
	          type: 'POST',
	          data: '{"id":'+ JSON.stringify(factura.getDisplay('clienteId'))
                  + ', "calificacion":'+ JSON.stringify(factura.getDisplay('calificacion'))
                  + '}',
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	  callback(data);
	      },this)).error(_.bind(function(data){
	    	  callbackError(data);
	      },this));
	}
        
    });
});