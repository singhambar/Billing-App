Ext.define('App.store.BillStore', {
    extend: 'Ext.data.Store',
    alias: 'store.product',
    model: 'App.model.Product',
    pageSize: 15,
    remoteSort: true,
    sorters: [{
        property: 'billDate',
        direction: 'DESC'
    }],
    proxy: {
        type: 'ajax',
        url: 'rest/product',
        reader: {
            rootProperty: 'Bills',
            totalProperty: 'totalCount'
        },
        simpleSortMode: true
    }
});
