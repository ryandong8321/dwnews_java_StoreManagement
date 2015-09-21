package com.dwnews.storemanagement.dao;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author luyuan
 *
 */
public class Pagination<T> {
	
	//-- 是否自动查询总数 --//
	protected boolean autoCount = true;
	
	public int pageNO = 0;
	public int pageSize = 0;
	public int first = 0;
	
	//-- 返回结果 --//
	protected List<T> rows = new ArrayList<T>();
	protected long total = -1;
	
	
	
	public Pagination() {
		
	}
	
	public Pagination(int pageNo, int pageSize) {
		this.pageNO = pageNo;
		this.pageSize = pageSize;
	}
	
	//-- 分页参数访问函数 --//
	/**
		 * 获得当前页的页号,序号从1开始,默认为1.
		 */
		public int getPageNO() {
			return pageNO;
		}

		/**
		 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
		 */
		public void setPageNO(final int pageNo) {
			this.pageNO = pageNo;

			if (pageNo < 1) {
				this.pageNO = 1;
			}
		}

		/**
		 * 返回Page对象自身的setPageNo函数,可用于连续设置。
		 */
		public Pagination<T> pageNO(final int thePageNo) {
			setPageNO(thePageNo);
			return this;
		}

		/**
		 * 获得每页的记录数量, 默认为-1.
		 */
		public int getPageSize() {
			return pageSize;
		}

		/**
		 * 设置每页的记录数量.
		 */
		public void setPageSize(final int pageSize) {
			this.pageSize = pageSize;
		}

		/**
		 * 返回Page对象自身的setPageSize函数,可用于连续设置。
		 */
		public Pagination<T> pageSize(final int thePageSize) {
			setPageSize(thePageSize);
			return this;
		}

		/**
		 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
		 */
		public int getFirst() {
			return ((pageNO - 1) * pageSize) + 1;
		}
		
		public int getFirst2() {
			if(first == 1){
				return 0;
			}
			return first;
		}
		
		public void setFirst(int first) {
			this.first = first;
		}
		

		/**
		 * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
		 */
		public boolean isAutoCount() {
			return autoCount;
		}

		/**
		 * 设置查询对象时是否自动先执行count查询获取总记录数.
		 */
		public void setAutoCount(final boolean autoCount) {
			this.autoCount = autoCount;
		}

		/**
		 * 返回Page对象自身的setAutoCount函数,可用于连续设置。
		 */
		public Pagination<T> autoCount(final boolean theAutoCount) {
			setAutoCount(theAutoCount);
			return this;
		}

		//-- 访问查询结果函数 --//

		/**
		 * 获得页内的记录列表.
		 */
		public List<T> getRows() {
			return rows;
		}

		/**
		 * 设置页内的记录列表.
		 */
		public void setRows(final List<T> result) {
			this.rows = result;
		}

		/**
		 * 获得总记录数, 默认值为-1.
		 */
		public long getTotal() {
			return total;
		}

		/**
		 * 设置总记录数.
		 */
		public void setTotal(final long totalCount) {
			this.total = totalCount;
		}

		/**
		 * 根据pageSize与totalCount计算总页数, 默认值为-1.
		 */
		public long getTotalPages() {
			if (total < 0) {
				return -1;
			}

			long count = total / pageSize;
			if (total % pageSize > 0) {
				count++;
			}
			return count;
		}

		/**
		 * 是否还有下一页.
		 */
		public boolean isHasNext() {
			return (pageNO + 1 <= getTotalPages());
		}

		/**
		 * 取得下页的页号, 序号从1开始.
		 * 当前页为尾页时仍返回尾页序号.
		 */
		public int getNextPage() {
			if (isHasNext()) {
				return pageNO + 1;
			} else {
				return pageNO;
			}
		}

		/**
		 * 是否还有上一页.
		 */
		public boolean isHasPre() {
			return (pageNO - 1 >= 1);
		}

		/**
		 * 取得上页的页号, 序号从1开始.
		 * 当前页为首页时返回首页序号.
		 */
		public int getPrePage() {
			if (isHasPre()) {
				return pageNO - 1;
			} else {
				return pageNO;
			}
		}
	
	
}
