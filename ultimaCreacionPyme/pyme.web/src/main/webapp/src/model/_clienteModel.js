define([], function() {
    App.Model._ClienteModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'docId' : ''
 ,  
		 'tipo' : ''
 ,  
		 'password' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._ClienteList = Backbone.Collection.extend({
        model: App.Model._ClienteModel,
        initialize: function() {
        }

    });
    return App.Model._ClienteModel;
});