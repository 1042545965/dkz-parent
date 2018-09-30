package com.atgugui.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.atgugui.common.utils.http.HttpUtils;
import com.atgugui.model.config.WenLianConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author dkzadmin
 *2018-9-30 10:55:28
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";
        if (WenLianConfig.isAddressEnabled())
        {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            JSONObject data = obj.getObject("data", JSONObject.class);
            String region = data.getString("region");
            String city = data.getString("city");
            address = region + " " + city;
        }
        return address;
    }
}
