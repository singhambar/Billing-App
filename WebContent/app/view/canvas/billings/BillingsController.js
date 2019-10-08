Ext.define('App.view.canvas.billings.BillingsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.billings',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
