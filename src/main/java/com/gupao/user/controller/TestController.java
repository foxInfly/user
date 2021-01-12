package com.gupao.user.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TestController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * @description 获取服务实例列表
     * @author lp
     * @since 2021/1/12 17:50
     **/
    @RequestMapping("/test")
    public List<ServiceInstance> findServiceInstance(){
        //查询指定服务名称下的所有实例的信息
        List<ServiceInstance> list=this.discoveryClient.getInstances("order");
        ServiceInstance serviceInstance=list.get(0);
        URI uri = serviceInstance.getUri();
        System.out.println(uri.toString());
        this.testUrl(uri.toString());
        return list;
    }

    private void testUrl(String urlString){
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co =  url.openConnection();
            co.connect();
            System.out.println("连接可用");
        } catch (Exception e1) {

            System.out.println("连接打不开!");
        }
    }
}
