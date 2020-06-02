var roleVm;
(function (){
    roleVm = new Vue({
        el: '#roleApp',
        data: function(){
            return {
                roleName:'',
                panelTitle: '',
                showHide: true,
                roleData: [],
                multipleSelection: [],
                searchLoading: false,
                submitLoading: false,
                tableLoading: false,
                total:1,
                size:10,
                currentPage: 1,
                addModifyFlag: true,
                ruleForm: {
                    roleName: "",
                    // deptName: "",
                    remark: "",
                },
                rules: {
                    roleName: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' },
                        { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                    /*deptName: [
                        { required: true, message: '请选择上级部门', trigger: 'change' }
                    ]*/
                },
                roleDetails:'',
                treeVisible: false,
                treeLoading: false,
                zNodes: [],
                zTreeObj: null,
                selectZTreeData:'',
                zNodes02: [],
                zNodes03: [],
                zTreeObj02: null,
                // zTreeObj03: null,
                setting02: {
                    data : {
                        simpleData : {
                            enable : true,
                            rootPId : 0,
                            idKey : "id",
                            pIdKey : "parentId"
                        }
                    },
                    check : {
                        enable : true,
                        chkStyle : "checkbox",
                        chkboxType : {
                            "Y" : "ps",
                            "N" : "ps"
                        }
                    }
                },
                setting03: {
                    data : {
                        simpleData : {
                            enable : true,
                            rootPId : 0,
                            idKey : "id",
                            pIdKey : "parentId"
                        }
                    },
                    check : {
                        enable : true,
                        chkStyle : "checkbox",
                        chkboxType : {
                            "Y" : "ps",
                            "N" : "ps"
                        }
                    }
                }
            }
        },
        methods: {
            getRoleList: function(){
                var params = {
                    roleName: this.roleName,
                    size: this.size,
                    current: this.currentPage
                };
                var self = this;
                roleList(params).then(function(res){
                    /* res.data.list.map(item => {
                         item.createTime = parseTime(item.createTime);
                     });*/
                    self.searchLoading = false;
                    self.tableLoading = false;
                    self.roleData = res.data.list;
                    self.total = res.data.totalCount;
                    self.currentPage = res.data.currPage;
                    self.size = res.data.pageSize;
                })
            },
            /*getDepartList: function(){
                var self = this;
                departList().then(function(res){
                    self.zNodes = res.data;
                    self.zNodes03 = res.data;
                    // self.zTreeObj03 = $.fn.zTree.init($("#zTree03"), self.setting03, self.zNodes03);
                });
            },*/
            getMenuAllList: function(){
                var self = this;
                menuAllList().then(function(res){
                    self.zNodes02 = res.data;
                    self.zTreeObj02 = $.fn.zTree.init($("#zTree02"), self.setting02, self.zNodes02);
                    vm.deleteLoading();
                });
            },
            handleSelectionChange: function(val){
                this.multipleSelection = val;
            },
            handleSizeChange: function(val) {
                this.size = val;
                this.getRoleList();
            },
            handleCurrentChange: function(val) {
                this.currentPage = val;
                this.getRoleList();
            },
            submitForm: function(formName) {
                var self = this;
                this.$refs[formName].validate(function(valid){
                    if (valid) {
                        //self.$set(self,'submitLoading',true);
                        var menuIds= [],ids = [];
                        self.zTreeObj02.getCheckedNodes().map( function(item){
                            menuIds.push(item.id);
                        });
                        if(menuIds.length === 0){
                            self.$message({type: 'warning', message: '请先选择至少一个功能权限!'});
                            return;
                        }
                        /*self.zTreeObj03.getCheckedNodes().map( function(item) {
                            ids.push(item.id);
                        });*/
                        self.submitLoading = true;
                        if(self.addModifyFlag){ // 添加角色
                            var params = Object.assign( self.ruleForm, {
                                // deptId: self.selectZTreeData.id,
                                menuIdList: menuIds,
                                // deptIdList: ids
                            });
                            addRole(params).then(function(res){
                                self.$message({type: 'success', message: '添加成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                self.getRoleList();
                            });
                        }else { // 修改角色
                            var params = Object.assign( self.ruleForm, {
                                id: self.roleDetails.id,
                                menuIdList: menuIds,
                                // deptIdList: ids,
                                // deptId: self.roleDetails.deptId
                            });
                            roleModify(params).then(function(res){
                                self.$message({type: 'success', message: '修改成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                self.getRoleList();
                            });
                        }
                    } else {
                        return false;
                    }
                })
                // self.submitLoading = false;
            },
            searchInfo: function(){
                this.searchLoading = true;
                this.tableLoading = true;
                this.getRoleList();
            },
            addInfo: function(){
                this.addModifyFlag = true;
                this.panelTitle = "新增";
                this.showHide = false;
                this.$refs["ruleForm"].resetFields();
                this.zTreeObj02.checkAllNodes(false);
                // this.zTreeObj03.checkAllNodes(false);
            },
            modifyInfo: function(){
                var self = this;
                this.addModifyFlag = false;
                this.panelTitle = "修改";
                var msg = '';
                var flag = false;
                if(this.multipleSelection.length == 0){
                    msg = "没有选中角色";
                    flag = true;
                }else if( this.multipleSelection.length > 1 ){
                    msg = "只能选中一个角色进行修改";
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
                roleDetails(selectData.id).then(function(res){
                    if(res.data.menuIdList === null || res.data.menuIdList === undefined ){
                        res.data.menuIdList = [];
                    }
                    self.roleDetails = res.data;
                    self.ruleForm = {
                        roleName: self.roleDetails.roleName,
                        // deptName: self.roleDetails.deptName,
                        remark: self.roleDetails.remark
                    };
                    self.roleDetails.menuIdList.map(function(item){
                        var node =  self.zTreeObj02.getNodeByParam("id",item,null);
                        self.zTreeObj02.selectNode(node);
                        self.zTreeObj02.checkNode(node, true, false);
                        self.zTreeObj02.updateNode(node);
                    });

                    /*self.roleDetails.deptIdList.map(function(item){
                        var node =  self.zTreeObj03.getNodeByParam("id",item,null);
                        self.zTreeObj03.selectNode(node);
                        self.zTreeObj03.checkNode(node, true, false);
                        self.zTreeObj03.updateNode(node);
                    });*/
                });
            },

            deleteInfo: function(){
                var self = this;
                if(this.multipleSelection.length == 0){
                    this.$message({
                        message: '没有选中角色',
                        type: 'warning'
                    });
                    return false;
                }

                this.$confirm('此操作将永久删除角色, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var roleIds = [];
                    self.multipleSelection.map( function(item){
                        roleIds.push(item.id);
                    });
                    var params = JSON.stringify(roleIds);
                    deleteRole(params).then(function(res){
                        if(res.data >= 1){
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            self.getRoleList();
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
            getBack: function() {
                this.showHide = true;
            },
            departmentSelect: function(){
                this.treeVisible = true;
                this.$nextTick(function(){
                    this.initTree();
                });
            },
            initTree: function(){
                var zNodes  = this.zNodes;
                var setting = {
                    data : {
                        simpleData : {
                            enable : true,
                            rootPId : 0,
                            idKey : "id",
                            pIdKey : "parentId"
                        }
                    },
                    check : {
                        enable : false,
                        chkStyle : "checkbox",
                        chkboxType : {
                            "Y" : "ps",
                            "N" : "ps"
                        }
                    }
                };
                this.zTreeObj = $.fn.zTree.init($("#zTree"), setting, zNodes);
            },
            treeSubmit: function(){
                var selectNode = this.zTreeObj.getSelectedNodes();
                this.selectZTreeData = selectNode[0];
                // this.ruleForm.deptName = selectNode[0].name;
                this.treeVisible = false;
                if(!this.addModifyFlag){
                    this.roleDetails.deptId = selectNode[0].id;
                }
            }
        },
        mounted: function() {
            this.getRoleList();
            // this.getDepartList();
            this.getMenuAllList();

        }
    })
})();
