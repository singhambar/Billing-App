Ext.define('MyApp.view.canvas.users.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'registeration-wrapper',
    controller: 'users',
    layout: {
        type: 'vbox'
    },
    scrollable: true,
    padding: '10 20 10 20',
    height: '100%',
    width: '100%',
    items: [{
        xtype: 'user-form'
    }, {
        xtype: 'user-grid',
        margin: '10 0 0 0'
    }]
});