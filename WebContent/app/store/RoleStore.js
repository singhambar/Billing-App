Ext.define('App.store.RoleStore', {
    extend: 'Ext.data.Store',
    alias: 'store.role',
    fields: ['text', 'value'],
    autoLoad: true,
    data: [
        { text: 'Admin', value: 'admin' },
        { text: 'Owner', value: 'owner' },
        { text: 'Staff', value: 'staff' },
        { text: 'Company', value: 'company' }
    ]
});
