define(['controller/_clienteController','delegate/clienteDelegate','model/facturaModel','model/productoModel'],
function() {
    App.Controller.ClienteController = App.Controller._ClienteController.extend({
        
        postInit: function(options) {
            var self = this;
            
//            Backbone.on(this.componentId + '-toolbar-print', function(params) {
//                self.print();
//            });
            Backbone.on(this.componentId+'-'+'cliente-registrar', function() {
                
               self.registrarCliente();
            });
            
            this.searchTemplate = _.template($('#clienteSearch').html()+$('#clienteList').html());
 
            Backbone.on(this.componentId + '-' + 'toolbar-search', function(params) {
                self.search(params);
            });
            Backbone.on(this.componentId+'-cliente-search', function(params) {
                self.clienteSearch(params);
            });
             this.selection=new App.Controller.SelectionController({});
           
 
            Backbone.on('cliente-comprar',function(params){
                self.comprar(params);
            });
            
            Backbone.on('cliente-facturas',function(params){
                
                self.facturasCliente(params);
            });
            this.loginTemplate = _.template($('#clienteLogin').html());
            
            this.listFactTemplate = _.template($('#facturaList').html());
            this.listFactModelClass = options.listModelClass;
            var self = this;
            Backbone.on(this.componentId + '-' + 'toolbar-print', function(params) {
                self.facturasCliente(params);
            });
            
            Backbone.on(this.componentId+'-cliente-login', function(params) {
                self.clienteLogin(params);
            });
            
            this.listProductoTemplate = _.template($('#productoList').html());
            this.listProductoModelClass = options.listModelClass;
      
            
            
           //toolbar
            Backbone.on('show-login-cliente', function() {
               self._renderLogin();
            });
            Backbone.on('show-productos-cliente', function() {
               self.listProductos();
            });
            Backbone.on('show-cuenta-cliente', function() {
               self._renderEdit();
            });
            
            
          
          //Lo siguiente es un ejemplo para hacer modificaciones en el toolbar
          this.p1 = true;
           Backbone.on('button2-prueba', function(params) {
                alert('prueba2');
                if ( self.p1)
                {
                    Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Prueba'});
                       self.p1 = false;
                }
                else
                {
                    Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Prueba'});
                       self.p1 = true;
                }
            });
        },
        print: function(){
            window.open("/cliente.service.subsystem.web/webresources/Cliente/report","_blank");
        },
        comprar: function(params){
            console.log('comprar' + params.id);
            var self=this;
            self.comprarDelegate(params.id,function(data){
                
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
            });
        },
        facturasCliente: function(params){
            
          
            console.log('facturasCliente' + params.id);
            var self=this;
            self.facturaModelList = new App.Model.FacturaList();
            
            
            self.facturasDelegate(params.id,function(data){
                _.each(data, function(d) {
//Se hace el c�lculo del nuevo campo
                 
                 var model=new App.Model.FacturaModel(d);
                 
///*Ahora se instancia un SportPromModel, con un nuevo objeto JSON como par�metro como constructor (antes sportModel), extrayendo los datos de �d�.*/
//                var model = new App.Model.FacturaModel({name: d.attributes.name, 
//                    
//                        valor: d.attributes.valor, estado: d.attributes.estado,tipoDePago: d.attributes.tipoDePago,
//                fechaDeRealizacion: d.attributes.fechaDeRealizacion,fechaEsperadaEntrega: d.attributes.fechaEsperadaEntrega
//                ,dereccionDeEntrega: d.attributes.valor, clienteId: d.attributes.valor,});
                
                
                
//y se agrega finalmente a los modelos prom de la lista.
            self.facturaModelList.models.push(model);
            
            
     });
     
            self._renderFacturaList(params);
            Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
            });
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
        _renderFacturaList: function() {
               var self = this;
            /*Aqu� se utiliza el efecto gr�fico backbone deslizar. �$el� hace referencia al <div id=�main�> ubicado en el index.html. Dentro de este div se despliegue la tabla.*/
               this.$el.slideUp("fast", function() {
            /*Establece que en el <div> se despliegue el template de la variable �listPromTemplate�. Como par�metros entran las variables establecidas dentro de los tags <%%> con sus valores como un objeto JSON. En este caso, la propiedad sports tendr� la lista que instanci� �sportSearch� en la variable del bucle <% _.each(sports, function(sport) { %>*/

               self.$el.html(self.listFactTemplate({facturas: self.facturaModelList.models}));
                            self.$el.slideDown("fast");
               });
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
        clienteLogin: function() {
            var self = this;
            var model = $('#' + this.componentId + '-clienteFormLogin').serializeObject();
            this.currentClienteModel = new App.Model.ClienteModel ();
            this.currentClienteModel.set(model);
            self.loginP(
                self.currentClienteModel,
            
                function(data) {
                self.currentClienteModel=new App.Model.ClienteModel(data);
                //self._renderLogin();
                Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Login'});
                Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Cuenta'}); 
                Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Facturas'}); 
                Backbone.trigger('asignar-nombre',
                       {name: self.currentClienteModel.getDisplay("name")});
                       
                self._renderEdit();
            }, 
            
            function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-login', view: self, id: '', data: data, error: 'No se pudo iniciar sesion'});
            });
        },
        loginP: function(cliente, callback, callbackError) {
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
        _renderLogin: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.loginTemplate({clienteP: self.currentClienteModel, componentId: self.componentId}));
                self.$el.slideDown("fast");
            });
        },
        registrarCliente: function() {
            
           
            var self = this;
            var model = $('#' + this.componentId + '-clienteFormRegistro').serializeObject();
            this.currentClienteModel = new App.Model.ClienteModel();
            this.currentClienteModel.set(model);
            self.registrarDelegate(self.currentClienteModel, function(data) {
                
                var model=new App.Model.ClienteModel(data);
                self._renderEdit();
            }, function(data) {
                //Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-search', view: self, id: '', data: data, error: 'Error in cliente search'});
                alert("Ya existe un cliente con el mismo docID");
            });
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
        listProductos: function(){
            
          
            console.log('productos' );
            var self=this;
            self.productoModelList = new App.Model.ProductoList();
				
            self.productosDelegate(function(data){
                _.each(data, function(d) {
                    var model=new App.Model.ProductoModel(d);
                    console.log('productos:' +JSON.stringify(model));
                    self.productoModelList.models.push(model);
                });
     
            self._renderProductosList();
            //Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
            });
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
        _renderProductosList: function() {
            console.log('productosRender: inicio');
               var self = this;
               this.$el.slideUp("fast", function() {
            
               self.$el.html(self.listProductoTemplate({productos: self.productoModelList.models}));
                            self.$el.slideDown("fast");
               });
         }
        
        
    });
    return App.Controller.ClienteController;
}); 