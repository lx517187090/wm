//进行后台链接的统一定义
var ReportContractListUrl = '/report/manager/reportContract/list';
var ReportExportContractUrl = '/report/manager/reportContract/exportExcel';
var ReportContractListVm;
(function() {
	ReportContractListVm = new Vue({
		el : '#reportContractListApp',
		data : function() {
			return {
				// 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
				formObj : {
					"firstQuery": true,
					"registerTimeStart" : null ,
					"registerTimeEnd" : null 
				},
				searchLoading : false,
				tableLoading : false,
				minDate:'',
				maxDate:'',
				currentPage : 1,
				pageSize : 10,
				total : 0,
				tableData : [],
				pickerOptions: {
					onPick: function (obj) {
						ReportContractListVm.minDate = new Date(obj.minDate).getTime();
						ReportContractListVm.maxDate = new Date(obj.maxDate).getTime();
						ReportContractListVm.formObj.registerTimeStart = ReportContractListVm.formatDate(new Date(obj.minDate));
						ReportContractListVm.formObj.registerTimeEnd = ReportContractListVm.formatDate(new Date(obj.maxDate));
					},
					disabledDate: function (time) {
						var self = ReportContractListVm;
						if (self.minDate) {
							var day1 = 366 * 24 * 3600 * 1000;
							return time.getTime() > self.minDate + day1 || time.getTime() < self.maxDate + day1 || time.getTime() > Date.now();
						} else {
							return time.getTime() > Date.now();
						}
					}
				}
				//dialogFormVisible : false,
				//formLabelWidth : '120px'
			};
		},
		methods : {
			getStartTime : function(date) {
				this.formObj.registerTimeStart = date;
			},
			getEndTime: function(date){
				this.formObj.registerTimeEnd = date;
            },
			onSubmit : function() {
				this.searchLoading  = true;
                this.tableLoading = true;
				this.formObj.firstQuery = false;
				vm.getTableData(this, ReportContractListUrl);
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
			changeData: function (e) {
				if (isEmptyStr(e)) {
					this.minDate = '';
					this.maxDate = '';
					this.formObj.registerTimeStart = null;
					this.formObj.registerTimeEnd = null;
				}
			},
			// 点击导出触发事件
			exportExcel : function() {
				vm.exportExcel(this, ReportExportContractUrl)
			},
			handleSizeChange : function(pageSize) {
				this.pageSize = pageSize;
				this.onSubmit();
			},
			handleCurrentChange : function(currentPage) {
				this.currentPage = currentPage;
				this.onSubmit();
			}
		},
		// 初始获取表格数据，在加载该js时触发该方法，获取表格数据
		mounted : function() {
			vm.getTableData(this, ReportContractListUrl);
		}
	})
})();
