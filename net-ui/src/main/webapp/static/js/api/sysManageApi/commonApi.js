
var departList = function() {// 获取部门列表
    return fetch({
        url: '/sys/dept/list',
        method: 'get'
    });
};

var roleSelectList = function() {// 权限选择列表;
    return fetch({
        url: '/sys/role/select',
        method: 'get'
    });
};

var menuAllList = function() {// 权限选择列表;
    return fetch({
        url: '/sys/menu/list',
        method: 'get'
    });
};
