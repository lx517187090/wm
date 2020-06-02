//进行后台链接的统一定义
var ReportOrgListUrl = '/report/manager/reportOrg/list';
var ReportExportPreCreditUrl = '/report/manager/reportOrgList/exportExcel';
var ReportOrgListVm;
(function () {
    ReportOrgListVm = new Vue({
        el: '#reportOrgListApp',
        data: function () {
            return {
                // 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {
                    "firstQuery": true,
                    "thirdCode": null,
                    "registerTimeStart": null,
                    "registerTimeEnd": null
                },
                minDate: '',
                maxDate: '',
                searchLoading: false,
                tableLoading: false,
                allThirdList: [],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                tableData: [],
                dialogFormVisible: false,
                formLabelWidth: '120px',
                pickerOptions: {
                    onPick: function (obj) {
                        ReportOrgListVm.minDate = new Date(obj.minDate).getTime();
                        ReportOrgListVm.maxDate = new Date(obj.maxDate).getTime();
                        ReportOrgListVm.formObj.registerTimeStart = ReportOrgListVm.formatDate(new Date(obj.minDate));
                        ReportOrgListVm.formObj.registerTimeEnd = ReportOrgListVm.formatDate(new Date(obj.maxDate));
                    },
                    disabledDate: function (time) {
                        var self = ReportOrgListVm;
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
                vm.getTableData(this, ReportOrgListUrl);
            },
            // 点击导出触发事件
            exportExcel: function () {
                vm.exportExcel(this, ReportExportPreCreditUrl)
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
            formatStr: function (date) {
                if (date < 10) {
                    return '0' + date;
                }
                return date;
            },
            handleSizeChange: function (pageSize) {
                this.pageSize = pageSize;
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
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                this.onSubmit();
            }
        },
        filters: {
            tenThousand: function (num) {
                if (typeof num == 'number') {
                    return math.eval(num + '/10000').toFixed(2);
                }
                return num;
            }
        },
        // 初始获取表格数据，在加载该js时触发该方法，获取表格数据
        mounted: function () {
            this.getBankList();
            vm.getTableData(this, ReportOrgListUrl);
        }
    })
})();
