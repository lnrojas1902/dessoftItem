define(['controller/_clienteController','delegate/clienteDelegate'], function() {
    App.Controller.ClienteController = App.Controller._ClienteController.extend({
        
        postInit: function(options) {
            var self = this;
            
            Backbone.on(this.componentId + '-toolbar-print', function(params) {
                self.print();
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
        },
        print: function(){
            window.open("/cliente.service.subsystem.web/webresources/Cliente/report","_blank");
        },
        comprar: function(params){
            console.log('comprar' + params.id);
            var self=this;
            self.comprarDelegate(params.id,function(data){
                alert('comprar');
            },function(data){
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-comprar', view: self, id: params.id, data: data, error: 'Error in cliente comprar'});
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