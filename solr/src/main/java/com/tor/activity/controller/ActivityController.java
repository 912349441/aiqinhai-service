package com.tor.activity.controller;

import com.tor.activity.entity.Activity;
import com.tor.activity.mapper.ActivityMapper;
import com.tor.activity.service.impl.ActivityApplyServiceImpl;
import com.tor.activity.service.impl.ActivityItemServiceImpl;
import com.tor.activity.service.impl.ActivityServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@Controller
public class ActivityController {

    @Autowired
    private ActivityServiceImpl activityService;
    @Autowired
    private ActivityItemServiceImpl activityItemService;
    @Autowired
    private ActivityApplyServiceImpl activityApplyService;
    @Autowired
    private ActivityMapper activityMapper;

    private String url = "http://solr.aiqinhai.com/product";
    /**
     * 向solr的仓库添加数据
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/addSolr")
    public String upSolr() throws Exception {
        /* 1.创建连接对象 */
        HttpSolrClient solrServer = new HttpSolrClient(url);
        List<Activity> byParams = activityService.findAll();
        for (Activity activity : byParams) {
            //2.创建一个文档对象
            SolrInputDocument inputDocument = new SolrInputDocument();
            //向文档中添加域以及对应的值,注意：所有的域必须在schema.xml中定义过,前面已经给出过自定义的域。
            inputDocument.addField("id", activity.getId());
            inputDocument.addField("pro_name", activity.getActivityTitle());
            //3.将文档写入索引库中
            solrServer.add(inputDocument);
            //提交
            solrServer.commit();
        }
        return "add success!";
    }

    /**
     * 删除db下所有的数据
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delSolr")
    public String deleteDocument() throws Exception {
        //创建一连接
        HttpSolrClient solrServer = new HttpSolrClient(url);
        //solrServer.deleteById("test001");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
        return "del success!";
    }

    /**
     * 测试与其他项目相连
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/find")
    public String find(String search)throws Exception  {
        System.out.printf("有人来啦！有人来啦！有人来啦！有人来啦！\n");
        return testQuery(search).toString();
    }

    public JSONArray testQuery(String search) throws Exception {
        // 创建Solr单机版客户端
        HttpSolrClient httpSolrClient =  new HttpSolrClient(url);

        SolrQuery solrQuery = new SolrQuery();
        // 设置默认域
        //solrQuery.set("df", "pro_name");
        // 设置关键词
        solrQuery.set("q", "search:"+search);
        // 设置价格区间
        //solrQuery.set("fq", "item_price:[5000 TO *]");
        // 设置排序
        solrQuery.addSort("pro_name", SolrQuery.ORDER.desc);
        // 设置分页
        solrQuery.setStart(1);
        solrQuery.setRows(10);
        // 开启高亮
        solrQuery.setHighlight(true);
        // 设置高亮字段
        solrQuery.addHighlightField("pro_name");
        // 高亮前缀
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        // 高亮后缀
        solrQuery.setHighlightSimplePost("</span>");

        // 执行查询
        QueryResponse response = httpSolrClient.query(solrQuery);
        // 获取文档结果集
        SolrDocumentList docs = response.getResults();
        // 获取高亮结果集
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        // 总条数
        long numFound = docs.getNumFound();
        System.out.println("共查到：" + numFound + "条数据");
        JSONArray jsonArray = new JSONArray();
        for (SolrDocument doc : docs) {
            System.out.println("id:" + doc.get("id"));
            System.out.println("pro_name:" + doc.get("pro_name"));

            JSONObject object=new JSONObject();
            object.put("id",doc.get("id"));
            object.put("pro_name",doc.get("pro_name"));
            jsonArray.put(object);
            // 标题高亮
            Map<String, List<String>> map = highlighting.get(doc.get("id"));
            List<String> list = map.get("pro_name");
            if(list!=null && list.size()>0){
                for (String s : list) {
                    System.out.println("activity_title:" + s);
                }
            }
            System.out.println("==================");
        }
        return jsonArray;
    }

    @RequestMapping(value="/solr/configAddDocument")
    public String configAddDocument(String id){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url+"/dataimport");
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("ID", id));
        formparams.add(new BasicNameValuePair("start_row", String.valueOf(0)));
        formparams.add(new BasicNameValuePair("page_size", String.valueOf(5000)));
        formparams.add(new BasicNameValuePair("command", "full-import"));
        formparams.add(new BasicNameValuePair("clean", "false"));
        formparams.add(new BasicNameValuePair("entity", "product"));
        formparams.add(new BasicNameValuePair("entity", "product_item"));
        formparams.add(new BasicNameValuePair("verbose", "false"));
        formparams.add(new BasicNameValuePair("optimize", "false"));
        formparams.add(new BasicNameValuePair("commit","true"));//改为非实时提交(false)，降低solr性能消耗
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(uefEntity);
            HttpResponse response;
            response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String resultStr = EntityUtils.toString(httpEntity, "UTF-8");
                if (resultStr.contains("A command is still running...")) {
                    return "";
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return "";
    }
}
