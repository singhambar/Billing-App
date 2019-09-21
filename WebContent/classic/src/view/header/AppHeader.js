Ext.define('App.view.header.AppHeader', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-header',

    controller: 'header',
    ui: 'navigationn',
    initComponent: function() {
    	var me = this;
	    Ext.apply(me, {
	        items: me.buildItems()
	    });
	    me.callParent(arguments);
    },
    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretchmax'
        },
        title: {
            bind: {
                text: '{name}'
            },
            flex: 0
        },
//        iconCls: 'fa-th-list'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        }
    },

    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left'
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },
    buildItems: function () {
    	var me=this;
        return [{
	        title: Literal.home,
	        iconCls: 'fa fa-home',
	        items: [{
	            xtype: 'mainlist'
	        }]
	    }, {
	        title: 'Users',
	        iconCls: 'fa fa-user',
	        bind: {
	            html: '{loremIpsum}'
	        }
	    }, {
	        title: 'Groups',
	        iconCls: 'fa fa-users',
	        bind: {
	            html: '{loremIpsum}'
	        }
	    }, {
	        title: Literal.settings,
	        iconCls: 'fa fa-cog',
	        bind: {
	            html: '{loremIpsum}'
	        }
	    }];
    }
});
