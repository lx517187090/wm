

var roleList = function(params){// 获取角色列表
    return fetch({
        url: '/sys/role/list',
        method: 'get',
        params: params
    });
};


var menuList = function(){// 获取用户菜单
    return fetch({
        url: '/sys/menu/nav',
        method: 'get'
    });
};

var menuDetails = function(params){ // 获取菜单详情
    return fetch({
        url: '/sys/menu/info/' + params,
        method: 'get'
    })
};

var menuSelectList = function(){ // 获取无功能按钮菜单列表
    return fetch({
        url: '/sys/menu/select',
        method: 'get'
    })
};

var addMenu = function(params) {// 新增菜单
    return fetch({
        url: '/sys/menu/save',
        method: 'post',
        data: params
    });
};

var ModifyMenu = function(params) {// 修改菜单
    return fetch({
        url: '/sys/menu/update',
        method: 'put',
        data: params
    });
};

var deleteMenu = function(params) {// 删除菜单;
    return fetch({
        url: '/sys/menu/delete',
        method: 'delete',
        data: params
    });
};