Ext.define('MyApp.view.canvas.settings.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'settings-wrapper',
    controller: 'users',
    layout: {
        type: 'hbox'
    },
    items: [{
        xtype: 'registration-form'
    }]
});