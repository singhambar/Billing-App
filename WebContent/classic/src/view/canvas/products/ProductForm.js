Ext.define('App.view.canvas.products.ProductForm', {
    extend: 'App.view.baseComponents.BaseForm',
    xtype: 'product-form',
    
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            title: Literal.addProduct,
            items: me.buildItems(),
            buttons: me.buildButtons()
        });
        me.callParent(arguments);
    },
    
    buildItems: function() {
        var me = this;
        return [{
                items: [{
                    name: 'id',
                    allowBlank: true,
                    hidden: true
                }, {
                    fieldLabel: Literal.name,
                    maxLength: 128,
                    name: 'name'
                }, {
                    fieldLabel: Literal.vendorName,
                    maxLength: 128,
                    allowBlank: true,
                    name: 'vendor'
                }, {
                    fieldLabel: Literal.quantity,
                    name: 'quantity',
                    inputType: 'number'
                }]
            },
            {
                items: [{
                    fieldLabel: Literal.costPrice,
                    name: 'costPrice',
                    allowDecimals: true,
                    decimalPrecision: 2,
                    step: 50.00,
                    inputType: 'number'
                }, {
                    fieldLabel: Literal.sellingPrice,
                    name: 'sellingPrice',
                    allowDecimals: true,
                    decimalPrecision: 2,
                    step: 50.00,
                    inputType: 'number'
                }, {
                    xtype: 'combo',
                    fieldLabel: Literal.gst,
                    name: 'gst',
                    displayFiled: 'text',
                    valueField: 'value',
                    editable: false,
                    forceSelection: true,
                    store: 'GstStore'
                }]
            }, {
                items: [{
                    xtype: 'textarea',
                    fieldLabel: Literal.extraDetails,
                    maxLength: 2096,
                    name: 'extraDetails',
                    allowBlank: true
                }]
            }
        ];
    },
    
    buildButtons: function() {
        return ['->', {
            text: Literal.save,
            handler: 'onSaveProduct'
        }, {
            text: Literal.reset
        }];
    }
});