package hk.hku.cs.xlog.solr;

import hk.hku.cs.xlog.exception.SolrServerException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.CoreDescriptor;
import org.apache.solr.core.SolrCore;
import org.apache.solr.core.SolrResourceLoader;
import org.xml.sax.SAXException;

public class EmbeddedSolrServerContainer {

	private static CoreContainer container;

	public EmbeddedSolrServerContainer(String solrServerHome) {

		if (StringUtils.isNotBlank(solrServerHome)) {
			File f = new File(solrServerHome, "solr.xml");
			container = new Initializer().initialize();
			try {
				container.load(solrServerHome, f);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			container.setPersistent(true);

		} else {
		}
	}

	public void shutdown() {
		if (container == null)
			throw new SolrServerException("不能关闭搜索server,server容器为空!!!");
		if (container.isPersistent())
			container.persist();
		container.shutdown();

	}

	public synchronized void persite() {
		if (container == null)
			throw new SolrServerException("持久化错 Server容器不能为空!!!");
		container.persist();

	}

	public synchronized void addCore(String solrName, String instanceDir, boolean isPersist) throws ParserConfigurationException, IOException, SAXException {
		CoreDescriptor p = new CoreDescriptor(container, solrName, instanceDir);
		SolrCore core = container.create(p);
		container.register(solrName, core, false);
		if (isPersist)
			container.persist();
	}

	public SolrServer getSolrServer(String solrName) {
		if (container.getCoreNames().contains(solrName))
			return new EmbeddedSolrServer(container, solrName);
		return null;
	}

	static class Initializer extends CoreContainer.Initializer {
		public Initializer() {
		}

		@Override
		public CoreContainer initialize() {
			return new CoreContainer(new SolrResourceLoader(SolrResourceLoader.locateSolrHome()));
		}
	}

	public CoreContainer getContainer() {
		return container;
	}

}
