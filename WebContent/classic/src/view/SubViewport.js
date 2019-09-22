Ext.define('App.view.SubViewport', {
    extend: 'Ext.container.Container',
    xtype: 'sub-viewport',
    layout: {
    	type: 'card'
    },
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems()
        });
        me.callParent(arguments);
    },
    activeItem: 0,
    buildItems: function() {
        return [{
            xtype: 'login-wrapper'
        },{
                xtype: 'canvas'
            }];
    }
});