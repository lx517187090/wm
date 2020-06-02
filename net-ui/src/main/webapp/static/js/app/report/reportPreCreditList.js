0//进行后台链接的统一定义
var ReportPreCreditListUrl = '/report/manager/reportPreCredit/list';
var ReportExportPreCreditUrl = '/report/manager/reportPreCredit/exportExcel';
var ReportPreCreditListVm;
(function () {
    ReportPreCreditListVm = new Vue({
        el: '#reportPreCreditListApp',
        data: function () {
            return {
                // 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {
                    "firstQuery": true,
                    "thirdCode": null,
                    "sourceTyp": null,
                    "registerTimeStart": null,
                    "registerTimeEnd": null
                },
                searchLoading: false,
                minDate:'',
                maxDate:'',
                tableLoading: false,
                currentPage: 1,
                allThirdList: [],
                allSourceList: [],
                pageSize: 10,
                total: 0,
                tableData: [],
                dialogFormVisible: false,
                formLabelWidth: '120px',
                pickerOptions: {
                    onPick: function (obj) {
                        ReportPreCreditListVm.minDate = new Date(obj.minDate).getTime();
                        ReportPreCreditListVm.maxDate = new Date(obj.maxDate).getTime();
                        ReportPreCreditListVm.formObj.registerTimeStart = ReportPreCreditListVm.formatDate(new Date(obj.minDate));
                        ReportPreCreditListVm.formObj.registerTimeEnd = ReportPreCreditListVm.formatDate(new Date(obj.maxDate));
                    },
                    disabledDate: function (time) {
                        var self = ReportPreCreditListVm;
                        if (self.minDate) {
                            var day1 = 366 * 24 * 3600 * 1000;
                            return time.getTime() > self.minDate + day1 || time.getTime() < self.maxDate + day1 || time.getTime() > Date.now();
                        } else {
                            return time.getTime() > Date.now();
                        }
                    }
                }
            };
        },
        methods: {
            getStartTime: function (date) {
                this.formObj.registerTimeStart = date;
            },
            getEndTime: function (date) {
                this.formObj.registerTimeEnd = date;
            },
            onSubmit: function () {
                if (isEmptyStr(this.formObj.registerTimeStart) || isEmptyStr(this.formObj.registerTimeEnd)) {
                    this.$message({
                        message: "查询时间段必填",
                        type: 'warning'
                    });
                    return false;
                }
                this.searchLoading = true;
                this.tableLoading = true;
                this.formObj.firstQuery = false;
                vm.getTableData(this, ReportPreCreditListUrl);
            },
            changeData: function (e) {
                if (isEmptyStr(e)) {
                    this.minDate = '';
                    this.maxDate = '';
                    this.formObj.registerTimeStart = null;
                    this.formObj.registerTimeEnd = null;
                }
            },
            formatDate: function (time) {
                var day = time.getDate();
                var month = time.getMonth() + 1;
                return time.getFullYear() + "-" + (this.formatStr(month)) + "-" + (this.formatStr(day));
            },
            formatStr: function(date) {
                if (date < 10) {
                    return '0' + date;
                }
                return date;
            },
            // 点击导出触发事件
            exportExcel: function () {
                if (isEmptyStr(this.formObj.registerTimeStart) || isEmptyStr(this.formObj.registerTimeEnd)) {
                    this.$message({
                        message: "查询时间段必填",
                        type: 'warning'
                    });
                    return false;
                }
                vm.exportExcel(this, ReportExportPreCreditUrl)
            },
            handleSizeChange: function (pageSize) {
                this.pageSize = pageSize;
                this.onSubmit();
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                this.onSubmit();
            },
            getBankList: function () {
                var prams = {
                    productId: '399'
                };
                var self = this;
                bankList(prams).then(function (res) {
                    $.each(res.data, function (i, val) {
                        $.each(val.bankBranchOrgPageDtoList, function (i, val) {
                            self.allThirdList.push(val);
                        });
                    });
                });
            },
            getSourceList: function () {
                var self = this;
                sourceList().then(function (res) {
                    $.each(res.data, function (i, val) {
                        self.allSourceList.push(val);
                    });
                });
            }
        },
        filters: {
            tenThousand: function(num){
                if(typeof num ==  'number' ){
                    return math.eval(num+'/10000').toFixed(2);
                }
                return num;
            }
        },
        // 初始获取表格数据，在加载该js时触发该方法，获取表格数据
        mounted: function () {
            this.getBankList();
            this.getSourceList();
            vm.getTableData(this, ReportPreCreditListUrl);
        }
    })
})();
