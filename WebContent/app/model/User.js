Ext.define('App.model.User', {
    extend: 'App.model.Base',
    fields: ['id', 'name', 'firstName', 'lastName', 'passw0rd', 'role', 'emailId', 'contactNo',
        {
            name: 'name',
            type: 'string',
            convert: function(v, rec) {
                return rec.get('firstName') + ' ' + rec.get('lastName');
            }
        }
    ]
});