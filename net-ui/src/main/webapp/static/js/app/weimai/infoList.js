//进行后台链接的统一定义
var orderInfoListUrl = '/weimai/info/table';
var orderInfoExportUrl = '/weimai/info/exportExcel';
var ReportContractListVm;
(function () {
    ReportContractListVm = new Vue({
        el: '#orderInfoApp',
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
                tableLoading: false,
                machineList: [],
                colorList:[],
                partsList:[],
                expressList:[],
                showHide: true,
                showPageType: '',
                currentPage: 1,
                pageSize: 10,
                size:10,
                multipleSelection: [],
                panelTitle: '',
                orderForm: {},
                rules: {
                    machineModel: [
                        {required: true, message: '请选择机器型号', trigger: 'blur'}
                    ],
                    num: [
                        {required: true, message: '请输入数量', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ],
                    mobilePhone: [
                        {required: true, message: '请输入手机', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'}
                    ],
                    express: [
                        {required: true, message: '请选择快递公司', trigger: 'blur'}
                    ],
                    courierNumber: [
                        {required: true, message: '请输入快递单号', trigger: 'blur'}
                    ],
                    badProblems: [
                        {required: true, message: '请输入不良问题', trigger: 'blur'}
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
                debugger;
                this.showHide = true;
                var self = this;
                vm.getTableData(self, orderInfoListUrl);
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
                        if(self.addModifyFlag) {
                            var params = Object.assign(self.orderForm);
                            saveOrderInfo(params).then(function (res) {
                                self.$message({type: 'success', message: '保存成功!'});
                                self.showHide = true;
                                vm.getTableData(self, orderInfoListUrl);
                            });
                        }else {
                            var params = Object.assign(self.orderForm);
                            updateOrderInfo(params).then(function (res) {
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
            searchInfo: function(){
                this.searchLoading = true;
                this.tableLoading = true;
                this.getRoleList();
            },
            addInfo: function(){
                var self = this;
                self.addModifyFlag = true;
                self.panelTitle = "新增";
                self.showHide = false;
                self.$refs["orderForm"].resetFields();
                self.orderForm = {
                    id: "",
                    happenDate: "",
                    machineModel:"",
                    part: "",
                    num:"",
                    name: "",
                    fixedTelephone:"",
                    mobilePhone: "",
                    address:"",
                    express: "",
                    courierNumber:"",
                    color: "",
                    sn:"",
                    badProblems: "",
                    parts: [],
                    appearance:"",
                    createBy:"",
                    createDate:"",
                    deliveryDate:"",
                    faultProblem:"",
                    repairType:"",
                    rmk2:"",
                    rmk:""
                };
            },
            modifyInfo: function () {
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
                var parts = [];
                if (!isEmptyStr(selectData.parts)) {
                    var arr = selectData.parts.split(",");
                    $.each(arr, function (i, val) {
                        parts.push(val);
                    });
                }
                self.orderForm = {
                    id: selectData.id,
                    happenDate: selectData.happenDate,
                    machineModel:selectData.machineModel,
                    part: selectData.part,
                    num:selectData.num + "",
                    name: selectData.name,
                    fixedTelephone:selectData.fixedTelephone,
                    mobilePhone: selectData.mobilePhone,
                    address:selectData.address,
                    express: selectData.express,
                    courierNumber:selectData.courierNumber,
                    color: selectData.color,
                    sn:selectData.sn,
                    badProblems: selectData.badProblems,
                    parts: parts,
                    appearance:selectData.appearance,
                    createBy:selectData.createBy,
                    createDate:selectData.createDate,
                    deliveryDate:selectData.deliveryDate,
                    faultProblem:selectData.faultProblem,
                    repairType:selectData.repairType,
                    rmk2:selectData.rmk2,
                    rmk:selectData.rmk
                };
            },
            deleteInfo: function(){
                var self = this;
                if(this.multipleSelection.length == 0){
                    this.$message({
                        message: '没有选中订单',
                        type: 'warning'
                    });
                    return false;
                }
                this.$confirm('此操作将永久删除数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var roleIds = [];
                    self.multipleSelection.map( function(item){
                        roleIds.push(item.id);
                    });
                    var params = JSON.stringify(roleIds);
                    deleteInfo(params).then(function(res){
                        if(res.data >= 1){
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            vm.getTableData(self, orderInfoListUrl);
                        }else{
                            self.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                    });
                }).catch(function(){
                    self.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            getJiQiList: function () {
                var prams = {
                    confGroup: 'machine'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.machineList.push(val)
                    });
                });
            },
            getColorList: function () {
                var prams = {
                    confGroup: 'color'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.colorList.push(val)
                    });
                });
            },
            getPartsList: function () {//配件
                var prams = {
                    confGroup: 'parts'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.partsList.push(val)
                    });
                });
            },
            getExpressList:function () {//快递
                var prams = {
                    confGroup: 'express'
                };
                var self = this;
                configList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.expressList.push(val)
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
            this.getJiQiList();
            this.getColorList();
            this.getExpressList();
            this.getPartsList();
            vm.getTableData(this, orderInfoListUrl);
        }
    })
})();
