package hk.hku.cs.xlog.solr;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class EntityConvert {

	public static SolrInputDocument entity2SolrInputDocument(Object obj) {
		if (obj != null) {
			Class<?> cls = obj.getClass();
			Field[] filedArrays = cls.getDeclaredFields();
			Method m = null;
			SolrInputDocument sid = new SolrInputDocument();
			for (Field f : filedArrays) {
				try {
					m = cls.getMethod(getMethodName(f.getName(), "get"));
					sid.addField(f.getName(), m.invoke(obj));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			return sid;
		}
		return null;
	}

	public static <T> T solrDocument2Entity(SolrDocument sd, Class<T> cls) {
		if (sd != null) {
			try {
				Object obj = cls.newInstance();
				Method m = null;
				Field f = null;
				Class<?> fieldType = null;
				for (String fieldName : sd.getFieldNames()) {

					f = cls.getDeclaredField(fieldName);
					fieldType = f.getType();
					m = cls.getMethod(getMethodName(fieldName, "set"), fieldType);
					if (fieldType.equals(Integer.TYPE)) {
						fieldType = Integer.class;
					} else if (fieldType.equals(Float.TYPE)) {
						fieldType = Float.class;
					} else if (fieldType.equals(Double.TYPE)) {
						fieldType = Double.class;
					} else if (fieldType.equals(Boolean.TYPE)) {
						fieldType = Boolean.class;
					} else if (fieldType.equals(Short.TYPE)) {
						fieldType = Short.class;
					} else if (fieldType.equals(Long.TYPE)) {
						fieldType = Long.class;
					}
					m.invoke(obj, fieldType.cast(sd.getFieldValue(fieldName)));
				}
				return cls.cast(obj);
			} catch (ClassCastException e) {
				e.printStackTrace();
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
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static <T> Collection<SolrInputDocument> entityList2SolrInputDocument(List<T> entityList) {
		if (entityList != null && entityList.size() > 0) {
			Collection<SolrInputDocument> solrInputDocumentList = new ArrayList<SolrInputDocument>();
			SolrInputDocument sid = null;
			for (Object o : entityList) {
				sid = entity2SolrInputDocument(o);
				if (sid != null) {
					solrInputDocumentList.add(sid);
				}
			}
			return solrInputDocumentList;
		}
		return null;
	}

	public static <T> List<T> solrDocument2Entity(SolrDocumentList solrDocumentList, Class<T> cls) {
		if (solrDocumentList != null && solrDocumentList.size() > 0) {
			List<T> objectList = new ArrayList<T>();
			for (SolrDocument sd : solrDocumentList) {
				Object obj = solrDocument2Entity(sd, cls);
				if (obj != null) {
					objectList.add(cls.cast(obj));
				}
			}
			return objectList;
		}
		return null;
	}

	public static SolrInputDocument solrDocument2SolrInputDocument(SolrDocument sd, Object object) {
		if (object != null && sd != null) {
			SolrInputDocument sid = new SolrInputDocument();
			Collection<String> fieldNameCollection = sd.getFieldNames();

			Class<?> cls = object.getClass();
			Object o = null;
			for (String fieldName : fieldNameCollection) {
				try {
					o = cls.getMethod(EntityConvert.getMethodName(fieldName, "get")).invoke(object);

					Class<?> fieldType = cls.getDeclaredField(fieldName).getType();

					if (fieldType.equals(Integer.TYPE)) {
						Integer fieldValue = Integer.class.cast(o);
						if (fieldValue != null && fieldValue.compareTo(0) != 0) {
							sid.addField(fieldName, fieldValue);
						}
					} else if (fieldType.equals(Float.TYPE)) {
						Float fieldValue = Float.class.cast(o);
						if (fieldValue != null && fieldValue.compareTo(0f) != 0) {
							sid.addField(fieldName, fieldValue);
						}
					} else if (fieldType.equals(Double.TYPE)) {
						Double fieldValue = Double.class.cast(o);
						if (fieldValue != null && fieldValue.compareTo(0d) != 0) {
							sid.addField(fieldName, fieldValue);
						}
					} else if (fieldType.equals(Short.TYPE)) {
						Short fieldValue = Short.class.cast(o);
						if (fieldValue != null && fieldValue.compareTo((short) 0) != 0) {
							sid.addField(fieldName, fieldValue);
						}
					} else if (fieldType.equals(Long.TYPE)) {
						Long fieldValue = Long.class.cast(o);
						if (fieldValue != null && fieldValue.compareTo((long) 0) != 0) {
							sid.addField(fieldName, fieldValue);
						}
					} else {
						if (o != null) {
							sid.addField(fieldName, o.toString());
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
			return sid;
		}
		return null;
	}

	public static List<SolrInputDocument> solrDocumentList2SolrInputDocumentList(SolrDocumentList sdl, String idName, Map<Object, Object> objectMap) {
		List<SolrInputDocument> solrInputDocuemntList = new ArrayList<SolrInputDocument>();

		Class<?> cls = null;
		try {
			cls = objectMap.get(0).getClass().getDeclaredField(idName).getType();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		for (SolrDocument sd : sdl) {
			Collection<String> fieldNameCollection = sd.getFieldNames();
			SolrInputDocument sid = null;

			Object object = objectMap.get(cls.cast(sd.getFieldValue(idName)));

			Object o = null;

			if (object != null) {
				sid = new SolrInputDocument();
				for (String fieldName : fieldNameCollection) {
					try {
						o = cls.getMethod(EntityConvert.getMethodName(fieldName, "get")).invoke(object);

						Class<?> fieldType = cls.getDeclaredField(fieldName).getType();

						if (fieldType.equals(Integer.TYPE)) {
							Integer fieldValue = Integer.class.cast(o);
							if (fieldValue != null && fieldValue.compareTo(0) != 0) {
								sid.addField(fieldName, fieldValue);
							}
						} else if (fieldType.equals(Float.TYPE)) {
							Float fieldValue = Float.class.cast(o);
							if (fieldValue != null && fieldValue.compareTo(0f) != 0) {
								sid.addField(fieldName, fieldValue);
							}
						} else if (fieldType.equals(Double.TYPE)) {
							Double fieldValue = Double.class.cast(o);
							if (fieldValue != null && fieldValue.compareTo(0d) != 0) {
								sid.addField(fieldName, fieldValue);
							}
						} else if (fieldType.equals(Short.TYPE)) {
							Short fieldValue = Short.class.cast(o);
							if (fieldValue != null && fieldValue.compareTo((short) 0) != 0) {
								sid.addField(fieldName, fieldValue);
							}
						} else if (fieldType.equals(Long.TYPE)) {
							Long fieldValue = Long.class.cast(o);
							if (fieldValue != null && fieldValue.compareTo((long) 0) != 0) {
								sid.addField(fieldName, fieldValue);
							}
						} else {
							if (o != null) {
								sid.addField(fieldName, o.toString());
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return solrInputDocuemntList;
	}

	public static String getMethodName(String property, String prefix) {
		String prop = Character.toUpperCase(property.charAt(0)) + property.substring(1);
		return prefix + prop;
	}

}
