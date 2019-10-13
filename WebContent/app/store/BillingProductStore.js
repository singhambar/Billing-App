Ext.define('App.store.BillingProductStore', {
    extend: 'Ext.data.Store',
    alias: 'store.billingproduct',
    model: 'App.model.BillingProduct',
    pageSize: 1000,
    remoteSort: false
});
