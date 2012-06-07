package hk.hku.cs.xlog.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SolrServer Exception
 * 
 */
public class SolrServerException extends RuntimeException {

	private static final long serialVersionUID = -6150092853751278477L;

	Log log = LogFactory.getLog(SolrServerException.class);

	public SolrServerException(String message) {
		super(message);
		log.warn(message);
	}

}
