/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. 
package it.eng.spagobi.tools.dataset.common.datareader;

import it.eng.spago.base.SourceBean;
import it.eng.spago.base.SourceBeanAttribute;
import it.eng.spago.base.SourceBeanException;
import it.eng.spago.dbaccess.sql.DataRow;
import it.eng.spagobi.commons.utilities.StringUtilities;
import it.eng.spagobi.tools.dataset.common.datastore.DataStore;
import it.eng.spagobi.tools.dataset.common.datastore.Field;
import it.eng.spagobi.tools.dataset.common.datastore.IDataStore;
import it.eng.spagobi.tools.dataset.common.datastore.IField;
import it.eng.spagobi.tools.dataset.common.datastore.IRecord;
import it.eng.spagobi.tools.dataset.common.datastore.Record;
import it.eng.spagobi.tools.dataset.common.metadata.FieldMetadata;
import it.eng.spagobi.tools.dataset.common.metadata.MetaData;
import it.eng.spagobi.utilities.exceptions.SpagoBIRuntimeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

*//**
 * @authors Angelo Bernabei (angelo.bernabei@eng.it) Andrea Gioia
 *          (andrea.gioia@eng.it)
 *//*
public class XmlDataReader extends AbstractDataReader {

	DocumentBuilderFactory domFactory;

	private static transient Logger logger = Logger.getLogger(XmlDataReader.class);

	public XmlDataReader() {
		super();
		domFactory = DocumentBuilderFactory.newInstance();

	}

	public IDataStore read(Object data) {
		String dataString;
		InputStream dataStream;

		DataStore dataStore;
		MetaData dataStoreMeta;

		logger.debug("IN");

		dataStream = null;
		dataStore = new DataStore();
		dataStoreMeta = new MetaData();
		dataStore.setMetaData(dataStoreMeta);
		if (data.toString().startsWith("request")) {
			data = data.toString().replace("request", "");
			String columnName = "Data";
			Class<? extends String> columnValue = data.toString().getClass();
			FieldMetadata fieldMeta = new FieldMetadata();
			fieldMeta.setName(columnName);
			fieldMeta.setType(columnValue);
			dataStoreMeta.addFiedMeta(fieldMeta);
			IRecord record = new Record(dataStore);
			IField field = new Field((String) data);
			record.appendField(field);
			dataStore.appendRecord(record);
		} else {
			try {
				if (data == null)
					throw new IllegalArgumentException("Input parameter [data] cannot be null");

				dataStream = openStream(data);
				// DocumentBuilder documentBuilder =
				// domFactory.newDocumentBuilder();
				DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); // added
																												// by
																												// rg8981
				Document document = null;
				try {
					document = documentBuilder.parse(dataStream);
				} catch (Throwable t) {
					if (data instanceof String) {
						document = adaptSyntax((String) data);
					} else {
						throw t;
					}
				}

				// NodeList nodes = readXMLNodes(document, "/ROWS/ROW");
				NodeList nodes = document.getElementsByTagName("return"); // added
																			// by
																			// rg8981
				if (nodes == null) {
					throw new RuntimeException("Malformed data. Impossible to find tag rows.row");
				}

				int rowNumber = nodes.getLength();
				System.out.println("row number   "+rowNumber);
				 boolean firstRow = true;
				 for (int i = 0; i < rowNumber; i++, firstRow = false) {
				//for (int i = 0; i < rowNumber; i++) {
					IRecord record = new Record(dataStore);
					Node tempNode = nodes.item(i); // added
					if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					if (tempNode.hasAttributes()) { // added
						NamedNodeMap nodeAttributes = tempNode.getAttributes();
						// NamedNodeMap nodeAttributes =
						// nodes.item(i).getAttributes();
						for (int j = 0; j < nodeAttributes.getLength(); j++) {
							Node attribute = nodeAttributes.item(j);
							String columnName = attribute.getNodeName();
							System.out.println("column" + columnName);
							 String columnValue = attribute.getNodeValue();
							//String columnValue = attribute.getTextContent();// added
							Class columnType = attribute.getNodeValue().getClass();

							 if (firstRow == true) {
							FieldMetadata fieldMeta = new FieldMetadata();
							fieldMeta.setName(columnName);
							fieldMeta.setType(columnType);
							dataStoreMeta.addFiedMeta(fieldMeta);
							 }

							IField field = new Field(columnValue);
							record.appendField(field);
						}
					}
					if (tempNode.hasChildNodes()) {

						// loop again if has child nodes
						read( data);

					}
					String columnName = tempNode.getNodeName();
					System.out.println("column" + columnName);
					// String columnValue = attribute.getNodeValue();
					String columnValue = tempNode.getTextContent();// added
					Class columnType = tempNode.getTextContent().getClass();

					 if (firstRow == true) {
					FieldMetadata fieldMeta = new FieldMetadata();
					fieldMeta.setName(columnName);
					fieldMeta.setType(columnType);
					dataStoreMeta.addFiedMeta(fieldMeta);
					 }

					IField field = new Field(columnValue);
					record.appendField(field);

					dataStore.appendRecord(record);
				}
				
				}

			} catch (Throwable t) {
				logger.error("Exception reading data", t);
			} finally {
				if (dataStream != null)
					try {
						dataStream.close();
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("IOException during File Closure");
					}
			}
		
			
			}
		return dataStore;
	}

	public boolean isSyntaxCorrect(String data) {

		logger.debug("IN");

		SourceBean dataSourceBean = null;
		try {
			dataSourceBean = SourceBean.fromXMLString(data);
		} catch (Throwable t) {
			return false;
		}

		if (dataSourceBean == null || !dataSourceBean.getName().equalsIgnoreCase("ROWS")) {
			return false;
		}

		List rowsList = dataSourceBean.getAttributeAsList(DataRow.ROW_TAG);
		if ((rowsList == null) || (rowsList.size() == 0)) {
			return false;
		}

		logger.debug("OUT");

		return true;
	}

	private Document adaptSyntax(String data) {

		Document document;
		InputStream dataStream;

		logger.debug("IN");

		document = null;
		dataStream = null;
		try {
			StringBuffer dataBuffer = new StringBuffer();
			dataBuffer.append("<ROWS>");
			dataBuffer.append("<ROW value=\"" + data + "\"/>");
			dataBuffer.append("</ROWS>");

			dataStream = openStream(dataBuffer.toString());
			DocumentBuilder documentBuilder = domFactory.newDocumentBuilder();
			document = documentBuilder.parse(dataStream);
		} catch (Throwable t) {
			throw new SpagoBIRuntimeException(
					"An unexpected error occured while adapting syntax of script result [" + data + "]", t);
		} finally {
			if (dataStream != null)
				try {
					dataStream.close();
				} catch (IOException t) {
					throw new SpagoBIRuntimeException(
							"Impossible to close stream associated to data string [" + data + "]", t);
				}
			logger.debug("OUT");
		}

		return document;
	}

	private NodeList readXMLNodes(Document doc, String xpathExpression) throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xpathExpression);

		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		return nodes;
	}

	private InputStream openStream(Object data) {
		InputStream inputDataStream;
		if (!(data instanceof InputStream)) {
			inputDataStream = new StringBufferInputStream((String) data);
		} else {
			inputDataStream = (InputStream) data;
		}
		return inputDataStream;
	}

}
*/


/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. */



/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. */






package it.eng.spagobi.tools.dataset.common.datareader;

import it.eng.spago.base.SourceBean;
import it.eng.spago.base.SourceBeanAttribute;
import it.eng.spago.base.SourceBeanException;
import it.eng.spago.dbaccess.sql.DataRow;
import it.eng.spagobi.commons.utilities.StringUtilities;
import it.eng.spagobi.tools.dataset.bo.WebServiceDataSet;
import it.eng.spagobi.tools.dataset.common.dataproxy.WebServiceDataProxy;
import it.eng.spagobi.tools.dataset.common.datareader.JSONPathDataReader.JSONPathAttribute;
import it.eng.spagobi.tools.dataset.common.datastore.DataStore;
import it.eng.spagobi.tools.dataset.common.datastore.Field;
import it.eng.spagobi.tools.dataset.common.datastore.IDataStore;
import it.eng.spagobi.tools.dataset.common.datastore.IField;
import it.eng.spagobi.tools.dataset.common.datastore.IRecord;
import it.eng.spagobi.tools.dataset.common.datastore.Record;
import it.eng.spagobi.tools.dataset.common.metadata.FieldMetadata;
import it.eng.spagobi.tools.dataset.common.metadata.IFieldMetaData;
import it.eng.spagobi.tools.dataset.common.metadata.MetaData;
import it.eng.spagobi.utilities.Helper;
import it.eng.spagobi.utilities.assertion.Assert;
import it.eng.spagobi.utilities.exceptions.SpagoBIRuntimeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jayway.jsonpath.JsonPath;

/**
 * @authors Angelo Bernabei (angelo.bernabei@eng.it) Andrea Gioia
 *          (andrea.gioia@eng.it)
 */
public class XmlDataReader extends AbstractDataReader {

	DocumentBuilderFactory domFactory;

	 String xmlnode;

	private static transient Logger logger = Logger.getLogger(XmlDataReader.class);

	public XmlDataReader() {
		super();
		domFactory = DocumentBuilderFactory.newInstance();

	}

	public IDataStore read(Object data) {
		String dataString;
		InputStream dataStream;

		DataStore dataStore;
		MetaData dataStoreMeta;

		logger.debug("IN");

		dataStream = null;
		dataStore = new DataStore();
		dataStoreMeta = new MetaData();
		dataStore.setMetaData(dataStoreMeta);
		if (data.toString().startsWith("request")) {
			data = data.toString().replace("request", "");
			String columnName = "Data";
			Class<? extends String> columnValue = data.toString().getClass();
			FieldMetadata fieldMeta = new FieldMetadata();
			fieldMeta.setName(columnName);
			fieldMeta.setType(columnValue);
			dataStoreMeta.addFiedMeta(fieldMeta);
			IRecord record = new Record(dataStore);
			IField field = new Field((String) data);
			record.appendField(field);
			dataStore.appendRecord(record);
		} else {
			try {
				if (data == null)
					throw new IllegalArgumentException("Input parameter [data] cannot be null");

				//dataStream = openStream(data);
				// DocumentBuilder documentBuilder =
				// domFactory.newDocumentBuilder();
				//File file = new File("C:/Users/rg8981/Desktop/request1.txt");
				
				//DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); // added
																												// by
																												// rg8981
				/*Document document = null;
				try {
					document = documentBuilder.parse(file);
				} catch (Throwable t) {
					if (data instanceof String) {
						document = adaptSyntax((String) data);
					} else {
						throw t;
					}
				}*/

				// NodeList nodes = readXMLNodes(document, "/ROWS/ROW");
				//NodeList nodes = document.getElementsByTagName("staff"); // added
																			// by
																			// rg8981
				/*if (nodes == null) {
					throw new RuntimeException("Malformed data. Impossible to find tag rows.row");
				}*/
			//	Set<String> s = new HashSet<String>();
				//IRecord record = new Record(dataStore);
				//System.out.println(record.getFields().toString());
				
				//String xml=readJson("C:/Users/rg8981/Desktop/request1.txt");
				//String newXml = xmlsplit(xml);
				//xmlParser(dataStore, dataStoreMeta, nodes, s, record);
				System.out.println(XML.toJSONObject(data.toString()).toString(4));
				 dataStore=read1(XML.toJSONObject(data.toString()).toString(4));
				System.out.println("datastoredatastoredatastoredatastore"+dataStore);
				//System.out.println("               record              "+record.getFields().toString());

				//dataStore.appendRecord(record);
			} catch (Throwable t) {
				logger.error("Exception reading data", t);
			} finally {
				if (dataStream != null)
					try {
						dataStream.close();
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("IOException during File Closure");
					}
			}
		
			
			}
		//System.out.println("dataStore        "+dataStore);
		return dataStore;
	}

	private void xmlParser(DataStore dataStore, MetaData dataStoreMeta, NodeList nodes, Set s, IRecord record) {
		int rowNumber = nodes.getLength();
	
		System.out.println("row number   "+rowNumber);
		 boolean firstRow = true;
		 
		for (int i = 0; i < rowNumber; i++, firstRow = false) {
			Node tempNode = nodes.item(i); // added
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
			if (tempNode.hasAttributes()) { // added
				NamedNodeMap nodeAttributes = tempNode.getAttributes();

				for (int j = 0; j < nodeAttributes.getLength(); j++) {
					Node attribute = nodeAttributes.item(j);
					String columnName = attribute.getNodeName();
					System.out.println("columnName" + columnName);
					 String columnValue = attribute.getNodeValue();
					 

					Class columnType = attribute.getNodeValue().getClass();
					if (add(attribute.getNodeName(), s) == true) {
						System.out.println("columnName" + columnName);
					FieldMetadata fieldMeta = new FieldMetadata();
					fieldMeta.setName(columnName);
					fieldMeta.setType(columnType);
					dataStoreMeta.addFiedMeta(fieldMeta);
					 }else{
						// record = new Record(dataStore); 
					 }
					
					
					System.out.println("columnValue    ....." + columnValue);
					IField field = new Field(columnValue);
					record.appendField(field);
				}
			}
			if (tempNode.hasChildNodes()) {
				System.out.println("tempNode.hasChildNodes()    "+ tempNode.getChildNodes().item(0).getNodeName());
				xmlParser(dataStore, dataStoreMeta, tempNode.getChildNodes(), s,  record);
			

			}
			if(!tempNode.getNodeName().equalsIgnoreCase("staff")){
			String columnName = tempNode.getNodeName();
			System.out.println("column    " + columnName);

			String columnValue = tempNode.getTextContent();// added
			Class columnType = tempNode.getTextContent().getClass();
			
			if (add(tempNode.getNodeName(), s) == true) {
				System.out.println("columnName        ,,,," + columnName);
			FieldMetadata fieldMeta = new FieldMetadata();
			fieldMeta.setName(columnName);
			fieldMeta.setType(columnType);
			dataStoreMeta.addFiedMeta(fieldMeta);
			 }else{
				// record = new Record(dataStore); 
			 }
			System.out.println("columnValue   " + columnValue);
			IField field = new Field(columnValue);
			record.appendField(field);	
			
		}else{
			 dataStore.appendRecord(record);
			 record = new Record(dataStore);
			 System.out.println( " dataStore.appendRecord(record)"+dataStore);
		}
			
		}
			 //dataStore.appendRecord(record);	 
		}
		
		
	}
	
	
	
	
	public boolean isSyntaxCorrect(String data) {

		logger.debug("IN");

		SourceBean dataSourceBean = null;
		try {
			dataSourceBean = SourceBean.fromXMLString(data);
		} catch (Throwable t) {
			return false;
		}

		if (dataSourceBean == null || !dataSourceBean.getName().equalsIgnoreCase("ROWS")) {
			return false;
		}

		List rowsList = dataSourceBean.getAttributeAsList(DataRow.ROW_TAG);
		if ((rowsList == null) || (rowsList.size() == 0)) {
			return false;
		}

		logger.debug("OUT");

		return true;
	}

	private Document adaptSyntax(String data) {

		Document document;
		InputStream dataStream;

		logger.debug("IN");

		document = null;
		dataStream = null;
		try {
			StringBuffer dataBuffer = new StringBuffer();
			/*dataBuffer.append("<ROWS>");
			dataBuffer.append("<ROW value=\"" + data + "\"/>");*/
			dataBuffer.append(data);

			dataStream = openStream(dataBuffer.toString());
			DocumentBuilder documentBuilder = domFactory.newInstance().newDocumentBuilder();
			document = documentBuilder.parse(dataStream);
		} catch (Throwable t) {
			throw new SpagoBIRuntimeException(
					"An unexpected error occured while adapting syntax of script result [" + data + "]", t);
		} finally {
			if (dataStream != null)
				try {
					dataStream.close();
				} catch (IOException t) {
					throw new SpagoBIRuntimeException(
							"Impossible to close stream associated to data string [" + data + "]", t);
				}
			logger.debug("OUT");
		}

		return document;
	}

	private NodeList readXMLNodes(Document doc, String xpathExpression) throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xpathExpression);

		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		return nodes;
	}

	private InputStream openStream(Object data) {
		InputStream inputDataStream;
		if (!(data instanceof InputStream)) {
			inputDataStream = new StringBufferInputStream((String) data);
		} else {
			inputDataStream = (InputStream) data;
		}
		return inputDataStream;
	}
	  public static boolean add(String e,Set set) {
			 // Set<String> set = new HashSet<String>();
			 // System.out.println(set);
			  
		      return set.add(e);
		  }
	  
private static final String ATTRIBUTES_DIRECTLY = "attributesDirectly";
	  
	  private static final Class<String> ALL_OTHER_TYPES = String.class;
	  
	  
	  
	  
	  
	  
	  
	 /* @SuppressWarnings("unchecked")
		private List<Object> getItems(String data) {
			Object parsed = JsonPath.read(data, jsonPathItems);
			if (parsed == null) {
				throw new JSONPathDataReaderException(String.format("Items not found in %s with json path %s", data, jsonPathItems));
			}

			// can be an array or a single object
			List<Object> parsedData;
			if (parsed instanceof List) {
				parsedData = (List<Object>) parsed;
			} else {
				parsedData = Arrays.asList(parsed);
			}
			return parsedData;
		}*/
	  
	  
	  public synchronized DataStore read1(Object data) {
		  
		  data= data.toString();
			Helper.checkNotNull(data, "data");
			if (!(data instanceof String)) {
				throw new IllegalArgumentException("data must be a string");
			}

			String d = (String) data;	

			try {
				DataStore dataStore = new DataStore();
				MetaData dataStoreMeta = new MetaData();
				dataStore.setMetaData(dataStoreMeta);
				List<Object> parsedData = getItems(d);
				//addFieldMetadata(dataStoreMeta, parsedData);
				System.out.println("parsedData  "+parsedData);
				addData(d, dataStore, dataStoreMeta, parsedData);
				return dataStore;
			} catch (ParseException e) {
				throw new JSONPathDataReaderException(e);
			} catch (JSONPathDataReaderException e) {
				throw e;
			} catch (Exception e) {
				throw new JSONPathDataReaderException(e);
			}
		}
	  
	  
	  
	  @SuppressWarnings("unchecked")
		private List<Object> getItems(String data) {
			// WebServiceDataSet.this.getXmlnode();
		String xmlNode =  WebServiceDataProxy.xmlnode;
			Object parsed = JsonPath.read(data, "$."+xmlNode+"[*]");
			System.out.println("Object parsed"+  parsed);
			if (parsed == null) {
				throw new JSONPathDataReaderException(String.format("Items not found in %s with json path %s", data, "$.staff[*]"));
			}

			// can be an array or a single object
			List<Object> parsedData;
			if (parsed instanceof List) {
				parsedData = (List<Object>) parsed;
			} else {
				parsedData = Arrays.asList(parsed);
			}
			return parsedData;
		}
	  private void addData(String data, DataStore dataStore, MetaData dataStoreMeta, List<Object> parsedData) throws ParseException {
		 
			for (Object o : parsedData) {
				IRecord record = new Record(dataStore);
System.out.println("dataStoreMeta.getFieldCount()"+dataStoreMeta.getFieldCount());
				for (int j = 0; j < dataStoreMeta.getFieldCount(); j++) {
					IFieldMetaData fieldMeta = dataStoreMeta.getFieldMeta(j);
					Object propAttr = fieldMeta.getProperty(ATTRIBUTES_DIRECTLY);
					if (propAttr != null && (Boolean) propAttr) {
						// managed after this process
						continue;
					}
					
				
					manageDirectlyAttributes(o, record, dataStoreMeta, dataStore);
				
System.out.println(dataStore);
				
			}
				manageDirectlyAttributes(o, record, dataStoreMeta, dataStore);
				dataStore.appendRecord(record);
		}
	  }
	  
	  
	  
	  
	  
	  @SuppressWarnings({ "rawtypes", "unchecked" })
		private static void manageDirectlyAttributes(Object data, IRecord rec, MetaData dsm, DataStore dataStore) {
			Assert.assertTrue(data instanceof Map, "data instanceof Map");
			Map jsonObject = (Map) data;

			for (int j = 0; j < dsm.getFieldCount(); j++) {
				IFieldMetaData fieldMeta = dsm.getFieldMeta(j);
				Object propAttr = fieldMeta.getProperty(ATTRIBUTES_DIRECTLY);
				if (propAttr == null || !(Boolean) propAttr) {
					// managed before
					continue;
				}

				String name = fieldMeta.getName();
				if (jsonObject.containsKey(name)) {
					Object value = jsonObject.get(name);
					value = normalizeNumber(value);
					rec.appendField(new Field(value));
				} else {
					// add null value
					rec.appendField(new Field(null));
				}
			}

			// find new elements
			for (String key : new TreeSet<String>(jsonObject.keySet())) {
				int index = dsm.getFieldIndex(key);
				if (index != -1) {
					// already present,manged before
					continue;
				}

				// not found
				Object value = jsonObject.get(key);
				value = normalizeNumber(value);
				rec.appendField(new Field(value));

				// value can be null from json object
				Class<? extends Object> type = value == null ? ALL_OTHER_TYPES : value.getClass();
				if (Number.class.isAssignableFrom(type)) {
					// use always double for numbers, just to prevent problems
					// if it's represented as an integer without trailing 0
					type = Double.class;
				}
				FieldMetadata fm = new FieldMetadata(key, type);
				fm.setProperty(ATTRIBUTES_DIRECTLY, true);
				dsm.addFiedMeta(fm);

				// add null to previous records
				// current record not added
				for (int i = 0; i < dataStore.getRecordsCount(); i++) {
					IRecord previousRecord = dataStore.getRecordAt(i);
					Assert.assertTrue(previousRecord != rec, "previousRecord!=rec");
					previousRecord.appendField(new Field(null));
				}
			}
		}
	  
	  
	  
	 
	  
	  
	  private static Object normalizeNumber(Object value) {
			if (value == null) {
				return value;
			}
			if (Number.class.isAssignableFrom(value.getClass())) {
				// use always double for numbers, just to prevent problems
				// if it's represented as an integer without trailing 0
				value = ((Number) value).doubleValue();
			}
			return value;
		}
	  
	  
	  
	  private static String readJson(String fileName)
			    throws IOException
			  {
			    String str = null;
			    BufferedReader br = null;
			    try {
			      br = new BufferedReader(new FileReader(fileName));
			    }
			    catch (FileNotFoundException e) {
			      e.printStackTrace();
			    }
			    try {
			      StringBuilder sb = new StringBuilder();
			      String line = br.readLine();

			      while (line != null) {
			        sb.append(line);
			        line = br.readLine();
			      }
			      str = sb.toString();
			    }
			    catch (IOException e) {
			      e.printStackTrace();
			    } finally {
			      br.close();
			    }
			    return str;
			  }
	  
	  
	  
	 private String xmlsplit(String xml) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerFactoryConfigurationError, TransformerException{
		 
		 Document doc = adaptSyntax(xml);
		// Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:/Users/rg8981/Desktop/request1.txt"));
	        XPath xPath = XPathFactory.newInstance().newXPath();
	        XPathExpression exp = xPath.compile("//"+"staff");
	       
	        NodeList nl = (NodeList)exp.evaluate(doc, XPathConstants.NODESET);
	        System.out.println("Found " + nl.getLength() + " results");
	        
	        StringWriter buf = new StringWriter();
	        for (int i = 0; i < nl.getLength(); i++) {
	            Node node = nl.item(i);
	                Transformer xform = TransformerFactory.newInstance().newTransformer();
	                xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	                xform.transform(new DOMSource(node), new StreamResult(buf));
	                System.out.println(buf.toString());
	               
	        }
	        
		return buf.toString();
		 
	 }
		
	 
	 public void setXmlnode(String xmlnode) {
			this.xmlnode = xmlnode;
		}
		public String getXmlnode() {
			return xmlnode;
		}
}


