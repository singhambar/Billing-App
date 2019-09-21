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
    constructor: function() {
    	tag=Ext.isEmpty(tag)?Date.now():tag;
        Ext.Ajax.request({
            url: cdnPath +'rest/themesettings?companyId=1',
            async: false,
            method:'GET',
//            headers: {
//                "Authorization": "Basic YWRtaW46aW5kaWdvMQ=="
//            },
            success: function(response, opts) {
                var themeSettings = Ext.decode(response.responseText);
            if(!Ext.isEmpty(themeSettings[0])){
            	Runtime.setThemeId(themeSettings[0].id);
       			Runtime.setThemeSettings(JSON.parse(themeSettings[0].themeInfo));
       			if(!Ext.isEmpty(themeSettings[0].pdThemeInfo)){
       				Runtime.setPDTheme(JSON.parse(themeSettings[0].pdThemeInfo));
       			}else{
       				Runtime.setPDTheme(HelperUtils.getDefaultPDTheme());
       			}
       		 }
            },
            failure: function(response, opts) {}
        });
        Ext.Ajax.request({
            url: cdnPath + 'resources/data/enterprise-en.json?tag='+tag,
            disableCaching:false,
            async: false,
            success: function(response, opts) {
                var obj = Ext.decode(response.responseText);
            	obj=HelperUtils.applyThemeSettingsToLiterals(obj);
                Ext.override(Literal, obj);
            },
            failure: function(response, opts) {}
        });
        this.callParent(arguments);
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
            token = Ext.util.History.getToken(),
            view = 'App.view.Viewport';
        me.setMainView({
            xclass: view
        });
    },
    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
