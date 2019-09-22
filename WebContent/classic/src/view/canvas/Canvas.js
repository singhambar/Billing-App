Ext.define('App.view.Canvas', {
    extend: 'Ext.tab.Panel',
    xtype: 'canvas',
    controller: 'header',
    ui: 'custom-navigation',
    initComponent: function() {
        var me = this;
        Ext.apply(me, {
            items: me.buildItems()
        });
        me.callParent(arguments);
    },
    tabBarHeaderPosition: 1,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            text: '<img style="width: 50px; height: 50px" src="resources/images/logo.png?206">',
            flex: 0.1
        }
    },

    tabBar: {

        flex: 2,
        border: false
    },
    border: false,
    defaults: {
        iconAlign: 'left',
        bodyPadding: 0
    },
    buildItems: function() {
        var me = this;
        return [{
            title: Literal.home,
            iconCls: 'fa-home',
            items: [{
                xtype: 'pie-donut'
            }]
        }, {
            title: Literal.products,
            iconCls: 'fa-shopping-bag',
            bind: {
                html: '{loremIpsum}'
            }
        }, {
            title: Literal.users,
            iconCls: 'fa-users',
            bind: {
                html: '{loremIpsum}'
            }
        }, {
            title: Literal.settings,
            iconCls: 'fa-cog',
            bind: {
                html: '{loremIpsum}'
            }
        }];
    }
});