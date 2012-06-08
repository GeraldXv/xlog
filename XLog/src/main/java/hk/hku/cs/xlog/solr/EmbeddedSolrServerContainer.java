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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * EmbeddedSolrServer容器，主要提供该类型server <br/>
 * 
 */
public class EmbeddedSolrServerContainer {

	private Logger logger = LoggerFactory.getLogger(EmbeddedSolrServer.class);

	/**
	 * 默认SOLR_HOME地址
	 */

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

			logger.info("启动搜索server完成");
		} else {
			logger.error("solrServerHome必须给出，不能为空！");
		}
	}

	public void shutdown() {
		if (container == null)
			throw new SolrServerException("不能关闭搜索server,server容器为空!!!");
		if (container.isPersistent())
			container.persist();
		container.shutdown();

		logger.info("搜索server关闭!");
	}

	public synchronized void persite() {
		if (container == null)
			throw new SolrServerException("持久化错 Server容器不能为空!!!");
		container.persist();

		logger.info("搜索server持久化完成！");
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
