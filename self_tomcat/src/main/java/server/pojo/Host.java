package server.pojo;

public class Host {

    private String localhost;

    private String appBase;

    public Host(String localhost, String appBase) {
        this.localhost = localhost;
        this.appBase = appBase;
    }

    public String getLocalhost() {
        return localhost;
    }

    public void setLocalhost(String localhost) {
        this.localhost = localhost;
    }

    public String getAppBase() {
        return appBase;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }
}
