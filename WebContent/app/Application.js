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
        'Ext.window.MessageBox'
    ],
    stores:['ChartStore'],
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
        }debugger
        AppUtil.loadUserInfo();
        var username = Ext.util.Cookies.get('ACCESS_TOKEN');
        if (Ext.isEmpty(username)) {
            activeItem = 0;
            Ext.util.Cookies.set('OLD_TOKEN',token);
            token = 'login';
        } else {
            activeItem = 1;
            token = Ext.isEmpty(token) ? me.getDefaultToken() : token;
        }
        this.getMainView().down('sub-viewport').getLayout().setActiveItem(activeItem);
        me.redirectTo(token, true);
    },
    onAppUpdate: function () {debugger
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    },
    onClickLogin: function() {debugger
        var viewport = Ext.ComponentQuery.query('sub-viewport')[0];
        if (viewport) {
            var layout = viewport.getLayout();
            layout.setActiveItem(1);
        }
    },
});
