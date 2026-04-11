public class AdCampaign {
    private String client;
    private String adName;
    private String budget;

    public AdCampaign(String client, String adName, String budget) {
        this.client = client;
        this.adName = adName;
        this.budget = budget;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}