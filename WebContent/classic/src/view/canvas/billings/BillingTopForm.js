Ext.define('App.view.canvas.billings.BillingTopForm', {
    extend: 'Ext.toolbar.Toolbar',
    xtype: 'billing-top-form',
    
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems(),
        });
        me.callParent(arguments);
    },
    layout: {
        type: 'vbox',
        align: 'start'
    },
    buildItems: function() {
    	var me = this,
        	comboStore = Ext.data.StoreManager.lookup('BillingProductComboStore');
	    if (Ext.isEmpty(comboStore)) {
	        comboStore = Ext.create('App.store.ProductStore', {
	            storeId: 'BillingProductComboStore',
	            model: 'App.model.BillingProduct',
	            pazeSize: 1000
	        });
	    }
        return [{
            xtype: 'base-form',
            bodyPadding: 0,
            collapsed: false,
            frame: false,
            collapsible: false,
            layout: 'hbox',
            defaults: {
                xtype: 'container',
                border: false,
                flex: 1,
                layout: 'anchor',
                defaults: {
                    labelAlign: 'left',
                    labelSeparator: '',
                    labelWidth: 100,
                    margin: '0 0 10 0',
                    xtype: 'textfield',
                    msgTarget: 'side',
                    anchor: '100%',
                    allowBlank: false
                },
            },
            items: [{
                flex: 2,
                items: [{
                    fieldLabel: Literal.name,
                    maxLength: 128,
                    name: 'customerName'
                }, {
                    xtype: 'textarea',
                    fieldLabel: Literal.address,
                    maxLength: 128,
                    allowBlank: true,
                    name: 'address'
                }, {
                    fieldLabel: Literal.contactNo,
                    name: 'contactNo',
                    inputType: 'number'
                }]
                
            }, {
                xtype: 'tbspacer',
                flex: 2
            }, {
                items: [{
                    name: 'id',
                    hidden: true
                }, {
                    xtype: 'datefield',
                    value: new Date(),
                    format: 'd-m-Y',
                    fieldLabel: Literal.date,
                    maxLength: 128,
                    name: 'date'
                }, {
                    fieldLabel: Literal.invoiceNo,
                    maxLength: 128,
                    allowBlank: true,
                    name: 'invoiceNo'
                }, {
                    fieldLabel: Literal.gstin,
                    name: 'gstin',
                    maxLength: 15,
                    inputType: 'number'
                }]
            }]
        }, {
            xtype: 'tbspacer',
            height: 25
        }, {
            xtype: 'combo',
            width: '100%',
            store: comboStore,
            displayField: 'name',
            valueField: 'id',
            listeners: {
                select: 'onSelectProduct'
            }
        }];
    }
});