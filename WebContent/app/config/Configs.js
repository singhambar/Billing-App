Ext.define('App.config.Configs',{
    singleton : true,
    alternateClassName: ['Configs'],
    constructor : function(config){
        var me = this;
        me.initConfig(config);
	},
	config : {
	    restUserUrl    : 'rest/user'
	},
	getUrl:function(config,path){
		var me=this;
		return me['getRest'+config+'Url']()+'/'+path;
	}
});
