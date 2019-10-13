Ext.define('App.view.canvas.billings.BillingsController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.billings',
    
    onSelectProduct: function(combo, record) {
        var me = this,
            vm = me.getViewModel(),
            store = Ext.data.StoreManager.lookup('BillingProductStore');
        store.add(record.copy());
        vm.addAmount(record.get('totalPrice'));
        combo.setValue(null);
    },
    
    onRemove: function(widget, rowIndex) {
        var me = this,
            vm = me.getViewModel(),
            grid = me.getView(),
            store = grid.getStore(),
            sm = grid.getSelectionModel(),
            rec = null;
        if (Number.isInteger(rowIndex)) {
            rec = store.getAt(rowIndex);
            sm.select(rec);
        }
        vm.subtractAmount(rec.get('totalPrice'));
        store.remove(rec);
    },
    
    onEditCell: function(editor, e) {
        var me=this,
        rec = e.record;
        e.record.set('totalPrice', (rec.get('sellingPrice') + (rec.get('sellingPrice') * rec.get('gst') / 100)) * rec.get('perchasedUnit'));
        e.record.commit();
    }
});