var menuListUrl = '/sys/menu/listMenu?t='+ new Date().getTime();
var vm;

Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

function restHeight() {
    var windowInnerHeight =  window.innerHeight;
    if( windowInnerHeight > 126) {
        $('#content_table').css({
            'height' : (windowInnerHeight - 127) +'px',
            'overflow-y': 'scroll'
        });
    }
}
restHeight();
$(window).resize(function () {//当浏览器大小变化时
    restHeight();
});

(function (){
    //定义全局vue实例
    vm = new Vue({
        el: '#app',
        data:function(){
            var self = this;
            var validateRepeatPass = function (rule, value, callback) {
                if (value === self.ruleForm.password) {
                    callback(new Error('新密码与原密码相同!'));
                }else {
                    callback();
                }
            }
            var validatePass = function (rule, value, callback) {
                if (value !== self.ruleForm.newPassword) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                updatePasswordVisible: false,
                loading:true,
                activeWidth:180,
                isCollapse: false,
                parentName: '首页',
                childName: null,
                currentRouter: '/preCredit',
                menuArr:[],
                formLabelWidth: '80px',
                ruleForm:{
                    password:'',
                    newPassword:'',
                    confirmNewPassword:''
                },
                rules: {
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 12, message: '密码长度在6到12位之间', trigger: 'blur' }
                    ],
                    newPassword: [
                        { required: true, message: '请输入新密码', trigger: 'blur' },
                        { min: 6, max: 12, message: '密码长度在6到12位之间', trigger: 'blur' },
                        { validator: validateRepeatPass, trigger: 'blur' }
                    ],
                    confirmNewPassword: [
                        { required: true, message: '请输入确认密码', trigger: 'blur' },
                        { min: 6, max: 12, message: '密码长度在6到12位之间', trigger: 'blur' },
                        { validator: validatePass, trigger: 'blur' }
                    ],
                }
            }
        },
        filters:{
            hashLink: function(val){
                return '#' + val;
            }
        },
        methods: {
            /**
             * 公共方法，用于获取后台传递过来的表格数据
             * @param self 这里固定写this即可
             * @param url 需要访问的路径的request url
             * @param data 需要传递的查询参数
             */
            getTableData: function (self,url) {
                //处理输入参数前后的空格
                for(var item in self.formObj){
                    var stringItem = self.formObj[item];
                    if(typeof(stringItem) === 'string'){
                        self.formObj[item] = stringItem == null ? null : stringItem.trim();
                    }
                }
                var data= Object.assign({
                    currentPage: self.currentPage,
                    pageSize: self.pageSize
                },self.formObj);
                var params = {
                    url: url,
                    data: data
                };
                getDataList(params).then(function (res) {
                    self.searchLoading = false; /* 取消掉按钮加载;*/
                    self.tableLoading = false; /*取消table加载动画;*/
                    var resultData = res.data;
                        self.tableData = resultData;
                        self.currentpage = res.current;
                        self.pageSize = res.size;
                        self.total = res.total;
                    //数据查询出后展示内容页面
                    vm.deleteLoading();

                });
            },
            exportExcel: function (self,url) {
                var str = '?';
                var obj = self.formObj;
                for(var item in obj){
                	if(!(obj[item] == "" || obj[item] == null)){
                        str += (item + '=' + obj[item] + '&');
                	}
                }
                location.href = ctx + url + str;
            },
            //点击侧边栏链接对应的方法
            handleSelect: function (key, keyPath) {
            },
            handleCommand: function (command) {
                if(command === "loginOutBtn"){
                    this.loginOutBtn();
                }else if(command === "updatePasswordBtn"){
                    this.updatePasswordBtn();
                }
            },
            loginOutBtn: function(){
                vm.loading = false;
                this.$confirm('确认退出登录吗?', '提示', {
                    type: 'info'
                }).then(function(){
                    console.log("确认退出登录吗");
                    logout().then(function(res){
                          location.href = ctx;
                    }).catch(function(err){

                    })
                }).catch(function(){

                });
            },
            updatePasswordBtn: function () {
                this.updatePasswordVisible = true;
                if(this.$refs["ruleForm"]){
                    this.$refs["ruleForm"].resetFields();
                }
            },
            submitForm: function (formName) {
                var self = this;
                vm.loading = false;
                this.$refs[formName].validate(function(valid){
                    if(valid){
                        updatePassword(self.ruleForm).then(function(res) {
                            self.updatePasswordVisible = false;
                            self.$message({type: 'success', message: '密码修改成功!'});
                        })
                    }
                });


            },
            menuItemClick: function (parentName, childName) {
                this.parentName = parentName;
                this.childName = childName;
            },
            deleteLoading:function () {
                vm.loading = false;
                var $content = $("#content_table");
                var c = $content.find($("div[id$=App]"));
                c.show();
            },
            collapseChange: function() {
                this.isCollapse = !this.isCollapse;
                this.activeWidth = this.isCollapse ? 54 : 180;
                this.marginLeft = this.isCollapse ? 74 : 200;
            }
        },
        created: function () {
            getMenuData();
        }
    })
})();
