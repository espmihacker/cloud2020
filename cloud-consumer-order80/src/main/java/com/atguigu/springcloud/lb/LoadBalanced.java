package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {

    // List<String> services = discoveryClient.getServices();

    ServiceInstance instance(List<ServiceInstance>serviceInstances);

}
