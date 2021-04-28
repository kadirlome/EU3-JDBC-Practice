package apitests.Pojo;

public class RegionPost {

    private int region_id;
    private String region_nae;

    public RegionPost(){}

    public RegionPost(int region_id, String region_nae) {
        this.region_id = region_id;
        this.region_nae = region_nae;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_nae() {
        return region_nae;
    }

    public void setRegion_nae(String region_nae) {
        this.region_nae = region_nae;
    }
}
