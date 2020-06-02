
    // 获取menuData 数据;
     var routerList = [];
     var menuData = [];

     var getMenuData = function() {
        axios.get(ctx+menuListUrl).then(function (res) {
            vm.menuArr = res.data.data;
            menuData = res.data.data;
            routerList = filterRouterData(res.data.data);

            initRouter(routerList)
        }).catch(function (error) {
            console.log(error);
        });
    };

    function initRouter(routerList){
        spaRouters.map('/home',function(transition){
            var parmas = {url: './home' };
            axios.get(parmas.url).then(function (res) {
                vm.loading = false;
                if(res.data.indexOf('id="loginApp"') != -1){
                    location.href = ctx;
                }else{
                    var $content = $("#content_table");
                    var b = $content.find($("div[id$=App]"));
                    b.remove();
                    $content.append(res.data);
                   /* //先隐藏内容页面,防止页面提前加载无css的数据
                    var c = $content.find($("div[id$=App]"));
                    c.hide();*/
                }
            });
        });
        for( var i = 0; i < routerList.length; i++){
            var path = routerList[i].path;
            spaRouters.map(path,function(transition){
                var parentPath = getParentPath(transition.path);
                var parmas = {url: parentPath +  transition.path };
                getContentTemplate(parmas).then(function (res) {
                    if(res.data.indexOf('id="loginApp"') != -1){
                        location.href = ctx;
                    }else{
                        var $content = $("#content_table");
                        var b = $content.find($("div[id$=App]"));
                        b.remove();
                        $content.append(res.data);
                        //先隐藏内容页面,防止页面提前加载无css的数据
                        var c = $content.find($("div[id$=App]"));
                        c.hide();
                    }
                });
            });
        }
        spaRouters.init();
        spaRouters.urlChange();
    }

    function filterRouterData(listArr){
         var targetArr = [];
        for(var i=0; i<listArr.length;i++){
            targetArr = targetArr.concat(listArr[i].children);
        }
        return targetArr;
    }
    function getParentPath (path) {
        for(var i=0; i< routerList.length; i++){
            if(routerList[i].path  === path){
                return routerList[i].parentPath;
            }
        }
        return null;
    }
    function getPathName(path){
        for(var i=0; i< menuData.length;i++){
            for(var j=0; j < menuData[i].children.length; j++){
                if(menuData[i].children[j].path == path){
                    vm.childName = menuData[i].children[j].name;
                    vm.parentName = menuData[i].name;
                    return true;
                }
            }
        }
    }
    spaRouters.beforeEach(function(transition){
        vm.loading = true;
        vm.currentRouter = transition.to.path;
        getPathName(transition.to.path);
        setTimeout(function(){
            transition.next()
        },100)
    });
    spaRouters.afterEach(function(transition){
        console.log("切换之后dosomething")
    });

