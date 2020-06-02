//进行后台链接的统一定义
var RateConfigureUrl = '/sys/rateConfigure/list';
var RateConfigureVm;
(function (){
    RateConfigureVm = new Vue({
        el: '#RateConfigureApp',
        data: function() {
            return {
                //此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {},
                currentPage:1,
                pageSize:10,
                total:0,
                tableData:[],
                addModifyFlag: true,
                showHide: true,
                multipleSelection: [],
                panelTitle: '',
                ruleForm: {
                    grade: "",
                    rate: "",
                    province:"",
                    provinceCode:""
                },
                rules: {
                    grade: [
                        { required: true, message: '请输入等级', trigger: 'blur' },
                    ],
                    rate: [
                        { required: true, message: '请输入利率', trigger: 'blur' },
                    ]
                },
                searchLoading: false,
                submitLoading: false,
                tableLoading: false
            };
        },
        methods: {
            addInfo: function(){
                this.addModifyFlag = true;
                this.panelTitle = "新增";
                this.showHide = false;
                this.$refs["ruleForm"].resetFields();
            },
            modifyInfo: function(){
                this.addModifyFlag = false;
                this.panelTitle = "修改";
                var msg = '';
                var flag = false;
                if(this.multipleSelection.length == 0){
                    msg = "没有选中利率配置行";
                    flag = true;
                }else if( this.multipleSelection.length > 1 ){
                    msg = "只能选中一个利率配置行";
                    flag = true;
                }
                if(flag){
                    this.$message({
                        message: msg,
                        type: 'warning'
                    });
                    return false;
                }
                this.showHide = false;
                var selectData = this.multipleSelection[0];
                this.ruleForm ={
                    grade: selectData.grade,
                    rate: selectData.rate+"",
                    remark: selectData.remark
                }
            },
            deleteInfo: function(){
                var self = this;
                if(this.multipleSelection.length == 0){
                    this.$message({
                        message: '没有选中利率配置行',
                        type: 'warning'
                    });
                    return false;
                }
                this.$confirm('此操作将永久删除利率, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var parameterIds = [];
                    self.multipleSelection.map( function(item){
                        parameterIds.push(item.id);
                    });
                    var params = JSON.stringify(parameterIds);
                    deleteRateConfigure(params).then(function(res){
                        self.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        vm.getTableData(self,RateConfigureUrl);
                    });
                }).catch(function() {
                    self.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            submitForm: function(formName) {
                var self = this;
                this.$refs[formName].validate(function(valid){
                    if (valid) {
                        self.submitLoading = true;
                        if(self.addModifyFlag){ // 添加数据
                            var params = self.ruleForm;
                            addRateConfigure(params).then(function(res){
                                self.$message({type: 'success', message: '添加成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                vm.getTableData(self,RateConfigureUrl);
                            });
                        }else {
                            var params = Object.assign(self.ruleForm,{id: self.multipleSelection[0].id});
                            updateRateConfigure(params).then(function (res) {
                                self.$message({type: 'success', message: '修改成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                vm.getTableData(self,RateConfigureUrl);
                            });
                        }
                    } else {
                        return false;
                    }
                });
            },
            getBack: function(){
                this.showHide = true;
            },
            onSubmit:function () {
                this.searchLoading = false;
                self.submitLoading = false;
                this.tableLoading = false;
                vm.getTableData(this,RateConfigureUrl);
            },
            handleSelectionChange: function(val){
                this.multipleSelection = val;
            },
            handleSizeChange: function(pageSize) {
                this.pageSize = pageSize;
                this.tableLoading = false;
                vm.getTableData(this,RateConfigureUrl);
            },
            handleCurrentChange: function(currentPage) {
                this.currentPage = currentPage;
                this.tableLoading = false;
                vm.getTableData(this,RateConfigureUrl);
            }
        },
        //初始获取表格数据，在加载该js时触发该方法，获取表格数据
        mounted:function () {
            var data = {currentPage:this.currentPage,pageSize:this.pageSize};
            vm.getTableData(this,RateConfigureUrl);
        }
    })
})();
   