package Radiacia.data;

/**
 * Created by Cntgfy on 16.08.2016.
 */
public class ConnectData extends ClientData {
    private long id;

    public ConnectData() {
        super();
    }

    public ConnectData(ConnectData cd) {
        super(cd);
        this.id = cd.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
