Ext.define('App.view.canvas.products.ProductsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.products',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
