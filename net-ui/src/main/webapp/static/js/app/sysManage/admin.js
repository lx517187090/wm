//进行后台链接的统一定义
var adminVm;
(function () {
    adminVm = new Vue({
        el: '#adminApp',
        data: function () {
            var self = this;
            var validateEmail = function (rule, value, callback) {
                var reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
                if (!reg.test(value.trim())) {
                    callback(new Error('邮箱格式不正确!'));
                } else {
                    callback();
                }
            };
            var validatePhoneNum = function (rule, value, callback) {
                if (!(/^1[34578]\d{9}$/.test(value))) {
                    callback(new Error('手机号码不合法!'));
                } else {
                    callback();
                }
            };
            var validateUsername = function (rule, value, callback) {
                if (!self.addModifyFlag) {
                    callback();
                } else {
                    var params = {username: value};
                    validateUserName(params).then(function (res) {
                        if (!res.data) {
                            callback(new Error('该用户名已存在！'));
                        } else {
                            callback();
                        }
                    })
                }
            };
            return {
                addModifyFlag: true,
                userName: '',
                panelTitle: '',
                showHide: true,
                userList: [],
                multipleSelection: [],
                total: 10,
                size: 10,
                currentPage: 1,
                roleList: [],
                userDetails: null,
                ruleForm: {
                    username: '',
                    deptName: '',
                    passwd: '',
                    email: '',
                    mobile: '',
                    roleIdList: [],
                    status: ''
                },
                bankMap: [],
                secondList: [],
                thirdList: [],
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'},
                        {required: true, validator: validateUsername, trigger: 'blur'}
                    ],
                    loginName: [
                        {required: true, message: '姓名不能为空', trigger: 'blur'}
                    ],
                    passwd: [
                        {required: true, message: '请输入密码', trigger: 'change'},
                        {min: 6, max: 12, message: '密码长度在6到12位之间', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '请输入邮箱', trigger: 'change'},
                        {required: true, validator: validateEmail, trigger: 'blur'}
                    ],
                    mobile: [
                        {required: true, validator: validatePhoneNum, trigger: 'blur'},
                        {min: 11, max: 11, message: '手机号的长度为11位', trigger: 'blur'}
                    ],
                    roleIdList: [
                        {type: 'array', required: true, message: '请至少选择一个角色', trigger: 'change'}
                    ],
                    status: [
                        {required: true, message: '状态不能为空', trigger: 'change'}
                    ]
                },
                searchLoading: false,
                tableLoading: false,
                submitLoading: false,
                treeVisible: false,
                treeLoading: false,
                zNodes: [],
                zTreeObj: null,
                selectZTreeData: ''
            };
        },
        methods: {
            getRoleSelectList: function () {
                var self = this;
                roleSelectList().then(function (res) {
                    self.roleList = res.data;
                    vm.deleteLoading();
                })
            },
            getUserList: function () {
                var prams = {
                    username: this.userName.trim(),
                    size: this.size,
                    current: this.currentPage
                };
                var self = this;
                userList(prams).then(function (res) {
                    self.searchLoading = false;
                    self.tableLoading = false;
                    self.userList = res.data.list;
                    self.total = res.data.totalCount;
                    self.currentPage = res.data.currPage;
                    self.size = res.data.pageSize;
                });
            },
            handleSelectionChange: function (val) {
                this.multipleSelection = val;
            },
            selectTrigger: function (val) {
                var self = this;
                if (val === '' || val === undefined) {
                    return;
                }
            },
            handleSizeChange: function (val) {
                this.size = val;
                this.tableLoading = true;
                this.getUserList();
            },
            handleCurrentChange: function (val) {
                this.currentPage = val;
                this.tableLoading = true;
                this.getUserList();
            },
            submitForm: function (formName) {
                var self = this;
                this.$refs[formName].validate(function (valid) {
                    if (valid) {
                        self.submitLoading = true;
                        if (self.addModifyFlag) { // 添加数据
                            var params = Object.assign(self.ruleForm, {deptId: self.selectZTreeData.id});
                            addUser(params).then(function (res) {
                                self.$message({type: 'success', message: '添加成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                self.getUserList();
                            });
                        } else { // 修改数据
                            var params = Object.assign(self.ruleForm, {
                                id: self.userDetails.id,
                                deptId: self.userDetails.deptId
                            });
                            ModifyUser(params).then(function (res) {
                                if (res.data === 1) {
                                    self.$message({type: 'success', message: '修改成功!'});
                                    self.submitLoading = false;
                                    self.showHide = true;
                                    self.getUserList(); // 重新获取数据列表;
                                } else {
                                    self.$message({type: 'error', message: '修改失败!'});
                                }
                            });
                        }
                    } else {
                        return false;
                    }
                });
            },
            searchInfo: function () {
                this.searchLoading = true;
                this.tableLoading = true;
                this.getUserList()
            },
            formatRoleName: function (row) {
                var roleIdList = row.roleIdList;
                roleIdList.join(",");
                return roleIdList.toString()
            },
            addInfo: function () {
                this.addModifyFlag = true;
                this.panelTitle = "新增";
                this.showHide = false;
                this.$refs["ruleForm"].resetFields();
            },
            modifyInfo: function () {
                this.addModifyFlag = false;
                this.panelTitle = "修改";
                this.$refs["ruleForm"].resetFields();
                var msg = '';
                var flag = false;
                if (this.multipleSelection.length == 0) {
                    msg = "没有选中管理员";
                    flag = true;
                } else if (this.multipleSelection.length > 1) {
                    msg = "只能选中一个管理员进行修改";
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
                var self = this;
                userDetails(selectData.id).then(function (res) {
                    if (res.data.roleIdList === null || res.data.roleIdList === undefined) {
                        res.data.roleIdList = [];
                    }
                    self.userDetails = res.data;
                    self.ruleForm = {
                        loginName:self.userDetails.loginName,
                        username: self.userDetails.username,
                        email: self.userDetails.email,
                        mobile: self.userDetails.mobile,
                        roleIdList: self.userDetails.roleIdList,
                        status: self.userDetails.status.toString(),
                    };
                });
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
                this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    var userIds = [];
                    self.multipleSelection.map(function (item) {
                        userIds.push(item.id);
                    });
                    var params = JSON.stringify(userIds);
                    deleteUser(params).then(function (res) {
                        if (res.data >= 1) {
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            self.getUserList();
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
            resetPassword: function () {
                if (this.multipleSelection.length == 0) {
                    this.$message({
                        message: '没有选中用户',
                        type: 'warning'
                    });
                    return false;
                }
                var self = this;
                this.$confirm('确定将选中的用户密码重置吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    var userIds = [];
                    self.multipleSelection.map(function (item) {
                        userIds.push(item.id);
                    });
                    var params = JSON.stringify(userIds);
                    resetPwd(params).then(function (res) {
                        if (res.data >= 1) {
                            self.$message({
                                type: 'success',
                                message: '重置密码成功!重置后的密码为888888'
                            });
                            self.getUserList();
                        } else {
                            self.$message({
                                type: 'error',
                                message: '重置密码失败!'
                            });
                        }
                    });
                }).catch(function () {
                    self.$message({
                        type: 'info',
                        message: '已取消重置密码'
                    });
                });
            },
            getBack: function () {
                this.showHide = true;
            },
            departmentSelect: function () {
                var self = this;
                this.treeVisible = true;
                this.$nextTick(function () {
                    self.initTree();
                });
            },
            initTree: function () {
                var zNodes = this.zNodes;
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
                this.zTreeObj = $.fn.zTree.init($("#zTree"), setting, zNodes);
            },
            treeSubmit: function () {
                var selectNode = this.zTreeObj.getSelectedNodes();
                this.selectZTreeData = selectNode[0];
                this.ruleForm.deptName = selectNode[0].name;
                this.treeVisible = false;
                if (!this.addModifyFlag) {
                    this.userDetails.deptId = selectNode[0].id;
                }
            }
        },
        filters: {
            statesFormat: function (value) {
                switch (value) {
                    case 0:
                        return "禁用";
                        break;
                    case 1:
                        return "正常";
                        break;
                    default:
                        return "";
                        break;
                }
            },
            statesType: function (value) {
                switch (value) {
                    case 0:
                        return "warning";
                        break;
                    case 1:
                        return "success";
                        break;
                    default:
                        return "";
                        break;
                }
            }
        },
        mounted: function () {
            this.getUserList();
            this.getRoleSelectList();
        },
        watch: {
            /*secondCode(newName, oldName) {
                // ...
            }*/
        }
    })
})();
