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

var configList = function (params) {
    return fetch({
        url: '/weimai/config/configList',
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

/**派件录入保存方法*/
var saveDelivery = function (params) {
    return fetch({
        url: '/weimai/info/saveDelivery',
        method: 'post',
        data: params
    });
};

var updateDelivery = function (params) {
    return fetch({
        url: '/weimai/info/updateDelivery',
        method: 'post',
        data: params
    });
};

var deleteDelivery = function(params){// 删除派件信息
    return fetch({
        url: '/weimai/info/deleteDelivery',
        method: 'delete',
        data: params
    });
};

var isEmptyStr = function (str) {
    return str === undefined || str == null || str === '';
};

var deliveryTypeOptions = [{
    value: "1",
    label: '寄付月结'
}, {
    value: "0",
    label: '到付现结'
}];

var deliverySignOptions = [{
    value: "1",
    label: '已发货'
}, {
    value: "0",
    label: '未发货'
}];