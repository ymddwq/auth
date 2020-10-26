package base.form;

import org.springframework.util.StringUtils;

public class PageForm extends BaseForm {

	private Integer pageSize;
	
	private Integer pageNum;
	
	public Integer getPageSize() {
		if(StringUtils.isEmpty(this.pageSize)) {
			this.pageSize = 5;
		}
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageNum() {
		if(StringUtils.isEmpty(this.pageNum)) {
			this.pageNum = 1;
		}
		return pageNum;
	}
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
}