Ext.define('App.view.footer.AppFooter', {
    extend: 'Ext.container.Container',
    xtype: 'app-footer',
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: [{
                xtype: 'button',
                text: Configs.getCopyright()
            }]
        });
        me.callParent(arguments);
    },
    layout: 'fit',

});