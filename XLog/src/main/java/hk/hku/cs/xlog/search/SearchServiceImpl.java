package hk.hku.cs.xlog.search;

import hk.hku.cs.xlog.entity.Friend;
import hk.hku.cs.xlog.entity.Message;
import hk.hku.cs.xlog.entity.Status;
import hk.hku.cs.xlog.solr.PaginationSupport;
import hk.hku.cs.xlog.solr.SolrClient;
import hk.hku.cs.xlog.solr.SolrServerFactory;
import java.util.List;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Override
	public void createIndex(String serverName) {
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer(serverName);
		ModifiableSolrParams params = new ModifiableSolrParams();
		params.set("qt", "/dataimport");
		params.set("command", "full-import");
		params.set("commit", "true");
		try {
			solrServer.query(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateIndex(String servername) {
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer(servername);
		ModifiableSolrParams params = new ModifiableSolrParams();
		params.set("qt", "/dataimport");
		params.set("command", "delta-import");
		params.set("commit", "true");

		try {
			solrServer.query(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createFriendIndex() {

		createIndex("friend");
	}

	@Override
	public void updateFriendIndex() {
		updateIndex("friend");
	}

	@Override
	public void createStatusIndex() {
		createIndex("status");

	}

	@Override
	public void updateStatusIndex() {
		updateIndex("status");

	}

	@Override
	public void createMessageIndex() {
		createIndex("message");
	}

	@Override
	public void updateMessageIndex() {
		createIndex("message");
	}

	@Override
	public List<Friend> searchFriends(String query) {
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer("friend");
		PaginationSupport<Friend> friends = SolrClient.query(query, Friend.class, 0, 10, solrServer);
		return friends.getItems();
	}

	@Override
	public List<Status> searchStatus(String query) {
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer("status");
		PaginationSupport<Status> status = SolrClient.query(query, Status.class, 0, 10, solrServer);
		return status.getItems();
	}

	@Override
	public List<Message> searchMessages(String query) {
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer("message");
		PaginationSupport<Message> messageList = SolrClient.query(query, Message.class, 0, 10, solrServer);
		return messageList.getItems();
	}

	@Override
	public PaginationSupport<Friend> searchFriends(String userName, String query, int start, int rows) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.add("q", "screenName:" + query);
		solrParams.add("fq", "refUser:" + userName);
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer("friend");
		return SolrClient.query(solrParams, Friend.class, start, rows, solrServer);
	}

	@Override
	public PaginationSupport<Status> searchStatus(String userName, String query, int start, int rows, String startTime, String endTime) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.add("q", "content:" + query);
		solrParams.add("fq", "deleted:0");
		solrParams.add("fq", "refUser:" + userName);
		if (!startTime.equals("none"))
			solrParams.add("fq", "createdTime:[" + startTime + " TO " + endTime + "]");
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer("status");
		return SolrClient.query(solrParams, Status.class, start, rows, solrServer);
	}

	@Override
	public PaginationSupport<Message> searchMessages(String userName, String query, int start, int rows, String startTime, String endTime) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.add("q", "content:" + query);
		solrParams.add("fq", "refUser:" + userName);
		if (!startTime.equals("none"))
			solrParams.add("fq", "createdDate:[" + startTime + " TO " + endTime + "]");
		SolrServer solrServer = SolrServerFactory.getEmbeddedSolrServer("message");
		return SolrClient.query(solrParams, Message.class, start, rows, solrServer);
	}

}
