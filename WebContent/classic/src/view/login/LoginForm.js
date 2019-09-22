Ext.define('App.view.login.LoginForm', {
    extend: 'Ext.form.Panel',
    xtype: 'login-form',
    title: 'Login',
    frame:true,
    width: 320,
    bodyPadding: 10,
    
    buildItems:function(){return [{
        allowBlank: false,
        name: 'user',
        emptyText: 'User Id'
    }, {
        allowBlank: false,
        name: 'pass',
        emptyText: 'Password',
        inputType: 'password'
    }, {
        xtype:'checkbox',
        boxLabel: 'Remember me',
        name: 'remember'
    }]
    },
    
    buttons: [
        { text:'Login',handler: 'onClickLogin' }
    ],
    
    initComponent: function() {
    	var me = this;
    	Ext.apply(me, {
        	defaults :{
                    anchor: '100%',
                    labelWidth: 120,
                    xtype:'textfield',
                	margin: '20 20 20 20'
                },
	        items: me.buildItems()
	    });
        
        me.callParent(arguments);
    }
});