var flowSettingList = function() {// 获取配置列表
    return fetch({
        url: '/sys/flow/list',
        method: 'get',
    });
};

var updateFlowSetting = function(params){
    return fetch({
        url: 'sys/flow/update',
        method:'put',
        data:params
    })
};