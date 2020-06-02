var orderList = function (params) {
    return fetch({url: "/order/manager/list", method: "post", data: params})
};

/**基础信息维护保存方法*/
var saveConfig = function (params) {
    return fetch({
        url: '/weimai/config/save',
        method: 'post',
        data: params
    });
};

var updateConfig = function (params) {
    return fetch({
        url: '/weimai/config/update',
        method: 'post',
        data: params
    });
};

/**订单录入保存方法*/
var saveOrderInfo = function (params) {
    return fetch({
        url: '/weimai/info/save',
        method: 'post',
        data: params
    });
};

var updateOrderInfo = function (params) {
    return fetch({
        url: '/weimai/info/update',
        method: 'post',
        data: params
    });
};

var deleteInfo = function(params){// 删除订单信息
    return fetch({
        url: '/weimai/info/delete',
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

var enabledOptions = [{
    value: "1",
    label: '入库'
}, {
    value: "0",
    label: '出库'
}];
var statusOptions = [{
    value: 1,
    label: '可见'
}, {
    value: 0,
    label: '不可见'
}];
var isEmptyStr = function (str) {
    return str === undefined || str == null || str === '';
};


