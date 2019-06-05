package com.juebingliu.boot4enhance.common.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlUtil {
	
	/**
	 * Object 序列化 XML Object->@XmlRootElement
	 * 
	 * @param object
	 * @return
	 */
	public static String marshal(Object object, String encode) throws JAXBException{
		String xmlStr = "";
		JAXBContext jaxbContext = getJaxbContext(object.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encode);
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter out = new StringWriter();
		marshaller.marshal(object, new StreamResult(out));
		xmlStr = out.toString();
		return xmlStr.toString();
	}

	public static String marshalNoHead(Object object) throws JAXBException{
		String xmlStr = "";
		JAXBContext jaxbContext = getJaxbContext(object.getClass());			
		Marshaller marshaller = jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_ENCODING, encode);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter out = new StringWriter();
		marshaller.marshal(object, new StreamResult(out));
		xmlStr = out.toString();
		return xmlStr.toString();
	}
	
	/**
	 * XML 反序列化 Object Object->@XmlRootElement
	 * 
	 * @param xml
	 * @param a
	 * @return
	 */
	public static<T> T unmarshal(String xml, Class<?> a) {
		T result;
		try {
			JAXBContext jaxbContext = getJaxbContext(a);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//			unmarshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
			result = (T) unmarshaller.unmarshal(new StringReader(xml));
			return result;
		} catch (JAXBException e) {
			if ((xml != null) && !"".equals(xml)) {
				e.printStackTrace();
			} else {
			}
		}
		return null;

	}

	/**
	 * 
	 * 实例化JAXBContext对象，只实例化一次。 因为 JAXBContext.newInstance()方法在并发量高时有性能问题。
	 * 
	 */
	private static Map<Class<?>, JAXBContext> map = new HashMap<Class<?>, JAXBContext>();

	private static synchronized JAXBContext getJaxbContext(Class<?> a) {
		try {
			if (map.containsKey(a)) {
				return map.get(a);
			} else {
				JAXBContext jaxbContext = JAXBContext.newInstance(a);
				map.put(a, jaxbContext);
				return jaxbContext;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String formatXML(String inputXML) throws Exception {  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(new StringReader(inputXML));  
	    String requestXML = null;  
	    XMLWriter writer = null;  
	    if (document != null) {  
	      try {  
	        StringWriter stringWriter = new StringWriter();  
	        OutputFormat format = new OutputFormat(" ", true);  
	        writer = new XMLWriter(stringWriter, format);  
	        writer.write(document);  
	        writer.flush();  
	        requestXML = stringWriter.getBuffer().toString();  
	      } finally {  
	        if (writer != null) {  
	          try {  
	            writer.close();  
	          } catch (IOException e) {  
	        	  e.printStackTrace();
	          }  
	        }  
	      }  
	    }  
	    return requestXML;  
	  }  
	


	/** 
     * 通过xml字符获取document文档 
     * @param xmlstr    要序列化的xml字符 
     * @return          返回文档对象 
     * @throws DocumentException 
     */  
    public static Document getXMLByString(String xmlstr) throws DocumentException{  
        if("".equals(xmlstr) || xmlstr==null){  
            return null;  
        }  
        Document document = DocumentHelper.parseText(xmlstr);  
        return document;  
    }  
  
    /** 
     * 获取某个元素的所有的子节点 
     * @param node  制定节点 
     * @return      返回所有的子节点 
     */  
    public static List<Element> getChildElements(Element node) {  
        if (null == node) {  
            return null;  
        }  
        @SuppressWarnings("unchecked")  
        List<Element> lists = node.elements();  
        return lists;  
    }  
      
    /** 
     * 获取指定节点的子节点 
     * @param node          父节点 
     * @param childnode     指定名称的子节点 
     * @return              返回指定的子节点 
     */  
    public static Element getChildElement(Element node,String childnode){  
        if(null==node||null == childnode||"".equals(childnode)){  
            return null;  
        }  
        return node.element(childnode);  
    }  
      
    /** 
     * 获取所有的属性值 
     * @param node 
     * @param arg 
     * @return 
     */  
    public static Map<String, String> getAttributes(Element node,String...arg){  
        if(node==null||arg.length==0){  
            return null;  
        }  
        Map<String, String> attrMap = new HashMap<String,String>();  
        for(String attr:arg){  
            String attrValue = node.attributeValue(attr);  
            attrMap.put(attr, attrValue);  
        }  
        return attrMap;  
    }  
      
    /** 
     * 获取element的单个属性 
     * @param node      需要获取属性的节点对象 
     * @param attr      需要获取的属性值 
     * @return          返回属性的值 
     */  
    public static String getAttribute(Element node,String attr){  
        if(null == node||attr==null||"".equals(attr)){  
            return "";  
        }  
        return node.attributeValue(attr);  
    }  

	
//	public static void main(String[] args) {
//		BodyExample body= new BodyExample();
//		body.setTest("testBody");
//		JytHeadResp head = new JytHeadResp();
//		head.setVersion("1.00");
//		head.setTranTime("yune");
//		MessageExample me = new MessageExample();
//		me.setHead(head);
//		me.setBody(body);
//		String xml = marshal(me);
//		System.out.println(xml);
//		MessageExample r = XmlUtil.<MessageExample>unmarshal(xml,MessageExample.class);
//		System.out.println(r.getBody().getTest());
//	}

}
