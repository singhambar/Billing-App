Ext.define('App.view.login.LoginForm', {
    extend: 'Ext.form.Panel',
    
    xtype: 'login-form',
    
    title: 'Login',
    width: 400,
    frame : true,
	bodyPadding : 10,
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems(),
            buttons: me.buildButtons()
        });
        me.callParent(arguments);
    },
    defaults : {
		labelAlign : 'top',
		labelSeparator : '',
		labelWidth: 120,
		xtype : 'textfield',
		margin : '0 10 10 10',
		msgTarget : 'side',
		anchor: '100%',
		allowBlank: false
	},
    buildItems: function() {
        return [{
            allowBlank: false,
            name: 'emailId',
            fieldLabel: Literal.emailId,
            emptyText: Literal.emailId
        }, {
            allowBlank: false,
            name: 'password',
            fieldLabel: Literal.password,
            emptyText: Literal.password,
            inputType: 'password'
        }]
    },

    buildButtons: function() {
        return [{
            text: Literal.login,
            handler: 'onClickLogin'
        }];
    }

});