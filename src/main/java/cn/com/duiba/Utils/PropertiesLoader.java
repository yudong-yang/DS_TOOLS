package cn.com.duiba.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class PropertiesLoader {
	
	
	
	/** 
	 * Spring 提供的 PropertiesLoaderUtils 允许您直接通过基于类路径的文件地址加载属性资源 
	 * 最大的好处就是：实时加载配置文件，修改后立即生效，不必重启 
	 */  
	public  void springUtil(){  
	    Properties props = new Properties();   
	        try {  
	            props=PropertiesLoaderUtils.loadAllProperties("KeySecretMap.properties");  
	            for(Object key:props.keySet()){  
	                System.out.print(key+":");  
	                System.out.println(props.get(key));  
	            }  
	        } catch (IOException e) {  
	            System.out.println(e.getMessage());  
	        }    
	    }  
	  
	
	/** 
     * 传递键值对的Map，更新properties文件 
     *  
     * @param fileName 
     *            文件名(放在resource源包目录下)，需要后缀 
     * @param keyValueMap 
     *            键值对Map 
     */  
    public static void updateProperties(String fileName,Map<String, String> keyValueMap) {  
        //getResource方法使用了utf-8对路径信息进行了编码，当路径中存在中文和空格时，他会对这些字符进行转换，这样，  
        //得到的往往不是我们想要的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的中文及空格路径。  
//        String filePath = PropertiesLoader.class.getClassLoader().getResource(fileName).getFile();  
    	
    	String filePath="./src/mian/resources/"+fileName;
        System.out.println(filePath);
        Properties props = null;  
        BufferedWriter bw = null;  
        try {  
            filePath = URLDecoder.decode(filePath,"utf-8");      
//            System.out.println("updateProperties propertiesPath:" + filePath);  
            props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(fileName));  
            System.out.println("updateProperties old:"+props);  
              
            // 写入属性文件  
            
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true)));  
              
            props.clear();// 清空旧的文件  
              
            for (String key : keyValueMap.keySet())  
                props.setProperty(key, keyValueMap.get(key));  
              
            System.out.println("updateProperties new:"+props);  
            props.store(bw, "appdemo");  
        } catch (IOException e) {  
        	System.out.println(e.getMessage());  
        } finally {  
            try {  
                bw.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    
    
    /** 
	 * Spring 提供的 PropertiesLoaderUtils 允许您直接通过基于类路径的文件地址加载属性资源 
	 * 最大的好处就是：实时加载配置文件，修改后立即生效，不必重启 
	 */  
    public static String GetPropertiesValue(String key){  
	    Properties props = new Properties();   
	    String value=null;
	        try {  
	          props=PropertiesLoaderUtils.loadAllProperties("KeySecretMap.properties");  
	          value = props.getProperty(key) ;    
	        } catch (IOException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return value; 
	    }  
	  
 public static void main(String[] args) throws Exception {
		
	     Map<String, String> map = new LinkedHashMap<String, String>();  
	        map.put("aaa","vvvv");  
	        System.out.println(GetPropertiesValue("aaa"));
	        if(!GetPropertiesValue("aaa").endsWith(map.get("aaa"))){
		 updateProperties("KeySecretMap.properties", map);
	        }
	    }

}
