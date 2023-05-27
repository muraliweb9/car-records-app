package com.interview.carrecords.config.custom;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Temporary work around for:
 * <p>
 * https://github.com/yidongnan/grpc-spring-boot-starter/pull/775
 */
@Configuration
@ImportAutoConfiguration({
    net.devh.boot.grpc.common.autoconfigure.GrpcCommonCodecAutoConfiguration.class,
    net.devh.boot.grpc.common.autoconfigure.GrpcCommonTraceAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcAdviceAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcHealthServiceAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcMetadataConsulConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcMetadataEurekaConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcMetadataNacosConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcMetadataZookeeperConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcReflectionServiceAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcServerFactoryAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcServerMetricAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcServerSecurityAutoConfiguration.class,
    net.devh.boot.grpc.server.autoconfigure.GrpcServerTraceAutoConfiguration.class
})
class GrpcStarterConfig {}