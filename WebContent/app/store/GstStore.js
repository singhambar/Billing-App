Ext.define('App.store.GstStore', {
    extend: 'Ext.data.Store',
    alias: 'store.gst',
    fields: ['text', 'value'],
    autoLoad: true,
    constructor: function(config) {
        var me = this,
            gstRates = Configs.getGstRates(),
            data = [];
        if (gstRates) {
            var temp;
            gstRates.forEach(function(gst) {
                temp = {};
                temp['text'] = gst + '%';
                temp['value'] = gst;
                data.push(temp);
            })
            
        }
        Ext.apply(config, {
            data: data
        }, config);
        me.callParent([config])
    }
});