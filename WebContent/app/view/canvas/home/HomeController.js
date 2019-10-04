Ext.define('App.view.canvas.home.HomeController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.home',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});