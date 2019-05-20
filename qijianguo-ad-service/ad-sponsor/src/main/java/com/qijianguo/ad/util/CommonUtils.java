package com.qijianguo.ad.util;

import com.qijianguo.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class CommonUtils {

    private static String[] parsePatterns = {
      "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    public static String md5(String str) {
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    public static Date parseStringDate(String time) throws AdException {
        try {
            return DateUtils.parseDate(time, parsePatterns);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }
}
