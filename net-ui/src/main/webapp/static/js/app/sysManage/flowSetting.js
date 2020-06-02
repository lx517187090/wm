(function () {
    new Vue({
        el:'#sysConfigApp',
        data:function () {
            return {
                GuaranteeTypeTemp:[],
                MainAppSwitchData:{
                    id:null,
                    value:null
                },
                CompatibilityAppSwitchData:{
                    id:null,
                    value:null
                },
                GuaranteeTypeData:[],
                form: {
                    MainAppSwitch: null,
                    CompatibilityAppSwitch: null,
                    GuaranteeType: []
                },
                beforeRadio: null
            }
        },
        methods: {
            //切换人工总开关
            MainAppSwitchChange: function(label){
                this.MainAppSwitchData.value = label;
                var self = this;
                this.updateFun(this.MainAppSwitchData);
            },
            //切换不兼容贷款品种审核方式
            CompatibilityAppSwitchChange: function(label){
                if(label != this.beforeRadio){
                    this.CompatibilityAppSwitchData.value = label;
                    var self = this;
                    updateFlowSetting(this.CompatibilityAppSwitchData).then(function(res){
                        if(res.httpCode == 200){
                            self.beforeRadio = label;
                            self.$message({
                                message: res.msg,
                                type: 'success',
                                duration: 3 * 1000
                            });
                        }else{
                            self.$message({
                                message: res.msg,
                                type: 'error',
                                duration: 3 * 1000
                            });
                        }
                    });
                }

            },
            //多选不兼容担保方式
            GuaranteeTypeChange: function(selectedArr){
                var value = null;
                var data = {};
                if(this.form.GuaranteeType.length < this.GuaranteeTypeTemp.length){
                    data.enabled = 0;
                    this.compareAndSubmitData(this.GuaranteeTypeTemp,this.form.GuaranteeType,data);
                }else{
                    data.enabled = 1;
                    this.compareAndSubmitData(this.form.GuaranteeType,this.GuaranteeTypeTemp,data);
                }
                this.GuaranteeTypeTemp = this.form.GuaranteeType;
            },
            compareAndSubmitData: function(data1,data2,data){
                var self = this;
                data1.map(function(item1){
                    var flag = 0;
                    data2.map(function(item2){
                        if(item1 == item2) flag = 1;
                    });
                    if(flag == 0){
                        data.id = self.GuaranteeTypeData[item1];
                        self.updateFun(data);
                        return;
                    }
                });
            },
            updateFun: function(data){
                var self = this;
                updateFlowSetting(data).then(function(res){
                    if(res.httpCode == 200){
                        self.$message({
                            message: res.msg,
                            type: 'success',
                            duration: 3 * 1000
                        });
                    }else{
                        self.$message({
                            message: res.msg,
                            type: 'error',
                            duration: 3 * 1000
                        });
                    }
                });
            },
            getFlowSettingList: function(){
                var self = this;
                flowSettingList().then(function(res){
                    var list = res.data;
                    list.map(function(item){
                        switch (item.key){
                            case "MainAppSwitch":
                                self.form.MainAppSwitch = item.value;
                                self.MainAppSwitchData.id = item.id;
                                break;
                            case "CompatibilityAppSwitch":
                                // console.log(item.value);
                                self.beforeRadio = item.value;
                                self.form.CompatibilityAppSwitch = item.value;
                                self.CompatibilityAppSwitchData.id = item.id;
                                break;
                            case "GuaranteeType":
                                if(item.enabled == 1){
                                    self.form.GuaranteeType.push(item.value);
                                }
                                self.GuaranteeTypeData[item.value] = item.id;
                                break;
                        }
                    });
                    self.GuaranteeTypeTemp = self.form.GuaranteeType;
                    vm.deleteLoading();
                })
            }
        },
        mounted: function() {
            this.getFlowSettingList();
        }
    })
})();