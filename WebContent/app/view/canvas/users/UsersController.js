Ext.define('App.view.canvas.users.UsersController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.users',

    onSaveUser: function (btn) {
        var me = this,
            form = btn.up('user-form');
        if (form.isValid()) {
        	var data=form.getValues(),
        	id=data.id||'';
            Ext.Ajax.request({
                url: Configs.getUrl('User',id),
                method: id?'PUT':'POST',
                jsonData: form.getValues(),
                success: function(response, opts) {
                	form.reset();
                	form.setTitle(Literal.addUser);
                },

                failure: function(response, opts) {
                    console.log('server-side failure with status code ' + response.status);
                }
            });
        }
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    },
    onEdit: function(widget, rowIndex) {
    	var me=this,
    	grid=me.getView(),
    	sm=grid.getSelectionModel(),
    	form= grid.previousSibling('user-form'),
        rec = sm.getSelection()[0];
    	if(Number.isInteger(rowIndex)){
    		rec=grid.getStore().getAt(rowIndex);
    		sm.select(rec);
    	}
    	
    	form.getForm().setValues(rec.data);
    	form.setTitle(Literal.editUser);
        
    },

    onRemove: function(widget, event) {
    	var me=this,
        rec = me.getView().getSelectionModel().getSelection()[0];
        if (rec) {
            Ext.example.msg('Sell', 'Sell ' + rec.get('name'));
        }
    },
    
    onGridContextMenu: function(grid, rec, node, index, e) {
        var me=this,
        sm=grid.getSelectionModel();
        e.stopEvent();
        me.getContextMenu().show().setLocalXY(e.getXY());
        sm.select(rec);
        console.log(grid.xtype+' - '+rec.data.name);
        return false;
    },

    getContextMenu: function() {
        return this.contextMenu || (this.contextMenu = this.view.add({
            xtype: 'menu',
            items: [
                '@edit',
                '@remove'
            ]
        }));
    }
});
