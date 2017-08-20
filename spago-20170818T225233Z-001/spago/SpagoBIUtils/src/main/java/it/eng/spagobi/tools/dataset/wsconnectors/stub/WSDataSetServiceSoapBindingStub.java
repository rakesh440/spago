/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package it.eng.spagobi.tools.dataset.wsconnectors.stub;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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

import org.apache.axis.NoEndPointException;
import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;
import com.eviware.soapui.support.SoapUIException;


public class WSDataSetServiceSoapBindingStub extends org.apache.axis.client.Stub implements it.eng.spagobi.tools.dataset.wsconnectors.stub.IWsConnector {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("readDataSet");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"), java.util.Map.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "readDataSetReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

    }

    public WSDataSetServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WSDataSetServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WSDataSetServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }
   
  
    public java.lang.String readDataSet(java.lang.String in0, java.util.Map in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5) {
    	String line = null;
        if (super.cachedEndpoint == null) {
            try {
				throw new org.apache.axis.NoEndPointException();
			} catch (NoEndPointException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try {
			org.apache.axis.client.Call _call = createCall();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    /*   // _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
       // _call.setOperationName(new javax.xml.namespace.QName("urn:spagobiwsdataset", "readDataSet"));
        String nameSpace=(String) in1.get("namespace");
        _call.setOperationName(new javax.xml.namespace.QName(nameSpace, in2));
        _call.addParameter(new javax.xml.namespace.QName("", "in0"), XMLType.XSD_STRING, String.class, ParameterMode.IN);
        _call.addParameter(new javax.xml.namespace.QName("", "in1"), XMLType.XSD_STRING, String.class, ParameterMode.IN);
        _call.addParameter(new javax.xml.namespace.QName("", "in2"), XMLType.XSD_STRING, String.class, ParameterMode.IN);
        _call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
        _call.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        _call.setReturnClass(java.lang.String.class);
        _call.setReturnQName(new javax.xml.namespace.QName("", "readDataSetReturn"));
        String arg0=(String) in1.get("arg0");
        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2,arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}  */
        
        String getHello=(String) in1.get("getHello");
        String endPoint=(String) in1.get("endPoint");
        /*if(in4==null){
        WsdlProject project = null;
		try {
			project = new WsdlProject();
		} catch (XmlException | IOException | SoapUIException e) {
			e.printStackTrace();
		}
	    WsdlInterface[] wsdls = null;
		try {
			wsdls = WsdlImporter.importWsdl(project, in0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    WsdlInterface wsdl = wsdls[0];
	    String request = null;
		
	    for (Operation operation : wsdl.getOperationList()) {
	    	WsdlOperation wsdlOperation = (WsdlOperation) operation;
	    	if((in2).equals(wsdlOperation.getName())){
	        System.out.println("OP:"+wsdlOperation.getName());
	        System.out.println("Request:");
	        request = (java.lang.String)wsdlOperation.createRequest(true);
	        System.out.println(wsdlOperation.createRequest(true));
	    	}
	    	
	    }*/
	    /*try {
			request = XML.toJSONObject(request).toString(4);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//return "home";
	/*	return "request"+request;
        }
        else{*/
        	
        	String soapXml = in4;
        /*	JSONObject json = null;
			try {
				json = new JSONObject(soapXml);
			} catch (JSONException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
        	try {
				String xml = XML.toString(json);
				System.out.println("rrrrrrrrrrrr"+ xml);
			} catch (JSONException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}*/
    		System.out.println(soapXml);
    		
    				java.net.URL url = null;
					try {
						url = new java.net.URL(in3);
					} catch (MalformedURLException e4) {
					
						e4.printStackTrace();
					}
    				java.net.URLConnection conn = null;
					try {
						conn = url.openConnection();
					} catch (IOException e3) {
					
						e3.printStackTrace();
					}
    				// Set the necessary header fields
    				conn.setRequestProperty("SOAPAction", "");
    				conn.setRequestProperty("Content-Type", 
    				           "text/xml");
    				conn.setDoOutput(true);
    				// Send the request
    				java.io.OutputStreamWriter wr = null;
					try {
						wr = new java.io.OutputStreamWriter(conn.getOutputStream());
					} catch (IOException e3) {
					
						e3.printStackTrace();
					}
    				try {
						wr.write(soapXml);
					} catch (IOException e2) {
					
						e2.printStackTrace();
					}
    				try {
						wr.flush();
					} catch (IOException e1) {
					
						e1.printStackTrace();
					}
    				// Read the response
    				java.io.BufferedReader rd = null;
					try {
						rd = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
    				
    				try {
						while ((line = rd.readLine()) != null) 
						{ 
							System.out.println(line);
							 line = xmlsplit( line, in5);
							return line;
							
						}
					} /*catch (IOException e) {
					
						e.printStackTrace();
					} catch (XPathExpressionException e) {

						e.printStackTrace();
					} catch (SAXException e) {

						e.printStackTrace();
					} catch (ParserConfigurationException e) {

						e.printStackTrace();
					} catch (TransformerFactoryConfigurationError e) {

						e.printStackTrace();
					} catch (TransformerException e) {

						e.printStackTrace();
					}*/
    				catch(Exception e){
    					
    				}
    	//}
        	
       
			return line;
        }
        
    
    private String xmlsplit(String xml, String xmlnode) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerFactoryConfigurationError, TransformerException{
		 
		 Document doc = adaptSyntax(xml);
		// Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("C:/Users/rg8981/Desktop/request.txt"));
	        XPath xPath = XPathFactory.newInstance().newXPath();
	        XPathExpression exp = xPath.compile("//"+xmlnode);
	       
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
    
    private static Document adaptSyntax(String data) {
    	DocumentBuilderFactory domFactory = null;
		Document document;
		InputStream dataStream;


		document = null;
		dataStream = null;
		try {
			StringBuffer dataBuffer = new StringBuffer();
			dataBuffer.append( data );
			

			dataStream = openStream(dataBuffer.toString());
			DocumentBuilder documentBuilder = domFactory.newInstance().newDocumentBuilder();
			document = documentBuilder.parse(dataStream);
		} catch (Exception t) {
			
		} finally {
			if (dataStream != null)
				try {
					dataStream.close();
				} catch (IOException t) {
					
				}
			
		}

		return document;
	}

    
    private static InputStream openStream(Object data) {
		InputStream inputDataStream;
		if (!(data instanceof InputStream)) {
			inputDataStream = new StringBufferInputStream((String) data);
		} else {
			inputDataStream = (InputStream) data;
		}
		return inputDataStream;
	}
}
