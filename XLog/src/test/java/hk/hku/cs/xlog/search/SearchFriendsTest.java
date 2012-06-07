package hk.hku.cs.xlog.search;

import org.junit.Test;

public class SearchFriendsTest {
	SearchServiceImpl search;

	@Test
	public void test() {
		search = new SearchServiceImpl();

		// System.out.println(search.searchFriends("Kim").size());
		search.updateFriendIndex();
		search.updateMessageIndex();
		search.updateStatusIndex();
	}

}
