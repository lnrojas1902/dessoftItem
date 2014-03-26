define([], function() {
    App.Delegate._ClienteDelegate = App.Component.BasicComponent.extend({
    
    search: function(cliente, callback, callbackError) {
            console.log('Cliente Search: ');
            $.ajax({
                url: '/cliente.service.subsystem.web/webresources/Cliente/search',
                type: 'POST',
                data: JSON.stringify(cliente),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        }
    });
});