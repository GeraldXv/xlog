package hk.hku.cs.xlog.entity;

import java.util.HashSet;
import java.util.Set;

public class Tag {
	private String tagName;
	private int tagCount;
	private Set<Status> status = new HashSet<Status>();

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	public Set<Status> getStatus() {
		return status;
	}

	public void setStatus(Set<Status> status) {
		this.status = status;
	}

}
