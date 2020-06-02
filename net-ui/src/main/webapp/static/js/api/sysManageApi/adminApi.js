
var userList = function(params){// 获取用户列表
    return fetch({
        url: '/sys/user/list',
        method: 'post',
        data: params
    });
};

var bankList = function(params){// 获取支行列表
    return fetch({
        url: '/sys/user/bankList',
        method: 'post',
        data: params
    });
};

var userDetails = function(params) {// 查询用户详情
    return fetch({
        url: '/sys/user/info/' + params,
        method: 'get'
    });
};

var validateUserName = function (params) {
    return fetch({
        url: '/sys/user/validateUserName',
        method: 'post',
        data: params
    });
}

var addUser = function(params) {// 新增用户
    return fetch({
        url: '/sys/user/save',
        method: 'post',
        data: params
    });
};

var ModifyUser = function(params) {// 修改用户
    return fetch({
        url: '/sys/user/update',
        method: 'put',
        data: params
    });
};

var deleteUser = function(params) {//删除用户
    return fetch({
        url: '/sys/user/delete',
        method: 'delete',
        data: params
    });
};

var roleSelectList = function(){// 权限选择列表;
    return fetch({
        url: '/sys/role/select',
        method: 'get'
    });
};

var resetPwd = function(params) {//重置密码
    return fetch({
        url: '/sys/user/resetPwd',
        method: 'put',
        data: params
    });
};