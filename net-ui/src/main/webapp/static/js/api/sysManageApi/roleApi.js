
var roleList = function(params){// 获取角色列表
    return fetch({
        url: '/sys/role/list',
        method: 'post',
        data: params
    });
};

var roleSelectList = function(){// 权限选择列表;
    return fetch({
        url: '/sys/role/select',
        method: 'get'
    });
};

var roleDetails = function(params){// 获取角色详情
    return fetch({
        url: '/sys/role/info/'+params,
        method: 'get'
    });
};

var addRole = function(params){// 添加角色
    return fetch({
        url: '/sys/role/save',
        method: 'post',
        data: params
    });
};

var roleModify = function(params){// 修改角色
    return fetch({
        url: '/sys/role/update',
        method: 'PUT',
        data: params
    });
};

var deleteRole = function(params){// 删除角色
    return fetch({
        url: '/sys/role/delete',
        method: 'delete',
        data: params
    });
};


