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
            
            this.listProductoTemplate = _.template($('#productoList').html());
            this.listProductoModelClass = options.listModelClass;
      
            
            
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
        print: function(){
            window.open("/cliente.service.subsystem.web/webresources/Cliente/report","_blank");
        },
        comprar: function(params){
            console.log('comprar' + params.id);
            var self=this;
            self.clienteDelegate = new App.Delegate.ClienteDelegate();
            self.clienteDelegate.comprarDelegate(params.id,function(data){
                
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
            });
        },
        logout: function (){
            
            var self = this;
            self.currentClienteModel = new App.Model.ClienteModel();
            self.listProductos();
            
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
        facturasCliente: function(params){
            
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
     
            self._renderFacturaList(params);
            Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
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
                       
                self._renderEdit();
            }, 
            
            function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-login', view: self, id: '', data: data, error: 'No se pudo iniciar sesion'});
                alert("Usuario o contraseña inválidos");
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
            
               self.$el.html(self.listProductoTemplate({productos: self.productoCarritoModelList.models}));
                            self.$el.slideDown("fast");
               });
         }
        
        
    });
    return App.Controller.ClienteController;
}); 