package la.ipo.pagination;

public class Page implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int pageSize = 15; // 每页默认10条数据
	protected int currentPage = 1; // 当前页
	protected int totalPages = 0; // 总页数
	protected int totalRows = 0; // 总数据数
	protected int pageStartRow = 0; // 每页的起始行数
	protected int pageEndRow = 0; // 每页显示数据的终止行数
	protected boolean pagination = false; // 是否分页
	boolean hasNextPage = false; // 是否有下一页
	boolean hasPreviousPage = false; // 是否有前一页
	protected String pagedView; // 用于页面显示

	Object obj; // 参数对象与返回对象

	public Page(int rows, int pageSize) {
		this.init(rows, pageSize);
	}

	public Page() {

	}

	/**
	 * 初始化分页参数:需要先设置totalRows
	 * 
	 */

	public void init(int rows, int pageSize) {

		this.pageSize = pageSize;

		this.totalRows = rows;

		if ((totalRows % pageSize) == 0) {
			totalPages = totalRows / pageSize;
		} else {
			totalPages = totalRows / pageSize + 1;
		}

	}

	public void init(int rows, int pageSize, int currentPage) {

		this.pageSize = pageSize;

		this.totalRows = rows;

		if ((totalRows % pageSize) == 0) {
			totalPages = totalRows / pageSize;
		} else {
			totalPages = totalRows / pageSize + 1;
		}
		if (currentPage != 0)
			gotoPage(currentPage);
		setPagedView();
	}

	/**
	 * 计算当前页的取值范围：pageStartRow和pageEndRow
	 * 
	 */
	private void calculatePage() {
		if ((currentPage - 1) > 0) {
			hasPreviousPage = true;
		} else {
			hasPreviousPage = false;
		}

		if (currentPage >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}

		if (currentPage * pageSize < totalRows) { // 判断是否为最后一页
			pageEndRow = currentPage * pageSize;
			pageStartRow = pageEndRow - pageSize;
		} else {
			pageEndRow = totalRows;
			pageStartRow = pageSize * (totalPages - 1);
		}

	}

	/**
	 * 直接跳转到指定页数的页面
	 * 
	 * @param page
	 */
	public void gotoPage(int page) {
		currentPage = page;
		calculatePage();
	}

	public void setPagedView(String path) {

		StringBuffer sb = new StringBuffer();
		sb.append("<TABLE width='100%'  class='content9'>");
		sb.append("<TBODY>");
		sb.append("<TR>");
		sb.append("<TD align=left width='50%'>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");

		if (hasPreviousPage) {
			sb.append("<a href='" + path + "page=1'><IMG title='第一页' src='../../images/0.gif' border=0></a>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href='" + path + "page=" + (currentPage - 1) + "'><IMG title='上一页' src='../../images/1.gif' border=0></a>");
		} else {
			sb.append("<IMG title='第一页' src='../../images/0.gif' border=0>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<IMG title='上一页' src='../../images/1.gif' border=0>");
		}
		sb.append("&nbsp;&nbsp;");

		if (hasNextPage) {
			sb.append("<a href='" + path + "page=" + (currentPage + 1) + "'><IMG title='下一页' src='../../images/2.gif' border=0></a>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href='" + path + "page=" + totalPages + "'><IMG title='最末页' src='../../images/3.gif' border=0></a>");
		} else {
			sb.append("<IMG title='下一页' src='../../images/2.gif' border=0>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<IMG title='最末页' src='../../images/3.gif' border=0>");
		}
		sb.append("</TD>");
		sb.append("<TD align=right width='50%'>");
		sb.append("&nbsp;每页<INPUT type=text size=5 name=pageSize value=" + pageSize + " class='form' style='width:30px;'>");
		sb.append("总计: " + totalRows + ", 共" + totalPages + "页, 第" + currentPage + "页, 转到 ");
		sb.append("<INPUT type=text size=5 name=page value=" + currentPage + ">");
		sb.append("&nbsp;");
		sb.append("<INPUT onclick='submit_pagedForm()' type=button class='button2' value=' GO '>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		sb.append("<INPUT name=totalPages type=hidden value='" + totalPages + "'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("<INPUT name=totalRows type=hidden value='" + totalRows + "'>");
		sb.append("&nbsp;&nbsp;");

		sb.append("</TD>");
		sb.append("</TR>");
		sb.append("</TBODY>");
		sb.append("</TABLE>");

		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function submit_pagedForm(){\n");
		sb.append(" var page = document.forms[0].page.value;\n");
		sb.append("var reg=eval('/^[0-9]+$/');\n");
		sb.append("var flag = reg.test(page);\n");
		sb.append("if(!flag){\n" + "alert('跳转页必须是数字');\n" + "return false;}\n");
		sb.append("var pageSize = document.forms[0].pageSize.value;\n");
		sb.append("var reg=eval('/^[0-9]+$/');\n");
		sb.append("var flag = reg.test(pageSize);\n");
		sb.append("if(!flag){alert('每页显示数必须是数字');\n" + "return false;}\n");
		sb.append("var totalPages = 0;\n");
		sb.append("totalPages = document.forms[0].totalPages.value;\n");
		sb.append("if (parseInt(page) > parseInt(totalPages) || parseInt(page) <1) {page =1;}\n");
		sb.append("document.forms[0].submit();\n");
		sb.append("}\n");
		sb.append("function search_pagedForm(page) {\n");
		sb.append("document.pagedForm.page.value = page;\n");
		sb.append("document.pagedForm.target='_self';\n");
		sb.append("document.pagedForm.submit();\n");
		sb.append("}\n");
		sb.append("</script>\n");
		pagedView = sb.toString();

	}

	public void setPagedView() {

		StringBuffer sb = new StringBuffer();
		sb.append("<TABLE width='100%' class='content9' cellpadding='0' cellspacing='0'>");
		sb.append("<TBODY>");
		sb.append("<TR>");
		sb.append("<TD align=left width='40%'>");
		sb.append("&nbsp;&nbsp;");
		if (hasPreviousPage) {
			sb.append("<input type='button' value='首页' class='button' onclick='search_pagedForm(1);return false;'>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<input type='button' value='上一页' class='button' onclick='search_pagedForm(" + (currentPage - 1) + ");return false;'>");
		} else {
			sb.append("<input type='button' value='首页' class='button' onclick='search_pagedForm(1);return false;' disabled='disabled'>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<input type='button' value='上一页' class='button' onclick='search_pagedForm(" + (currentPage - 1) + ");return false;' disabled='disabled'>");
		}
		sb.append("&nbsp;&nbsp;");
		if (hasNextPage) {
			sb.append("<input type='button' value='下一页' class='button' onclick='search_pagedForm(" + (currentPage + 1) + ");return false;'>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<input type='button' value='末页' class='button' onclick='search_pagedForm(" + totalPages + ");return false;'>");
		} else {
			sb.append("<input type='button' value='下一页' class='button' onclick='search_pagedForm(" + (currentPage + 1) + ");return false;' disabled='disabled'>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<input type='button' value='末页' class='button' onclick='search_pagedForm(" + totalPages + ");return false;' disabled='disabled'>");
		}
		sb.append("</TD>");
		sb.append("<TD align=right width='60%'>");

		sb.append("总计: " + totalRows + ", 共" + totalPages + "页, 第" + currentPage + "页, 转到 ");
		sb.append("<INPUT type=text size=5 name=page value=" + currentPage + " class='form'  style='width:30px;'>");
		sb.append("&nbsp;");
		sb.append("&nbsp;每页<INPUT type=text size=5 name=pageSize value=" + pageSize + " class='form' style='width:30px;'>");
		sb.append("<INPUT name=totalPages type=hidden value='" + totalPages + "'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("<INPUT name=totalRows type=hidden value='" + totalRows + "'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("<INPUT onclick='submit_pagedForm()' type=button value='GO' class='button2'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("</TD>");
		sb.append("</TR>");
		sb.append("</TBODY>");
		sb.append("</TABLE>");

		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function submit_pagedForm(){\n");
		sb.append(" var page = document.forms[0].page.value;\n");
		sb.append("var reg=eval('/^[0-9]+$/');\n");
		sb.append("var flag = reg.test(page);\n");
		sb.append("if(!flag){\n" + "alert('跳转页必须是数字');\n" + "return false;}\n");
		sb.append("var pageSize = document.forms[0].pageSize.value;\n");
		sb.append("var reg=eval('/^[0-9]+$/');\n");
		sb.append("var flag = reg.test(pageSize);\n");
		sb.append("if(!flag){alert('每页显示数必须是数字');\n" + "return false;}\n");
		sb.append("var totalPages = 0;\n");
		sb.append("totalPages = document.forms[0].totalPages.value;\n");
		sb.append("if (parseInt(page) > parseInt(totalPages) ) {document.pagedForm.page.value =totalPages;}\n");
		sb.append("if(parseInt(page) <1) {document.pagedForm.page.value =1;}\n");
		sb.append("document.forms[0].submit();\n");
		sb.append("}\n");
		sb.append("function search_pagedForm(page) {\n");
		sb.append("document.pagedForm.page.value = page;\n");
		sb.append("document.pagedForm.target='_self';\n");
		sb.append("document.pagedForm.submit();\n");
		sb.append("}\n");
		sb.append("</script>\n");
		pagedView = sb.toString();
	}

	public void setPagedView1() {

		StringBuffer sb = new StringBuffer();

		sb.append("<TABLE width='100%' class='content9'>");
		sb.append("<TBODY>");
		sb.append("<TR>");
		sb.append("<TD align=left width='20%'>");
		sb.append("&nbsp;&nbsp;");
		if (hasPreviousPage) {
			sb.append("<a href='#' onclick='search_pagedForm(1);return false;'><IMG title='第一页' src='../images/0.gif' border=0></a>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href='#' onclick='search_pagedForm(" + (currentPage - 1) + ");return false;'><IMG title='上一页' src='../images/1.gif' border=0></a>");
		} else {
			sb.append("<IMG title='第一页' src='../images/0.gif' border=0>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<IMG title='上一页' src='../images/1.gif' border=0>");
		}
		sb.append("&nbsp;&nbsp;");
		if (hasNextPage) {
			sb.append("<a href='#' onclick='search_pagedForm(" + (currentPage + 1) + ");return false;'><IMG title='下一页' src='../images/2.gif' border=0></a>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<a href='#' onclick='search_pagedForm(" + totalPages + ");return false;'><IMG title='最末页' src='../images/3.gif' border=0></a>");
		} else {
			sb.append("<IMG title='下一页' src='../images/2.gif' border=0>");
			sb.append("&nbsp;&nbsp;");
			sb.append("<IMG title='最末页' src='../images/3.gif' border=0>");
		}
		sb.append("</TD>");
		sb.append("<TD align=right width='80%'>");
		sb.append("总计: " + totalRows + ", 共" + totalPages + "页, 第" + currentPage + "页, 转到 ");
		sb.append("<INPUT type=text size=5 name=page value=" + currentPage + " class='form'  style='width:30px;'>");
		sb.append("&nbsp;");
		sb.append("&nbsp;每页<INPUT type=text size=5 name=pageSize value=" + pageSize + " class='form' style='width:30px;'>");
		sb.append("<INPUT name=totalPages type=hidden value='" + totalPages + "'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("<INPUT name=totalRows type=hidden value='" + totalRows + "'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("<INPUT onclick='submit_pagedForm()' type=button value=' GO ' class='button2'>");
		sb.append("&nbsp;&nbsp;");
		sb.append("</TD>");
		sb.append("</TR>");
		sb.append("</TBODY>");
		sb.append("</TABLE>");

		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function submit_pagedForm(){\n");
		sb.append(" var page = document.forms[0].page.value;\n");
		sb.append("var reg=eval('/^[0-9]+$/');\n");
		sb.append("var flag = reg.test(page);\n");
		sb.append("if(!flag){\n" + "alert('跳转页必须是数字');\n" + "return false;}\n");
		sb.append("var pageSize = document.forms[0].pageSize.value;\n");
		sb.append("var reg=eval('/^[0-9]+$/');\n");
		sb.append("var flag = reg.test(pageSize);\n");
		sb.append("if(!flag){alert('每页显示数必须是数字');\n" + "return false;}\n");
		sb.append("var totalPages = 0;\n");
		sb.append("totalPages = document.forms[0].totalPages.value;\n");
		sb.append("if (parseInt(page) > parseInt(totalPages) || parseInt(page) <1) {page =1;}\n");
		sb.append("document.forms[0].submit();\n");
		sb.append("}\n");
		sb.append("function search_pagedForm(page) {\n");
		sb.append("document.pagedForm.page.value = page;\n");
		sb.append("document.pagedForm.target='_self';\n");
		sb.append("document.pagedForm.submit();\n");
		sb.append("}\n");
		sb.append("</script>\n");
		pagedView = sb.toString();

	}

	public String getPagedView() {
		return pagedView;
	}

	/**
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * @return
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/**
	 * @return
	 */
	public int getPageEndRow() {
		return pageEndRow;
	}

	/**
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return
	 */
	public int getPageStartRow() {
		return pageStartRow;
	}

	/**
	 * @return
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @return
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param i
	 */
	public void setTotalPages(int i) {
		totalPages = i;
	}

	/**
	 * @param i
	 */
	public void setCurrentPage(int i) {
		currentPage = i;
	}

	/**
	 * @param b
	 */
	public void setHasNextPage(boolean b) {
		hasNextPage = b;
	}

	/**
	 * @param b
	 */
	public void setHasPreviousPage(boolean b) {
		hasPreviousPage = b;
	}

	/**
	 * @param i
	 */
	public void setPageEndRow(int i) {
		pageEndRow = i;
	}

	/**
	 * @param i
	 */
	public void setPageSize(int i) {
		pageSize = i;
	}

	/**
	 * @param i
	 */
	public void setPageStartRow(int i) {
		pageStartRow = i;
	}

	/**
	 * @param i
	 */
	public void setTotalRows(int i) {
		totalRows = i;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

}
