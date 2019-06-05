package com.juebingliu.boot4enhance.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 签名规则如下：
 * 
 * 如果接口中有head（比如versionNo、notifyUrl等字段）部分，有list部分（比如ccy、amount等字段），规则如下：
 * 获取list中的每条数据，第一条数据中所有字段添加前缀"0_"（不含引号），第二条数据中所有字段添加前缀"1_"（不含引号）。
 * 然后所有的字段按照字段名称升序排列，组合成0_key1=value1&0_key2=value2的形式；将list中的数据拼接完成后，再拼接head部分；
 * head部分的规则，直接拼接为key3=value3&key4=value4。拼接完成后，最后拼接秘钥。
 * 
 * 举例：提交数据：
 * 
 * {
 * "fromUserID": "hfh101",
 * "versionNo": "1.06",
 * "notifyUrl": "http://10.100.140.62:8902/fire-phoenix/notice.htm",
 * "acqId": "02",
 * "channelId": "00000011111",
 * "merchId": "0000190",
 * "signInfo": "7b99d3d6d46a501609837e8ba29b345c",
 * "list": [
 *   {
 *     "ccy": "CNY",
 *     "remark": "转账一对多测试",
 *     "extend1": "扩展字段1",
 *     "sendTime": "20161009135934",
 *     "bizTpDt": "1000",
 *     "extend2": "扩展字段2",
 *     "orglContractId": "org_con",
 *     "nlTransferContractId": "",
 *     "investProtocol": "",
 *     "amount": "1.00",
 *     "toUserID": "hfh102",
 *     "transferContractId": "con_transfer1",
 *     "loanProtocol": "",
 *     "contractId": "con_testno",
 *     "bizId": "T84920161009135934223"
 *   },
 *   {
 *     "ccy": "CNY",
 *     "remark": "转账一对多测试",
 *     "extend1": "扩展字段1",
 *     "sendTime": "20161009135934",
 *     "bizTpDt": "1000",
 *     "extend2": "扩展字段2",
 *     "orglContractId": "org_con",
 *     "nlTransferContractId": "",
 *     "investProtocol": "",
 *     "amount": "1.00",
 *     "toUserID": "hfh102",
 *     "transferContractId": "con_transfer1",
 *     "loanProtocol": "",
 *     "contractId": "con_testno",
 *     "bizId": "T75220161009135934223"
 *   }
 * ]
 *}
 *
 *待签名字符串，以key为"ytshhyvwcanjwnwyfxv0qmdm7gwnc4j49flzxhydwzy0qsj70v6v9wt4ju4ukzia"举例（不含引号）：
 *0_amount=1.00&0_bizId=T84920161009135934223&0_bizTpDt=1000&0_ccy=CNY&0_contractId=con_testno&0_extend1=扩展字段1&0_extend2=扩展字段2&0_orglContractId=org_con&0_remark=转账一对多测试&0_sendTime=20161009135934&0_toUserID=hfh102&0_transferContractId=con_transfer1&1_amount=1.00&1_bizId=T75220161009135934223&1_bizTpDt=1000&1_ccy=CNY&1_contractId=con_testno&1_extend1=扩展字段1&1_extend2=扩展字段2&1_orglContractId=org_con&1_remark=转账一对多测试&1_sendTime=20161009135934&1_toUserID=hfh102&1_transferContractId=con_transfer1&acqId=02&channelId=00000011111&fromUserID=hfh101&merchId=0000190&notifyUrl=http://10.100.140.62:8902/fire-phoenix/notice.htm&versionNo=1.06&ytshhyvwcanjwnwyfxv0qmdm7gwnc4j49flzxhydwzy0qsj70v6v9wt4ju4ukzia
 *
 *
 * 
 * @author liyi 2014-12-27
 * @version 1.0
 * 
 */

public class BaseUtil {

	private static String env     = "";
	private static List<String> prodEnv = new ArrayList<String>();

	/**
	 * @param obj  
	 * @param seed 秘钥
	 * @return
	 * @throws Exception
	 */
	public static String generateSignInfoNew(Object obj, String seed) throws Exception {

		TreeMap<String, String> map = new TreeMap<String, String>();

		map = convertObjToMapNew(obj, map, null);

		String signStr = buildSignStr(map, seed);// 签名前字符串

		String signInfo = md5Sign(signStr);

		return signInfo;
	}

	/**
	 * @Title:generateSignInfoForFastPay
	 * @Author:ruyiwang
	 * @Date:2016年8月15日 下午7:56:32
	 * @param obj
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String generateSignInfo(Object obj, String seed, String... exclude) throws Exception {

		TreeMap<String, String> map = new TreeMap<String, String>();

		map = convertObjToMapNew(obj, map, null);
		if(null != exclude){
			for(String str : exclude){
				map.remove(str);
			}
		}

		String signStr = buildSignStr(map, seed);// 签名前字符串

		String signInfo = md5Sign(signStr);


		return signInfo;
	}


	private static void putMap(String name, Object value, TreeMap<String, String> map, String prefix) throws Exception {
		if (value == null) {
			return;
		}
		String key = (prefix == null ? name : prefix + "_" + name);

		if (key.indexOf("_serialVersionUID") != -1) {
			return;
		}

		if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
			map.put(key, value.toString());
			return;
		}
		if (value instanceof Double) {
			map.put(key, formatDb2Str((Double) value));
			return;
		}
		if (value instanceof List) {
			List<?> list = (List<?>) value;
			for (int i = 0; i < list.size(); i++) {
				Object currObj = list.get(i);
				if ((currObj instanceof String) || (currObj instanceof Integer) || (currObj instanceof Long)) {
					key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
					map.put(key, currObj.toString());
				} else if (currObj instanceof Double) {
					key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
					map.put(key, formatDb2Str((Double) currObj));
				} else {
					convertObjToMapNew(currObj, map, String.valueOf(i));
				}

			}
			return;
		}

		if (value instanceof Object[]) {
			Object[] list = (Object[]) value;
			for (int i = 0; i < list.length; i++) {
				Object currObj = list[i];
				if ((currObj instanceof String) || (currObj instanceof Integer) || (currObj instanceof Long)) {
					key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
					map.put(key, currObj.toString());
				} else if (currObj instanceof Double) {
					key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
					map.put(key, formatDb2Str((Double) currObj));
				} else {
					convertObjToMapNew(currObj, map, String.valueOf(i));
				}
			}
			return;
		}
	}

	public static TreeMap<String, String> convertObjToMapNew(Object obj, TreeMap<String, String> map, String prefix)
			throws Exception {
		if (obj == null) {
			return map;
		}

		if (obj instanceof Map) {
			Map<?, ?> m = (Map<?, ?>) obj;
			for (Iterator<?> i = m.keySet().iterator(); i.hasNext();) {
				String key = String.valueOf(i.next());
				putMap(key, m.get(key), map, prefix);
			}

		} else {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(obj);
				putMap(field.getName(), value, map, prefix);
			}
		}
		map.remove("__equalsCalc");
		map.remove("__hashCodeCalc");
		map.remove("serialVersionUID");
		map.remove("signInfo");// 本身的签名信息不参与签名
		map.remove("typeDesc");// 本身的签名信息不参与签名

		return map;
	}

	/**
	 * map 转换成键值对的字符串 生成key1=value1&key2=value2的待签名字符串,最后加上seed
	 * 
	 * @param map
	 * @param seed
	 * @return
	 */
	public static String buildSignStr(TreeMap<String, String> map, String seed) {

		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = map.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if ((null != v) && !"".equals(v) && !"null".equalsIgnoreCase(v) && !"signInfo".equals(k) && !"seed".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append(seed);
		return sb.toString();
	}

	/**
	 * 对字符串签名
	 * 
	 * @param signStr
	 * @return
	 */
	public static String md5Sign(String signStr) {

		byte[] signInfo = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			signInfo = md.digest(signStr.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return byteArrayToHexString(signInfo);

	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String formatDb2Str(Double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}
	
	public static byte[] addBytes(byte[] data1, byte[] data2) {  
		    byte[] data3 = new byte[data1.length + data2.length];  
		    System.arraycopy(data1, 0, data3, 0, data1.length);
		    System.arraycopy(data2, 0, data3, data1.length, data2.length);
		    return data3;  
		  
	}  
	 
	public static void main(String[] args) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("amount", "2.00");
		map.put("userId", "002");

		map.put("signInfo", BaseUtil.generateSignInfoNew(map, "seed"));

		System.out.println(map.get("signInfo"));
	}

}
