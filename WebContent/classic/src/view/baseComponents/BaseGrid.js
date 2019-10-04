Ext.define('App.view.baseComponents.BaseGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'base-grid',
    width: 750,
    height: 350,
    collapsible: true,
    multiSelect: true,
    headerBorders: false,
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            emptyText: '<div style="text-align: center;"><span style="font-size: 12pt;font-weight:400;color: #636363;">'+Literal.noDataToDisplay+'</span></div>',
            minHeight	:450,
            viewConfig: {
                enableTextSelection: true
            }
        });
        me.callParent(arguments);
    }

});