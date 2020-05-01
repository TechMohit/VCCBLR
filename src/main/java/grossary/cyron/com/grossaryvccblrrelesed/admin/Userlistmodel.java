package grossary.cyron.com.grossaryvccblrrelesed.admin;

class Userlistmodel {
private  String LoginId,UserName;

    public Userlistmodel(String loginId, String userName) {
        LoginId = loginId;
        UserName = userName;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
