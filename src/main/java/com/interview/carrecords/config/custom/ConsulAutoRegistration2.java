package com.interview.carrecords.config.custom;

import com.ecwid.consul.v1.agent.model.NewService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulManagementRegistrationCustomizer;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

public class ConsulAutoRegistration2 extends ConsulAutoRegistration {
    public ConsulAutoRegistration2(
            NewService service,
            AutoServiceRegistrationProperties autoServiceRegistrationProperties,
            ConsulDiscoveryProperties properties,
            ApplicationContext context,
            HeartbeatProperties heartbeatProperties,
            List<ConsulManagementRegistrationCustomizer> managementRegistrationCustomizers) {
        super(
                service,
                autoServiceRegistrationProperties,
                properties,
                context,
                heartbeatProperties,
                managementRegistrationCustomizers);
    }

    public static ConsulAutoRegistration registration(
            AutoServiceRegistrationProperties autoServiceRegistrationProperties,
            ConsulDiscoveryProperties properties,
            ApplicationContext context,
            List<ConsulRegistrationCustomizer> registrationCustomizers,
            List<ConsulManagementRegistrationCustomizer> managementRegistrationCustomizers,
            HeartbeatProperties heartbeatProperties) {

        NewService2 service = new NewService2();
        String appName = getAppName(properties, context.getEnvironment());
        service.setId(getInstanceId(properties, context));
        if (!properties.isPreferAgentAddress()) {
            service.setAddress(properties.getHostname());
        }
        service.setIpAddress(properties.getIpAddress());
        service.setName(normalizeForDns(appName));
        service.setTags(new ArrayList<>(properties.getTags()));
        service.setEnableTagOverride(properties.getEnableTagOverride());
        service.setMeta(getMetadata(properties));

        if (properties.getPort() != null) {
            service.setPort(properties.getPort());
            // we know the port and can set the check
            setCheck(service, autoServiceRegistrationProperties, properties, context, heartbeatProperties);
        }

        ConsulAutoRegistration2 registration = new ConsulAutoRegistration2(
                service,
                autoServiceRegistrationProperties,
                properties,
                context,
                heartbeatProperties,
                managementRegistrationCustomizers);
        customize(registrationCustomizers, registration);
        return registration;
    }

    private static Map<String, String> getMetadata(ConsulDiscoveryProperties properties) {
        LinkedHashMap<String, String> metadata = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(properties.getMetadata())) {
            metadata.putAll(properties.getMetadata());
        }

        // add metadata from other properties. See createTags above.
        if (!ObjectUtils.isEmpty(properties.getInstanceZone())) {
            metadata.put(properties.getDefaultZoneMetadataName(), properties.getInstanceZone());
        }
        if (!ObjectUtils.isEmpty(properties.getInstanceGroup())) {
            metadata.put("group", properties.getInstanceGroup());
        }

        // store the secure flag in the tags so that clients will be able to figure
        // out whether to use http or https automatically
        metadata.put("secure", Boolean.toString(properties.getScheme().equalsIgnoreCase("https")));

        return metadata;
    }

    @Override
    public void initializePort(int knownPort) {
        super.initializePort(knownPort);
        getService().setId(getCustomId());
    }

    private String getCustomId() {
        NewService2 service = (NewService2) getService();
        String name = service.getName();
        Integer tcp = service.getPort();
        Integer grpc = Integer.valueOf(service.getMeta().get("gRPC_port"));
        String address = service.getAddress();
        String ipAddress = service.getIpAddress();
        return name + "|" + address + "(" + ipAddress + ")" + "|" + tcp + "(web)" + "|" + grpc + "(gRPC)";
    }
}
