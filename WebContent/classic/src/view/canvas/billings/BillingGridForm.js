Ext.define('App.view.canvas.billings.BillingGridForm', {
    extend: 'App.view.baseComponents.BaseGrid',
    xtype: 'billing-grid-form',
    controller: 'billings',
    width: '100%',
    height: 550,
    padding: '0 20 0 20',
    allowDeselect: true,
    defaultActionType: 'button',
    plugins: {
        cellediting: {
            clicksToEdit: 1
        }
    },
    cls: 'grid-cls',
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            title: Literal.products,
            store: Ext.data.StoreManager.lookup('BillingProductStore'),
            tbar: {
                xtype: 'billing-top-form',
                padding: '10px 1px 10px 0px'
            },
            bbar: {
                xtype: 'billing-bottom-form',
                padding: '10px 1px 10px 0px'
            },
            columns: {border:true,items:me.buildColumns()},
            viewConfig: {},
        });
        me.callParent(arguments);
    },
    actions: {
        remove: {
            iconCls: 'blue-icon x-fa fa-times',
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
            text: Literal.name,
            flex: 2,
            dataIndex: 'name'
        }, {
            text: Literal.units,
            flex: 1,
            dataIndex: 'perchasedUnit',
            editor: {
                xtype: 'numberfield',
                hideTrigger: true,
                allowBlank: false,
                allowDecimals: false,
                keyNavEnabled: false,
                mouseWheelEnabled: false
            }
        }, {
            text: Literal.price,
            flex: 1,
            renderer: Ext.util.Format.numberRenderer('0.00'),
            dataIndex: 'sellingPrice',
            editor: {
                xtype: 'numberfield',
                hideTrigger: true,
                allowBlank: false,
                allowDecimals: true,
                decimalPrecision: 2,
                step: 50.00,
                keyNavEnabled: false,
                mouseWheelEnabled: false
            }
        }, {
            text: Literal.gst,
            flex: 0.5,
            dataIndex: 'gst'
        }, {
            text: Literal.lineTotal,
            flex: 1,
            renderer: Ext.util.Format.numberRenderer('0.00'),
            dataIndex: 'totalPrice',
        }, {
            xtype: 'actioncolumn',
            width: 25,
            menuDisabled: true,
            sortable: false,
            items: ['@remove']
        }];
    },
    listeners: {
        edit: 'onEditCell'
    }
});