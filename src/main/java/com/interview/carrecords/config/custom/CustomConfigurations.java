package com.interview.carrecords.config.custom;

import java.util.List;
import net.devh.boot.grpc.server.config.GrpcServerProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulManagementRegistrationCustomizer;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;
import org.springframework.cloud.function.utils.SocketUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfigurations {

    @Bean
    public GrpcServerProperties customGrpcServerProperties() {
        return new GrpcServerProperties() {
            private int port = 9090;

            @Override
            public int getPort() {
                if (this.port == 0) {
                    this.port = SocketUtils.findAvailableTcpPort();
                }
                return this.port;
            }

            public void setPort(int port) {
                super.setPort(port);
                this.port = port;
            }
        };
    }

    @Bean
    public ConsulAutoRegistration customConsulAutoRegistration(
            AutoServiceRegistrationProperties autoServiceRegistrationProperties,
            ConsulDiscoveryProperties properties,
            ApplicationContext applicationContext,
            ObjectProvider<List<ConsulRegistrationCustomizer>> registrationCustomizers,
            ObjectProvider<List<ConsulManagementRegistrationCustomizer>> managementRegistrationCustomizers,
            HeartbeatProperties heartbeatProperties) {

        return ConsulAutoRegistration2.registration(
                autoServiceRegistrationProperties,
                properties,
                applicationContext,
                registrationCustomizers.getIfAvailable(),
                managementRegistrationCustomizers.getIfAvailable(),
                heartbeatProperties);
    }
}
