Ext.define('App.model.BillingProduct', {
    extend: 'App.model.Base',
    fields: ['id', 'name', 'description', 'vendor','sellingPrice', 'costPrice', 'gst', 'quantity','modifiedDate','totalPrice',
    	{
    	name: 'perchasedUnit',
    	convert: function(v, rec) {debugger
    		v=v||1;
        	return v;
        }
    },{
        name: 'totalPrice',
        mapping: 'sellingPrice',
        convert: function(v, rec) {debugger
        	var unit=rec.get('perchasedUnit')||1;
        	return (rec.get('sellingPrice')+ (rec.get('sellingPrice')*rec.get('gst')/100))*unit;
        }
    }]
});
