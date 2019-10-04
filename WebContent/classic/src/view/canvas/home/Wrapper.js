Ext.define('MyApp.view.canvas.home.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'home-wrapper',
    controller: 'home',
    layout: {
        type: 'hbox'
    },
    items: [{
        xtype: 'pie-donut'
    }]
});