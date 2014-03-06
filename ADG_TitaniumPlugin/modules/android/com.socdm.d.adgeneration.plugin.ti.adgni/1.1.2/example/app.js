var win = Ti.UI.createWindow({
    title:'Window',
    backgroundColor:'white'
});
win.open();

var adgni = require('com.socdm.d.adgeneration.plugin.ti.adgni');

var adgview = adgni.createView({
    iosLocationId:"10723",
    aosLocationId:"10724",
    width:320,
    height:50,
    top:0,
    left:0,
    adtype:"SP",
});

win.add(adgview);