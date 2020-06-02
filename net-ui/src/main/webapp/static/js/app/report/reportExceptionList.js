//进行后台链接的统一定义
var ReportExceptionOrderListUrl = '/report/manager/reportExceptionOrder/list';
var ReportExportExceptionOrderUrl = '/report/manager/reportExceptionOrder/exportExcel';
var ReportPreCreditListVm;
(function () {
    ReportPreCreditListVm = new Vue({
        el: '#reportExceptionListApp',
        data: function () {
            return {
                // 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
                formObj: {
                    "corporateIdNumber":"",
                    "contactName":"",
                    "taxNum":"",
                    "thirdCode": null,
                    "registerTimeStart": null,
                    "registerTimeEnd": null
                },
                searchLoading: false,
                tableLoading: false,
                currentPage: 1,
                allThirdList: [],
                pageSize: 10,
                total: 0,
                tableData: [],
                dialogFormVisible: false,
                formLabelWidth: '120px'
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
                this.searchLoading = true;
                this.tableLoading = true;
                vm.getTableData(this, ReportExceptionOrderListUrl);
            },
            // 点击导出触发事件
            exportExcel: function () {
                vm.exportExcel(this, ReportExportExceptionOrderUrl)
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
            vm.getTableData(this, ReportExceptionOrderListUrl);
        }
    })
})();
