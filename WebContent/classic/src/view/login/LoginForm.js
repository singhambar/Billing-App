Ext.define('App.view.login.LoginForm', {
    extend: 'Ext.form.Panel',
    xtype: 'login-form',
    title: 'Login',
    frame: true,
    width: 400,
    bodyPadding: 10,
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems(),
            buttons: me.buildButton()
        });

        me.callParent(arguments);
    },
    defaults: {
        anchor: '100%',
        labelWidth: 80,
        labelSeparator:'',
        xtype: 'textfield',
        margin: '20 20 20 20',
        msgTarget: 'under'
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

    buildButton: function() {
        return [{
            text: Literal.login,
            handler: 'onClickLogin'
        }];
    }

});