function makeADGTag(adid , divid, width, height){
    var adg = document.getElementById(divid);
    var script = document.createElement('script');
    script.type = 'text/javascript';

    // async=trueにすることによって非同期に対応されます
    script.src ='https://i.socdm.com/sdk/js/adg-script-loader.js?id=' + adid + '&adType=FREE&width='+ width +'&height='+ height +'&displayid=0&targetID=adgAd_' + divid + '&async=true';
    
    if(adg != null){
        adg.appendChild(script);
    }
    adg.style.display = 'none';
    var count = 0;
    var timer = setInterval(function checkAd(){
        count++;
        if(count > 100){
            clearInterval(timer);
            return;
        }
        if(checkADGTag(adg) === true){
            clearInterval(timer);
            adg.style.display = 'inline';
        }
    },100);
}

// aタグを探す
function checkADGTag(obj){
    var links = obj.getElementsByTagName('a');
    var frms = obj.getElementsByTagName('iframe');
    if(links.length > 0){
        makeADGLink(links);
        return true;
    }

    var res = false;
    for(var i = 0; i < frms.length; i++){
        if(checkADGTag(frms[i].contentWindow.document) === true){
            res = true
        }
    }
    return res;
}

// aタグを置換する
function makeADGLink(arr){
    for(var i = 0; i < arr.length; i++){
        (function(a) {
            var href = a.href;
            a.href = '#';
            a.onclick = function(){
                var ref = window.open(href, '_system');
                return false;
            }
        })(arr[i]);
    }
}

