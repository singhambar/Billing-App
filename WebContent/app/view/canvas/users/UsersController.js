Ext.define('App.view.canvas.users.UsersController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.users',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
