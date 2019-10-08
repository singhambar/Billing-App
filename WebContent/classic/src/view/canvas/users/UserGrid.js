Ext.define('App.view.canvas.users.UserGrid', {
    extend: 'App.view.baseComponents.BaseGrid',
    xtype: 'user-grid',
    requires: [
        'Ext.ProgressBar',
        'Ext.Action',
        'Ext.toolbar.Paging',
        'Ext.ux.ProgressBarPager'
    ],
    controller: 'users',
    width: '100%',
    height: 523,
    allowDeselect: true,
    defaultActionType: 'button',
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            title: Literal.manageUsers,
            store: Ext.data.StoreManager.lookup('UserStore'),
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
            text: Literal.emailId.toUpperCase(),
            flex: 2,
            dataIndex: 'emailId'
        }, {
            text: Literal.contactNo.toUpperCase(),
            flex: 1,
            dataIndex: 'contactNo'
        }, {
            text: Literal.userType.toUpperCase(),
            flex: 0.8,
            dataIndex: 'role'
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