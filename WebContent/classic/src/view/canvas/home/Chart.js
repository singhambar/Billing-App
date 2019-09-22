Ext.define('App.view.canvas.home.Chart', {
    extend: 'Ext.Panel',
    xtype: 'pie-donut',
//    controller: 'pie-basic',

    width: 650,height:3000,
    requires: [
        'Ext.chart.AbstractChart',
        'Ext.chart.PolarChart'
    ],

    tbar: [
        '->',
        {
            text: 'Preview',
            handler: 'onPreview'
        }
    ],

    items: [{
        xtype: 'polar',
        reference: 'chart',
        width: '100%',
        height: 500,
        insetPadding: 50,
        innerPadding: 20,
        store: {
            type: 'chartstore'
        },
        legend: {
            docked: 'bottom'
        },
        interactions: ['rotate', 'itemhighlight'],
        sprites: [{
            type: 'text',
            text: 'Donut Charts - Basic',
            fontSize: 22,
            width: 100,
            height: 30,
            x: 40, // the sprite x position
            y: 20  // the sprite y position
        }, {
            type: 'text',
            text: 'Data: IDC Predictions - 2017',
            x: 12,
            y: 425
        }, {
            type: 'text',
            text: 'Source: Internet',
            x: 12,
            y: 440
        }],
        series: [{
            type: 'pie',
            angleField: 'data1',
            donut: 50,
            label: {
                field: 'os',
                display: 'outside'
            },
            highlight: true,
            tooltip: {
                trackMouse: true,
                renderer: 'onSeriesTooltipRender'
            }
        }]
    }]

});
