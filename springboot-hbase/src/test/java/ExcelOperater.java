import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Boolean;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ExcelOperater {

    public static void main(String[] args) throws IOException, BiffException, WriteException {
//        jxl.Workbook readwb = null;
//        try {
//            //构建Workbook对象, 只读Workbook对象
//            //直接从本地文件创建Workbook
//            InputStream instream = new FileInputStream("d:/表梳理.xls");
//            readwb = Workbook.getWorkbook(instream);
//            Sheet readsheet = readwb.getSheet(0);
//            //获取Sheet表中所包含的总列数
//            int rsColumns = readsheet.getColumns();
//            //获取Sheet表中所包含的总行数
//            int rsRows = readsheet.getRows();
//            //获取指定单元格的对象引用
//            for (int i = 1; i < rsRows; i++) {
//                for (int j = 0; j < rsColumns; j++) {
//                    Cell cell = readsheet.getCell(j, i);
//                    System.out.print(cell.getContents() + " ");
//                }
//                System.out.println();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            readwb.close();
//        }

        String count = "ADM_LOAN_RISK.ANTI_ADDRESS_GPS_UID_IDNO_CNT                                       0\n" +
                "ADM_LOAN_RISK.DOUDI_DEVICE_LAST_180DAY_CNT                                        0\n" +
                "ADM_LOAN_RISK.DOUDI_GPS_LAST_180DAY_CNT                                           0\n" +
                "ADM_LOAN_RISK.DOUDI_GPS_LAST_30DAY                                                0\n" +
                "ADM_LOAN_RISK.DOUDI_WIFI_LAST_180DAY_CNT                                          0\n" +
                "ALGO.XX_XQFCALLTEST_TOCALLER_REG_RES                                              0\n" +
                "ALGO.XX_XQFCALLTEST_TOCALLER_REG_RES_V2                                           0\n" +
                "APOLLO.DWS_USER_CDT_CREDIT_INFO_DD                                             5727\n" +
                "APOLLO.DWS_USER_CDT_CRTCARD_TRADEINFO_DD                                       5700\n" +
                "ATM.USER_TAG                                                                     76\n" +
                "BKK.DWS_USER_BEV_PFM_BKK_USER_INFO_DD                                          1922\n" +
                "BKK.MODEL_INVOKE_RESULT                                                           0\n" +
                "BULLSEYE.ADM_COMMON_ACTIVE_FIRSTIMEI_FEEDBACK                                  7114\n" +
                "BULLSEYE.BULLSEYE_GATEWAY_H5_CLICK_DATA                                        3140\n" +
                "BULLSEYE.BULLSEYE_H5_DATA                                                     11357\n" +
                "BULLSEYE.BULLSEYE_REPORT_RESULT                                               13408\n" +
                "CREDITCARD_PRIVATE.ADM_AD_M_MODEL_LOAN_API_ALGORITHM_PRODUCT_FEATURE_DD           0\n" +
                "CREDITCARD_PRIVATE.ADM_AD_M_MODEL_LOAN_CTR_LOAN_USER_FEATURE_DS                   0\n" +
                "CREDITCARD_PRIVATE.ADM_ALGO_FLJ_SOCIAL_NETWORK_1DU_FEATURE_2HBASE_WD_DT           0\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_MBR_PTR_CREDIT_DS                                    0\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_MBR_PTR_WEALTH_DS                                    0\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_BLACK_LIST_INFO_DD                       24422\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_BOOK_INFO_DD                             23956\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_CARD_INFO_DD                             25145\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_CRDT_INFO_DD                              1050\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_CRDT_INFO_V2_DD                          24094\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_FIN_INFO_DD                              24050\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_LOAN_INFO_DD                             23821\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_SOCIAL_INFO_DD                            1012\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_SOCIAL_INFO_V2_DD                        23793\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SCE_USR_WJF_INFO_DD                              25311\n" +
                "CREDITCARD_PRIVATE.ADM_CRT_A_SOCIAL_USER_TAG_DD                                   0\n" +
                "CREDITCARD_PRIVATE.BASE_INFO                                                      0\n" +
                "CREDITCARD_PRIVATE.BBS_ARTICLE                                                  247\n" +
                "CREDITCARD_PRIVATE.CREDITCARD_DEV_BAS_INF                                         0\n" +
                "CREDITCARD_PRIVATE.CREDITCARD_USER_PORTRAIT_BASE                                  0\n" +
                "CREDITCARD_PRIVATE.CREDITCARD_USER_PORTRAIT_BEHAVIOR                              0\n" +
                "CREDITCARD_PRIVATE.CREDITCARD_USER_PORTRAIT_BRL_CLICK                             0\n" +
                "CREDITCARD_PRIVATE.CREDITCARD_USER_PORTRAIT_MKT_REMIND                            0\n" +
                "CREDITCARD_PRIVATE.OPERATOR_REPORT_DATA                                           0\n" +
                "CREDITTOOL.BONUS_POINTS_BEHAVIOR                                               1134\n" +
                "CREDITTOOL.MIDDLEWARE_TRANSACTION_MESSAGE                                         0\n" +
                "CREDITTOOL.POP_WINDOW_USER_DETAIL                                             66002\n" +
                "CREDITTOOL.USER_AUTH_INFO_LOG                                                     0\n" +
                "CREDITTOOL.USER_BEHAVIOR_INFO_LABEL                                           21454\n" +
                "DEEPBLUE.CELL_CONTACT_CALL_STAT_CACHE                                          2818\n" +
                "DE_TRACE_STAT                                                                     0\n" +
                "DE_TRACE_STRATEGY                                                             78156\n" +
                "DPN.DPN_LOAN_SMS                                                                  0\n" +
                "DWS.DEVICEID_PK_LAST_365DAY_RELATE_CNT                                            0\n" +
                "DWS.DWS_USER_BEV_LOAN_BASIC_INFO_DD                                               0\n" +
                "DWS.DWS_USER_BEV_PFM_BKK_USER_INFO_DD                                             0\n" +
                "DWS.DWS_USER_BEV_REG_INFO_DD                                                      0\n" +
                "DWS.DWS_USER_BEV_SSA_APP_DD                                                       0\n" +
                "DWS.DWS_USER_IDT_BASE_INFO_DD                                                     0\n" +
                "DWS.DWS_USER_POS_INFO_DD                                                          0\n" +
                "DWS.UID_RELATION_WITH_PHONE                                                       0\n" +
                "GALAXY.CELL_BASIC                                                                 0\n" +
                "GALAXY.CELL_BILL                                                                  0\n" +
                "GALAXY.ECOMMERCE_ACCOUNTS                                                         0\n" +
                "GALAXY.ECOMMERCE_ADDRESSES                                                        0\n" +
                "GALAXY.ECOMMERCE_TRADE_FLOWS                                                      0\n" +
                "GALAXY.GJJ_ACCOUNTS                                                               0\n" +
                "GALAXY.GJJ_ACCOUNT_FLOWS                                                          0\n" +
                "GALAXY.LOAN_APP_CLIENT                                                        10163\n" +
                "GALAXY.LOAN_APP_LIST                                                            130\n" +
                "GALAXY.LOAN_CALL_RECORD                                                           1\n" +
                "GALAXY.LOAN_CONSUMER_CONTACT                                                      0\n" +
                "GALAXY.SPECIAL_GROUP_PHONE                                                     2793\n" +
                "GALAXY.USER_CELL_NETS                                                           182\n" +
                "GALAXY.USER_CELL_SMSES                                                          180\n" +
                "GALAXY.USER_CREDITCARD_NET_ACCOUNT                                                0\n" +
                "GALAXY.USER_CREDITCARD_NET_BILL                                                   0\n" +
                "GALAXY.USER_CREDITCARD_NET_CASHFLOW                                               0\n" +
                "GALAXY.USER_CREDITCARD_NET_LINE                                                   0\n" +
                "GALAXY.USER_CREDITCARD_NET_PAYMENT                                                0\n" +
                "GALAXY.USER_GJJ_FLOWS                                                         23714\n" +
                "GALAXY.USER_GJJ_LOAN_FLOWS                                                        0\n" +
                "GALAXY.USER_LOAN_CALL                                                            43\n" +
                "GALAXY.USER_LOAN_CONSUMER_CONTACT                                             90694\n" +
                "GALAXY.USER_LOAN_SMS                                                             39\n" +
                "GJJ.GJJ_ACCOUNTS                                                                 91\n" +
                "GJJ.GJJ_ACCOUNT_FLOWS                                                             0\n" +
                "GOBLIN.APP_ACTIVE_STATIC                                                     241064\n" +
                "GOBLIN.CALL_RECORD_COLLECTION                                                     0\n" +
                "GOBLIN.CASE_ASSIGNMENT_FACTOR_VALUES                                              0\n" +
                "GOBLIN.CELL_CALL                                                                  1\n" +
                "GOBLIN.CELL_CONTACT_CALL_STAT                                                     0\n" +
                "GOBLIN.EVENT_TRACK                                                            75352\n" +
                "GOBLIN.LOAN_CONSUMER_CONTACT                                                   6829\n" +
                "GOBLIN.LOAN_SCORE                                                            194990\n" +
                "GRAPH.USER_CONTACT_SIMHASH                                                     2832\n" +
                "GROWTH.OVERTIME_PROFILE                                                         848\n" +
                "GROWTH.PROFILE_RECORD                                                         18665\n" +
                "GROWTH.QUOTA_COUPON_CLEARING_RECORD                                            1350\n" +
                "GROWTH.QUOTA_COUPON_DEFINE_RECORD                                              7598\n" +
                "GROWTH.REALTIME_PROFILE                                                         132\n" +
                "GROWTH.RECEIPT_RECORD                                                             0\n" +
                "GROWTH.SENDING_RECORD                                                         11779\n" +
                "JIELEMA.CELL_BASIC                                                                0\n" +
                "JIELEMA.CELL_BILL                                                                 0\n" +
                "JIELEMA.CSW_ACCOUNTS                                                              0\n" +
                "JIELEMA.CSW_CASHFLOW                                                              0\n" +
                "JLM.CELL_CALL                                                                     0\n" +
                "JZ.REALTIME_JZ_ADS_UNIT_CLICK                                                 32790\n" +
                "JZ.REALTIME_JZ_ADS_UNIT_IMPRESSION                                            32856\n" +
                "JZ.REALTIME_JZ_API_ORDER_STATUS                                                   0\n" +
                "KALEIDOSCOPE.LOAN_APP_CLIENT                                                 198513\n" +
                "KALEIDOSCOPE.LOAN_CONSUMER_CONTACT                                            13062\n" +
                "KALEIDOSCOPE.LOAN_USER_PERSON_DATA                                           437632\n" +
                "KALEIDOSCOPE.XDATA                                                           215709\n" +
                "LOAN_NORMANDY.NS_APPLY_CHANNEL                                                  516\n" +
                "LOAN_ORIGINAL_DATA                                                              499\n" +
                "MISSILE.KAFKA_OFFSET                                                              0\n" +
                "MISSILE.LOAN_USER_COMMON                                                          0\n" +
                "MISSILE.LOAN_USER_CORE                                                            0\n" +
                "MISSILE.MISSILE_UID_SEARCH                                                        0\n" +
                "MISSILE.MISSILE_UID_USERGROUP                                                     0\n" +
                "MISSILE.USER_COMMON                                                               0\n" +
                "MISSILE.USER_CORE                                                                 0\n" +
                "MODEL.APPLIST_RESULT_CATEGORY_MERGE                                            5462\n" +
                "MODEL.APP_DOWNLOAD_TOP100                                                      5633\n" +
                "MODEL.APP_INFORMATION                                                         40199\n" +
                "MODEL.APP_RISK_STAT                                                            5507\n" +
                "MODEL.APP_SYSTEM                                                              11100\n" +
                "MODEL.BUS_THIRD_RELATION                                                          0\n" +
                "MODEL.CITY_INDEX                                                              53623\n" +
                "MODEL.CORPORATION_NUMBER                                                       2503\n" +
                "MODEL.CORPORATION_NUMBER_VECTOR                                                   2\n" +
                "MODEL.CSAI_PHONE_RANKING_SCORE                                                 2454\n" +
                "MODEL.DEEPBLUE_REPLAY                                                           373\n" +
                "MODEL.DEEP_KEYWORDS                                                              28\n" +
                "MODEL.DIMKEYWORDS                                                             31122\n" +
                "MODEL.FUXIANG_BLACKLIST_SCHEMA_CLEAN                                              2\n" +
                "MODEL.IDNO_ADDRESS                                                           110259\n" +
                "MODEL.KEYWORDS                                                                    0\n" +
                "MODEL.LIQUIDATE_ENGINE_LABEL                                                  24329\n" +
                "MODEL.LOAN_CALL_RECORD                                                            3\n" +
                "MODEL.LOAN_SMS                                                                 1521\n" +
                "MODEL.MODEL_MSG_PHONE_LIB                                                      4049\n" +
                "MODEL.THIRD_PARTY_KV                                                         460922\n" +
                "MODEL.XF_AID_CORR_DEVICECODE                                                    539\n" +
                "MODEL.XY_FIRST_BATCHES_OF_THE_WHITE_LIST_BY_TGT                               39569\n" +
                "PF.ADM_LOAN_BI_F2LMERCHANT_LOAN_INFO_DT                                         260\n" +
                "PF.ADM_LOAN_BI_FINUSER_CONTACTS_DT                                               54\n" +
                "PF.ADM_LOAN_BI_FINUSER_EMERGENCY_CONTACT_DT                                      65\n" +
                "PRECREDIT.USER_CREDIT                                                            23\n" +
                "PRECREDIT.USER_CREDIT_RECORD                                                      0\n" +
                "RAW.ACCT_DEDUCT                                                                3019\n" +
                "RAW.ADM_COMMON_ACTIVE_FIRSTIMEI                                                7802\n" +
                "RAW.ADM_PFM_SSA_USER_SEX_DD                                                       0\n" +
                "RAW.APPROVE_APPLICATION                                                        6115\n" +
                "RAW.APPROVE_ASSIGN_HIST                                                        2132\n" +
                "RAW.APPROVE_CORP_INFO                                                            92\n" +
                "RAW.APPROVE_RESULT_INFO                                                        3063\n" +
                "RAW.ASSETS_SOCIAL_SECURITY_ACCOUNT_INFO                                       11269\n" +
                "RAW.ASSETS_SOCIAL_SECURITY_INSURANCE_FLOW                                     23119\n" +
                "RAW.ASSETS_SOCIAL_SECURITY_ORG_LIST                                           11503\n" +
                "RAW.ATM_CREDIT_AMOUNT_IDENTITY                                                 2090\n" +
                "RAW.ATM_PERSON_BASE_INFO                                                       2121\n" +
                "RAW.ATM_PERSON_WORK_INFO                                                       2123\n" +
                "RAW.ATM_REPAY_FLOW                                                               62\n" +
                "RAW.ATM_USER_ACCOUNT_RELATION                                                   285\n" +
                "RAW.ATM_WJFUSER_INFO                                                          10867\n" +
                "RAW.ATM_WJF_ACCOUNT                                                            6691\n" +
                "RAW.ATM_WJF_FLOW                                                               5182\n" +
                "RAW.ATM_WJF_LOAN                                                                141\n" +
                "RAW.BACKEND_CASE_HANDLER                                                          0\n" +
                "RAW.BJ_LGD_OUTPUT                                                                 0\n" +
                "RAW.BLACKLIST_TOHBASE                                                            17\n" +
                "RAW.CELL_BASIC                                                                20056\n" +
                "RAW.CELL_BILL                                                                 14612\n" +
                "RAW.CREDIT_ACCOUNT                                                             2588\n" +
                "RAW.CREDIT_REFERENCE                                                           2083\n" +
                "RAW.CREDIT_USER_INFO                                                           2097\n" +
                "RAW.CRT_MAIL                                                                   1558\n" +
                "RAW.CRT_MAIL_RECORD                                                            1070\n" +
                "RAW.CSW_ACCOUNTS                                                             113862\n" +
                "RAW.CSW_CASHFLOW                                                              53402\n" +
                "RAW.CSW_CREDITCARD_BILLS                                                      39646\n" +
                "RAW.CSW_CREDITCARD_HISTORY_LINE                                               12625\n" +
                "RAW.CSW_CREDITCARD_LINES                                                      36588\n" +
                "RAW.CSW_CREDITCARD_PAYMENTS                                                   36620\n" +
                "RAW.CSW_HUABEI                                                                11923\n" +
                "RAW.CSW_OPENAPI_BILL                                                           8781\n" +
                "RAW.DEVICE                                                                     5773\n" +
                "RAW.EMAIL_SENDER                                                                259\n" +
                "RAW.FEATURE_PREDICT_OFFLINE                                                    2670\n" +
                "RAW.GBL_COLLECTION_CASE                                                        4663\n" +
                "RAW.GBL_INTERACTION                                                            4413\n" +
                "RAW.GBOSSEDUCATION_STUDENT_INFO                                                5427\n" +
                "RAW.GJJ_BASIC                                                                 30559\n" +
                "RAW.GJJ_DETAIL                                                                 6141\n" +
                "RAW.GJJ_LOAN_DEMAND_MODEL                                                      1092\n" +
                "RAW.GJJ_LOAN_PRE_CREDIT_LINE_MODEL                                              667\n" +
                "RAW.KGJ_LOW_RISK_RECALL_UID_LAST_NEW                                            248\n" +
                "RAW.LOAN_APP_DATA                                                              7651\n" +
                "RAW.LOAN_APP_FLOW                                                             80484\n" +
                "RAW.LOAN_APP_LIST                                                            163591\n" +
                "RAW.LOAN_CLIENT_INFO                                                           4075\n" +
                "RAW.LOAN_CONSUMER_APPLICATION                                               1527889\n" +
                "RAW.LOAN_CONSUMER_APPLICATION_META                                              789\n" +
                "RAW.LOAN_CONSUMER_APPLY_INFO                                                      2\n" +
                "RAW.LOAN_CONSUMER_BASIC_INFO                                                      4\n" +
                "RAW.LOAN_CONSUMER_CONTACT                                                      1087\n" +
                "RAW.LOAN_CONSUMER_REPAYMENT                                                  303729\n" +
                "RAW.LOAN_CONTACT_PHONE                                                            2\n" +
                "RAW.LOAN_KALEIDOSCOPE_APP_MATERIAL                                             3442\n" +
                "RAW.LOAN_KDS_ORDER_INFO                                                        8251\n" +
                "RAW.LOAN_MATERIAL_REFERENCES                                                   3142\n" +
                "RAW.LOAN_MOBILE_ACCOUNT                                                           0\n" +
                "RAW.LOAN_MOBILE_BILL                                                              0\n" +
                "RAW.LOAN_MOBILE_CALL_FLOW                                                         0\n" +
                "RAW.LOAN_USER_BASIC_INFO                                                         10\n" +
                "RAW.MONEY_ALIPAY_ADDR                                                         76219\n" +
                "RAW.MSG_PUSH_RECORD                                                          648217\n" +
                "RAW.MSG_PUSH_RECORD_REQ                                                           0\n" +
                "RAW.MY_POSITION                                                                4146\n" +
                "RAW.MY_POSITION_TRANS                                                          1280\n" +
                "RAW.NBK_ENTRY                                                                 17654\n" +
                "RAW.NBK_ENTRY_ADDR                                                                0\n" +
                "RAW.NBK_USER_ENTRY                                                            26337\n" +
                "RAW.NOE_AGENT_SINGLE                                                         144994\n" +
                "RAW.NOE_BLACK_SINGLE                                                          89269\n" +
                "RAW.NOE_CUISHOU_PHONE                                                        176101\n" +
                "RAW.NOE_FP_SINGLE                                                             26268\n" +
                "RAW.NOE_GREY_SINGLE                                                           52328\n" +
                "RAW.NOE_LC_BLACK                                                              13576\n" +
                "RAW.OLA_ORDER                                                                  2810\n" +
                "RAW.OVERDUE_UID_FIRST_FOUND                                                   29982\n" +
                "RAW.P2P_BASIC_INFOMATION                                                          0\n" +
                "RAW.P2P_ORDER                                                                     0\n" +
                "RAW.PBOC_CREDIT_CARD                                                          30368\n" +
                "RAW.PBOC_CREDIT_CARD_PBOC_INFO_RPTNO                                              0\n" +
                "RAW.PBOC_CREDIT_INFO                                                           5765\n" +
                "RAW.PBOC_HSLN_DTL                                                              9897\n" +
                "RAW.PBOC_INFO                                                                 10071\n" +
                "RAW.PBOC_OTHLN_DTL                                                             9847\n" +
                "RAW.PBOC_QURY_DTL                                                              9249\n" +
                "RAW.PHONE_PHONE_REL_FULL                                                          1\n" +
                "RAW.PHONE_UID_REL                                                                 1\n" +
                "RAW.RELOAN_COUPON_RECOMMEND                                                    3570\n" +
                "RAW.RELOAN_SCORE_BEFORE_DT                                                        0\n" +
                "RAW.RULE_CELLPHONE                                                               74\n" +
                "RAW.RULE_CONTACT_CELLPHONE                                                       46\n" +
                "RAW.RULE_DEVICE_ID                                                               27\n" +
                "RAW.RULE_ID_CARD                                                                 38\n" +
                "RAW.SELF_JOINT_RISK_MODEL_APP_RISK_STAT                                           5\n" +
                "RAW.TBL_BI_LOG                                                                 1381\n" +
                "RAW.TBL_FRAUD_HIT_RULE                                                          579\n" +
                "RAW.TBL_FRAUD_PROCESS_RULE                                                    12036\n" +
                "RAW.TBL_FRAUD_REQUEST                                                          2275\n" +
                "RAW.TBL_FRAUD_RESPONSE                                                         3427\n" +
                "RAW.TBL_IDENTITY_FACE_RESULT                                                    664\n" +
                "RAW.TBL_IDENTITY_IDCARD_PHOTO                                                   363\n" +
                "RAW.TBL_IDENTITY_PEOPLE_PHOTO                                                   256\n" +
                "RAW.TBL_LOG_ACTIVE_USER_XINYE                                                     0\n" +
                "RAW.TBL_USER                                                                   2441\n" +
                "RAW.TBL_USER_ACCOUNT                                                             17\n" +
                "RAW.THIRDPARTY_APP_SOURCE                                                       778\n" +
                "RAW.THIRD_PARTY_BLACKLIST                                                     76669\n" +
                "RAW.UCENTER_ACCOUNT                                                          146180\n" +
                "RAW.UCENTER_ACCOUNT_APP                                                       59590\n" +
                "RAW.USER_LOGIN_INFO                                                               0\n" +
                "RAW.WIFI_DPD_DAILY                                                            67590\n" +
                "RELATION_NET.PHONE2AIDS                                                           0\n" +
                "RONG360.CELL_BASIC                                                                0\n" +
                "RONG360.CELL_BILL                                                                 0\n" +
                "RONG360.CSW_ACCOUNTS                                                              0\n" +
                "RONG360.CSW_CASHFLOW                                                              0\n" +
                "RONG360.CSW_HUABEI                                                                9\n" +
                "RONG360.LOAN_APP_LIST                                                             0\n" +
                "RONG360.LOAN_CONSUMER_CONTACT                                                     0\n" +
                "RONG360.NBK_ENTRY                                                                17\n" +
                "RS_CALL_TRACE                                                                     0\n" +
                "SENSOR.SENSOR_EVENT                                                               0\n" +
                "SENSOR.SKL_SESSION_ID_SENSOR_EVENT                                                1\n" +
                "SENSOR.SKL_UID_SENSOR_EVENT                                                       0\n" +
                "SOCIALSECURITY.ACCOUNT                                                       304104\n" +
                "SOCIALSECURITY.ACCOUNT_IDCARD_BAK                                                 0\n" +
                "SOCIALSECURITY.FLOWS                                                         136244\n" +
                "SOCIALSECURITY.INSURANCE                                                      20241\n" +
                "SOCIALSECURITY.NEWFLOWS                                                           0\n" +
                "SOCIALSECURITY.SSA_ACTIVE_USER_APPLIST_MODEL_SCORE_INFO_DT                        0\n" +
                "TESLA.DEVICE_ADVERTISING_IMEI_IDFA                                                0\n" +
                "TESLA.DEVICE_ADVERTISING_IP_AGENT                                                 0\n" +
                "TESLA.GOVERNMENT_ANSWERS                                                          0\n" +
                "TESLA.GOVERNMENT_META                                                             0\n" +
                "TESLA.GOVERNMENT_STANDARD                                                         0\n" +
                "TESLA.SOCIAL_SECURITY_ACCOUNT_REALTIME_INDEX                                      1\n" +
                "TESLA.SOCIAL_SECURITY_META                                                        0\n" +
                "TESLA.SOCIAL_SECURITY_STANDARD_DATA                                           88348\n" +
                "TESLA.SOCIAL_SECURITY_USER_REALTIME_INDEX                                       304\n" +
                "TESLA.USER_DEVICE_ADVERTISING                                                     0\n" +
                "TEST.BENCHMARK                                                                    0\n" +
                "TEST.TEST_CACHE_FREQ                                                              0\n" +
                "TRIDENT.AD_FATIGUE_LAST_TIME                                                   5570\n" +
                "TRIDENT.AD_FATIGUE_TOTAL                                                     492235\n" +
                "TRIDENT.MISSILE_UID_USERGROUP                                                     0\n" +
                "TRIDENT.REAL_TIME_PROFILE                                                         0\n" +
                "TRIDENT.UID_CREDIT_CARD_LIST                                                      0\n" +
                "TRIDENT.UID_SEARCH                                                            29423\n" +
                "TRIDENT.UID_TEMP_CHANGE_CREDIT                                                    0\n" +
                "TRIDENT.UID_TMP_CREDIT                                                            0\n" +
                "TRIDENT.UID_TMP_SNAPSHOT_CREDIT                                                   0\n" +
                "WDI.HBASE_REGION                                                                  0\n" +
                "WJZ.ADM_PFM_WJZ_FINOVERVIEW_TARGETUID                                             0\n" +
                "WJZ.ADM_PFM_WJZ_GUESSTEXT                                                         4\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_DEPOSIT_MS_OUTPUT                                    14\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_INCOME_MS_OUTPUT                                    188\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_AREA_MS_OUTPUT                                          6878\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_OVER_BUDGET_MS_OUTPUT                                      0\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_PROVINCE_MS_OUTPUT                                        13\n" +
                "WJZ.DWS_WJZ_FIN_HEALTH_SCORE_MS_OUTPUT                                         2262\n" +
                "ZEUS.APP_CATEGORY                                                                 1\n" +
                "ZEUS.CELL_BASIC                                                              217892\n" +
                "ZEUS.CELL_BILL                                                               108769\n" +
                "ZEUS.CELL_CALL                                                               696754\n" +
                "ZEUS.CELL_NAME                                                                 7719\n" +
                "ZEUS.CREDITCARD_ACCOUNTS                                                       1045\n" +
                "ZEUS.CREDITCARD_BILLS                                                           226\n" +
                "ZEUS.CREDITCARD_CASHFLOWS                                                       130\n" +
                "ZEUS.CREDITCARD_JIZHANG_USER                                                  10439\n" +
                "ZEUS.DAIZHONG_WHITE_LIST                                                          0\n" +
                "ZEUS.DEBIT_CARD_TRADE_ACCOUNT                                                    23\n" +
                "ZEUS.DEBIT_CARD_TRADE_FLOW                                                       43\n" +
                "ZEUS.ECOMMERCE_ACCOUNTS                                                         641\n" +
                "ZEUS.ECOMMERCE_TRADE_FLOWS                                                      131\n" +
                "ZEUS.LOAN_APP_LIST                                                            75753\n" +
                "ZEUS.LOAN_CONSUMER_CONTACT                                                   365819\n" +
                "ZEUS.MOB_NOCELL_LIST                                                             33\n" +
                "ZEUS.RISK_EVALUATE_LIST                                                          19\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_CONSIGNEE_INFO                                      386\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_HISTORY_RISK                                          0\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_INCREASE_QUOTA                                        0\n" +
                "ZEUS.SHARE_EXCHANGE_MACHINE_PHONE_INFO                                          393\n" +
                "ZEUS.SOCIALSECURITY_ACCOUNT                                                    8995\n" +
                "ZEUS.SOCIALSECURITY_INSURANCE                                                  9102\n" +
                "ZEUS.USER_CELL_NETS                                                           12270\n" +
                "ZEUS.USER_CELL_SMSES                                                           9809\n" +
                "ZEUS.USER_CREDITCARD_NET_ACCOUNT                                              11178\n" +
                "ZEUS.USER_CREDITCARD_NET_BILL                                                  3782\n" +
                "ZEUS.USER_CREDITCARD_NET_CASHFLOW                                             22291\n" +
                "ZEUS.USER_CREDITCARD_NET_LINE                                                  3214\n" +
                "ZEUS.USER_CREDITCARD_NET_PAYMENT                                               4136\n" +
                "ZEUS.USER_LOAN_SMS                                                            33555\n" +
                "ZEUS.ZHONGTONG_SITES                                                             18\n" +
                "ZMXY.WATCH_LISTII_MAPPING                                                         0\n" +
                "creditcard-tracer                                                                 0";

        String[] split = count.split("\\n");
        for(int i = 0 ; i < split.length; i ++){
            String[] s = split[i].split(" ");
            map.put(s[0], Integer.parseInt(s[s.length-1]));
        }

        createExcel(new FileOutputStream("d://ssa.xls"));
    }

    static Map<String, Integer> map = new HashMap<>();

    public static void createExcel(OutputStream os) throws WriteException, IOException, BiffException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //创建要显示的具体内容
        Label formate = new Label(0,0,"大小（单位MB）");
        sheet.addCell(formate);
        Label floats = new Label(1,0,"表名");
        sheet.addCell(floats);
        Label integers = new Label(2,0,"ticket");
        sheet.addCell(integers);
        Label booleans = new Label(3,0,"QPS");
        sheet.addCell(booleans);
        Label dates = new Label(4,0,"是否核心（数字大优先级高）");
        sheet.addCell(dates);
        Label dates5 = new Label(5,0,"日增长数据量");
        sheet.addCell(dates5);
        Label dates6 = new Label(6,0,"用途");
        sheet.addCell(dates6);
        Label dates7 = new Label(7,0,"相关人员");
        sheet.addCell(dates7);
        Label dates8 = new Label(8,0,"是否可以迁到mysql");
        sheet.addCell(dates8);
        Label dates9 = new Label(9,0,"是否有二级索引");
        sheet.addCell(dates9);
        Label dates10 = new Label(10,0,"最近一天是否有请求");
        sheet.addCell(dates10);
        Label dates11 = new Label(11,0,"如何建索引");
        sheet.addCell(dates11);

        //读取数据写入。
        InputStream instream = new FileInputStream("d:/表梳理.xls");
        Workbook readwb = Workbook.getWorkbook(instream);
        Sheet readsheet = readwb.getSheet(0);
        //获取Sheet表中所包含的总列数
        int rsColumns = readsheet.getColumns();
        //获取Sheet表中所包含的总行数
        int rsRows = readsheet.getRows();
        //获取指定单元格的对象引用
        for (int i = 1; i < rsRows; i++) {
            String tablename = "";
            for (int j = 0; j < rsColumns; j++) {
                Cell cell = readsheet.getCell(j, i);
                if(j == 1){
                    tablename = cell.getContents();
                }
                if(j == 0){
                    jxl.write.Number number = new jxl.write.Number(j,i,Double.parseDouble(cell.getContents()));
                    sheet.addCell(number);
                }else{
                    Label example = new Label(j,i,cell.getContents());
                    sheet.addCell(example);
                }
            }
            Label example = new Label(10,i, map.getOrDefault(tablename, 0).toString());
            sheet.addCell(example);
        }


        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
}