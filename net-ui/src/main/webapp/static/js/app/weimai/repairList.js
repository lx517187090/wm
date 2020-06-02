var orderInfoListUrl = '/weimai/info/repairTable';
var orderInfoExportUrl = '/order/manager/exportExcel';
var ReportContractListVm;
(function () {
    ReportContractListVm = new Vue({
        el: '#deliveryApp',
        data: function () {
            return {
                // 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {
                    "express": null,
                    "machineModel": null,
                    "happenDateStart": null,
                    "happenDateEnd": null
                },
                searchLoading: false,
                repairLoading: false,
                tableLoading: false,
                showHide: true,
                showPageType: '',
                currentPage: 1,
                pageSize: 10,
                multipleSelection: [],
                faultList:[],
                repairList:[],
                panelTitle: '',
                orderForm: {},
                repairForm: {},
                rules: {
                    faultProblem: [
                        {required: true, message: '请输入故障问题', trigger: 'blur'}
                    ]
                },
                total: 0,
                tableData: []
            };
        },
        methods: {
            getStartTime: function (date) {
                this.formObj.registerTimeStart = date;
            },
            getEndTime: function (date) {
                this.formObj.registerTimeEnd = date;
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
                this.formObj.firstQuery = false;
                this.searchLoading = true;
                this.tableLoading = true;
                vm.getTableData(this, orderInfoListUrl);
            },
            // 点击导出触发事件
            exportExcel: function () {
                vm.exportExcel(this, orderInfoExportUrl)
            },
            getBack: function () {
                this.$refs["repairForm"].resetFields();
                this.showHide = true;
            },
            handleSizeChange: function (pageSize) {
                this.pageSize = pageSize;
                this.onSubmit();
            },
            updateOrderInfo: function (formName) {
                var self = this;
                this.$refs[formName].validate(function (valid) {
                    if (valid) {
                        self.repairLoading = true;
                        if (self.addModifyFlag) {
                            debugger
                            var params = Object.assign(self.repairForm);
                            params.infoId = self.orderForm.infoId;
                            saveRepair(params).then(function (res) {
                                if (res.data === 1) {
                                    self.$message({type: 'success', message: '保存成功!'});
                                    self.showHide = true;
                                    self.repairLoading = false;
                                    vm.getTableData(self, orderInfoListUrl);
                                } else {
                                    self.repairLoading = false;
                                    self.$message({type: 'error', message: '保存失败!' + res.data});
                                }
                            });
                        } else {
                            var params = Object.assign(self.repairForm);
                            updateRepair(params).then(function (res) {
                                if (res.data === 1) {
                                    self.$message({type: 'success', message: '修改成功!'});
                                    self.showHide = true;
                                    self.repairLoading = false;
                                    vm.getTableData(self, orderInfoListUrl);
                                } else {
                                    self.repairLoading = false;
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
                debugger
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
                    msg = "该单已填写维修信息，请删除后再重新填写或修改";
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
                //this.$refs["repairForm"].resetFields();
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
                    parts: selectData.parts
                };
            },
            modifyInfo: function () {
                debugger;
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
                } else if (isEmptyStr(selectData.id)) {
                    msg = "该单还未填写维修信息，请填写后再修改";
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
                    parts: selectData.parts
                };
                self.repairForm = {
                    id:selectData.id,
                    infoId: selectData.infoId,
                    faultProblem: selectData.faultProblem,
                    repairType: selectData.repairType,
                    rmk2: selectData.rmk2,
                    maintainer:selectData.maintainer
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
                        message: '该单还未填写维修信息,不允许删除',
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
                    deleteRepair(params).then(function (res) {
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
            getFaultList:function () {//故障问题
                var prams = {
                    confGroup: 'fault'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.faultList.push(val)
                    });
                });
            },
            getRepairList:function () {//维修方式
                var prams = {
                    confGroup: 'repair'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.repairList.push(val)
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
            this.getFaultList();
            this.getRepairList();
            vm.getTableData(this, orderInfoListUrl);
        }
    })
})();

