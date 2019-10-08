Ext.define('App.store.ProductStore', {
    extend: 'Ext.data.Store',
    alias: 'store.product',
    model: 'App.model.Product',
    pageSize: 15,
    remoteSort: true,
    sorters: [{
        property: 'name',
        direction: 'DESC'
    }],
    proxy: {
        type: 'ajax',
        url: 'rest/product',
        reader: {
            rootProperty: 'Products',
            totalProperty: 'totalCount'
        },
        simpleSortMode: true
    }
});
