var rateConfigureList = function(params){// 获取利率配置列表
    return fetch({
        url: '/sys/rateConfigure/list',
        method: 'post',
        data: params
    });
};
var addRateConfigure = function(params) {// 新增利率配置行
    return fetch({
        url: '/sys/rateConfigure/save',
        method: 'post',
        data: params
    });
};

var updateRateConfigure = function (params) {//修改利率配置行
    return fetch({
        url: '/sys/rateConfigure/update',
        method: 'put',
        data: params
    });
};

var deleteRateConfigure = function(params) {//删除利率配置行
    return fetch({
        url: '/sys/rateConfigure/delete',
        method: 'delete',
        data: params
    });
}