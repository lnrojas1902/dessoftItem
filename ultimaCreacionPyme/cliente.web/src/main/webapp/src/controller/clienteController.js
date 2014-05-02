define(['controller/_clienteController','delegate/clienteDelegate','model/facturaModel','model/productoModel'],
function() {
    App.Controller.ClienteController = App.Controller._ClienteController.extend({
        
        postInit: function(options) {
            var self = this;
            
            this.clienteActual;
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
            
            Backbone.on(this.componentId+'-ver-producto', function(params) {
                
                self.verProducto(params);
            });
            this.listProductoTemplate = _.template($('#productoList').html());
            this.listProductoModelClass = options.listModelClass;
      
            this.verProductoTemplate = _.template($('#verProducto').html());
            
            
           //toolbar
            Backbone.on('show-login-cliente', function() {
               self._renderLogin();
            });
            Backbone.on('show-productos-cliente', function() {
               self.listProductos();
            });
            Backbone.on('show-carrito-cliente', function() {
               self.productosCarritoCliente();
            });
            Backbone.on('show-carrito-cliente', function() {
               self.productosCarritoCliente();
            });
            Backbone.on('act-factura', function() {
               self.actualizarFacturas();
            });
            
            
            Backbone.on('show-clientes', function() {
               self.listaClientes();
            });
            Backbone.on('show-facturas-cliente', function() {
               self.facturasCliente();
            });
            Backbone.on('logout-cliente', function() {
               self.logout();
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
        listProductos: function(){
            this.list();
        },
        verProducto: function(params){
            
            var self = this;
            var idProducto = params.producto;           
                      
            console.log('producto: '+idProducto );           
            
            self.clienteDeledate = new App.Delegate.ClienteDelegate();
            self.clienteDeledate.getProductoId(idProducto,function(data){
                
                 var model=new App.Model.ProductoModel(data);
                 console.log('producto:' +JSON.stringify(model));
                                    
                 //this.$el.slideUp("fast", function() {
            
               self.$el.html(self.verProductoTemplate({producto: model ,componentId: self.componentId}));
                            //self.$el.slideDown("fast");
               //});
               
            //Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'ver-Producto', view: self, id: params.id, data: data, error: 'Error in ver producto'});
            });
        },
        renderToolbarCliente: function(){
            var self = this;
            
            Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Login'});
                Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Cuenta'}); 
                Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Facturas'}); 
                Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Logout'}); 
                       
                       
                Backbone.trigger('asignar-nombre',
                       {name: self.currentClienteModel.getDisplay("name")});
        },
        renderToolbarInicio: function(){
            
            var self = this;
            
            Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Login'});
            Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Cuenta'}); 
            Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Facturas'}); 
            Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Logout'}); 
                       
                       
                Backbone.trigger('asignar-nombre',
                       {name: 'Cliente'});
        },
        print: function(){
            window.open("/cliente.service.subsystem.web/webresources/Cliente/report","_blank");
        },
        comprar: function(params){
            console.log('comprar' + params.id);
            var self=this;
            self.clienteDelegate = new App.Delegate.ClienteDelegate();
            self.clienteDelegate.comprarDelegate(params.id,function(data){
                self.actualizarFacturas();
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
            });
        },
        logout: function (){
            
            var self = this;
            self.currentClienteModel = new App.Model.ClienteModel();
            self.listProductos();
            
            self.renderToolbarInicio();
            
        },
        facturasCliente: function(){
            
            var idCliente = this.currentClienteModel.getDisplay("id");
            console.log('facturasCliente' + idCliente);
            var self=this;
            self.facturaModelList = new App.Model.FacturaList();
            
            self.clienteDelegate = new App.Delegate.ClienteDelegate();
            var contador = 0;
            self.clienteDelegate.facturasDelegate(idCliente,function(data){
                _.each(data, function(d) {
//Se hace el cï¿½lculo del nuevo campo
                 
                 var model=new App.Model.FacturaModel(d);
                 console.log('factura: '+(contador++)+" - facturaNombre:" +model.getDisplay("name"));
///*Ahora se instancia un SportPromModel, con un nuevo objeto JSON como parï¿½metro como constructor (antes sportModel), extrayendo los datos de ï¿½dï¿½.*/
//                var model = new App.Model.FacturaModel({name: d.attributes.name, 
//                    
//                        valor: d.attributes.valor, estado: d.attributes.estado,tipoDePago: d.attributes.tipoDePago,
//                fechaDeRealizacion: d.attributes.fechaDeRealizacion,fechaEsperadaEntrega: d.attributes.fechaEsperadaEntrega
//                ,dereccionDeEntrega: d.attributes.valor, clienteId: d.attributes.valor,});
                
                
                
//y se agrega finalmente a los modelos prom de la lista.
            self.facturaModelList.models.push(model);
            
            
     });
     
            self._renderFacturaList();
            Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-facturas', view: self, id: idCliente, data: data, error: 'Error in facturas de un cliente'});
            });
        },
        _renderFacturaList: function() {
               var self = this;
            /*Aquï¿½ se utiliza el efecto grï¿½fico backbone deslizar. ï¿½$elï¿½ hace referencia al <div id=ï¿½mainï¿½> ubicado en el index.html. Dentro de este div se despliegue la tabla.*/
               this.$el.slideUp("fast", function() {
            /*Establece que en el <div> se despliegue el template de la variable ï¿½listPromTemplateï¿½. Como parï¿½metros entran las variables establecidas dentro de los tags <%%> con sus valores como un objeto JSON. En este caso, la propiedad sports tendrï¿½ la lista que instanciï¿½ ï¿½sportSearchï¿½ en la variable del bucle <% _.each(sports, function(sport) { %>*/

               self.$el.html(self.listFactTemplate({facturas: self.facturaModelList.models}));
                            self.$el.slideDown("fast");
               });
         },
        clienteLogin: function() {
            var self = this;
            var model = $('#' + this.componentId + '-clienteFormLogin').serializeObject();
            this.currentClienteModel = new App.Model.ClienteModel ();
            this.currentClienteModel.set(model);
            self.clienteDelegate = new App.Delegate.ClienteDelegate();
            self.clienteDelegate.loginDelegate(
                self.currentClienteModel,
            
                function(data) {
                self.currentClienteModel=new App.Model.ClienteModel(data);
                self.clienteActual = self.currentClienteModel;
                //self._renderLogin();
                self.renderToolbarCliente();
                       
                self._renderEdit();
            }, 
            
            function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-login', view: self, id: '', data: data, error: 'No se pudo iniciar sesion'});
                alert("Usuario o password invalidos");
            });
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
            self.clienteDelegate = new App.Delegate.ClienteDelegate();
            self.clienteDelegate.registrarDelegate(self.currentClienteModel, function(data) {
                
                var model=new App.Model.ClienteModel(data);
                self.currentClienteModel = model;
                self.renderToolbarCliente();
                self._renderEdit();
            }, function(data) {
                //Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-search', view: self, id: '', data: data, error: 'Error in cliente search'});
                alert("Ya existe un cliente con el mismo docID");
            });
        },
        productosCarritoCliente: function(){
            if(!this.clienteModelList){
                 this.clienteModelList = new this.listModelClass();
	    }
          
            console.log('productos' );
            var self=this;
            self.productoCarritoModelList = new App.Model.ProductoList();
            self.clienteDeledate = new App.Delegate.ClienteDelegate();
            self.clienteDeledate.productosCarritoDelegate(function(data){
                _.each(data, function(d) {
                    var model=new App.Model.ProductoModel(d);
                    console.log('productos:' +JSON.stringify(model));
                    self.productoCarritoModelList.models.push(model);
                });
     
            self._renderProductosCarritoCliente();
            //Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
            });
        },       
        _renderProductosCarritoCliente: function() {
            console.log('productosRender: inicio');
               var self = this;
               this.$el.slideUp("fast", function() {
            
               self.$el.html(self.listProductoTemplate({productos: self.productoCarritoModelList.models, componentId: self.componentId}));
                            self.$el.slideDown("fast");
               });
         },
         actualizarFacturas: function(){
	    console.log('Facturas: ');
            
            $.ajax({
	          url: '/factura.service.subsystem.web/webresources/Factura/actualizarFacturas',
	          type: 'POST',
	          data: '',
	          contentType: 'application/json'
	      }).done(_.bind(function(data){
	    	 alert("Funcionó .|.");
	      },this)).error(_.bind(function(data){
	    	 alert("Error .|.");
	      },this));
	},
        
        
    });
    return App.Controller.ClienteController;
}); 