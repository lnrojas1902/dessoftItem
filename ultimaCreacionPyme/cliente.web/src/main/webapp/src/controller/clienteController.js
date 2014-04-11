define(['controller/_clienteController','delegate/clienteDelegate','model/facturaModel'], function() {
    App.Controller.ClienteController = App.Controller._ClienteController.extend({
        
        postInit: function(options) {
            var self = this;
            
//            Backbone.on(this.componentId + '-toolbar-print', function(params) {
//                self.print();
//            });
            
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
            
            this.listFactTemplate = _.template($('#facturaList').html());
            this.listFactModelClass = options.listModelClass;
            var self = this;
            Backbone.on(this.componentId + '-' + 'toolbar-print', function(params) {
                self.facturasCliente(params);
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
//Se hace el cálculo del nuevo campo
                 
                 var model=new App.Model.FacturaModel(d);
                 
///*Ahora se instancia un SportPromModel, con un nuevo objeto JSON como parámetro como constructor (antes sportModel), extrayendo los datos de “d”.*/
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
            /*Aquí se utiliza el efecto gráfico backbone deslizar. “$el” hace referencia al <div id=”main”> ubicado en el index.html. Dentro de este div se despliegue la tabla.*/
               this.$el.slideUp("fast", function() {
            /*Establece que en el <div> se despliegue el template de la variable “listPromTemplate”. Como parámetros entran las variables establecidas dentro de los tags <%%> con sus valores como un objeto JSON. En este caso, la propiedad sports tendrá la lista que instanció “sportSearch” en la variable del bucle <% _.each(sports, function(sport) { %>*/

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
	}
        
        
    });
    return App.Controller.ClienteController;
}); 