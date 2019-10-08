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
	},
	alert: function(mainMsg, msg){
		mainMsg=mainMsg?'<div class="alert-message-wrap"><div class="alert-message-text">'+mainMsg+'</div>':'';
		
		var message=mainMsg+'<br/><span>'+msg+msg+'</span></div>';
		Ext.Msg.show({
		    title:Literal.failure,
		    message: message,
		    buttons: Ext.Msg.OK,
		    icon: Ext.Msg.ERROR
		});
	},
	warn: function(mainMsg, msg){
		var message='<div class="alert-message-wrap"><div class="alert-message-text">'+mainMsg+'</div><br/><span>'+msg+msg+'</span></div>';
		Ext.Msg.show({
		    title:Literal.warning,
		    message: message,
		    buttons: Ext.Msg.OK,
		    icon: Ext.Msg.ERROR
		});
	}
});