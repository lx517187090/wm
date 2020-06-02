//进行后台链接的统一定义
var orderInfoListUrl = '/weimai/info/deliveryTable';
var orderInfoExportUrl = '/weimai/info/exportExcel';
var ReportContractListVm;
(function () {
    ReportContractListVm = new Vue({
        el: '#deliveryApp',
        data: function () {
            return {
                // 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {
                    "happenDate": null,
                    "name": "",
                    "mobilePhone":"",
                    "courierNumber":"",
                    "sn": "",
                    "deliveryDate": ""
                },
                searchLoading: false,
                deliveryLoading:false,
                tableLoading: false,
                showHide: true,
                showPageType: '',
                currentPage: 1,
                pageSize: 10,
                multipleSelection: [],
                payAcountList:[],
                panelTitle: '',
                orderForm: {},
                deliveryForm: {},
                rules: {
                    deliveryDate: [
                        {required: true, message: '请输入寄出时间', trigger: 'blur'}
                    ]
                },
                total: 0,
                tableData: []
            };
        },
        methods: {
            getStartTime: function (date) {
                this.formObj.happenDate = date;
            },
            getEndTime: function (date) {
                this.formObj.deliveryDate = date;
            },
            formatStr: function (date) {
                if (date < 10) {
                    return '0' + date;
                }
                return date;
            },
            handleSelectionChange: function (val) {
                this.multipleSelection = val;
            },
            onSubmit: function () {
                this.searchLoading = true;
                this.tableLoading = true;
                vm.getTableData(this, orderInfoListUrl);
            },
            // 点击导出触发事件
            exportExcel: function () {
                vm.exportExcel(this, orderInfoExportUrl)
            },
            getBack: function () {
                this.$refs["deliveryForm"].resetFields();
                this.showHide = true;
            },
            handleSizeChange: function (pageSize) {
                this.pageSize = pageSize;
                this.onSubmit();
            },
            updateOrderInfo: function (formName) {
                debugger;
                var self = this;
                this.$refs[formName].validate(function (valid) {
                    if (valid) {
                        self.deliveryLoading = true;
                        if (self.addModifyFlag) {
                            var params = Object.assign(self.deliveryForm);
                            params.infoId = self.deliveryForm.infoId;
                            saveDelivery(params).then(function (res) {
                                if (res.data === 1) {
                                    self.$message({type: 'success', message: '保存成功!'});
                                    self.showHide = true;
                                    vm.getTableData(self, orderInfoListUrl);
                                    self.deliveryLoading = false;
                                } else {
                                    self.$message({type: 'error', message: '保存失败!' + res.data});
                                    self.deliveryLoading = false;
                                }
                            });
                        } else {
                            var params = Object.assign(self.deliveryForm);
                            updateDelivery(params).then(function (res) {
                                if (res.data === 1) {
                                    self.$message({type: 'success', message: '修改成功!'});
                                    self.showHide = true;
                                    self.deliveryLoading = false;
                                    vm.getTableData(self, orderInfoListUrl);
                                } else {
                                    self.deliveryLoading = false;
                                    self.$message({type: 'error', message: '修改失败!' + res.data});
                                }
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
            searchInfo: function () {
                this.searchLoading = true;
                this.tableLoading = true;
            },
            addInfo: function () {
                var self = this;
                var msg = '';
                var flag = false;
                var selectData = this.multipleSelection[0];
                if (this.multipleSelection.length == 0) {
                    msg = "没有选中数据";
                    flag = true;
                } else if (this.multipleSelection.length > 1) {
                    msg = "只能选中一个数据进行修改";
                    flag = true;
                } else if (!isEmptyStr(selectData.id)) {
                    msg = "该单已派件，请删除后再重新派件";
                    flag = true;
                }

                if (flag) {
                    this.$message({
                        message: msg,
                        type: 'warning'
                    });
                    return false;
                }
                this.addModifyFlag = true;
                this.panelTitle = "新增";
                this.showHide = false;
                //this.$refs["deliveryForm"].resetFields();
                self.orderForm = {
                    happenDate: selectData.happenDate,
                    machineModel: selectData.machineModel,
                    infoId: selectData.infoId,
                    part: selectData.part,
                    num: selectData.num,
                    name: selectData.name,
                    fixedTelephone: selectData.fixedTelephone,
                    mobilePhone: selectData.mobilePhone,
                    address: selectData.address,
                    express: selectData.express,
                    courierNumber: selectData.courierNumber,
                    color: selectData.color,
                    sn: selectData.sn,
                    badProblems: selectData.badProblems,
                    parts: selectData.parts,
                    weight:selectData.weight
                };
                self.deliveryForm = {
                    infoId: selectData.infoId
                }
            },
            modifyInfo: function () {
                debugger
                var self = this;
                this.addModifyFlag = false;
                this.panelTitle = "修改";
                var msg = '';
                var flag = false;
                var selectData = this.multipleSelection[0];
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
                self.orderForm = {
                    happenDate: selectData.happenDate,
                    machineModel: selectData.machineModel,
                    part: selectData.part,
                    num: selectData.num,
                    name: selectData.name,
                    fixedTelephone: selectData.fixedTelephone,
                    mobilePhone: selectData.mobilePhone,
                    address: selectData.address,
                    express: selectData.express,
                    courierNumber: selectData.courierNumber,
                    color: selectData.color,
                    sn: selectData.sn,
                    badProblems: selectData.badProblems,
                    parts: selectData.parts,
                    weight:selectData.weight,
                    faultProblem:selectData.faultProblem,
                    repairType:selectData.repairType,
                    rmk2:selectData.rmk2
                };
                var money = selectData.money;
                if (isEmptyStr(money)) {
                    money = "";
                }
                self.deliveryForm = {
                    id:selectData.id,
                    infoId: selectData.infoId,
                    deliveryDate: selectData.deliveryDate,
                    deliveryType: selectData.deliveryType,
                    money: money + "",
                    payDate: selectData.payDate,
                    payAccount: selectData.payAccount,
                    rmk: selectData.rmk
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
                var selectData = this.multipleSelection[0];
                if (isEmptyStr(selectData.id)) {
                    this.$message({
                        message: '该单还未填写派件信息,不允许删除',
                        type: 'warning'
                    });
                    return false;
                }
                this.$confirm('此操作将永久删除数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    var roleIds = [];
                    self.multipleSelection.map(function (item) {
                        roleIds.push(item.id);
                    });
                    var params = JSON.stringify(roleIds);
                    deleteDelivery(params).then(function (res) {
                        if (res.data == 1) {
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            vm.getTableData(self, orderInfoListUrl);
                        } else {
                            self.$message({
                                type: 'error',
                                message: '删除失败!' + res.data
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
            getPayAccountList:function () {//付款账号
                var prams = {
                    confGroup: 'payAcc'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.payAcountList.push(val)
                    });
                });
            }
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
            this.getPayAccountList();
            vm.getTableData(this, orderInfoListUrl);
        }
    })
})();

