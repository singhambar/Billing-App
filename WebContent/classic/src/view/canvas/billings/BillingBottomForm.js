Ext.define('App.view.canvas.billings.BillingBottomForm', {
    extend: 'Ext.toolbar.Toolbar',
    xtype: 'billing-bottom-form',
    
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems(),
        });
        me.callParent(arguments);
    },
    layout: 'vbox',
    buildItems: function() {
        var me = this;
        return [{
            xtype: 'base-form',
            bodyPadding: 0,
            collapsed: false,
            frame: false,
            collapsible: false,
            flex: 1,
            layout: {
                type: 'hbox',
                pack: 'end'
            },
            items: [{
                    xtype: 'combo',
                }, {
                    xtype: 'tbspacer',
                    flex: 2
                },
                {
                    xtype: 'container',
                    layout: {
                        type: 'vbox',
                        align: 'start'
                    },
                    defaults: {
                        labelAlign: 'left',
                        labelSeparator: '',
                        labelWidth: 120
                    },
                    items: [{
                        xtype: 'displayfield',
                        fieldLabel: Literal.subTotal,
                        bind: {
                            value: '{totalAmount}'
                        }
                    }, {
                        xtype: 'numberfield',
                        fieldLabel: Literal.offerdiscount,
                        hideTrigger: true,
                        allowBlank: false,
                        allowDecimals: false,
                        keyNavEnabled: false,
                        mouseWheelEnabled: false,
                        bind: {
                            value: '{offerdiscount}'
                        }
                    }, {
                        xtype: 'displayfield',
                        fieldLabel: Literal.totalDue,
                        bind: {
                            value: '{totalAmount - offerdiscount}'
                        }
                    }]
                }
            ],
            buttons: ['->', {
                text: Literal.save,
                handler: 'onSaveProduct'
            }, {
                text: Literal.reset
            }]
        }];
    }
});