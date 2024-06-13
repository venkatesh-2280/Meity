package cdfi.fintantra.meity.Pawhs.model;

public class RangeNameDao {

    private String rangeName;
    private int id;

    public RangeNameDao(String rangeName, int id) {
        this.rangeName = rangeName;
        this.id = id;
    }

    public RangeNameDao(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
