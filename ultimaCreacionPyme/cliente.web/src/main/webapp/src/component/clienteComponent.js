define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/clienteModel', 'controller/clienteController'], function() {
    App.Component.ClienteComponent = App.Component._CRUDComponent.extend({
        name: 'cliente',
        model: App.Model.ClienteModel,
        listModel: App.Model.ClienteList,
        controller : App.Controller.ClienteController,
         postInit: function(){
            var self=this;
 
            //se modifica el nombre del botón "Create" por "Crear"
            this.toolbarModel.set('createName','Crear');
            //se modifica el titulo del Toolbar 
            //this.toolbarModel.set('title','Crear Carrito');
 
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                {name: 'Productos',icon: 'glyphicon-list',event: 'show-productos-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                {name: 'Carrito',icon: 'glyphicon-shopping-cart',event: 'show-carrito-cliente'});            
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                {name: 'Login',icon: 'glyphicon-log-in',event: 'show-login-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                {name: 'Cuenta',icon: 'glyphicon-user',event: 'show-cuenta-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                {name: 'Compras',icon: 'glyphicon-folder-open',event: 'show-facturas-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                {name: 'Clientes',icon: 'glyphicon-align-justify',event: 'show-clientes'});
             Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                       {name: 'Logout',icon: 'glyphicon-log-out',event: 'logout-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                       {name: 'Act',icon: 'glyphicon-log-out',event: 'act-factura'});
                       
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                       {name: 'About us',icon: 'glyphicon-eye-open',event: 'acerca-nosotros'});
             
             Backbone.trigger(self.componentId + '-hide-button',
                {name: 'Cuenta'});
            Backbone.trigger(self.componentId + '-hide-button',
                {name: 'Compras'});
            Backbone.trigger(self.componentId + '-hide-button',
                {name: 'Logout'});  
                       
            Backbone.on('asignar-nombre', function(params) {
                self.toolbarModel.set('title',params.name);
            });  
            
            Backbone.on('mostrar-save', function() {
                self.toolbarModel.set('showSave',true);
            });
            
            Backbone.on('ocultar-save', function() {
                self.toolbarModel.set('showSave',false);
            }); 
            
            this.toolbarModel.set('title','Cliente');
            this.toolbarModel.set('showPrint',false);
            this.toolbarModel.set('showRefresh',false);
            this.toolbarModel.set('showCreate',false);
             this.toolbarModel.set('showSearch',false);
             
             
                 Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Clientes'}); 
             
        }
    });
    return App.Component.ClienteComponent;
});