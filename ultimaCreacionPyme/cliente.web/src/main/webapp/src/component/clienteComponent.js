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
                       {name: 'Productos',icon: 'glyphicon-shopping-cart',event: 'show-productos-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                       {name: 'Login',icon: 'glyphicon-log-in',event: 'show-login-cliente'});
            Backbone.trigger(this.toolbarModel.get('componentId')+'-add-button',
                       {name: 'Cuenta',icon: 'glyphicon-user',event: 'show-cuenta-cliente'}); 
            Backbone.trigger(self.componentId + '-hide-button',
                       {name: 'Cuenta'});
           
        }
    });
    return App.Component.ClienteComponent;
});