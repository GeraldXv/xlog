package hk.hku.cs.xlog.solr;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.core.CoreContainer;

public class SolrServerFactory {

	private static Map<String, SolrServer> solrServerMap = Collections.synchronizedMap(new HashMap<String, SolrServer>());
	private static Map<String, EmbeddedSolrServerContainer> esscMap = new HashMap<String, EmbeddedSolrServerContainer>();

	private SolrServerFactory() {
	}

	public static SolrServer getEmbeddedSolrServer(String solrName) {
		ResourceBundle solrProperties = ResourceBundle.getBundle("solrclient");
		return getEmbeddedSolrServer(solrProperties.getString("solrServerHome"), solrName);
	}

	public static SolrServer getEmbeddedSolrServer(String solrServerHome, String solrName) {
		if (!esscMap.containsKey(solrServerHome)) {
			EmbeddedSolrServerContainer essc = new EmbeddedSolrServerContainer(solrServerHome);
			if (essc != null) {
				esscMap.put(solrServerHome, essc);
				CoreContainer cc = essc.getContainer();
				Collection<String> solrCoreNameCollection = cc.getCoreNames();
				for (String scn : solrCoreNameCollection) {
					solrServerMap.put(scn, essc.getSolrServer(scn));
				}
			}
		}
		SolrServer solrServer = null;
		if (!solrServerMap.containsKey(solrName)) {
			solrServer = esscMap.get(solrServerHome).getSolrServer(solrName);
			if (solrServer != null) {
				solrServerMap.put(solrName, solrServer);
			}
		}
		return solrServerMap.get(solrName);
	}

}
