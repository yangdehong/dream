package server.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private List<Service> serviceList;

    private Map<String, String> url2AppBase;

    public Mapper() {
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Map<String, String> getUrl2AppBase() {
        return url2AppBase;
    }

    public void setUrl2AppBase(Map<String, String> url2AppBase) {
        this.url2AppBase = url2AppBase;
    }
}
