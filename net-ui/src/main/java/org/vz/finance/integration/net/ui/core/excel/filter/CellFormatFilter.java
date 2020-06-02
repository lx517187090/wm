package org.vz.finance.integration.net.ui.core.excel.filter;

public class CellFormatFilter {



    public String filterStatus(Object val) {
        String tmp = (String)val;
        String rtn;
        switch (tmp) {
            case "10":
                rtn = "正常";
                break;
            case "20":
                rtn = "非正常";
                break;
            default:
                rtn = tmp;
        }
        return rtn;
    }

    public String filterIsAccess(Object val) {
        return (Boolean)val ? "通过" : "不通过";
    }

    public String filterTransactionStatus(Object val) {
        String tmp = (String)val;
        String rtn;
        switch (tmp) {
            case "0":
                rtn = "未开始";
                break;
            case "1":
                rtn = "审批中";
                break;
            case "2":
                rtn = "结束";
                break;
            case "3":
                rtn = "挂起";
                break;
            default:
                rtn = tmp;
        }
        return rtn;
    }

    public String operateTypeTransfer(Object operateType) {
        String tmp = (String)operateType;
        String rtn;
        switch (tmp) {
            case "0":
                rtn =  "其他";
                break;
            case "1":
                rtn = "新增";
                break;
            case "2":
                rtn = "更新";
                break;
            case "3":
                rtn = "删除";
                break;
            case"4":
                rtn =  "下载";
                break;
            case"5":
                rtn =  "导出";
                break;
            default:
                rtn = tmp;
        }
        return rtn;
    }

    public Double formatNumber(Object num) {
        Double tmp = (Double)num;
        String result;
        if (tmp > 0) {
            tmp = tmp/10000;
            result = String .format("%.2f",tmp);
        } else {
            result = "0.00";
        }

        return Double.valueOf(result);
    }
}
