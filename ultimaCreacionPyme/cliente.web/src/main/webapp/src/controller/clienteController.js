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
        },
        print: function(){
            window.open("/cliente.service.subsystem.web/webresources/Cliente/report","_blank");
        }
        
    });
    return App.Controller.ClienteController;
}); 