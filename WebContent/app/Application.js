/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('App.Application', {
    extend: 'Ext.app.Application',

    name: 'App',
    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },
    requires: [
        'Ext.app.*',
        'Ext.state.CookieProvider',
        'Ext.chart.*',
        'App.utilities.AppUtil',
        'App.config.Configs',
        'App.config.Runtime',
        'Ext.window.MessageBox'
    ],
    stores:['ChartStore','ProductStore','UserStore','GstStore','RoleStore','BillingProductStore'],
    defaultToken: 'login',
    listen: {
        controller: {
            'login': {
                action: 'onClickLogin'
            }
        }
    },
    constructor: function() {
        Ext.Ajax.request({
            url: 'resources/data/literal.json?tag='+Date.now(),
            disableCaching:false,
            async: false,
            success: function(response, opts) {
                var obj = Ext.decode(response.responseText);
                Ext.override(Literal, obj);
            },
            failure: function(response, opts) {}
        });
        Ext.Ajax.request({
            url: 'resources/data/configs.json?tag='+Date.now(),
            async: false,
            disableCaching:false,
            success: function(response, opts) {
                var obj = Ext.decode(response.responseText);
                Configs.setConfig(obj);
            },
            failure: function(response, opts) {}
        });
        this.callParent(arguments);
    },
    launch: function() {
        var me = this,
            activeItem = 0,
            token = Ext.util.History.getToken();
        
        Ext.fly('loading').fadeOut({
            callback: function() {
                Ext.fly('loading').destroy();
            }
        });
        if(token == 'logout'){
    	 	return;
        }
        AppUtil.loadUserInfo();
        if (Ext.isEmpty(Context.getLoggedInUser())) {
            activeItem = 0;
            token = 'login';
        } else {
            activeItem = 1;
            token = Ext.isEmpty(token) ? me.getDefaultToken() : token;
        }
        me.getMainView().down('sub-viewport').getLayout().setActiveItem(activeItem);
        me.redirectTo(token, true);
    },
    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    },
    onClickLogin: function() {
        var viewport = Ext.ComponentQuery.query('sub-viewport')[0];
        if (viewport) {
            var layout = viewport.getLayout();
            layout.setActiveItem(1);
        }
    },
});
