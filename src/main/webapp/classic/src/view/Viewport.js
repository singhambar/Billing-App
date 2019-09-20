Ext.define('App.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: [
               'App.view.main.Main'
     ],
    controller: 'main',
    viewModel: 'main',
    stateful: {
	     height: false, // never persist the height
	     width: false    // always persist the width
	 },
    stateId: 'enterprise-viewport',
    itemId     : 'appviewport',
    layout     : {
        type           : 'card',
        deferredRender : true
    },
    activeItem : 0,
    style:{
    	'overflow':'auto'
    },
    autoScroll:true,
    initComponent : function(){
        var me = this;
        Ext.apply(me,{
            items : me.buildItems()
        });
        me.callParent(arguments);
    },
    buildItems : function() {
    	return [
	        {
	             xtype : 'app-header'
	        }
        ];
    }
});
