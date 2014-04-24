package io.zhpooer.bean;

import io.zhpooer.domain.Customer;

import java.util.List;

public class Page {
    private int pageSize = 10; // 每页显示的记录条数
    private List<Customer> recorder; // 每页显示的记录          DAO
    private int pageNum;  // 当前页码              用户传
    private int totalPage;  // 总共页码            计算
    private int startIndex; // 每页开始记录的索引    计算
    private int totalRecords; // 总记录的条数        Dao

    public Page(int pageNum, int totalRecords){
        this.pageNum = pageNum;
        this.totalRecords = totalRecords;
        this.totalPage = (totalRecords%pageSize)==0?(totalRecords/pageSize):totalRecords/pageSize+1;
        startIndex = (pageNum-1)*pageSize;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Customer> getRecorder() {
		return recorder;
	}

	public void setRecorder(List<Customer> recorder) {
		this.recorder = recorder;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
    
}
