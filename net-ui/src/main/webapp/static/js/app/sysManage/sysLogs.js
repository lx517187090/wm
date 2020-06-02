var sysLogsVm;
var sysLogExportUrl = '/sys/log/exportExcel';
(function () {
    sysLogsVm = new Vue({
        el: '#sysLogsApp',
        data: function () {
            return {
                formObj: {
                    "username": '',
                    "operateType": '',
                    "startTime": this.starTimeDefault(),
                    "endTime": this.endTimeDefault()
                },
                panelTitle: '',
                showHide: true,
                logData: [],
                allUserList:[],
                total: 1,
                size: 10,
                currentPage: 1,
                dialogVisible: true,
                searchLoading: false,
                tableLoading: false
            }
        },
        methods: {
            paramsDetails: function (json) {
                $("#jsonFormat_Parent").show(200);
                this.dialogVisible = true;
                var input = eval('(' + json + ')');
                $("#jsonFormat_Parent #jsonFormat").jsonViewer(input);
            },
            getStartTime: function (date) {
                this.formObj.startTime = date;
            },
            getEndTime: function (date) {
                this.formObj.endTime = date;
            },
            starTimeDefault: function () {
                debugger;
                var date = new Date();
                var s1 = (new Date(new Date().getTime() - 7 * 24 * 3600 * 1000)).getFullYear()
                    + "-" + ((new Date(new Date().getTime() - 7 * 24 * 3600 * 1000)).getMonth() + 1) + "-" + ((new Date(new Date().getTime() - 7 * 24 * 3600 * 1000)).getDate());
                return s1;
            },
            endTimeDefault: function () {
                var date = new Date();
                var s1 = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate());
                return s1;
            },
            getAllUser:function(){
                var self = this;
                userList().then(function (res) {
                    debugger
                    $.each(res.data, function (i, val) {
                        self.allUserList.push(val);
                    });

                });
            },
            closeDialog: function () {
                $("#jsonFormat_Parent").hide(200);
            },
            getSysLogList: function () {
                debugger;
                var self = this;
                var params = Object.assign(self.formObj);
                params.size = this.size;
                params.current = this.currentPage;
                sysLogList(params).then(function (res) {
                    self.searchLoading = false;
                    self.tableLoading = false;
                    self.logData = res.data.list;
                    self.total = res.data.totalCount;
                    self.currentPage = res.data.currPage;
                    self.size = res.data.pageSize;
                    vm.deleteLoading();
                });
            },
            exportExcel: function () {
                vm.exportExcel(this, sysLogExportUrl)
            },
            searchInfo: function () {
                this.searchLoading = true;
                this.tableLoading = true;
                this.getSysLogList();
            },
            handleSizeChange: function (val) {
                this.size = val;
                this.tableLoading = true;
                this.getSysLogList();
            },
            handleCurrentChange: function (val) {
                this.currentPage = val;
                this.tableLoading = true;
                this.getSysLogList();
            }
        },
        mounted: function () {
            this.getAllUser();
            this.getSysLogList();
        }
    })
})();
