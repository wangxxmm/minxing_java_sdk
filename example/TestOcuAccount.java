
import com.minxing.client.app.AppAccount;
import com.minxing.client.app.OcuMessageSendResult;
import com.minxing.client.ocu.Article;
import com.minxing.client.ocu.ArticleMessage;
import com.minxing.client.ocu.ArticleMessageNew;
import com.minxing.client.ocu.ArticleNew;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestOcuAccount {
    public static void main(String[] args) {
//		testSendOcuMessageToUsers();
        while (true) {
            testSendOcuMessage();
            try {
                Thread.sleep(1000 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//		clientTest();
    }

    public static void testSendOcuMessageToUsers() {
        for (int i = 0; i < 10; i++) {
            // oa.sendMessageToUsersStr(new
            // HtmlMessage("测试<a href='http://www.baidu.com'>百度</a>"),
            // "dev001@dehui220.com.cn");
            String title = "嘟嘟【待办】OA事务申请" + i;// 提醒标题
            String content = "嘟嘟运营FATCA\\CRS需求上线申请（RL04994）贾晓梅【jiaxm06】温馨提示：不支持PC端点击!";// 提醒内容
            ArticleMessage am = new ArticleMessage();
            Article article = new Article(title, content, "", "http://oawxn.taikang.com/moa//m/s?s=EXKVes0tP93BoyetMuqX8mFzl+FLNFjZKd7WlNrBtokpqSMdB3RI9w==&quot", null);
            am.addArticle(article);
            AppAccount account = AppAccount.loginByAccessToken(
                    "http://dev5.dehuinet.com:8015",
                    "oCnV5eM3zfVwdqACyiQCa-P8_Kq_ZeoFBEA2vwWohRvZHEuP");

//			String ocuId = "40dbd78cc10e32d7a36f2a518460f7f7";
//			String ocuSecret = "88cb9f89de14332abc787486a4249b30";
            String ocuId = "app_notification";
            String ocuSecret = "4fa6a29b49a273240a0947c4a20178ed";

            OcuMessageSendResult send_to = account.sendOcuMessageToUsers(
                    new String[]{"69"}, am, ocuId, ocuSecret);
            System.out.println("发送至:" + send_to.getCount() + "人");
            System.out.println("发送消息Id:" + send_to.getMessageId());
            System.out.println("发送用户Id列表:" + send_to.getUserIds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testSendOcuMessage() {


        AppAccount account = AppAccount.loginByAccessToken(
                "http://dev5.dehuinet.com:8015",
                "qMjJKypzc4JdijbbPKndty2aWBNG-t9fsu2KmG5F9mD1XXJc");
//		社区标识
        int network_id = 2;
//		ocuId和ocuSecret这俩参数在公众号平台的管理页面里找
        String ocuId = "domain_17";
        String ocuSecret = "f8aac0ae2cb7e0cb0db779407f5d81a1";
        ArticleNew.Attachment attachment = new ArticleNew.Attachment();
        attachment.setName("6a702689-9b60-4e2e-b4e9-ed89ccf1fb4c (1) 2_1513765766550.zip");
        attachment.setOrigin_url("files/attachments/6a702689-9b60-4e2e-b4e9-ed89ccf1fb4c (1) 2_1513765766550.zip");
        attachment.setOriginal_name("6a702689-9b60-4e2e-b4e9-ed89ccf1fb4c (1) 2.zip");
        attachment.setSize(32594l);
        attachment.setThumb_url("");
        attachment.setType("application/zip");

        ArticleNew.Category category1 = new ArticleNew.Category();
        ArticleNew.Category category2 = new ArticleNew.Category();
        category1.setId(468l);
        category2.setId(467l);
        category1.setName("23232");
        category2.setName("分享测试");

        List<ArticleNew> articles = new ArrayList<>();
        List<ArticleNew.Attachment> attList = new ArrayList<>();
        attList.add(attachment);
        List<ArticleNew.Category> catList = new ArrayList<>();
        catList.add(category1);
        catList.add(category2);
        ArticleNew article = new ArticleNew()
//				文章标题
                .setTitle("备降")
//				封面的图片地址
                .setImage("upload/mxpp_1509957004221.jpg")
//				文章简介
                .setDescription("11月3日，从纽约飞往广州的南航CZ600航班上，一名女性旅客空中突发病情。")
//				文章作者
                .setAuthor("小程序")
//				内容，是一段html
                .setBody("<html>这是body<html>");
        article.setAttachments(attList);
        article.setCategories(catList);
//		可以添加多个文章
        articles.add(article);
        ArticleMessageNew articleMessage = new ArticleMessageNew()
                .setOcuId(ocuId)
                .setOcuSecret(ocuSecret)
                .setArticles(articles);

        account.sendOcuMessage(articleMessage, network_id);
    }

    public static void clientTest() {
		/*System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "192.168.40.244");
		System.setProperty("http.proxyPort", "8888");*/

        String url = "https://www.baidu.com";
        String params = "";
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        RequestEntity entity = new InputStreamRequestEntity(new ByteArrayInputStream(params.getBytes()), "application/json");
        postMethod.setRequestEntity(entity);
        try {
            client.executeMethod(postMethod);
            String res = postMethod.getResponseBodyAsString();
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
