define(['controller/_clienteController','delegate/clienteDelegate','model/facturaModel','model/productoModel', 'model/itemModel'],
function() {
    App.Controller.ClienteController = App.Controller._ClienteController.extend({
        
        postInit: function(options) {
            var self = this;
            
            this.clienteActual;
            this.carritoListTemplate = _.template($('#carritoList').html());
            
            
//            Backbone.on(this.componentId + '-toolbar-print', function(params) {
//                self.print();
//            });

            Backbone.on(this.componentId+'-votar', function(params) {
                
                self.votar(params);
            });
            
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
            this.acercaNosotrosTemplate = _.template($('#acercaNosotros').html());
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
            this.verFacturaTemplate = _.template($('#verFactura').html());
            
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
            Backbone.on('show-cuenta-cliente', function() {
               self._renderEdit();
            });
            Backbone.on('act-factura', function() {
               self.actualizarFacturas();
            });
            Backbone.on('acerca-nosotros', function() {
               self.acercaDeNosotros();
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
            
            
            Backbone.on(this.componentId+'-comprar-producto', function(params) {
                self.addProductoACarrito(params);
            });
            Backbone.on(this.componentId+'-confirmar-compra-cliente', function(params) {
                self.confirmarCompra();
            });
            
            
            Backbone.on(this.componentId+'-ver-factura', function(params) {
                
                self.verFactura(params);
            });
            
             Backbone.on(this.componentId+'-buscarProducto', function(params) {
                
                self.buscarProducto(params);
            });
            Backbone.on(this.componentId+'-buscarProductosCosto', function(params) {
                
                self.buscarProductosCosto(params);
            });
            Backbone.on(this.componentId+'-buscarProductosPeso', function(params) {
                
                self.buscarProductosPeso(params);
            });
           
           Backbone.on(this.componentId+'-eliminar-producto-carrito', function(params) {
               self.eliminarProducto(params);
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
                       {name: 'Compras'}); 
                Backbone.trigger(self.componentId + '-show-button',
                       {name: 'Logout'}); 
                
                 Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Clientes'}); 
                       
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
                       {name: 'Compras'}); 
            Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Logout'}); 
           
                 Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Clientes'}); 
                       
                       
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
            self.currentClienteModel = null;
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

               self.$el.html(self.listFactTemplate({facturas: self.facturaModelList.models, componentId: self.componentId}));
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
            var self = this;
            if(!this.clienteModelList){
                 this.clienteModelList = new this.listModelClass();
	    }
            console.log('productos Carrito' );
            self._renderProductosCarritoCliente();
//            var self=this;
//            self.productoCarritoModelList = new App.Model.ProductoList();
//            self.clienteDeledate = new App.Delegate.ClienteDelegate();
//            self.clienteDeledate.productosCarritoDelegate(function(data){
//                _.each(data, function(d) {
//                    var model=new App.Model.ProductoModel(d);
//                    console.log('productos:' +JSON.stringify(model));
//                    self.productoCarritoModelList.models.push(model);
//                });
//     
//            self._renderProductosCarritoCliente();
//            //Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
//            },function(data){
//                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
//            });
        },  
       addProductoACarrito: function(params){
            var self=this;
            if ( !self.productoCarritoModelList )
            {
                self.productoCarritoModelList = new App.Model.ProductoList();
            }
            var model = $('#' + this.componentId + '-productoToAdd').serializeObject();
            var model1=new App.Model.ProductoModel( model);
            console.log('producto añadido:' +JSON.stringify(model1));
            var estaba = false;
            var costo = 0;
            _.each(self.productoCarritoModelList.models, function(d) {
                if ( d.getDisplay('productoId') === model1.getDisplay('productoId'))
                {
                    d.set('cantidad', (parseInt(d.getDisplay('cantidad'))+parseInt(model1.getDisplay('cantidad'))) );
                    estaba = true;
                }
                    costo = costo + parseInt(d.getDisplay('costo')); 
                    console.log('* ' + d.getDisplay('name')  + ' costo: ' + costo.toString());
            });
            if (!estaba)
            {
                self.productoCarritoModelList.models.push(model1);
            }
        
            self._renderProductosCarritoCliente();
        },
        eliminarProducto: function(params){
            var self=this;
            self.listaApoyoCarritoList = new App.Model.ProductoList();;
            _.each(self.productoCarritoModelList.models, function(d) {
                console.log('comparar :' + d.getDisplay('productoId') + '  ' + params.idProducto);
                if ( parseInt(d.getDisplay('productoId')) === params.idProducto)
                {
                    console.log('find');
                }
                else
                {
                    self.listaApoyoCarritoList.models.push(d);
                }
            });
            self.productoCarritoModelList = self.listaApoyoCarritoList;
            self._renderProductosCarritoCliente();
        },
        confirmarCompra: function(){
            var self=this;
            
            if(!self.currentClienteModel){
                 self._renderLogin();
                 alert("Para poder comprar debes iniciar sesión o registrarte. ");
	    }
            else
            {
                var datosDeEntrega = $('#' + this.componentId + '-clienteForm-confirmarCompra').serializeObject();
                var datosDeEntrega2 =new App.Model.ProductoModel( datosDeEntrega);
                self.itemsAComprar = new App.Model.ItemList ();
                
                _.each(self.productoCarritoModelList.models, function(d) {
                    var item = new App.Model.ItemModel();
                    item.set ('cantidad', d.getDisplay('cantidad'));
                    item.set ( 'productoId', d.getDisplay('productoId') ) ;
                    console.log(d.getDisplay('productoId'));
                    console.log(d.getDisplay('cantidad'));
                    
                    self.itemsAComprar.models.push(item);
                });
                
                self.clienteDelegate = new App.Delegate.ClienteDelegate();
                self.clienteDelegate.confirmarCompraDelegate(self.currentClienteModel, self.itemsAComprar,datosDeEntrega2 , function(data) {
                self.productoCarritoModelList = new App.Model.ProductoList();
                
                self.facturasCliente();
                alert("Muchas gracias por su compra. Esperamos que su experienca como usario de nuestro servicio haya sido la mejor.");
                }, function(data) {
                    
                    alert("confirmarcompra 2 error")
                });
            }
        },
        _renderProductosCarritoCliente: function() {
            console.log('productosCarritoRender: inicio');
               var self = this;
               if ( !self.productoCarritoModelList)
               {
                   self.productoCarritoModelList = new App.Model.ProductoList();
               }
               
               this.$el.slideUp("fast", function() {
            
               self.$el.html(self.carritoListTemplate({productos: self.productoCarritoModelList.models, componentId: self.componentId}));
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
	    	 alert("Funcionï¿½ .|.");
	      },this)).error(_.bind(function(data){
	    	 alert("Error");
	      },this));
	},
        buscarProducto: function(){
	   var self=this;
            
            
                var nombre = $('#' + this.componentId + '-buscarProducto').serializeObject();
                var producto = new App.Model.ProductoModel(nombre);
                
                self.clienteDelegate = new App.Delegate.ClienteDelegate();
                self.clienteDelegate.buscarProductoDelegate(producto,function(data) {
                    var producto2 = new App.Model.ProductoModel(data);
                    
                    console.log('data'+data);
                    console.log('producto2 id: '+producto2.id);
                    self.verProducto({producto: producto2.id});
                    
                 console.log('PRODUCTO BUSCADO');
                }, function(data) {
                    
                    alert("No se encontro el producto, puede que el nombre que ingreso sea incorrecto.");
                });
             
	},
        buscarProductosCosto: function(){
	   var self=this;
            
            
                var costo = $('#' + this.componentId + '-buscarProductosCosto').serializeObject();
                console.log('costo YYYY:'+costo);
                console.log('costo YYYY2:'+JSON.stringify(costo));
                var producto = new App.Model.ProductoModel();
                producto.set('costo',parseInt(costo.costo));
                console.log('costo YYYY3:'+JSON.stringify(producto));
                self.clienteDelegate = new App.Delegate.ClienteDelegate();
                self.productoModelList = new App.Model.ProductoList();
                self.clienteDelegate.buscarProductosCostoDelegate(producto,function(data) {
                    
                    _.each(data, function(d) {
                        console.log('costo YY7:'+JSON.stringify(d));
                        var model=new App.Model.ProductoModel(d);
                        console.log('costo YY8:'+JSON.stringify(model));
                    //console.log('productos:' +JSON.stringify(model));
                        self.productoModelList.models.push(model);
                    });
     
                    self._renderProductosList();
                    
                    console.log('PRODUCTO BUSCADO costo');
                }, function(data) {
                    
                    alert("No se encontro el producto, puede que el costo que ingreso no se encuentre.");
                });
             
	},
        buscarProductosPeso: function(){
	   var self=this;
            
            
                var peso = $('#' + this.componentId + '-buscarProductosPeso').serializeObject();
                console.log('peso YYYY:'+peso);
                console.log('peso YYYY2:'+JSON.stringify(peso));
                var producto = new App.Model.ProductoModel();
                producto.set('peso',parseInt(peso.peso));
                console.log('peso YYYY3:'+JSON.stringify(producto));
                self.clienteDelegate = new App.Delegate.ClienteDelegate();
                self.productoModelList = new App.Model.ProductoList();
                self.clienteDelegate.buscarProductosPesoDelegate(producto,function(data) {
                    
                    _.each(data, function(d) {
                        console.log('peso YY7:'+JSON.stringify(d));
                        var model=new App.Model.ProductoModel(d);
                        console.log('peso YY8:'+JSON.stringify(model));
                    //console.log('productos:' +JSON.stringify(model));
                        self.productoModelList.models.push(model);
                    });
     
                    self._renderProductosList();
                    
                    console.log('PRODUCTO BUSCADO peso');
                }, function(data) {
                    
                    alert("No se encontro el producto, puede que el costo que ingreso no se encuentre.");
                });
             
	},
        acercaDeNosotros: function(){
	    console.log('Acerca De Nosotros: ');
            var self = this;
            
               
           this.$el.slideUp("fast", function() {

           self.$el.html(self.acercaNosotrosTemplate());
                        self.$el.slideDown("fast");
           });
            
	},
        verFactura: function(params){
            
            var self = this;
            var idFactura = params.factura;           
                      
            console.log('factura: '+idFactura );           
            
            self.clienteDeledate = new App.Delegate.ClienteDelegate();
            self.clienteDeledate.getFacturaId(idFactura,function(data){
                
                var model=new App.Model.FacturaModel(data);
                     console.log('factura:' +JSON.stringify(model));
                     
                 self.clienteDeledate.getItemsFactura(idFactura,function(data){
                
                     self.itemsFactura = new App.Model.ItemList ();
                
                    _.each(data, function(d) {
                        var item = new App.Model.ItemModel(d);
                        var productoId = item.getDisplay('productoId');
                        console.log('item-prodID '+item.id );           
            
                        self.clienteDeledate.getProductoId(productoId, function(data){
                            var producto = new App.Model.ProductoModel(data);
                            console.log('producto: '+producto.getDisplay('name') );           
                            console.log(' - '+producto.getDisplay('imagen') ); 
                            console.log(' - '+producto.getDisplay('costo') ); 
                            
                            
                            
                           item.set ('nombre', producto.getDisplay('name'));
                            item.set ('imagen', producto.getDisplay('imagen'));
                            item.set ('costo', producto.getDisplay('costo'));
                            console.log('item-prodID 2 '+item.id );
                            console.log('item-prodName 2 '+item.getDisplay('nombre') );
                            console.log('item-prodImagen 2 '+item.getDisplay('imagen') );
                            console.log('item-prodCantidad 2 '+item.getDisplay('cantidad') );
                            console.log('item-prodCosto 2 '+item.getDisplay('costo'));
                             self.itemsFactura.models.push(item);                
                             self.$el.html(self.verFacturaTemplate({factura: model ,componentId: self.componentId, items: self.itemsFactura.models}));
                        },function(data){
                                console.log('Error producto Id' ); 
                        });
                       
                    });
                    
                     //this.$el.slideUp("fast", function() {

                  
                                //self.$el.slideDown("fast");
                           //});

                    //Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
                    },function(data){
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'ver-Factura', view: self, id: params.id, data: data, error: 'Error in ver factura'});
                    });
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'ver-Factura', view: self, id: params.id, data: data, error: 'Error in ver factura'});
            });
        },
        
        votar:function(params){

            var self = this; 
            
            var idFactura = params.factura;
            var calificacion = params.calificacion;
            
            console.log("idFactura: "+idFactura+"; calificacion" + calificacion);
            self.clienteDelegate = new App.Delegate.ClienteDelegate();
            
            var factura = new App.Model.FacturaModel();
            factura.set ('clienteId',idFactura);
            factura.set ('calificacion',calificacion);

            self.clienteDeledate.agregarCalificacion(factura,
                function(data){
                    self.verFactura({factura : idFactura});  
                    alert("Se ha registrado tu calificacion satisfactoriamente");
                },
                function(data) {
                //Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-search', view: self, id: '', data: data, error: 'Error in cliente search'});
                alert("Se ha producido un error inesperado");
            });
       } 
       
    });
    return App.Controller.ClienteController;
}); 