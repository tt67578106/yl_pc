package com.ylw.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String bean2Json(Object obj) throws IOException {  
		if(obj==null)
			return null;
        ObjectMapper mapper = new ObjectMapper();  
        StringWriter sw = new StringWriter();  
        JsonGenerator gen = new JsonFactory().createGenerator(sw);// createJsonGenerator(sw);  
        mapper.writeValue(gen, obj);  
        gen.close();  
        return sw.toString();  
    }  
  
    public static <T> T json2Bean(String jsonStr, Class<T> objClass)  
            throws JsonParseException, JsonMappingException, IOException {  
    	if(jsonStr==null || "".equals(jsonStr))
    		return null;
        ObjectMapper mapper = new ObjectMapper(); 
        //忽略无效属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonStr, objClass);  
    }  
    
    
	/**
 	 * 获取OMS流程节点
 	 * @param procedureNode
 	 * @return
 	 */
 	public static String getOmsProcedureNode(String procedureNode){
 		Map<String, String> map = new HashMap<String, String>();
 		map.put("11", "待沟通");
 		map.put("12", "已推荐");
 		map.put("21", "待面试");
 		map.put("22", "面试取消");
 		map.put("23", "面试未到");
 		map.put("24", "已到访");
 		map.put("25", "到访未面");
 		map.put("26", "面试待定");
 		map.put("27","面试成功");
 		map.put("28", "面试失败");
 		map.put("31", "已Offer");
 		map.put("32","拒绝Offer");		
 		map.put("33","接受Offer待入职");
 		map.put("34", "入职失败");		
 		map.put("31", "已Offer");
 		map.put("32","拒绝Offer");
 		map.put("41","已入职"); 		
 		map.put("51", "已离职");		
 		map.put("35", "体检成功"); 		
 		map.put("36","体检失败"); 		
 		map.put("45","签合同成功"); 		
 		map.put("46", "签合同失败");	
 		map.put("16", "接站成功"); 		
 		map.put("15","接站失败");
 		map.put("13","订单关闭");
 		map.put("18", "审核超时，转客服处理");
 		map.put("19", "审核失败");
 		map.put("17","待企业审核");
 		return map.get(procedureNode);
 	}
}
