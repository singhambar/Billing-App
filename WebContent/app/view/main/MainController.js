/**
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 */
Ext.define('App.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    init: function() {
        var me = this;
        me.control({
        	
        	
        });
//        Ext.util.History.on({
//            change: me.onChangeHistory
//        });
    },
    routes: {
        'home': {
            action: 'onHomePage'
        },
        
    },
    
    
    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    },
    onClickLogin:function(){debugger
    	var viewport = Ext.ComponentQuery.query('sub-viewport')[0];
            if (viewport) {
                var layout = viewport.getLayout();
                layout.setActiveItem(1);
            }
    Ext.Ajax.request({
    	url:'rest/user',
		method:'GET',
        success: function(response, opts) {
        	var viewport = Ext.ComponentQuery.query('sub-viewport')[0];
            if (viewport) {
                var layout = viewport.getLayout();
                layout.setActiveItem(1);
            }
        },

        failure: function(response, opts) {
            console.log('server-side failure with status code ' + response.status);
        }
    });
    }
});
