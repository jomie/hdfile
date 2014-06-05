package org.niko.utils;

import java.util.List;

public class Pager {
	//页面大小数组
	protected int[] pageSizeList={10,25,50};
	//页面记录数
	protected int pageSize=25;
	//当前页码
	protected int pageNo=1;
	//记录总数
	protected int rowCount=1;
	//页数
	protected int pageCount=1;
	//起始行数
	protected int startIndex=1;
	
	//结束行数
	protected int endIndex=1;
	
	protected int firstPageNo=1;
	protected int prePageNo=1;
	protected int nextPageNo=1;
	protected int lastPageNo=1;
	
	//结果集
	protected List<?> resultList;
		
	@SuppressWarnings("rawtypes")
	public Pager(int pageNo, int pageSize, int rowCount, List resultList) {
		this.pageSize =pageSize;
		this.pageNo = pageNo;
		this.rowCount = rowCount;
		this.resultList = resultList; 
		
		if(rowCount % pageSize == 0){
			this.pageCount = rowCount/pageSize;
		}else{
			this.pageCount = rowCount/pageSize + 1;
		}
		
		this.startIndex = pageSize*(pageNo - 1);
		this.endIndex = this.startIndex + resultList.size();
		
		this.lastPageNo = pageCount;
		if(pageNo > 1) this.prePageNo = this.pageNo-1;
		if(pageNo == pageCount){
			nextPageNo = this.lastPageNo;
		}else{
			nextPageNo = this.pageNo+1;
		}
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}
