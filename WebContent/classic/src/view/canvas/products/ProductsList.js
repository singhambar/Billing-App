Ext.define('App.view.canvas.products.ProductsList', {
    extend: 'App.view.baseComponents.BaseGrid',
    xtype: 'products-grid',

    title: 'Basic Grid',
    width: 750,
    height: 350,

    store: 'Companies',
    
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
        	actionTpl   : me.buildActionTpl(),
            store: Ext.data.StoreManager.lookup('rolestore'),
            emptyText: '<div style="text-align: center;"><span style="font-size: 12pt;font-weight:400;color: #636363;">'+Literal.noDataToDisplay+'</span></div>',
            minHeight	:450,
            columns: me.buildColumns(),
            dockedItems: me.buildDockedItems(),
            viewConfig: Settings.getDefaultViewConfig()
        });
        me.callParent(arguments);
    },
    buildColumns:function(){
    	return [{
            text: 'Company',
            flex: 1,
            dataIndex: 'name'
        }, {
            text: 'Price',
            width: 95,
            formatter: 'usMoney',
            dataIndex: 'price'
        }, {
            text: 'Change',
            width: 80,
            renderer: 'renderChange',
            dataIndex: 'priceChange'
        }, {
            text: '% Change',
            width: 100,
            renderer: 'renderPercent',
            dataIndex: 'priceChangePct'
        }, {
            text: 'Last Updated',
            width: 115,
            formatter: 'date("m/d/Y")',
            dataIndex: 'priceLastChange'
        }, {
            xtype: 'actioncolumn',
            width: 50,
            menuDisabled: true,
            sortable: false,

            items: [{
                iconCls: 'x-fa fa-check green',
                handler: 'onApprove'
            }, {
                iconCls: 'x-fa fa-ban red',
                handler: 'onDecline'
            }]
        }];
    },

    signTpl: '<span style="' +
            'color:{value:sign(\'"#cf4c35"\',\'"#73b51e"\')}"' +
        '>{text}</span>'
});