Ext.define('App.view.login.LoginHeader', {
    extend: 'Ext.container.Container',
    xtype: 'login-header',

    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems()
        });
        me.callParent(arguments);
    },
    height: 70,
    width: '100%',
    layout: {
        type: 'hbox',
        align: 'middle',
        pack: 'center'
    },
    border: true,
    style: {
        'background-color': '#404040',
    },
    buildItems: function() {
        var me = this;
        return [{
            xtype: 'image',
            height: 70,
            src: 'resources/images/company-logo.png'
        }];
    }
});