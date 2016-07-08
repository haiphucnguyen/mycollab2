package com.mycollab.billing.fastspring;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class Api {

    public Api() {

    }

    public static void main(String[] args) throws IOException {
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("linhduong@esofthead.com", "24pIlObiL14A"));
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet request = new HttpGet("https://api.fastspring.com/company/mycollab/subscription/MYC160706-4943-82180S");
        String auth = "linhduong@esofthead.com" + ":" + "24pIlObiL14A";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        rd.lines().forEach(line -> result.append(line));
        System.out.println(result.toString());
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        ObjectMapper xmlMapper = new XmlMapper();
//        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\" " +
//                "standalone=\"yes\"?><subscription><status>active</status><statusChanged>2016-07-06T05:01:36.772Z</statusChanged><cancelable>true</cancelable><reference>MYC160706-4943-82180S</reference><test>true</test><referrer>RrkPtckCB4reZJxpm7NGPg==</referrer><customer><firstName>Nguyen Phuc</firstName><lastName>Hai</lastName><email>haiphucnguyen@gmail.com</email><phoneNumber>918734068</phoneNumber></customer><customerUrl>https://sites.fastspring.com/mycollab/order/s/MYC160706-4943-18185S</customerUrl><productName>Standard</productName><quantity>1</quantity><nextPeriodDate>2016-08-06Z</nextPeriodDate></subscription>";
//        Subscription value = xmlMapper.readValue(result.toString(), Subscription.class);
//        System.out.println(BeanUtility.printBeanObj(value));
    }
}
