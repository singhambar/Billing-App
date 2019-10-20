Ext.define('App.view.canvas.billings.BillingGrid', {
    extend: 'App.view.baseComponents.BaseGrid',
    xtype: 'billing-grid',
    requires: [
        'Ext.ProgressBar',
        'Ext.Action',
        'Ext.toolbar.Paging',
        'Ext.ux.ProgressBarPager'
    ],
    controller: 'billings',
    width: '100%',
    height: 523,
    allowDeselect: true,
    defaultActionType: 'button',
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            title: Literal.manageProducts,
            store: Ext.data.StoreManager.lookup('ProductStore'),
            columns: me.buildColumns(),
            viewConfig: {
                listeners: {
                    itemcontextmenu: 'onGridContextMenu'
                }
            },
        });
        me.callParent(arguments);
    },
    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        plugins: {
            'ux-progressbarpager': true
        }
    },
    
    tbar: {
        xtype: 'toolbar',
        items:{xtype:'textfield'}
    },
    
    actions: {
        edit: {
            iconCls: 'blue-icon x-fa fa-pencil',
            text: 'Edit',
            handler: 'onEdit'
        },
        remove: {
            iconCls: 'blue-icon x-fa fa-trash',
            text: 'Sell stock',
            handler: 'onRemove'
        }
    },
    buildColumns: function() {
        var me = this;
        return [{
            text: Literal.id,
            flex: 1,
            hidden: true,
            dataIndex: 'id'
        }, {
            text: Literal.name.toUpperCase(),
            flex: 2,
            dataIndex: 'name'
        }, {
            text: Literal.costPrice.toUpperCase(),
            flex: 1,
            renderer: Ext.util.Format.numberRenderer('0.00'),
            dataIndex: 'costPrice'
        }, {
            text: Literal.sellingPrice.toUpperCase(),
            flex: 1,
            renderer: Ext.util.Format.numberRenderer('0.00'),
            dataIndex: 'sellingPrice'
        }, {
            text: Literal.gst.toUpperCase(),
            flex: 1,
            //            renderer: 'renderPercent',
            dataIndex: 'gst'
        }, {
            text: Literal.quantity.toUpperCase(),
            flex: 1,
            dataIndex: 'quantity'
        }, {
            text: Literal.modifiedDate.toUpperCase(),
            flex: 1,
            renderer: function(v) {
                return new Date(v);
            },
            dataIndex: 'modifiedDate'
        }, {
            xtype: 'actioncolumn',
            text: Literal.action.toUpperCase(),
            width: 70,
            menuDisabled: true,
            sortable: false,
            items: ['@edit', '@remove']
        }];
    },
    
    signTpl: '<span style="' +
        'color:{value:sign(\'"#cf4c35"\',\'"#73b51e"\')}"' +
        '>{text}</span>'
});