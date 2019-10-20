Ext.define('App.model.Bill', {
    extend: 'App.model.Base',
    fields: ['id', 'name', 'invoiceNo', 'gistin','billDate', 'totalAmount', 'discountAmount', 'paymentType','extraDetails','address','contactNo']
});
