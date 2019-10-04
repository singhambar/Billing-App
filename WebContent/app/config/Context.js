Ext.define('App.config.Runtime',{
    singleton : true,
    alternateClassName: ['Context'],
    constructor : function(config){
        var me = this;
        me.initConfig(config);
    },
    config : {
        loggedInUser    : null,
    }
});
