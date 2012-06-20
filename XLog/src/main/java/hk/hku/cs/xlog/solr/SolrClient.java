package hk.hku.cs.xlog.solr;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.SolrParams;

public class SolrClient {


	public static <T> PaginationSupport<T> query(String keyword, Class<T> cls, int start, int rows, SolrServer server) {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setStart(start);
		query.setRows(rows);

		QueryResponse response = null;
		try {
			response = server.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
			return null;
		}
		SolrDocumentList sdl = response.getResults();
		int totalCount = Long.valueOf(response.getResults().getNumFound()).intValue();
		return new PaginationSupport<T>(EntityConvert.solrDocument2Entity(sdl, cls), totalCount, start, rows);
	}

	
	public static <T> PaginationSupport<T> query(SolrParams params, Class<T> cls, int start, int rows, SolrServer server) {
		QueryResponse response = null;
		try {
			response = server.query(params);
		} catch (SolrServerException e) {
			e.printStackTrace();
			return null;
		}
		SolrDocumentList sdl = response.getResults();
		int totalCount = Long.valueOf(response.getResults().getNumFound()).intValue();
		return new PaginationSupport<T>(EntityConvert.solrDocument2Entity(sdl, cls), totalCount, start, rows);
	}

	
	public static void commitObject(Object obj, SolrServer server) {
		try {
			server.add(EntityConvert.entity2SolrInputDocument(obj));
			server.commit(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static <T> void commitList(List<T> objectList, SolrServer server) {
		try {
			server.add(EntityConvert.entityList2SolrInputDocument(objectList));
			server.commit(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void update(Map<Object, Object> objMap, String idName, SolrServer server) {
		if (objMap != null && objMap.size() > 0 && StringUtils.isNotBlank(idName)) {
			SolrQuery query = new SolrQuery();
			Set<Object> objSet = objMap.keySet();
			int i = 0;
			StringBuffer q = new StringBuffer();
			for (Object o : objSet) {
				if (i == 0) {
					q.append(idName + ":" + o.toString());
					i++;
				} else {
					q.append(" OR " + idName + ":" + o.toString());
				}
			}
			query.setQuery(q.toString());
			query.setStart(0);
			query.setRows(objMap.size());
			QueryResponse qr = null;
			try {
				qr = server.query(query);
				SolrDocumentList sdl = qr.getResults();
				if (sdl.size() > 0) {
					UpdateRequest updateRequest = new UpdateRequest();
					updateRequest.setAction(AbstractUpdateRequest.ACTION.COMMIT, false, false);
					updateRequest.add(EntityConvert.solrDocumentList2SolrInputDocumentList(sdl, idName, objMap));
					updateRequest.process(server);

				}
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void update(Object obj, String idName, SolrServer server) {
		if (obj != null && StringUtils.isNotBlank(idName)) {
			Class<?> cls = obj.getClass();
			try {
				Method method = cls.getMethod(EntityConvert.getMethodName(idName, "get"));
				Object o = method.invoke(obj);

				if (o != null) {// 主键不能为空
					SolrQuery query = new SolrQuery();
					query.setQuery(idName + ":" + o.toString());
					query.setStart(0);
					query.setRows(1);
					QueryResponse qr = server.query(query);
					SolrDocument sd = qr.getResults().get(0);

					UpdateRequest updateRequest = new UpdateRequest();
					updateRequest.setAction(AbstractUpdateRequest.ACTION.COMMIT, false, false);
					updateRequest.add(EntityConvert.solrDocument2SolrInputDocument(sd, obj));
					updateRequest.process(server);

				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void deleteByExample(Object obj, String idName, SolrServer server) {
		Class<?> cls = obj.getClass();
		try {
			Method method = cls.getMethod(EntityConvert.getMethodName(idName, "get"));
			Object o = method.invoke(obj);
			if (o != null) {
				deleteById(method.invoke(obj), idName, server);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	
	public static void deleteById(Object id, String idName, SolrServer server) {
		try {
			server.deleteById(idName + ":" + id.toString());
			server.commit(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void deleteById(Object[] idArrays, String idName, SolrServer server) {
		if (idArrays.length > 0) {
			try {
				StringBuffer query = new StringBuffer(idName + ":" + idArrays[0]);
				for (int i = 1; i < idArrays.length; i++) {
					if (idArrays[i] != null) {
						query.append(" OR " + idName + ":" + idArrays[i].toString());
					}
				}
				server.deleteByQuery(query.toString());
				server.commit(false, false);
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteAll(SolrServer server) {
		try {
			server.deleteByQuery("*:*");
			server.commit(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String ping(SolrServer server) {
		try {
			return server.ping().getResponse().toString();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static void optimize(SolrServer server) {
		try {
			server.optimize(true, false);
			server.optimize(false, false);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}