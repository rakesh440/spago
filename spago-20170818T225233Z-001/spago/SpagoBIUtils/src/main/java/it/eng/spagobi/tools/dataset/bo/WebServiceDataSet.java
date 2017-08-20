/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package it.eng.spagobi.tools.dataset.bo;

import it.eng.spagobi.container.ObjectUtils;
import it.eng.spagobi.services.dataset.bo.SpagoBiDataSet;
import it.eng.spagobi.tools.dataset.common.dataproxy.IDataProxy;
import it.eng.spagobi.tools.dataset.common.dataproxy.WebServiceDataProxy;
import it.eng.spagobi.tools.dataset.common.datareader.XmlDataReader;
import it.eng.spagobi.utilities.Helper;
import it.eng.spagobi.utilities.json.JSONUtils;

import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * @author Angelo Bernabei
 *         angelo.bernabei@eng.it
 */
public class WebServiceDataSet extends ConfigurableDataSet {
    
    public static String DS_TYPE = "SbiWSDataSet";
    public static final String WS_ADDRESS = "wsAddress";
	public static final String WS_OPERATION = "wsOperation";
	public static final String WS_ENDPOINT = "wsEndPoint";
	public static final String WS_REQUEST = "wsRequest";
	public static final String WS_XMLNODE = "wsXmlnode";
    
    private static transient Logger logger = Logger.getLogger(WebServiceDataSet.class);
    
	
	/**
	 * Instantiates a new wS data set.
	 */
	public WebServiceDataSet() {
		super();
		setDataProxy( new WebServiceDataProxy());
		setDataReader( new XmlDataReader() );
		//addBehaviour( new QuerableBehaviour(this) );
	}
	
	public WebServiceDataSet(SpagoBiDataSet dataSetConfig) {
		super(dataSetConfig);
		
		setDataProxy(  new WebServiceDataProxy() );
		setDataReader( new XmlDataReader() );
		
		try{
    		//JSONObject jsonConf  = ObjectUtils.toJSONObject(dataSetConfig.getConfiguration());
    		String config = JSONUtils.escapeJsonString(dataSetConfig.getConfiguration());		
    		JSONObject jsonConf  = ObjectUtils.toJSONObject(config);
    		this.setAddress((jsonConf.get(WS_ADDRESS) != null)?jsonConf.get(WS_ADDRESS).toString():"");   
    		this.setEndPoint((jsonConf.get(WS_ENDPOINT) != null)?jsonConf.get(WS_ENDPOINT).toString():"");  
    		this.setOperation((jsonConf.get(WS_OPERATION) != null)?jsonConf.get(WS_OPERATION).toString():"");
    		this.setRequest((jsonConf.get(WS_REQUEST) != null)?jsonConf.get(WS_REQUEST).toString():"");
    		this.setXmlnode((jsonConf.get(WS_XMLNODE) != null)?jsonConf.get(WS_XMLNODE).toString():"");
		}catch (Exception e){
			logger.error("Error while defining dataset configuration.  Error: " + e.getMessage());
		}
		//setAddress( dataSetConfig.getAdress() );
		//setOperation( dataSetConfig.getOperation() );
		//setParamsMap(dataSetConfig.getP)
		
		//addBehaviour( new QuerableBehaviour(this) );

		
	}
		
	public SpagoBiDataSet toSpagoBiDataSet() {
		SpagoBiDataSet sbd;
		
		sbd = super.toSpagoBiDataSet();
		
		sbd.setType( DS_TYPE );	
		/*next informations are already loaded in method super.toSpagoBiDataSet() through the table field configuration
		try{
			JSONObject jsonConf  = new JSONObject();
			jsonConf.put(WS_ADDRESS, getAddress() );	
			jsonConf.put(WS_OPERATION,   getOperation() );	
			sbd.setConfiguration(jsonConf.toString());
		}catch (Exception e){
			logger.error("Error while defining dataset configuration.  Error: " + e.getMessage());
		}
		//sbd.setAdress( getAddress() );
		//sbd.setOperation( getOperation() );
*/
		return sbd;
	}
	
	public WebServiceDataProxy getDataProxy() {
		IDataProxy dataProxy;
		
		dataProxy = super.getDataProxy();
		
		if(dataProxy == null) {
			setDataProxy( new WebServiceDataProxy() );
			dataProxy = getDataProxy();
		}
		
		if(!(dataProxy instanceof  WebServiceDataProxy)) throw new RuntimeException("DataProxy cannot be of type [" + 
				dataProxy.getClass().getName() + "] in WebServiceDataProxy");
		
		return (WebServiceDataProxy)dataProxy;
	}
	
	public String getAddress() {
		return getDataProxy().getAddress();
	}
	public String getXmlnode() {
		return getDataProxy().getXmlnode();
	}
	public void setAddress(String address) {
		getDataProxy().setAddress(address);
	}
	
	public void setEndPoint(String endpoint) {
		getDataProxy().setEndPoint(endpoint);
		System.out.println("endpointendpoint"+endpoint);
	}
	
	public void setRequest(String request) {
		getDataProxy().setRequest(request);
		System.out.println("requestrequest"+request);
	}
	
	public void setXmlnode(String xmlnode) {
		getDataProxy().setXmlnode(xmlnode);
		System.out.println("xmlnodexmlnode"+xmlnode);
	}
	
	public  String getOperation() {
		return getDataProxy().getOperation();
	}
	
	public  void setOperation(String operation) {
		getDataProxy().setOperation(operation);
	}
//added by rg8981
	@Override
	public String getSignature() {
		JSONObject config = getJSONConfig();
		// it's supposed that it doesn't change with same config derived from same json
		String res = config.toString();
		//res += toStringUserParameters();
		res = Helper.md5(res);
		return res;
	}
	
	private String toStringUserParameters() {
		StringBuilder res = new StringBuilder();
		for (Entry<String, Object> entry : new TreeMap<String, Object>(userProfileParameters).entrySet()) {
			res.append(",");
			res.append(entry.getKey());
			res.append(":");
			res.append(entry.getValue());
		}
		return res.toString();
	}
	
	private JSONObject getJSONConfig() {
		// String config = JSONUtils.escapeJsonString(getConfiguration());
		JSONObject jsonConf = ObjectUtils.toJSONObject(getConfiguration());
		return jsonConf;
	}

}
