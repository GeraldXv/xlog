package hk.hku.cs.xlog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gerald.xv at gmail.com
 * @version 创建时间：2012-7-5 下午7:19:30 类说明
 */
public class Pagination<T> {

	private int totalPage;

	private int totalNum;

	private int currentPage;

	private List<T> items = new ArrayList<T>();

	private int MaxPage = 7;

	public Pagination(List<T> allItems, int currentPage) {

		this.totalNum = allItems.size();
		this.totalPage = totalNum / MaxPage + 1;
		this.currentPage = currentPage;
		if(allItems.size()!=0)
		for (int i = (currentPage - 1) * MaxPage; i < allItems.size() && i < currentPage * MaxPage; i++) {
			this.items.add(allItems.get(i));
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
