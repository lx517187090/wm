var paramentVm;
(function (){
    paramentVm = new Vue({
        el: '#parameterApp',
        data: function() {
            return {
                name:'',
                addModifyFlag: true,
                panelTitle: '',
                showHide: true,
                tableData: [],
                multipleSelection: [],
                searchLoading: false,
                tableLoading: false,
                currentPage: 1,
                size: 10,
                total: 1,
                ruleForm: {
                    key: "",
                    name: "",
                    value: "",
                    enabled: "",
                    status: "",
                    remark: "",
                },
                rules: {
                    key: [
                        { required: true, message: '请输入所属组', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入参数名', trigger: 'blur' }
                    ],
                    value: [
                        { required: true, message: '请输入参数值', trigger: 'blur' }
                    ],
                    enabled: [
                        { required: true, message: '请选择是否启用' }
                    ],
                    status: [
                        { required: true, message: '请选择是否可见' }
                    ]
                },
                listLoading: false,
                treeVisible: false,//操作权限树是否显示
                commonLoading: false,
                submitLoading: false,
                treeLoading : false
            }
        },
        methods: {
            enabledFormatter:function (row) {

            },
            getParameterList: function () {
                var prams = {
                    name: this.name.trim(),
                    size: this.size,
                    current: this.currentPage
                };
                var self = this;
                parameterList(prams).then(function (res) {
                    self.searchLoading = false;
                    self.tableLoading = false;
                    self.tableData = res.data.list;
                    self.total = res.data.totalCount;
                    self.currentPage = res.data.currPage;
                    self.size = res.data.pageSize;
                    vm.deleteLoading();
                });
            },
            handleSelectionChange: function(val){
                this.multipleSelection = val;
            },
            handleSizeChange: function(val) {
                this.size = val;
                this.tableLoading = true;
                this.getParameterList();
            },
            handleCurrentChange: function(val) {
                this.currentPage = val;
                this.tableLoading = true;
                this.getParameterList();
            },
            submitForm: function(formName) {
                var self = this;
                this.$refs[formName].validate(function(valid){
                    if (valid) {
                        self.submitLoading = true;
                        if(self.addModifyFlag){ // 添加数据
                            var params = self.ruleForm;
                            addParameter(params).then(function(res){
                                self.$message({type: 'success', message: '添加成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                self.getParameterList();
                            });
                        }else {
                            var params = Object.assign(self.ruleForm,{id: self.multipleSelection[0].id});
                            updateParameter(params).then(function (res) {
                                self.$message({type: 'success', message: '修改成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                self.getParameterList(); // 重新获取数据列表;
                            });
                        }
                    } else {
                        return false;
                    }
                });
            },
            searchInfo: function(){
                this.searchLoading = true;
                this.tableLoading = true;
                this.getParameterList();
            },
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
                    msg = "没有选中参数行";
                    flag = true;
                }else if( this.multipleSelection.length > 1 ){
                    msg = "只能选中一个参数行";
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
                    key: selectData.key,
                    name: selectData.name,
                    value: selectData.value,
                    enabled: selectData.enabled,
                    status: selectData.status,
                    remark: selectData.remark
                }
            },
            deleteInfo: function(){
                var self = this;
                if(this.multipleSelection.length == 0){
                    this.$message({
                        message: '没有选中用户',
                        type: 'warning'
                    });
                    return false;
                }
                this.$confirm('此操作将永久删除参数, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var parameterIds = [];
                    self.multipleSelection.map( function(item){
                        parameterIds.push(item.id);
                    });
                    var params = JSON.stringify(parameterIds);
                    deleteParameter(params).then(function(res){
                        self.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        self.getParameterList();
                    });
                }).catch(function() {
                    self.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            getBack: function(){
                this.showHide = true;
            },
            departmentSelect: function(){
                var self = this;
                this.treeVisible = true;
                this.$nextTick(function() {
                    self.initTree();
                });
            }

        },
        filters: {
            enabledFilter: function(value){
                switch (parseInt(value)){
                    case 1: return "启用"; break;
                    case 0: return "未启用"; break;
                    default: return ""; break;
                }
            },
            statesType: function (value) {
                switch (parseInt(value)){
                    case 0: return "warning"; break;
                    case 1: return "success"; break;
                    default: return ""; break;
                }
            },
            statusFilter: function(value){
                switch (parseInt(value)){
                    case 0: return "不可见"; break;
                    case 1: return "可见"; break;
                    default: return ""; break;
                }
            }
        },
        mounted: function () {
            this.getParameterList();
        }
    })
})();
