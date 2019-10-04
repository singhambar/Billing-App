Ext.define('App.utilities.AppUtil',{
    singleton : true,
    alternateClassName: ['AppUtil'],
    config : {
        mainContainerTip : null
    },
	constructor : function(config) {
		this.initConfig(config);	
	},
	loadUserInfo:function(){
		var userInfo;
		Ext.Ajax.request({
	    	url: Configs.getUrl('User','userinfo'),
			method:'GET',
			async:false,
	        success: function(response, opts) {
	        	userInfo=Ext.decode(response.responseText);
	        	if(!Ext.isEmpty(userInfo)){
	        		Context.setLoggedInUser(userInfo);
	        	}
	        },
	        failure: function(response, opts) {
	            console.log('server-side failure with status code ' + response.status);
	        }
	    });
	}
});