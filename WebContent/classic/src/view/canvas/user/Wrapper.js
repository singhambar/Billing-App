Ext.define('MyApp.view.canvas.user.Wrapper', {
    extend: 'Ext.container.Container',
	xtype: 'registeration-wrapper',
	layout: {
        type: 'hbox'
    },
    items: [{
        xtype: 'registration-form'
    }]
});