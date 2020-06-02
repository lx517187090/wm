var parameterList = function(params){// 获取参数列表
    return fetch({
        url: 'sys/parameter/list',
        method: 'post',
        data: params
    });
};

var addParameter = function(params) {// 新增参数行
    return fetch({
        url: '/sys/parameter/save',
        method: 'post',
        data: params
    });
};

var updateParameter = function (params) {
    return fetch({
        url: '/sys/parameter/update',
        method: 'put',
        data: params
    });
};

var deleteParameter = function(params) {//删除参数行
    return fetch({
        url: '/sys/parameter/delete',
        method: 'delete',
        data: params
    });
};