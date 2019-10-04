Ext.define('MyApp.view.canvas.users.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'registeration-wrapper',
    controller: 'users',
    layout: {
        type: 'hbox'
    },
    items: [{
        xtype: 'registration-form'
    }]
});