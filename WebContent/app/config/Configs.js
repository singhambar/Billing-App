Ext.define('App.config.Configs',{
    singleton : true,
    alternateClassName: ['Configs'],
    constructor : function(config){
        var me = this;
        me.initConfig(config);
	},
	config : {
		requestTimeOut: 180000,
		copyright: "Copyright &copy; Company_Name 2019&nbsp;|&nbsp;All rights reserved.",
		gstRates: ["5","12","18","28"],
	    restUserUrl    : 'rest/user',
	    restProductUrl    : 'rest/product',
	},
	getUrl:function(config,path){
		var me=this;
		return me['getRest'+config+'Url']()+'/'+path;
	}
});
