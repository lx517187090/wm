//进行后台链接的统一定义
var [(${className})]Url = '[(${requestMappingMaps.list})]';
var [(${className})]ExportUrl = '[(${requestMappingMaps.exportExcel})]';
var [(${className})]Vm;
(function (){
    [(${className})]Vm = new Vue({
        el: '#[(${className})]App',
        data: function() {
            return {
                //此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                [# th:if="!${#lists.isEmpty(requestList)}"]
                formObj: [[${formObj}]],
                searchLoading: false,
                tableLoading: false,
                [/]
                currentPage:1,
                pageSize:10,
                total:0,
                tableData:[],
                [# th:if="${requestMappingMaps.save} and ${requestMappingMaps.update}"]
                addModifyFlag: true,
                [/]
                showHide: true,
                [# th:if="${requestMappingMaps.save} or ${requestMappingMaps.update} or ${requestMappingMaps.delete}"]
                multipleSelection: [],
                [/]
                panelTitle: '',
                ruleForm: [[${ruleForm}]],
                rules:{
                    [# th:each="map : ${responseList}"]
                    [(${map.name})]:[
                        {required: true, message:'请输入[(${map.remark})]', trigger: 'blur' }
                    ],
                    [/]
                }
            };
        },
        methods: {
            [# th:if="${requestMappingMaps.save}"]
            addInfo: function(){
                this.addModifyFlag = true;
                this.panelTitle = "新增";
                this.showHide = false;
                this.$refs["ruleForm"].resetFields();
            },
            [/]
            [# th:if="${requestMappingMaps.update}"]
            modifyInfo: function(){
                this.addModifyFlag = false;
                this.panelTitle = "修改";
                var msg = '';
                var flag = false;
                if(this.multipleSelection.length == 0){
                    msg = "没有选中(待输入)行";
                    flag = true;
                }else if( this.multipleSelection.length > 1 ){
                    msg = "只能选中一个(待输入)行";
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
                    [# th:each="map : ${responseList}"]
                    [(${map.name})]: selectData.[(${map.name})],
                    [/]
                }
            },
            [/]
            [# th:if="${requestMappingMaps.delete}"]
            deleteInfo: function(){
                var self = this;
                if(this.multipleSelection.length == 0){
                    this.$message({
                        message: '没有选中(待输入)行',
                        type: 'warning'
                    });
                    return false;
                }
                this.$confirm('此操作将永久删除该条记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var parameterIds = [];
                    self.multipleSelection.map( function(item){
                        parameterIds.push(item.id);
                    });
                    var params = JSON.stringify(parameterIds);
                    delete[(${originClassName})](params).then(function(res){
                        self.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        vm.getTableData(self,[(${className})]Url);
                    });
                }).catch(function() {
                    self.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            [/]
            [# th:if="${requestMappingMaps.save} or ${requestMappingMaps.update}"]
            submitForm: function(formName) {
                var self = this;
                this.$refs[formName].validate(function(valid){
                    if (valid) {
                        [# th:if="${requestMappingMaps.save} and !${requestMappingMaps.update}"]
                        var params = self.ruleForm;
                        add[(${originClassName})](params).then(function(res){
                            self.$message({type: 'success', message: '添加成功!'});
                            self.showHide = true;
                            vm.getTableData(self,[(${className})]Url);
                        });
                        [/]
                        [# th:if="!${requestMappingMaps.save} and ${requestMappingMaps.update}"]
                        var params = Object.assign(self.ruleForm,{id: self.multipleSelection[0].id});
                        update[(${originClassName})](params).then(function (res) {
                            self.$message({type: 'success', message: '修改成功!'});
                            self.showHide = true;
                            vm.getTableData(self,[(${className})]Url);
                        });
                        [/]
                        [# th:if="${requestMappingMaps.save} and ${requestMappingMaps.update}"]
                        if(self.addModifyFlag){ // 添加数据
                            var params = self.ruleForm;
                            add[(${originClassName})](params).then(function(res){
                                self.$message({type: 'success', message: '添加成功!'});
                                self.showHide = true;
                                vm.getTableData(self,[(${className})]Url);
                            });
                        }else {
                            var params = Object.assign(self.ruleForm,{id: self.multipleSelection[0].id});
                            update[(${originClassName})](params).then(function (res) {
                                self.$message({type: 'success', message: '修改成功!'});
                                self.showHide = true;
                                vm.getTableData(self,[(${className})]Url);
                            });
                        }
                        [/]
                    } else {
                        return false;
                    }
                });
            },
            getBack: function(){
                this.showHide = true;
            },
            [/]
            onSubmit:function () {
                [# th:if="!${#lists.isEmpty(requestList)}"]
                this.searchLoading  = true;
                this.tableLoading = true;
                [/]
                vm.getTableData(this,[(${className})]Url);
            },
            [# th:if="${requestMappingMaps.exportExcel}"]
            exportExcel: function () {
                vm.exportExcel(this,[(${className})]ExportUrl);
            },
            [/]
            [# th:if="${requestMappingMaps.save} or ${requestMappingMaps.update} or ${requestMappingMaps.delete}"]
            handleSelectionChange: function(val){
                this.multipleSelection = val;
            },
            [/]
            handleSizeChange: function(pageSize) {
                this.pageSize = pageSize;
                this.onSubmit();
            },
            handleCurrentChange: function(currentPage) {
                this.currentPage = currentPage;
                this.onSubmit();
            }
        },
        //初始获取表格数据，在加载该js时触发该方法，获取表格数据
        mounted:function () {
            vm.getTableData(this,[(${className})]Url);
        }
    })
})();
   