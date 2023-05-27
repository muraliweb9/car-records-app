package com.interview.carrecords.config.custom;

import com.ecwid.consul.v1.agent.model.NewService;

/** Needed to customise the ID used to register with Consul **/
public class NewService2 extends NewService {

    private transient String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "NewService{" + "id='"
                + getId() + '\'' + ", name='"
                + getName() + '\'' + ", tags="
                + getTags() + ", address='"
                + getAddress() + '\'' + ", ipAddress='"
                + getIpAddress() + '\'' + ", meta="
                + getMeta() + ", port="
                + getPort() + ", enableTagOverride="
                + getEnableTagOverride() + ", check="
                + getCheck() + ", checks="
                + getChecks() + '}';
    }
}
