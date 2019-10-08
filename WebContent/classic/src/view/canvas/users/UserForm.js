Ext.define('App.view.canvas.users.UserForm', {
    extend: 'App.view.baseComponents.BaseForm',
    xtype: 'user-form',
    
    title: 'Register',
    
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            title: Literal.addUser,
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
                hidden: true
            }, {
                fieldLabel: Literal.firstName,
                name: 'firstName'
            }, {
                fieldLabel: Literal.lastName,
                allowBlank: true,
                name: 'lastName'
            }, {
                xtype: 'combo',
                fieldLabel: Literal.userType,
                name: 'role',
                displayFiled: 'text',
                valueField: 'value',
                editable: false,
                forceSelection: true,
                store: 'RoleStore'
            }]
        }, {
            items: [{
                    fieldLabel: Literal.password,
                    name: 'pass',
                    emptyText: 'password',
                    inputType: 'password'
                },
                {
                    fieldLabel: Literal.confirmPassword,
                    name: 'pass',
                    emptyText: 'password',
                    inputType: 'password'
                }
            ]
        }, {
            items: [{
                fieldLabel: Literal.emailId,
                name: 'emailId',
                vtype: 'email'
            }, {
                fieldLabel: Literal.contactNo,
                name: 'contactNo',
                allowBlank: true,
                inputType: 'number'
            }]
        }]
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