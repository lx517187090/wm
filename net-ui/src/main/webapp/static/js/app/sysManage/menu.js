
var menuVm;
(function (){
    menuVm = new Vue({
        el: '#menuApp',
        data: function(){
            var isPositiveInteger = function(rule,value,callback){
                if(!( /^[0-9]+$/ .test(value))){
                    callback(new Error('id值为正整数!'));
                }else{
                    callback();
                }
            };
            return {
                addModifyFlag: true,
                panelTitle: '',
                showHide: true,
                menuData: [],
                filterMenuData:[],
                multipleSelection: [],
                total: 30,
                size: 10,
                currentPage: 1,
                menuDetails: null,
                ruleForm: {
                    name: '',
                    id:'',
                    parentName: '',
                    icon: '',
                    type: "0",
                    orderNum: '0',
                    uri: '',
                    perms: ''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入菜单名称', trigger: 'blur' }
                    ],
                    parentName: [
                        { required: true, message: '请选择上级菜单', trigger: 'change' }
                    ],
                    icon: [
                        { required: true, message: '请输入图标类名称(参考fontawesome字体库)', trigger: 'blur' }
                    ],
                    type: [
                        { required: true, message: '请输入类型', trigger: 'change' }
                    ],
                    uri: [
                        { required: true, message: '请输入菜单URL', trigger: 'blur' }
                    ],
                    perms: [
                        { required: true, message: '请输入授权标识', trigger: 'change' }
                    ],
                    orderNum: [
                        { required: true, message: '排序号不能为空', trigger: 'change' }
                    ]
                },
                listLoading: false,
                treeVisible: false,//操作权限树是否显示
                treeLoading : false,
                submitLoading: false,
                zNodes: [],
                zTreeObj: null,
                selectZTreeData: null,
                isActive: false
            }
        },
        methods: {
            filterMenuFun: function(id,i,type){
                var self = this;
                var switchFlag = false;
                this.filterMenuData.map(function(item){
                    if(item.id == id){
                        if(item.switchFlag === undefined){
                            item.switchFlag = false;
                        }else{
                            switchFlag = item.switchFlag;
                        }
                        return false;
                    }
                });
                if(!switchFlag){
                    this.isActive = true;
                    var arr =[],left_arr = [],right_arr=[];
                    this.menuData.map(function(item){
                        if (item.parentId == id) {
                            arr.push(item);
                        }
                    });
                    left_arr = this.filterMenuData.slice(0,i+1);
                    right_arr = this.filterMenuData.slice(i+1,this.filterMenuData.length+1);
                    this.filterMenuData = left_arr.concat(arr, right_arr);
                    this.filterMenuData.map(function(item){
                        if(item.id == id){
                            item.switchFlag = true;
                        }
                    });
                } else {
                    if(type == 0){
                        self.filterMenuData = [];
                        this.menuData.map(function(item){
                            if(item.type == 0){
                                item.switchFlag = false;
                                self.isActive = false;
                                self.filterMenuData.push(item);
                            }
                        });
                    }else{
                        this.filterMenuData.map(function(item){
                            if(item.id == id){
                                item.switchFlag = false;
                                self.isActive = false;
                                return false;
                            }
                        });
                        var arr =[];
                        this.menuData.map(function(item){
                            if (item.parentId == id) {
                                arr.push(item);
                            }
                        });

                        arr.map(function(item){
                            self.filterMenuData.remove(item);
                        })
                    }

                }

            },
            getMenuList: function(){
                var self = this;
                menuAllList().then(function(res){
                    self.menuData = res.data;
                    self.filterMenuData = [];
                    res.data.map(function(item){
                        if(item.type == 0){
                            self.filterMenuData.push(item);
                        }
                    });
                    vm.deleteLoading();
                });
            },
            getMenuSelectList: function(){
                var self = this;
                menuSelectList().then( function(res){
                    self.zNodes = res.data;
                });
            },
            handleSelectionChange: function(val){
                this.multipleSelection = val;
            },
            submitForm: function(formName){
                var self = this;
                this.$refs[formName].validate(function(valid){
                    if (valid) {
                        var checkFlag = false;
                        debugger;
                        if(self.selectZTreeData){
                            if( self.ruleForm.type == 0){ // 创建目录校验
                                if(self.selectZTreeData.type == 0){
                                    checkFlag = true;
                                }else{
                                    self.$message({message: '创建目录上级菜单错误', type: 'warning'});
                                    return false;
                                }
                            }else if(self.ruleForm.type == 1){
                                if(self.selectZTreeData.type == 0){
                                    checkFlag = true;
                                }else{
                                    self.$message({message: '创建菜单上级菜单错误', type: 'warning'});
                                    return false;
                                }
                            }else if(self.ruleForm.type == 2){
                                if(self.selectZTreeData.type == 1){
                                    checkFlag = true;
                                }else{
                                    self.$message({message: '创建按钮上级菜单错误', type: 'warning'});
                                    return false;
                                }
                            }
                        }
                        if(self.addModifyFlag){ // 添加菜单
                            if(checkFlag){
                                var params = Object.assign( self.ruleForm, {
                                    parentId: self.selectZTreeData.id
                                });
                                self.submitLoading = true;
                                addMenu(params).then(function(res){
                                    if(res.data == 1){
                                        self.$message({type: 'success', message: '添加成功!'});
                                        self.submitLoading = false;
                                        self.showHide = true;
                                        self.getMenuList();
                                    }
                                });
                            }
                        }else{ // 修改菜单
                            var params = Object.assign( self.ruleForm, {
                                id: self.menuDetails.id,
                                parentId: self.menuDetails.parentId
                            });
                            self.submitLoading = true;
                            ModifyMenu(params).then(function(res){
                                self.$message({type: 'success', message: '修改成功!'});
                                self.submitLoading = false;
                                self.showHide = true;
                                self.getMenuList();
                            });
                        }
                    } else {
                        return false;
                    }
                });
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
                    msg = "没有选中菜单";
                    flag = true;
                }else if( this.multipleSelection.length > 1 ){
                    msg = "只能选中一个菜单进行修改";
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
                var self = this;
                menuDetails(selectData.id).then(function(res){
                    var selectObj  = null;
                    self.menuDetails = res.data;
                    self.zNodes.map( function(item){
                        if(parseInt(item.id) == parseInt(self.menuDetails.parentId)){
                            self.menuDetails.parentName = item.name;
                            selectObj = item;
                            return false;
                        }
                    });
                    self.ruleForm = {
                        name: self.menuDetails.name,
                        id: self.menuDetails.id.toString(),
                        parentName: self.menuDetails.parentName,
                        icon: self.menuDetails.icon,
                        type: self.menuDetails.type.toString(),
                        orderNum: self.menuDetails.orderNum.toString(),
                        uri: self.menuDetails.uri,
                        perms: self.menuDetails.perms
                    };
                    /* this.zTreeObj.selectNode( selectObj, false, false );*/
                });
            },
            deleteInfo: function(){
                var self = this;
                if(this.multipleSelection.length == 0){
                    this.$message({
                        message: '没有选中菜单',
                        type: 'warning'
                    });
                    return false;
                }else if(this.multipleSelection.length >1){
                    this.$message({
                        message: '不能选中多个菜单',
                        type: 'warning'
                    });
                    return false;
                }
                this.$confirm('此操作将永久删除菜单, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var ids = [];
                    self.multipleSelection.map(function(item){
                        ids.push(item.id);
                    });
                    var params = JSON.stringify(ids);
                    deleteMenu(params).then(function(res){
                        if(res.data >= 1){
                            self.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            self.getMenuList();
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
            getBack: function(){
                this.showHide = true;
            },
            departmentSelect: function(){
                this.treeVisible = true;
                var self = this;
                this.$nextTick(function(){
                    self.initTree();
                });
            },
            initTree: function(){
                var setting = {
                    data : {
                        simpleData : {
                            enable : true,
                            rootPId : 0,
                            idKey : "id",
                            pIdKey : "parentId"
                        },
                        key: {
                            uri: ""
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
                this.zTreeObj = $.fn.zTree.init($("#zTree"), setting, this.zNodes);
            },
            treeSubmit: function(){
                var selectNode = this.zTreeObj.getSelectedNodes();
                this.selectZTreeData = selectNode[0];
                this.ruleForm.parentName = selectNode[0].name;
                this.treeVisible = false;
                if(!this.addModifyFlag){
                    this.menuDetails.parentId = selectNode[0].id;
                }
            }
        },
        filters: {
            statesFormat: function (value) {
                switch (value){
                    case 0: return "目录"; break;
                    case 1: return "菜单"; break;
                    case 2: return "按钮"; break;
                    default: return ""; break;
                }
            },
            statesType: function (value) {
                switch (value){
                    case 0: return "warning"; break;
                    case 1: return "success"; break;
                    case 2: return "primary"; break;
                    default: return ""; break;
                }
            }
        },
        mounted: function () {
            this.getMenuList();
            this.getMenuSelectList();
        }
    })
})();
