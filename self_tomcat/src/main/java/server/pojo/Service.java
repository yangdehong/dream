package server.pojo;

import java.util.List;

public class Service {

    private List<Connector> connectorList;

    private Engine engine;

    public Service(List<Connector> connectorList, Engine engine) {
        this.connectorList = connectorList;
        this.engine = engine;
    }

    public List<Connector> getConnectorList() {
        return connectorList;
    }

    public void setConnectorList(List<Connector> connectorList) {
        this.connectorList = connectorList;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
