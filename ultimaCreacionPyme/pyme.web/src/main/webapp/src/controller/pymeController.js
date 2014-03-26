define(['controller/_pymeController','delegate/pymeDelegate'], function() {
    App.Controller.PymeController = App.Controller._PymeController.extend({

        postInit: function(options) {
            var self = this;
            this.searchTemplate = _.template($('#pymeSearch').html()+$('#pymeList').html());
 
            Backbone.on(this.componentId + '-' + 'toolbar-search', function(params) {
                self.search(params);
            });
            Backbone.on(this.componentId+'pyme-search', function(params) {
                self.pymeSearch(params);
            });
        }
    });
    return App.Controller.PymeController;
}); 