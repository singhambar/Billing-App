Ext.define('App.view.Canvas', {
    extend: 'Ext.tab.Panel',
    xtype: 'canvas',
    controller: 'canvas',
    ui: 'custom-navigation',
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems()
        });
        me.callParent(arguments);
    },
    tabBarHeaderPosition: 1,

    header: {height:70,
        layout: {
            align: 'stretchmax'
        },
        title: {
            text: '<img style="width: 50px; height: 50px" src="resources/images/logo.png?206">',height:55,
            flex: 0.1
        }
    },

    tabBar: {
        flex: 2,height:70,
        border: false
    },
    border: false,
    defaults: {
        iconAlign: 'left'
    },
    listeners: {
        tabchange: 'onTabChange'
    },tools: [{
//        type: 'help',
        iconCls:'fa fa-question-circle',width:30,
        callback: function() {
            // show help here
        }
    }, {
        iconCls: 'fa fa-power-off',
        callback: 'onLogout'
    }],
    buildItems: function() {
        var me = this;
        return [{
            title: Literal.home,
            itemId: 'home',
            iconCls: 'fa-home',
            items: [{
                xtype: 'home-wrapper'
            }]
        }, {
            title: Literal.products,
            itemId: 'products',
            iconCls: 'fa-shopping-bag',
            items: [{
                xtype: 'products-wrapper'
            }]
        }, {
            title: Literal.users,
            itemId: 'users',
            iconCls: 'fa-users',
            items: [{
                xtype: 'registeration-wrapper'
            }]
        }, {
            title: Literal.billings,
            itemId: 'billings',
            iconCls: 'fa-cog',
            items: [{
                xtype: 'billings-wrapper'
            }]
        }];
    }
});