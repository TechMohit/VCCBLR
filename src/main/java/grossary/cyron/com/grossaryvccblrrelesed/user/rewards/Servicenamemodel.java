package grossary.cyron.com.grossaryvccblrrelesed.user.rewards;

class Servicenamemodel {

    public Servicenamemodel(String rewardType, String rewardPoint) {
        RewardType = rewardType;
        RewardPoint = rewardPoint;
    }

    public String getRewardType() {
        return RewardType;
    }

    public void setRewardType(String rewardType) {
        RewardType = rewardType;
    }

    public String getRewardPoint() {
        return RewardPoint;
    }

    public void setRewardPoint(String rewardPoint) {
        RewardPoint = rewardPoint;
    }

    String RewardType;
    String RewardPoint;
}
