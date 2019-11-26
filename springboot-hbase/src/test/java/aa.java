import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class aa {
    private static final String INSERT_TABLES_SQL = "update ds_tables set status = 3 where schema_name = %s and table_name=%s;\n";


    public static Map<String, String> map = new HashMap<>();
    public static List<String> list = new ArrayList<>();

    public static void readFile(File file) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String line = null;
        List<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line.trim());
        }

        br.close();


        list.forEach(s -> {
            try {
                writeFile(new File("d:\\113menabletable.txt"), "select * from "+s + " limit 2;\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public static void writeFile(File file, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);
        osw.flush();
    }

    public static void appendFile(File file, String content) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(
                new FileOutputStream(file, true), // true to append
                "UTF-8"
        );
        out.write(content);
        out.close();
    }

    @Test
    public void aaaaaaaa() throws IOException {
        readFile(new File("d:\\113mtables.txt"));
    }

    @Test
    public void bbbbbbbbb() throws IOException {
        String sql = "select * from ds_tables  where table_name='%s' and schema_name = '%s';";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:\\1.txt")), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] split = line.split("\\.");
            if (split.length == 1) {
                writeFile(new File("d:\\tablename.txt"), String.format("select * from ds_tables  where table_name='%s' and schema_name is null;", split[0]) + "\n");
            } else {
                writeFile(new File("d:\\tablename.txt"), String.format(sql, split[1], split[0]) + "\n");
            }
        }
    }


    @Test
    public void cccccc() throws IOException {

        String a = "RAW.RELOAN_COUPON_RECOMMEND\n" +
                "RAW.RULE_CELLPHONE\n" +
                "RAW.OVERDUE_UID_FIRST_FOUND\n" +
                "RAW.RULE_CONTACT_CELLPHONE\n" +
                "RAW.RULE_ID_CARD\n" +
                "RAW.RULE_DEVICE_ID\n" +
                "RAW.GJJ_LOAN_PRE_CREDIT_LINE_MODEL\n" +
                "RAW.BLACKLIST_TOHBASE\n" +
                "RAW.FEATURE_PREDICT_OFFLINE\n" +
                "RAW.GJJ_LOAN_DEMAND_MODEL\n" +
                "RAW.GBOSSEDUCATION_STUDENT_INFO\n" +
                "RAW.NBK_ENTRY\n" +
                "RAW.NOE_BLACK_SINGLE\n" +
                "RAW.PBOC_QURY_DTL\n" +
                "RAW.NBK_USER_ENTRY\n" +
                "RAW.CSW_HUABEI\n" +
                "RAW.TBL_USER_ACCOUNT\n" +
                "RAW.TBL_IDENTITY_IDCARD_PHOTO\n" +
                "RAW.CREDIT_ACCOUNT\n" +
                "RAW.PBOC_OTHLN_DTL\n" +
                "RAW.PBOC_CREDIT_CARD\n" +
                "RAW.EMAIL_SENDER\n" +
                "RAW.TBL_IDENTITY_PEOPLE_PHOTO\n" +
                "TESLA.DEVICE_ADVERTISING_IP_AGENT\n" +
                "RAW.CREDIT_USER_INFO\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_BOOK_INFO_DD\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_CRDT_INFO_V2_DD\n" +
                "RAW.NOE_GREY_SINGLE\n" +
                "RAW.ATM_CREDIT_AMOUNT_IDENTITY\n" +
                "APOLLO.DWS_USER_CDT_CREDIT_INFO_DD\n" +
                "MODEL.XF_AID_CORR_DEVICECODE\n" +
                "PF.ADM_LOAN_BI_F2LMERCHANT_LOAN_INFO_DT\n" +
                "GOBLIN.APP_ACTIVE_STATIC\n" +
                "RAW.NOE_CUISHOU_PHONE\n" +
                "ZEUS.CREDITCARD_BILLS\n" +
                "RAW.LOAN_USER_BASIC_INFO\n" +
                "RAW.PBOC_INFO\n" +
                "TRIDENT.AD_FATIGUE_LAST_TIME\n" +
                "RAW.MY_POSITION\n" +
                "RAW.PBOC_CREDIT_INFO\n" +
                "ZEUS.USER_CREDITCARD_NET_ACCOUNT\n" +
                "RAW.P2P_BASIC_INFOMATION\n" +
                "ZEUS.USER_CREDITCARD_NET_LINE\n" +
                "RAW.LOAN_KDS_ORDER_INFO\n" +
                "ZEUS.MOB_NOCELL_LIST\n" +
                "RAW.ATM_WJF_LOAN\n" +
                "ZEUS.DEBIT_CARD_TRADE_FLOW\n" +
                "MODEL.XY_FIRST_BATCHES_OF_THE_WHITE_LIST_BY_TGT\n" +
                "ZEUS.CREDITCARD_JIZHANG_USER\n" +
                "RAW.ATM_PERSON_BASE_INFO\n" +
                "TESLA.USER_DEVICE_ADVERTISING\n" +
                "JZ.REALTIME_JZ_ADS_UNIT_IMPRESSION\n" +
                "ZEUS.CREDITCARD_ACCOUNTS\n" +
                "APOLLO.DWS_USER_CDT_CRTCARD_TRADEINFO_DD\n" +
                "ZEUS.APP_CATEGORY\n" +
                "MODEL.APP_INFORMATION\n" +
                "RAW.APPROVE_CORP_INFO\n" +
                "LOAN_NORMANDY.NS_APPLY_CHANNEL\n" +
                "CREDITTOOL.POP_WINDOW_USER_DETAIL\n" +
                "GALAXY.GJJ_ACCOUNTS\n" +
                "RAW.ATM_PERSON_WORK_INFO\n" +
                "RAW.PBOC_HSLN_DTL\n" +
                "MODEL.APPLIST_RESULT_CATEGORY_MERGE\n" +
                "CREDITTOOL.BONUS_POINTS_BEHAVIOR\n" +
                "PF.ADM_LOAN_BI_FINUSER_EMERGENCY_CONTACT_DT\n" +
                "MODEL.CORPORATION_NUMBER\n" +
                "TESLA.GOVERNMENT_ANSWERS\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_PHONE_INFO\n" +
                "WJZ.ADM_PFM_WJZ_GUESSTEXT\n" +
                "ZEUS.RISK_EVALUATE_LIST\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_CONSIGNEE_INFO\n" +
                "GALAXY.SPECIAL_GROUP_PHONE\n" +
                "JZ.REALTIME_JZ_ADS_UNIT_CLICK\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_BLACK_LIST_INFO_DD\n" +
                "GOBLIN.CELL_CONTACT_CALL_STAT\n" +
                "PF.ADM_LOAN_BI_FINUSER_CONTACTS_DT\n" +
                "RAW.NOE_FP_SINGLE\n" +
                "MODEL.DEEP_KEYWORDS\n" +
                "MODEL.APP_SYSTEM\n" +
                "ZEUS.DEBIT_CARD_TRADE_ACCOUNT\n" +
                "CREDITCARD_PRIVATE.ADM_AD_M_MODEL_LOAN_API_ALGORITHM_PRODUCT_FEATURE_DD\n" +
                "JZ.REALTIME_JZ_API_ORDER_STATUS\n" +
                "ZMXY.WATCH_LISTII_MAPPING\n" +
                "ZEUS.ECOMMERCE_TRADE_FLOWS\n" +
                "ZEUS.ECOMMERCE_ACCOUNTS\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_PROVINCE_MS_OUTPUT\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_MS_OUTPUT\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_INCOME_MS_OUTPUT\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_DEPOSIT_MS_OUTPUT\n" +
                "RAW.KGJ_LOW_RISK_RECALL_UID_LAST_NEW\n" +
                "RAW.ASSETS_SOCIAL_SECURITY_ORG_LIST\n" +
                "MODEL.IDNO_ADDRESS\n" +
                "MODEL.FUXIANG_BLACKLIST_SCHEMA_CLEAN\n" +
                "MODEL.DIMKEYWORDS\n" +
                "MODEL.CITY_INDEX\n" +
                "MODEL.APP_DOWNLOAD_TOP100\n" +
                "GALAXY.ECOMMERCE_TRADE_FLOWS\n" +
                "GALAXY.ECOMMERCE_ADDRESSES\n" +
                "GALAXY.ECOMMERCE_ACCOUNTS\n" +
                "RONG360.NBK_ENTRY\n" +
                "RONG360.CSW_HUABEI\n";

        String b = "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_BOOK_INFO_DD\n" +
                "RAW.FEATURE_PREDICT_OFFLINE\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_CRDT_INFO_V2_DD\n" +
                "RAW.GJJ_LOAN_DEMAND_MODEL\n" +
                "RAW.NOE_GREY_SINGLE\n" +
                "TESLA.GOVERNMENT_META\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_SCORE_MS_OUTPUT\n" +
                "RAW.GJJ_LOAN_PRE_CREDIT_LINE_MODEL\n" +
                "RAW.ATM_CREDIT_AMOUNT_IDENTITY\n" +
                "APOLLO.DWS_USER_CDT_CREDIT_INFO_DD\n" +
                "MODEL.XF_AID_CORR_DEVICECODE\n" +
                "PF.ADM_LOAN_BI_F2LMERCHANT_LOAN_INFO_DT\n" +
                "GOBLIN.APP_ACTIVE_STATIC\n" +
                "RAW.NOE_CUISHOU_PHONE\n" +
                "ZEUS.CREDITCARD_BILLS\n" +
                "RAW.LOAN_USER_BASIC_INFO\n" +
                "RAW.PBOC_INFO\n" +
                "TRIDENT.AD_FATIGUE_LAST_TIME\n" +
                "RAW.MY_POSITION\n" +
                "RAW.PBOC_CREDIT_INFO\n" +
                "ZEUS.USER_CREDITCARD_NET_ACCOUNT\n" +
                "RAW.P2P_BASIC_INFOMATION\n" +
                "ZEUS.USER_CREDITCARD_NET_LINE\n" +
                "RAW.LOAN_KDS_ORDER_INFO\n" +
                "ZEUS.MOB_NOCELL_LIST\n" +
                "RAW.ATM_WJF_LOAN\n" +
                "ZEUS.DEBIT_CARD_TRADE_FLOW\n" +
                "MODEL.XY_FIRST_BATCHES_OF_THE_WHITE_LIST_BY_TGT\n" +
                "ZEUS.CREDITCARD_JIZHANG_USER\n" +
                "RAW.ATM_PERSON_BASE_INFO\n" +
                "RAW.RELOAN_COUPON_RECOMMEND\n" +
                "RAW.RULE_CELLPHONE\n" +
                "TESLA.USER_DEVICE_ADVERTISING\n" +
                "JZ.REALTIME_JZ_ADS_UNIT_IMPRESSION\n" +
                "RAW.OVERDUE_UID_FIRST_FOUND\n" +
                "RAW.RULE_CONTACT_CELLPHONE\n" +
                "ZEUS.CREDITCARD_ACCOUNTS\n" +
                "APOLLO.DWS_USER_CDT_CRTCARD_TRADEINFO_DD\n" +
                "ZEUS.APP_CATEGORY\n" +
                "MODEL.APP_INFORMATION\n" +
                "RAW.APPROVE_CORP_INFO\n" +
                "LOAN_NORMANDY.NS_APPLY_CHANNEL\n" +
                "CREDITTOOL.POP_WINDOW_USER_DETAIL\n" +
                "GALAXY.GJJ_ACCOUNTS\n" +
                "RAW.RULE_ID_CARD\n" +
                "RAW.ATM_PERSON_WORK_INFO\n" +
                "RAW.RULE_DEVICE_ID\n" +
                "RAW.PBOC_HSLN_DTL\n" +
                "RONG360.NBK_ENTRY\n" +
                "RONG360.CSW_HUABEI\n" +
                "MODEL.APPLIST_RESULT_CATEGORY_MERGE\n" +
                "CREDITTOOL.BONUS_POINTS_BEHAVIOR\n" +
                "PF.ADM_LOAN_BI_FINUSER_EMERGENCY_CONTACT_DT\n" +
                "MODEL.CORPORATION_NUMBER\n" +
                "TESLA.GOVERNMENT_ANSWERS\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_PHONE_INFO\n" +
                "WJZ.ADM_PFM_WJZ_GUESSTEXT\n" +
                "ZEUS.RISK_EVALUATE_LIST\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_CONSIGNEE_INFO\n" +
                "GALAXY.SPECIAL_GROUP_PHONE\n" +
                "JZ.REALTIME_JZ_ADS_UNIT_CLICK\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_OVER_BUDGET_MS_OUTPUT\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_BLACK_LIST_INFO_DD\n" +
                "RAW.GBOSSEDUCATION_STUDENT_INFO\n" +
                "GOBLIN.CELL_CONTACT_CALL_STAT\n" +
                "PF.ADM_LOAN_BI_FINUSER_CONTACTS_DT\n" +
                "RAW.NOE_FP_SINGLE\n" +
                "MODEL.DEEP_KEYWORDS\n" +
                "RAW.BLACKLIST_TOHBASE\n" +
                "MODEL.APP_SYSTEM\n" +
                "ZEUS.DEBIT_CARD_TRADE_ACCOUNT\n" +
                "JZ.REALTIME_JZ_API_ORDER_STATUS\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_PROVINCE_MS_OUTPUT\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_DEPOSIT_MS_OUTPUT\n" +
                "ZEUS.ECOMMERCE_TRADE_FLOWS\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_INCOME_MS_OUTPUT\n" +
                "ZEUS.ECOMMERCE_ACCOUNTS\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_MS_OUTPUT\n" +
                "MODEL.FUXIANG_BLACKLIST_SCHEMA_CLEAN\n" +
                "RAW.KGJ_LOW_RISK_RECALL_UID_LAST_NEW\n" +
                "MODEL.APP_DOWNLOAD_TOP100\n" +
                "RAW.ASSETS_SOCIAL_SECURITY_ORG_LIST\n" +
                "MODEL.DIMKEYWORDS\n" +
                "MODEL.CITY_INDEX\n" +
                "MODEL.IDNO_ADDRESS\n" +
                "GALAXY.ECOMMERCE_ADDRESSES\n" +
                "ZMXY.WATCH_LISTII_MAPPING\n" +
                "GALAXY.ECOMMERCE_ACCOUNTS\n" +
                "GALAXY.ECOMMERCE_TRADE_FLOWS\n" +
                "RAW.NBK_ENTRY\n" +
                "RAW.NOE_BLACK_SINGLE\n" +
                "RAW.PBOC_QURY_DTL\n" +
                "RAW.NBK_USER_ENTRY\n" +
                "RAW.CSW_HUABEI\n" +
                "RAW.TBL_USER_ACCOUNT\n" +
                "RAW.TBL_IDENTITY_IDCARD_PHOTO\n" +
                "RAW.CREDIT_ACCOUNT\n" +
                "RAW.PBOC_OTHLN_DTL\n" +
                "RAW.PBOC_CREDIT_CARD\n" +
                "RAW.EMAIL_SENDER\n" +
                "RAW.TBL_IDENTITY_PEOPLE_PHOTO\n" +
                "TESLA.DEVICE_ADVERTISING_IP_AGENT\n" +
                "RAW.CREDIT_USER_INFO\n" +
                "CREDITCARD_PRIVATE.ADM_AD_M_MODEL_LOAN_API_ALGORITHM_PRODUCT_FEATURE_DD";


        String[] splita = a.split("\\n");
        String[] splitb = b.split("\\n");

        List<String> lista = Arrays.asList(splita);
        List<String> listb = Arrays.asList(splitb);

        listb.forEach(s -> {
            if(!lista.contains(s)){
                System.out.println(s);
            }
        });

    }
}
