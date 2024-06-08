package wushi.cn.rule;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreeTimeLoadBalancer implements ReactorServiceInstanceLoadBalancer {
    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    private final String serviceId;
    private final AtomicInteger instanceCallCount = new AtomicInteger(0); // 记录同一实例的调用次数
    private final AtomicInteger instanceIndex = new AtomicInteger(0); // 当前提供服务的实例索引

    public ThreeTimeLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = this.serviceInstanceListSupplierProvider.getIfAvailable();
        return supplier.get().next().map(this::getInstanceResponse);
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instancesList) {
        if (instancesList.isEmpty()) {
            return new EmptyResponse();
        }

        // 获取当前的实例索引
        int currentIndex = instanceIndex.get() % instancesList.size();
        ServiceInstance instance = instancesList.get(currentIndex);

        // 更新调用次数和实例索引
        if (instanceCallCount.incrementAndGet() >= 3) {
            instanceCallCount.set(0);
            instanceIndex.incrementAndGet();
        }

        return new DefaultResponse(instance);
    }
}
