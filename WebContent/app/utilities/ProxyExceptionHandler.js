Ext.define('App.utilities.ProxyExceptionHandler', {
    singleton: true,
    requires: ['Ext.Ajax'],
    constructor: function() {
        var me = this;
        Ext.Ajax.on('beforerequest', me.handleBeforeRequest, me);
        Ext.Ajax.on('requestexception', me.handleException, me);
    },
    handleBeforeRequest: function(conn, option, opt) {
        var headers = option.headers,
            timeout = App.config.Configs.getRequestTimeOut();
        if (Ext.isEmpty(option.timeout) || isNaN(option.timeout) || option.timeout < timeout) {
            option.timeout = timeout;
        }
    },
    handleException: function(conn, rsp, opt) {
        var msg = '<h2><b>' + rsp.status + '- ' + rsp.statusText + '</b></h2><br/>' + rsp.responseText;
        
        AppUtil.alert(rsp.status + '- ' + rsp.statusText, rsp.responseText);
    }
});