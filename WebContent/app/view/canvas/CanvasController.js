Ext.define('App.view.canvas.CanvasController', {
    extend: 'Ext.app.ViewController',
    
    alias: 'controller.canvas',
    
    onTabChange: function(cmp) {
        var me = this;
        me.redirectTo('tab/' + cmp.getActiveTab().itemId)
    },
    onLogout: function(btn) {
        var me = this;
        Ext.MessageBox.confirm(Literal.confirm, Literal.logoutMsg, function(btn) {
            if (btn == 'yes') {
            	Ext.Ajax.request({
                    url: Configs.getUrl('User', 'logout'),
                    method: 'GET',
                    success: function(response, opts) {
                        var viewport = Ext.ComponentQuery.query('sub-viewport')[0];
                        if (viewport) {
                            var layout = viewport.getLayout();
                            layout.setActiveItem(0);
                        }
                    },

                    failure: function(response, opts) {
                        console.log('server-side failure with status code ' + response.status);
                    }
                });
            }
        }, me);
    }
});