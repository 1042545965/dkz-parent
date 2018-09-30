package com.atgugui.common.utils;

import org.springframework.util.StringUtils;

import com.atgugui.common.constant.UserConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dkzadmin
 * 2018-9-30 10:55:10
 */
public class CheckUtil {

    private final static String MOBILE_PATTERN = "^1[3-9]\\d{9}$";

    private final static String USERNAME_PATTERN = "^[a-zA-Z]\\w{3,15}$";

    private final static String PASSWORD_PATTERN = "^\\d+$";

    private final static String Biz_PASSWORD_PATTERN = "^\\d{6}$";

    private final static String Email = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean checkMobile(String mobile) {
        return Pattern.compile(MOBILE_PATTERN).matcher(mobile).find();
    }

    public static boolean checkUsername(String username) {
        return Pattern.compile(USERNAME_PATTERN).matcher(username).find();
    }

    public static boolean checkPassword(String password) {
        return AppUtil.isNotNull(password) && password.length() >= 6 && password.length() <= 20 && !Pattern.compile(PASSWORD_PATTERN).matcher(password).find();
    }

    public static boolean checkName(String name) {
        return AppUtil.isNotNull(name) && name.length() > 1;
    }

    public static void main(String[] args) {
        System.out.println(checkEmail("1042545965@qq.com"));
    }

    public static boolean checkEmail(String email) {
        return Pattern.compile(Email).matcher(email).find();
    }

    /**
     * 校验银行卡卡号是否合法
     * @Title: checkBankCard
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param bankCard
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public static boolean checkBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @Title: getBankCardCheckCode
     * @Description: TODO(从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位)
     * @param nonCheckCodeBankCard
     * @return char    返回类型
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard){
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    /**
     * 校验微信账号
     * 微信账号分手机号
     * qq号
     * 邮箱号
     * @param wxCode
     * @return
     */
    public static boolean checkThrid(String wxCode){

        boolean flag = false;
        if(!StringUtils.isEmpty(wxCode)){

            if(!StringUtils.isEmpty(wxCode)){
                if(wxCode.contains("@")){  //验证邮箱号
                    String check = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";
                    Pattern regex = Pattern.compile(check);
                    Matcher matcher = regex.matcher(wxCode);
                    flag = matcher.matches();
                }else {
                    String reg1 = "[1-9]\\d{5,19}";  //qq号 6 - 20
                    String reg2 = "1[3-9]\\d{9}";  //qq号或者手机号 11
                    String reg3 = "[a-zA-Z][-_a-zA-Z0-9]{5,19}"; //微信号带字母的 6-20
                    flag = wxCode.matches(reg1) || wxCode.matches(reg2) || wxCode.matches(reg3);
                }
            }

        }
        return flag;

    }

    /** 校验是否是手机号码
     * @param username
     * @return
     */
    public static boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }
    
    public static boolean checkBizPassword(String BizPwd) {
        return Pattern.compile(Biz_PASSWORD_PATTERN).matcher(BizPwd).find();
    }
}
