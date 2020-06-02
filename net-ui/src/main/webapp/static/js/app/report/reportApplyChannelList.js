//进行后台链接的统一定义
var ReportApplyChannelListUrl = '/report/manager/reportApplyChannel/list';
var ReportExportApplyChannelUrl = '/report/manager/reportApplyChannel/exportExcel';
var ReportApplyChannelListVm;
(function() {
	ReportApplyChannelListVm = new Vue({
		el : '#reportApplyChannelListApp',
		data : function() {
			return {
				// 此处对应的是页面查询参数对应字段,这里请统一命名为formObj
				formObj : {
					"registerTimeStart" : null ,
					"registerTimeEnd" : null 
				},
				searchLoading : false,
				tableLoading : false,
				currentPage : 1,
				pageSize : 10,
				total : 0,
				tableData : [],
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
				vm.getTableData(this, ReportApplyChannelListUrl);
			},
			// 点击导出触发事件
			exportExcel : function() {
				vm.exportExcel(this, ReportExportApplyChannelUrl)
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
			vm.getTableData(this, ReportApplyChannelListUrl);
		}
	})
})();
