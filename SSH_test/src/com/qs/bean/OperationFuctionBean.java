package com.qs.bean;

public class OperationFuctionBean {
	
	private boolean viewFunction = false;     //查看功能
	private boolean modifyFunction = false;   //修改功能
	private boolean delFunction = false;      //删除功能
	private boolean enableFunction = false;   //启用/禁用功能  停用/复用
	private boolean setTimeFunction = false;  //设置非优惠时间
	private boolean dnsegFunction = false;    //号段维护功能
	private boolean exportAll = false;        //导出全部
	private boolean importInfo = false;        //导入
	private boolean auditFunction  = false;    //审核功能
	
	//0918 +
	private boolean zhengshuguanli =false;//证书页面管理
	private boolean viewzhengshu =false;//后台查看证书
	
	public boolean isDelFunction() {
		return delFunction;
	}
	public void setDelFunction(boolean delFunction) {
		this.delFunction = delFunction;
	}
	
	public boolean isModifyFunction() {
		return modifyFunction;
	}
	public void setModifyFunction(boolean modifyFunction) {
		this.modifyFunction = modifyFunction;
	}
	public boolean isEnableFunction() {
		return enableFunction;
	}
	public void setEnableFunction(boolean enableFunction) {
		this.enableFunction = enableFunction;
	}
	public boolean isSetTimeFunction() {
		return setTimeFunction;
	}
	public void setSetTimeFunction(boolean setTimeFunction) {
		this.setTimeFunction = setTimeFunction;
	}
	public boolean isDnsegFunction() {
		return dnsegFunction;
	}
	public void setDnsegFunction(boolean dnsegFunction) {
		this.dnsegFunction = dnsegFunction;
	}
	
	public boolean isViewFunction() {
		return viewFunction;
	}
	public void setViewFunction(boolean viewFunction) {
		this.viewFunction = viewFunction;
	}
	public boolean isExportAll() {
		return exportAll;
	}
	public void setExportAll(boolean exportAll) {
		this.exportAll = exportAll;
	}
	public boolean isImportInfo() {
		return importInfo;
	}
	public void setImportInfo(boolean importInfo) {
		this.importInfo = importInfo;
	}
	public boolean isAuditFunction() {
		return auditFunction;
	}
	public void setAuditFunction(boolean auditFunction) {
		this.auditFunction = auditFunction;
	}
	public boolean isZhengshuguanli() {
		return zhengshuguanli;
	}
	public void setZhengshuguanli(boolean zhengshuguanli) {
		this.zhengshuguanli = zhengshuguanli;
	}
	public boolean isViewzhengshu() {
		return viewzhengshu;
	}
	public void setViewzhengshu(boolean viewzhengshu) {
		this.viewzhengshu = viewzhengshu;
	}
	
	
	
}
