package com.qs.bean;

import javax.servlet.http.HttpServletRequest;

import com.qs.tags.StrUtil;

public class Pagination {
	
	 private int pageSize = 10;      //每页显示多少条记录
	 private int currentPage;       //当前页数
	 private int totalResult;       //总记录数
	 private int firstResult;       //满足条件的第1条记录的位置即当前页开始记录
	 private int totalPageNum;      //总页数
	 private String strUrl;			// 出现[1][2][3][4]所用
	 private int startPage;			// 起始页码
	 private int endPage;			// 结束页码
	 private boolean hasPreviousPage = false;// 上一页是否显示
	 private boolean hasNextPage = false;	 // 下一页是否显示
	 private String action; 		// 请求目的

	 public Pagination() {

	 }
	 
	 public Pagination(int totalRows, String actionUrl,Integer pageSize1) {   //首次使用分页
		 	if(pageSize1!=null)	pageSize=pageSize1;
		 	totalResult = totalRows;// 数据数量
		 	totalPageNum = totalResult / pageSize;// 页数
		 	if (totalResult % pageSize > 0) {
				totalPageNum++;
			}
			currentPage = 1;
			firstResult = 0;
			if (totalPageNum > currentPage) {
				hasNextPage = true;
			}
			startPage = 1;
			// 当数据库中数据小于十页时
			if (totalPageNum < 10) {
				endPage = totalPageNum;
			} else {
				endPage = 10;
			}
			this.getMyUrl(actionUrl,currentPage);
		}
	

	 /*
		 * 方法名:getMyUrl 方法参数:空 方法返回值类型:void 方法功能：生成字符串送往客户端(a标签链接)
		 */

		public void getMyUrl(String url,int currentPage) {
			this.action = url;
			StringBuffer myUrl = new StringBuffer();
			for (int i = startPage; i <= endPage; i++) {
				if(i==currentPage){
					myUrl.append("【" + i
							+ "】");				
				}else{	
					myUrl.append("<a href=" + url + "currentPage=" + i
							+ "&totalPages=" + this.getTotalPageNum() + ">[" + i
							+ "]</a>");
				}
				
			}
			strUrl = myUrl.toString();
		}
		

		/*
		 * 方法名:refresh 方法参数:int 方法返回值类型:void 方法功能：根据参数跳到指定页
		 */
		public void refresh(int _currentPage,Integer pageSize) {
			currentPage = _currentPage;
			this.account(pageSize);
		}

		public static Pagination getPager(HttpServletRequest request, String url,Integer pageSize) {
			Pagination pager = new Pagination();
			pager.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			pager.setTotalPageNum(Integer.parseInt(request.getParameter("totalPages")));
			if (!StrUtil.isNull(request.getParameter("pageroperation"))) {
				if (StrUtil.isEqual(request.getParameter("pageroperation").toString(), "previous")) {
					pager.previous(pageSize);
					pager.getMyUrl(url,(Integer.parseInt(request.getParameter("currentPage"))-1));
				} else if (StrUtil.isEqual(request.getParameter("pageroperation").toString(), "next")) {
					pager.next(pageSize);
					pager.getMyUrl(url,(Integer.parseInt(request.getParameter("currentPage"))+1));
				}
			} else {
				pager.refresh(Integer.parseInt(request.getParameter("currentPage").toString()),pageSize);
				pager.getMyUrl(url,Integer.parseInt(request.getParameter("currentPage")));
			}
			return pager;
		}
		
		/*
		 * 方法名:previous 方法参数:空 方法返回值类型:void 方法功能：向上翻一页
		 */
		public void previous(Integer pageSize) {
			currentPage--;
			this.account(pageSize);

		}

		/*
		 * 方法名:next 方法参数:空 方法返回值类型:void 方法功能：向下翻一页
		 */
		public void next(Integer pageSize) {
			currentPage++;
			this.account(pageSize);
		}
	
		public void account(Integer pageSize1) {
			if(pageSize1!=null)	pageSize=pageSize1;
			if (totalPageNum <= 10) {
				startPage = 1;
				endPage = totalPageNum;
			} else if (totalPageNum > 10 && totalPageNum <= 20) {
				startPage = 1;
				endPage = currentPage + 9 > totalPageNum ? totalPageNum : currentPage + 9;
			} else {
				if (currentPage <= 11) {
					startPage = 1;
					endPage = currentPage + 9;
				}
				if (currentPage > 11) {
					startPage = currentPage - 10;
					endPage = currentPage + 9;
				}
				if (currentPage + 9 >= totalPageNum) {
					startPage = totalPageNum - 19;
					endPage = totalPageNum;
				}
			}
			if (currentPage == 1) {
				hasPreviousPage = false;
			} else {
				hasPreviousPage = true;
			}
			if (currentPage == totalPageNum) {
				hasNextPage = false;
			} else {
				hasNextPage = true;
			}
			firstResult = (currentPage - 1) * pageSize;
		}	

		/**
		 * 用户列表查询
		 * @createDate 2013.5.7
		 * @alterDate 2013.5.7
		 * @param _totalRows 总条数
		 * @param actionUrl 页面跳转路径
		 **/
		public Pagination(int _totalRows, String actionUrl,String js) {
			totalResult = _totalRows;// 数据数量
			totalPageNum = totalResult / pageSize;// 页数
			if (totalResult % pageSize > 0) {
				totalPageNum++;
			}
			currentPage = 1;
			firstResult = 0;
			if (totalPageNum > currentPage) {
				hasNextPage = true;
			}
			startPage = 1;
			// 当数据库中数据小于十页时
			if (totalPageNum < 10) {
				endPage = totalPageNum;
			} else {
				endPage = 10;
			}
			this.getMyJs(actionUrl,currentPage);
		}
		
		/*
		 * 方法名:getMyJs 方法参数:空 方法返回值类型:void 方法功能：生成字符串送往客户端(javaScript方法)
		 */

		public void getMyJs(String url,int currentPage) {
			this.action = url;
			StringBuffer myUrl = new StringBuffer();
			for (int i = startPage; i <= endPage; i++) {
				if(i==currentPage){
					myUrl.append("【" + i
							+ "】");				
				}else{	
					myUrl.append("<a href=\"javascript:void(0);\" onclick=\"getResult('"+ i+"','"+this.getTotalPageNum()+"','');\">["+i+"]</a>");
				}
				
			}
			strUrl = myUrl.toString();
		}
		
		public static Pagination getJsPager(HttpServletRequest request, String url) {
			Pagination pager = new Pagination();
			pager.setCurrentPage(Integer.parseInt(request
					.getParameter("currentPage")));
			pager.setTotalPageNum(Integer
					.parseInt(request.getParameter("totalPages")));
			
			if (request.getParameter("pageroperation")!="") {
				if (StrUtil.isEqual(request.getParameter("pageroperation")
						.toString(), "previous")) {
					pager.previous(null);
					pager.getMyJs(url,(Integer.parseInt(request
							.getParameter("currentPage"))-1));
				} else if (StrUtil.isEqual(request.getParameter("pageroperation")
						.toString(), "next")) {
					pager.next(null);
					pager.getMyJs(url,(Integer.parseInt(request
							.getParameter("currentPage"))+1));
				}
			} else {
				pager.refresh(Integer.parseInt(request.getParameter("currentPage")
						.toString()),null);
				pager.getMyJs(url,Integer.parseInt(request
						.getParameter("currentPage")));
			}
			return pager;
		}
		
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public String getStrUrl() {
		return strUrl;
	}

	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean getHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
