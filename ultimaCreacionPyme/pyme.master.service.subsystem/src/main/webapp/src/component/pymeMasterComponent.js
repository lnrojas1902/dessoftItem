define(['controller/selectionController', 'model/cacheModel', 'model/pymeMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/pymeComponent',
 'component/clienteComponent'
 ,
 'component/facturaComponent'
 ,
 'component/productoComponent'
 
 ],function(SelectionController, CacheModel, PymeMasterModel, CRUDComponent, TabController, PymeComponent,
 ClienteComponent
 ,
 FacturaComponent
 ,
 ProductoComponent
 ) {
    App.Component.PymeMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('pymeMaster');
            var uComponent = new PymeComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-pyme-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-pyme-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-pyme-list', function() {
                self.hideChilds();
            });
            Backbone.on('pyme-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'pyme-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-pyme-save', function(params) {
                self.model.set('pymeEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var clienteModels = self.clienteComponent.componentController.clienteModelList;
                self.model.set('listCliente', []);
                self.model.set('createCliente', []);
                self.model.set('updateCliente', []);
                self.model.set('deleteCliente', []);
                for (var i = 0; i < clienteModels.models.length; i++) {
                    var m = clienteModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createCliente').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateCliente').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < clienteModels.deletedModels.length; i++) {
                    var m = clienteModels.deletedModels[i];
                    self.model.get('deleteCliente').push(m.toJSON());
                }
                var facturaModels = self.facturaComponent.componentController.facturaModelList;
                self.model.set('listFactura', []);
                self.model.set('createFactura', []);
                self.model.set('updateFactura', []);
                self.model.set('deleteFactura', []);
                for (var i = 0; i < facturaModels.models.length; i++) {
                    var m = facturaModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createFactura').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateFactura').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < facturaModels.deletedModels.length; i++) {
                    var m = facturaModels.deletedModels[i];
                    self.model.get('deleteFactura').push(m.toJSON());
                }
                var productoModels = self.productoComponent.componentController.productoModelList;
                self.model.set('listProducto', []);
                self.model.set('createProducto', []);
                self.model.set('updateProducto', []);
                self.model.set('deleteProducto', []);
                for (var i = 0; i < productoModels.models.length; i++) {
                    var m = productoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createProducto').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateProducto').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < productoModels.deletedModels.length; i++) {
                    var m = productoModels.deletedModels[i];
                    self.model.get('deleteProducto').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'pyme-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Cliente", name: "cliente", enable: true},
                            ,
                            {label: "Factura", name: "factura", enable: true},
                            ,
                            {label: "Producto", name: "producto", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.PymeMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.clienteComponent = new ClienteComponent();
                    self.clienteModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ClienteModel), self.model.get('listCliente'));
                    self.clienteComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ClienteModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ClienteModel, App.Model.ClienteList, self.clienteModels)
                    });
                    self.clienteComponent.render(self.tabs.getTabHtmlId('cliente'));
                    Backbone.on(self.clienteComponent.componentId + '-post-cliente-create', function(params) {
                        params.view.currentClienteModel.setCacheList(params.view.clienteModelList);
                    });
					self.facturaComponent = new FacturaComponent();
                    self.facturaModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.FacturaModel), self.model.get('listFactura'));
                    self.facturaComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.FacturaModel),
                        listModelClass: App.Utils.createCacheList(App.Model.FacturaModel, App.Model.FacturaList, self.facturaModels)
                    });
                    self.facturaComponent.render(self.tabs.getTabHtmlId('factura'));
                    Backbone.on(self.facturaComponent.componentId + '-post-factura-create', function(params) {
                        params.view.currentFacturaModel.setCacheList(params.view.facturaModelList);
                    });
					self.productoComponent = new ProductoComponent();
                    self.productoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ProductoModel), self.model.get('listProducto'));
                    self.productoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ProductoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ProductoModel, App.Model.ProductoList, self.productoModels)
                    });
                    self.productoComponent.render(self.tabs.getTabHtmlId('producto'));
                    Backbone.on(self.productoComponent.componentId + '-post-producto-create', function(params) {
                        params.view.currentProductoModel.setCacheList(params.view.productoModelList);
                    });
                    self.clienteToolbarModel = self.clienteComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.clienteComponent.setToolbarModel(self.clienteToolbarModel);                    
                    self.facturaToolbarModel = self.facturaComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.facturaComponent.setToolbarModel(self.facturaToolbarModel);                    
                    self.productoToolbarModel = self.productoComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.productoComponent.setToolbarModel(self.productoToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'pyme-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.PymeMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.PymeMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.PymeMasterComponent;
});