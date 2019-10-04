Ext.define('App.view.login.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'login-wrapper',
    controller: 'main',
    layout: {
        type: 'vbox',
        align: 'middle'
    },
    style: {
        'overflow': 'auto',
        'background-color': '#fff !important'
    },
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems()
        });
        me.callParent(arguments);
    },
    buildItems: function() {
        return [{
            xtype: 'login-header'
        }, {
            xtype: 'container',
            flex: 1,
            layout: {
                type: 'vbox',
                align: 'middle',
                pack: 'center'
            },
            items: {
                xtype: 'login-form',

            }
        }];
    }
});