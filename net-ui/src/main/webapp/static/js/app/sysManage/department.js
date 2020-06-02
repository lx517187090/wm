var departmentVm;
(function () {
    departmentVm = new Vue({
        el: '#departmentApp',
        data: function () {
            return {
                panelTitle: '',
                showHide: true,
                depData: [],
                filterDepData: [],
                multipleSelection: [],
                treeVisible: false,
                treeLoading: false,
                zNodes: [],
                zTreeObj: null,
                selectZTreeData: '',
                addModifyFlag: false,
                ruleForm: {
                    departmentName: '',
                    pDepartment: '',
                    orderNum: 0
                },
                isActive: false,
                rules: {
                    departmentName: [
                        {required: true, message: '请输入部门名称', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    pDepartment: [
                        {required: true, message: '请选择上级部门', trigger: 'change'}
                    ],
                    orderNum: [
                        {required: true, message: '排序号不能为空', trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
            getListData: function () {
                var self = this;
                departList().then(function (res) {
                    var listArr = res.data;
                    self.depData = self.addIsHasChildState(listArr);
                    self.filterDepData = [];
                    self.depData.map(function (item) {
                        if (item.parentId === '0') {
                            self.filterDepData.push(item);
                        }
                    });
                    self.zNodes = res.data;
                    vm.deleteLoading();
                });
            },
            addIsHasChildState: function (list) {
                for (var i = 0; i < list.length; i++) {
                    if (list[i]['isHasChild'] == undefined) {
                        list[i]['isHasChild'] = false;
                    }
                    for (var j = 0; j < list.length; j++) {
                        if (i != j) {
                            if (list[i].id == list[j].parentId) {
                                list[i]['isHasChild'] = true;
                                break;
                            }
                        }
                    }
                }
                return list;
            },
            filterDepFun: function (id, i) {
                var self = this;
                var switchFlag = false;
                this.filterDepData.map(function (item) {
                    if (item.id == id) {
                        if (item.switchFlag === undefined) {
                            item.switchFlag = false;
                        } else {
                            switchFlag = item.switchFlag;
                        }
                        return false;
                    }
                });
                if (!switchFlag) {
                    this.isActive = true;
                    var arr = [], left_arr = [], right_arr = [];
                    this.depData.map(function (item) {
                        if (item.parentId == id) {
                            arr.push(item);
                        }
                    });
                    left_arr = this.filterDepData.slice(0, i + 1);
                    right_arr = this.filterDepData.slice(i + 1, this.filterDepData.length + 1);
                    this.filterDepData = left_arr.concat(arr, right_arr);
                    this.filterDepData.map(function (item) {
                        if (item.id == id) {
                            item.switchFlag = true;
                        }
                    });
                } else {
                    this.filterDepData.map(function (item) {
                        if (item.id == id) {
                            item.switchFlag = false;
                            self.isActive = false;
                            return false;
                        }
                    });
                    var arr = [];
                    this.depData.map(function (item) {
                        if (item.parentId == id) {
                            arr.push(item);
                        }
                    });

                    arr.map(function (item) {
                        self.filterDepData.remove(item);
                    })

                }
            },
            handleSelectionChange: function (val) {
                this.multipleSelection = val;
            },
            submitForm: function (formName) {
                var self = this;
                this.$refs[formName].validate(function (valid) {
                    if (valid) {
                        if (self.addModifyFlag) { // 添加数据
                            var params = {
                                name: self.ruleForm.departmentName,
                                id: "",
                                parentId: self.selectZTreeData.id,
                                orderNum: self.ruleForm.orderNum
                            };
                            departAdd(params).then(function (res) {
                                self.$message({type: 'success', message: '添加成功!'});
                                self.showHide = true;
                                self.getListData(); // 重新获取数据列表;
                            });
                        } else { // 修改数据
                            var params = {
                                name: self.ruleForm.departmentName,
                                id: self.multipleSelection[0].id,
                                parentId: self.multipleSelection[0].parentId,
                                orderNum: self.ruleForm.orderNum
                            };
                            departUpdate(params).then(function (res) {
                                self.$message({type: 'success', message: '修改成功!'});
                                self.showHide = true;
                                self.getListData(); // 重新获取数据列表;
                            });
                        }
                    } else {
                        return false;
                    }
                });
            },
            addInfo: function () {
                this.panelTitle = "新增";
                this.showHide = false;
                this.addModifyFlag = true;
                this.$refs["ruleForm"].resetFields();
            },
            modifyInfo: function () {
                this.panelTitle = "修改";
                this.addModifyFlag = false;
                var msg = '';
                var flag = false;
                if (this.multipleSelection.length == 0) {
                    msg = "没有选中部门";
                    flag = true;
                } else if (this.multipleSelection.length > 1) {
                    msg = "只能选中一个部门进行修改";
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
                this.ruleForm = {
                    departmentName: selectData.name,
                    pDepartment: selectData.parentName,
                    orderNum: selectData.orderNum.toString()
                };
            },
            deleteInfo: function () {
                if (this.multipleSelection.length == 0) {
                    this.$message({
                        message: '没有选中用户',
                        type: 'warning'
                    });
                    return false;
                }
                var self = this;
                this.$confirm('此操作将永久删除部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    departDelete(self.multipleSelection[0].id).then(function (res) {
                        if (res.data == 1) {
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            self.getListData();
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
            getBack: function () {
                this.showHide = true;
            },
            departmentSelect: function () {
                this.treeVisible = true;
                var self = this;
                this.$nextTick(function () {
                    self.initTree();
                });
            },
            initTree: function () {
                var setting = {
                    data: {
                        simpleData: {
                            enable: true,
                            rootPId: 0,
                            idKey: "id",
                            pIdKey: "parentId"
                        }
                    },
                    check: {
                        enable: false,
                        chkStyle: "checkbox",
                        chkboxType: {
                            "Y": "ps",
                            "N": "ps"
                        }
                    }
                };
                this.zTreeObj = $.fn.zTree.init($("#zTree"), setting, this.zNodes);
            },
            treeSubmit: function () {
                var selectNode = this.zTreeObj.getSelectedNodes();
                this.selectZTreeData = selectNode[0];
                this.ruleForm.pDepartment = selectNode[0].name;
                this.treeVisible = false;
                if (!this.addModifyFlag) {
                    this.multipleSelection[0].parentId = selectNode[0].id;
                }
                console.log(this.selectZTreeData.id);
            }
        },
        mounted: function () {
            this.getListData();
            this.initTree();

        }
    })
})();
