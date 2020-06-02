
/*
var departList = () => {// 获取部门列表
    return fetch({
        url: '/sys/dept/list',
        method: 'get',
    });
};
*/

var departAdd = function(params){// 添加
    return fetch({
        url: '/sys/dept/save',
        method: 'post',
        data: params
    });
};

var departUpdate= function(params) {// 修改
    return fetch({
        url: '/sys/dept/update',
        method: 'post',
        data: params
    });
};

var departDelete = function(params) {// 删除
    return fetch({
        url: '/sys/dept/delete/' + params,
        method: 'delete'
    });
};


