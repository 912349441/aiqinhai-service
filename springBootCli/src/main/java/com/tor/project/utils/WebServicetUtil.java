package com.tor.project.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WebServicetUtil {

    /**
     *
     * @param clientPath 地址
     * @param methedName 方法
     * @param params 参数
     * @return
     */
    public static Object common(String clientPath, String methedName, Object... params){

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(clientPath);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(methedName, params);
            return objects[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects[0];
    }

}
