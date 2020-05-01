package grossary.cyron.com.grossaryvccblrrelesed.utility;

public class Constant {

    public static boolean rolecode = false ;
    public static boolean adminuser = false ;


    public static class URL
    {
       public static final String BASE_URL = "http://Mobileapi.vccb2b.com/api";
      //  public static final String BASE_URL = "http://demo.mobileapi.vccb2b.com/api";
    }

    public interface CONSTANT{
        String CHECKOUT="Checkout";
        String PLACE_YOUR_ORDER="Place Your Order";
        String MAKE_PAYMENT="Confirm Order";
        String MAKE_PAYMENT_ONLINE="C";
        String LOGIN_ROLE_CODE_BUYER="PRT";
        String AUCTION_PRODUCTS="AUCTION PRODUCTS";
    }
    public interface KEY_NAME{
        String FRAG_PARAMETER="FRAG_PARAMETER";
        String ACT_HOME_PARAMETER="ACT_HOME_PARAMETER";
        String CURRENT_FRG="CURRENT_FRG";

    }
    public interface ADMIN_KEY_NAME{
        String ACT_ORDER_DETAIL="ACT_ORDER_DETAIL";
        String ACT_STOCK_DETAIL="ACT_STOCK_DETAIL";
        String CURRENT_ACT="CURRENT_ACT";
        String ADMIN_RECONDETAILS="ADMIN_RECONDETAILS";
        String ADMIN_PRE_ORDER="ADMIN_PRE_ORDER";

    }

    public interface CURRENT_STATE{
        String HOME_FRG="HOME_FRG";
        String OFFER_FRG="OFFER_FRG";
        String BRAND_FRG="BRAND_FRG";
        String SELLER_FRG="SELLER_FRG";
        String CATG_LIST_FRG="CATG_LIST_FRG";
        String VIEW_CART_FRG="VIEW_CART_FRG";
        String MY_ORDER_FRG="MY_ORDER_FRG";
        String MY_REWARD_FRG="MY_REWARD_FRG";
        String MY_PENDINGPAYMENT_FRG="MY_PENDINGPAYMENT_FRG";
        String ADDRESS_FRG="ADDRESS_FRG";
        String SEARCH_FRG="SEARCH_FRG";
        String MY_ORDER_DETAIL_FRG="MY_ORDER_DETAIL_FRG";

    }
    public interface NAV_DRAWER{
        String MY_HOME="HOME";
        String MY_PROFILE="MY_PROFILE";
        String MY_ORDER = "MY_ORDER";
        String MY_REWARDS = "MY_REWARDS";
        String PENDING_PAYMENT = "PENDING_PAYMENT";
        String ZERO_STOCK = "ZERO STOCK DETAILS";
        String PRIVICY="PRIVICY";
        String ABOUT="ABOUT US";
        String LOADUSER="Place Order";
        String PAYMENTCOLLECTION="Payment Collection";
        String LOG_OUT = "LOG_OUT";
    }

    public interface TABS{
        String HOME="HOME";
        String OFFER="OFFER";
        String SELLER="SELLER";
        String PRICERANGE="PRICERANGE";
        String BRANDS="BRANDS";
    }

    public interface ADMIN_TABS{
        String HOME="HOME";
        String OFFER="OFFER";
        String SELLER="SELLER";
        String PRE_ORDER="PAYMENTS";
        String AUCTION="AUCTION";
    }

    public interface CATEGORY{
        String VIEW_CART="VIEW_CART";
        String SUB_LIST="Sub_Category_list";
        String LIST="Category_list";
        String LIST_DETAILS="Category_list_details";
        String ADD="ADD";
        String ADD_TO_CART="ADD_TO_CART";
        String MIN="MIN";
        String UPDATE_DISCOUNT="UPDATE_DISCOUNT";
        String UPDATE_ORDER_TYPE="UPDATE_ORDER_TYPE";
        String UPDATE_REMARK="UPDATE_REMARK";

        String ONCLICK="ONCLICK";
        String DELETE="DELETE";
        String ORDER="ORDER";
        String ORDER_DETAIL="ORDER_DETAIL";
        String ADDRESS="ADDRESS";

    }

}
