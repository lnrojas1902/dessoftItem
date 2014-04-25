define(['controller/_facturaController','delegate/facturaDelegate'], function() {
    App.Controller.FacturaController = App.Controller._FacturaController.extend({
         postInit: function(options) {
            var self = this;
            this.searchTemplate = _.template($('#facturaSearch').html()+$('#facturaList').html());
 
            Backbone.on(this.componentId + '-' + 'toolbar-search', function(params) {
                self.search(params);
            });
            Backbone.on(this.componentId+'-factura-search-by-date', function(params) {
                self.userSearch(params);
            });
        },
        _renderSearch: function() {
 
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.searchTemplate({componentId: self.componentId,
                    facturas: self.facturaModelList.models,
                    factura: self.currentFacturaModel,
                    showEdit: false,
                    showDelete:false
                }));
                self.$el.slideDown("fast");
            });
        },
        search: function() {
            this.currentFacturaModel = new App.Model.FacturaModel();
            this.facturaModelList = new this.listModelClass();
            this._renderSearch();
        },
        userSearch: function() {
            var self = this;
            var model = $('#' + this.componentId + '-facturaForm').serializeObject();
            this.currentFacturaModel.set(model);
            self.search1(self.currentFacturaModel, function(data) {
                self.facturaModelList=new App.Model.FacturaList();
                _.each(data,function(d){
                    var model=new App.Model.FacturaModel(d);
                    self.facturaModelList.models.push(model);
                });
                self._renderSearch();
            }, function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'user-search', view: self, id: '', data: data, error: 'Error in user search'});
            });
        },


   search1: function(factura, callback, callbackError) {
            console.log('Factura Search: ');
            $.ajax({
                url: '/factura.service.subsystem.web/webresources/Factura/search',
                type: 'POST',
                data: JSON.stringify(factura),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        }



    });
    return App.Controller.FacturaController;
}); 