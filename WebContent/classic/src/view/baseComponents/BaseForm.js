Ext.define('App.view.baseComponents.BaseForm', {
	extend : 'Ext.form.Panel',
	xtype : 'base-form',

	frame : true,
	bodyPadding : 10,
	scrollable : true,
	width: '100%',
    height: '100%',
    collapsible: true,
    collapsed: true,
    layout: 'hbox',
	
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			
		});
		me.callParent(arguments);
	},
	defaults: {
        border: false,
        xtype: 'panel',
        flex: 1,
        layout: 'anchor',
        margin: '0 10 0 10',
        defaults: {
            labelAlign: 'top',
            labelSeparator: '',
            labelWidth: 120,
            xtype: 'textfield',
            margin: '0 10 10 10',
            msgTarget: 'side',
            anchor: '100%',
            allowBlank: false
        }
    },
});