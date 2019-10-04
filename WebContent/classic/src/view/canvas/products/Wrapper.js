Ext.define('MyApp.view.canvas.products.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'products-wrapper',
    controller: 'users',
    layout: {
        type: 'hbox'
    },
    items: [{
        xtype: 'registration-form'
    }]
});