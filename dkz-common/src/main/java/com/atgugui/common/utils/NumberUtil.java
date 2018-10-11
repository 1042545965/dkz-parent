package com.atgugui.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * @author dkzadmin
 * 2018年10月11日11:54:53
 */
public final class NumberUtil {

    public static final int RATE2 = 2;

    public static final int RATE8 = 8;

    public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public static final BigDecimal DAYS_OF_YEAR = BigDecimal.valueOf(365);

    public static BigDecimal getProfit(BigDecimal money, BigDecimal rate, Integer days) {
        return divide(divide(money.multiply(rate).multiply(BigDecimal.valueOf(days)), HUNDRED), DAYS_OF_YEAR);
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return divide(a, b, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b, int round) {
        return a.divide(b, RATE8, round);
    }

    public static double toRate2(double d) {
        if (AppUtil.isNull(d)) {
            return 0.00;
        }
        BigDecimal b = new BigDecimal(d);
        return b.setScale(RATE2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double toRate8(double d) {
        if (AppUtil.isNull(d)) {
            return 0.00;
        }
        BigDecimal b = new BigDecimal(d);
        return b.setScale(RATE8, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double strToDouble(String str) {
        if (AppUtil.isNull(str)) {
            return 0;
        }
        double d = 0;
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static long strToLong(String str) {
        if (AppUtil.isNull(str)) {
            return 0;
        }
        long d = 0;
        try {
            d = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static int toInt(String str) {
        if (AppUtil.isNull(str)) {
            return 0;
        }
        int d = 0;
        try {
            d = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 格式化金额，保留2位小数，包含千分位（4舍5入）
     *
     * @param str
     * @return
     */
    public static String prizeFormat(String str) {

        double val = strToDouble(str);

        if (val == 0) {
            return "0";
        }

        DecimalFormat format = new DecimalFormat("#,###.00");

        return format.format(val);

    }

    public static String prizeWanFormat(String str) {
        double val = strToDouble(str);

        if (val == 0) {
            return "0";
        }

        val = val / 10000;

        DecimalFormat format = new DecimalFormat("#,###.00");

        return format.format(val);
    }


    public static String to_100_percent(double val) {
        if (val == 0) {
            return "0";
        }

        DecimalFormat format = new DecimalFormat("0.00");

        return format.format(val * 100);
    }


    public static String to_percent(String val) {
        if (val == null || val.equals("0")) {
            return "";
        }

        DecimalFormat format = new DecimalFormat("#.00");

        return format.format(val);
    }


    public static String to_100_percent(String val) {
        if (AppUtil.isNull(val)) {
            return "0";
        }

        return to_100_percent(Double.parseDouble(val));
    }

    public static String to_10000_percent(String val) {
        if (AppUtil.isNull(val)) {
            return "0";
        }

        double dd = Double.parseDouble(val) / 10000.0;

        DecimalFormat format = new DecimalFormat("#,###.00");

        return format.format(dd);
    }

    public static long generateRandomNumber(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }

    
    /**
     * @param div 除数
     * @param dividend 被除数
     * @return 百分比值 80
     */
    public static Integer getPercentage(Integer divisor , Integer dividend) {
    	  // 创建一个数值格式化对象   
        NumberFormat numberFormat = NumberFormat.getInstance();   
         // 设置精确到小数点后2位   
         numberFormat.setMaximumFractionDigits(2);   
        String result = numberFormat.format((float)divisor/(float)dividend*100);
		return Integer.valueOf(result);
    }
    
	/**
	 * @param percentage 获取true的概率 为正整数
	 * @return true or false
	 */
	public static Boolean getChance(int percentage){
		Random random = new Random();
		int i = random.nextInt(99);
		if(i>=0&&i<percentage)
			return true ;
		else
			return false;
	}
    
    public static void main(String[] args) {
        System.out.println(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(1000), 2, BigDecimal.ROUND_UP));
    }
}
