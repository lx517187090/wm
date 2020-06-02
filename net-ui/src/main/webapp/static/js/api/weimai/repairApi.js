var orderList = function (params) {
    return fetch({url: "/order/manager/list", method: "post", data: params})
};

/**维修录入保存方法*/
var saveRepair = function (params) {
    return fetch({
        url: '/weimai/info/saveRepair',
        method: 'post',
        data: params
    });
};

var updateRepair = function (params) {
    return fetch({
        url: '/weimai/info/updateRepair',
        method: 'post',
        data: params
    });
};

var deleteRepair = function(params){// 删除维修信息
    return fetch({
        url: '/weimai/info/deleteRepair',
        method: 'delete',
        data: params
    });
};

var configList = function (params) {
    return fetch({
        url: '/weimai/config/configList',
        method: 'post',
        data: params
    });
};

var isEmptyStr = function (str) {
    return str === undefined || str == null || str === '';
};
