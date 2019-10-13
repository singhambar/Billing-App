Ext.define('MyApp.view.canvas.billings.Wrapper', {
    extend: 'Ext.container.Container',
    xtype: 'billings-wrapper',
    controller: 'billings',
    viewModel: 'billing',
    layout: {
        type: 'vbox'
    },
    scrollable: true,
    padding: '10 20 10 20',
    height: '100%',
    width: '100%',
    items: [{
        xtype: 'billing-grid-form'
    }]
});