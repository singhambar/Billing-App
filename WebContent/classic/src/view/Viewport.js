Ext.define('App.view.Viewport', {
    extend: 'Ext.container.Viewport',
    controller: 'main',
    viewModel: 'main',
    stateful: {
        height: false, // never persist the height
        width: false // always persist the width
    },
    stateId: 'enterprise-viewport',
    itemId: 'appviewport',
    layout: {
        type: 'vbox',
        pack: 'start',
        align: 'stretch'
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
        	xtype: 'sub-viewport',
        	flex: 1
        },{
            xtype: 'app-footer',
            height: 30
        }];
    }
});