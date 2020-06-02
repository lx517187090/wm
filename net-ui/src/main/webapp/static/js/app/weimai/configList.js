//进行后台链接的统一定义
var orderInfoListUrl = '/weimai/config/table';
var orderInfoExportUrl = '/order/manager/exportExcel';
var orderInfoUpdateUrl = '/order/manager/list';
var ReportContractListVm;
(function () {
    ReportContractListVm = new Vue({
        el: '#orderInfoApp',
        data: function () {
            return {
                // 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {
                    "confName": null,
                    "confKey": null,
                    "confGroup":""
                },
                searchLoading: false,
                tableLoading: false,
                showHide: true,
                showPageType: '',
                currentPage: 1,
                pageSize: 10,
                multipleSelection: [],
                panelTitle: '',
                configForm: {},
                type: '',
                rules: {

                    /*      thirdCode: [
                              {required: true, message: '请选择支行', trigger: 'blur'}
                          ]*/
                },
                total: 0,
                tableData: []

            };
        },
        methods: {
            handleSelectionChange: function (val) {
                this.multipleSelection = val;
            },
            onSubmit: function () {//查询
                this.formObj.firstQuery = false;
                this.searchLoading = true;
                this.tableLoading = true;
                vm.getTableData(this, orderInfoListUrl);
            },
            getBack: function () {//返回
                this.showHide = true;
            },
            handleSizeChange: function (pageSize) {
                this.pageSize = pageSize;
                this.onSubmit();
            },
            updateOrderInfo: function (formName) {//提交
                var self = this;
                this.$refs[formName].validate(function (valid) {
                    if (valid) {
                        if(self.addModifyFlag) {
                            var params = Object.assign(self.configForm);
                            params.confGroup = document.getElementById("type").value;
                            saveConfig(params).then(function (res) {
                                self.$message({type: 'success', message: '保存成功!'});
                                self.showHide = true;
                                vm.getTableData(self, orderInfoListUrl);
                            });
                        }else {
                            var params = Object.assign(self.configForm);
                            params.confGroup = document.getElementById("type").value;
                            updateConfig(params).then(function (res) {
                                self.$message({type: 'success', message: '修改成功!'});
                                self.showHide = true;
                                vm.getTableData(self, orderInfoListUrl);
                            });
                        }
                    } else {
                        return false;
                    }
                });
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                this.onSubmit();
            },
            addInfo: function () {
                debugger;
                this.addModifyFlag = true;
                this.panelTitle = "新增";
                this.showHide = false;
                this.$refs["configForm"].resetFields();
            },
            modifyInfo: function () {
                debugger;
                var self = this;
                this.addModifyFlag = false;
                this.panelTitle = "修改";
                var msg = '';
                var flag = false;
                if (this.multipleSelection.length == 0) {
                    msg = "没有选中数据";
                    flag = true;
                } else if (this.multipleSelection.length > 1) {
                    msg = "只能选中一个数据进行修改";
                    flag = true;
                }
                if (flag) {
                    this.$message({
                        message: msg,
                        type: 'warning'
                    });
                    return false;
                }
                this.showHide = false;
                var selectData = this.multipleSelection[0];
                self.configForm = {
                    id:selectData.id,
                    confName: selectData.confName,
                    confKey:selectData.confKey
                };
            },
            deleteInfo: function () {
                var self = this;
                if (this.multipleSelection.length == 0) {
                    this.$message({
                        message: '没有选中数据',
                        type: 'warning'
                    });
                    return false;
                }

                this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    var roleIds = [];
                    self.multipleSelection.map(function (item) {
                        roleIds.push(item.id);
                    });
                    var params = JSON.stringify(roleIds);
                    deleteConfig(params).then(function (res) {
                        if (res.data >= 1) {
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            vm.getTableData(self, orderInfoListUrl);
                        } else {
                            self.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                    });
                }).catch(function () {
                    self.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
        },
        filters: {
            tenThousand: function (num) {
                if (typeof num == 'number') {
                    return math.eval(num + '/10000').toFixed(2);
                }
                return num;
            }
        },
        // 初始获取表格数据，在加载该js时触发该方法，获取表格数据
        mounted: function () {
            this.formObj.confGroup=document.getElementById("type").value;
            vm.getTableData(this, orderInfoListUrl);
        }
    })
})();

var deleteConfig = function(params){// 删除
    return fetch({
        url: '/weimai/config/delete',
        method: 'delete',
        data: params
    });
};
