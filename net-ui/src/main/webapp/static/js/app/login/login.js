var vm;
(function(){
     vm = new Vue({
        el: '#loginApp',
        data: function(){
            return {
                loading: false,
                captchaSrc: '',
                loginForm: {
                    username: '',
                    passwd: '',
                    captcha: ''
                },
                loginRules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                    ],
                    passwd: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                    ],
                    captcha: [
                        { required: true, message: '请输入验证码', trigger: 'blur' },
                    ]
                },
            }
        },
        methods: {
            getCaptcha: function(){
                var time = new Date().getTime();
                this.captchaSrc = ctx + '/captcha.jpg' + '?t='+ time;
            },
            restCaptcha: function(){
                this.getCaptcha();
            },
            handleLogin: function() {
                var self = this;
                this.$refs.loginForm.validate(function(valid){
                    if (valid) {
                        self.loading = true;
                        loginByAccount(self.loginForm).then(function(res){
                            self.loading = false;
                            location.href = ctx+"/";
                        }).catch(function(err){
                            self.loading = false;
                        })
                    } else {
                        this.$message.error('登录信息校验不通过！');
                        return false;
                    }
                });
            }
        },
        mounted: function(){
            /*var login = location.href;
            var num = login.indexOf(";JSESSIONID");
            if(num!=-1){
                history.pushState({},"登录",login.substr(0,num));
            }*/
            this.getCaptcha();
        }
    })



})();