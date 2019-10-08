Ext.define('App.store.UserStore', {
    extend: 'Ext.data.Store',
    alias: 'store.user',
    model: 'App.model.User',
    pageSize: 15,
    remoteSort: true,
    sorters: [{
        property: 'firstName',
        direction: 'DESC'
    }],
    proxy: {
        type: 'ajax',
        url: 'rest/user',
        reader: {
            rootProperty: 'Users',
            totalProperty: 'totalCount'
        },
        simpleSortMode: true
    }
});
