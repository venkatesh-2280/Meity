package cdfi.fintantra.meity.Pawhs.model;

public class LsCostDao {

    private String costId;
    private String costName;

    public LsCostDao(String costId, String costName) {
        this.costId = costId;
        this.costName = costName;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }
}
