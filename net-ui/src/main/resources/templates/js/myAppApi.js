[# th:if="${requestMappingMaps.list}"]
var [(${className})]List = function(params){
    return fetch({
        url: '[(${requestMappingMaps.list})]',
        method: 'post',
        data: params
    });
};
[/]
[# th:if="${requestMappingMaps.save}"]
var add[(${originClassName})] = function(params) {
    return fetch({
        url: '[(${requestMappingMaps.save})]',
        method: 'post',
        data: params
    });
};
[/]
[# th:if="${requestMappingMaps.update}"]
var update[(${originClassName})] = function (params) {
    return fetch({
        url: '[(${requestMappingMaps.update})]',
        method: 'put',
        data: params
    });
};
[/]
[# th:if="${requestMappingMaps.delete}"]
var delete[(${originClassName})] = function(params) {
    return fetch({
        url: '[(${requestMappingMaps.delete})]',
        method: 'delete',
        data: params
    });
}
[/]