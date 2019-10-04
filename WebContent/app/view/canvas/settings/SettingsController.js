Ext.define('App.view.canvas.settings.SettingsController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.settings',

    onItemSelected: function (sender, record) {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
