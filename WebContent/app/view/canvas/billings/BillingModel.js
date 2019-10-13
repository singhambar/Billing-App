Ext.define('App.view.main.BillingModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.billing',
    
    data: {
    	totalAmount: 0,
    	offerdiscount: 0
    },
    addAmount: function(v){
    	this.set('totalAmount',this.data.totalAmount+v)
    },
    subtractAmount: function(v){
    	this.set('totalAmount',this.data.totalAmount-v)
    }
});
