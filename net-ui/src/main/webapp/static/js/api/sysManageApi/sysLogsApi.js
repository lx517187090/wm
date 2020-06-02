var sysLogList = function(params) {// 获取日志列表
    return fetch({
        url: '/sys/log/list',
        method: 'post',
        data: params
    });
};
var bankList = function (params) {// 获取支行列表
    return fetch({
        url: '/sys/user/bankList',
        method: 'post',
        data: params
    });
};

var userList = function (params) {// 获取用户列表
    return fetch({
        url: '/sys/user/allUser',
        method: 'post',
        data: params
    });
};

var operateTypes = [
    {
        value: null,
        label: '请选择'
    },
    {
        value: "1",
        label: '新增'
    },
    {
        value: "2",
        label: '更新'
    },
    {
        value: "3",
        label: '删除'
    },
    {
        value: "4",
        label: '下载'
    },
    {
        value: "5",
        label: '导出'
    }
];


var formatOperateType = function (row) {
    switch (row.operateType) {
        case '0':
            return '其他';
        case '1':
            return '新增';
        case '2':
            return '更新';
        case '3':
            return '删除';
        case'4':
            return '下载';
        case'5':
            return '导出';
    }
};