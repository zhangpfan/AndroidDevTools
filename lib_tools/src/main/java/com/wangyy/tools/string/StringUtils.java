package com.wangyy.tools.string;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author zhangpfan.
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("not support...");
    }

    /**
     * 判断字符串是否为空或长度为0
     *
     * @param str 需要判断的字符序列
     * @return 为空则返回true
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符序列不为null或""
     *
     * @param str 需要判断的字符序列
     * @return 不为空则返回true
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符序列是否为空或长度为0或由空白字符组成
     *
     * @param str 需要判断的字符序列
     * @return 如果为空白字符序列则返回true
     */
    public static boolean isBlank(CharSequence str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否非空白
     *
     * @param str 需要判断的字符序列
     * @return 如果不为空白字符序列则返回true
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 将null转成空字符串""
     *
     * @param str 需要处理的字符序列
     * @return <li>如果字符串为null， 将字符串转成""
     * <li>不为null， 则返回员字符串
     */
    @NonNull
    public static String nullToBlank(String str) {
        return str == null ? "" : str;
    }

    /**
     * 判断两个字符串内容是否相同
     *
     * @param actualStr   实际的字符串
     * @param expectedStr 预期的字符串
     * @return 如果actualStr和expectedStr相同则返回true
     */
    public static boolean equals(String actualStr, String expectedStr) {
        return actualStr == null ? expectedStr == null : actualStr
                .equals(expectedStr);
    }

    /**
     * 判断两个字符串内容是否相同(不区分大小写)
     *
     * @param actualStr   实际结果
     * @param expectedStr 预测值
     * @return 如果actualStr和expectedStr相同则返回true
     */
    public static boolean equalsIgnoreCase(String actualStr, String expectedStr) {
        return actualStr == null ? expectedStr == null : actualStr
                .equalsIgnoreCase(expectedStr);
    }

    /**
     * 字符串中是否包含某字符
     *
     * @param str        String
     * @param searchChar char
     * @return <li>如果字符串为空， 返回false
     * <li>如果字符串中包含searchChar字符返回true， 否则返回false
     */
    public static boolean contains(String str, char searchChar) {
        return isNotEmpty(str) && str.indexOf(searchChar) >= 0;
    }

    /**
     * 字符串中是否包含某字符串
     *
     * @param str       目标字符串
     * @param searchStr 被包含的字符串
     * @return <li>如果str或则searchStr为空， 返回false
     * <li>如果str字符串中包含searchChar字符返回true， 否则返回false
     */
    public static boolean contains(String str, String searchStr) {
        return !(str == null || searchStr == null) && str.contains(searchStr);
    }

    /**
     * 字符串中是否包含某字符(不区分大小写)
     *
     * @param str       目标字符串
     * @param searchStr 被包含的字符串
     * @return <li>如果str或则searchStr为空， 返回false
     * <li>如果str字符串中包含searchChar字符返回true， 否则返回false
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null)
            return false;
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从字符串左边开始截取指定长度的字符串
     *
     * @param str String
     * @param len 指定长度
     * @return <li>如果字符串为空，则返回null
     * <li>如果len小于0，则返回""
     * <li>如果字符串的长度小于等于len，返回原字符串
     * <li>否则返回str.substring(0, len)
     */
    @Nullable
    public static String subLeft(String str, int len) {
        if (str == null)
            return null;
        if (len < 0)
            return "";
        if (str.length() <= len)
            return str;
        else
            return str.substring(0, len);
    }

    /**
     * 从字符串右边开始截取指定长度的字符串
     *
     * @param len 指定长度
     *            <li>如果字符串为空，则返回null
     *            <li>如果len小于0，则返回""
     *            <li>如果字符串的长度小于等于len，返回原字符串
     *            <li>否则返回str.substring(str.length() - len)
     */
    @Nullable
    public static String subRight(String str, int len) {
        if (str == null)
            return null;
        if (len < 0)
            return "";
        if (str.length() <= len)
            return str;
        else
            return str.substring(str.length() - len);
    }

    /**
     * 从指定位置开始截取指定长度的字符串
     *
     * @param str 目标字符串
     * @param pos 位置索引
     * @param len 要截取的长度
     * @return <li>如果字符串为空，则返回null
     * <li>如果pos小于0，则将pos置为0
     * <li>如果pos大于字符串的长度，则返回""
     * <li>如果len小于0，则返回""
     * <li>如果字符串的长度小于等于len，返回原字符串
     * <li>否则返回str.substring(str.length() - len)
     */
    @Nullable
    public static String subMid(String str, int pos, int len) {
        if (str == null)
            return null;
        if (len < 0 || pos > str.length())
            return "";
        if (pos < 0)
            pos = 0;
        if (str.length() <= pos + len)
            return str.substring(pos);
        else
            return str.substring(pos, pos + len);
    }

    /**
     * 去除字符串尾部换行符(\r\n)
     *
     * @param str String
     * @return 返回去除尾部换行符的字符串
     */
    @Nullable
    public static String chomp(String str) {
        if (isEmpty(str))
            return str;
        if (str.length() == 1) {
            char ch = str.charAt(0);
            if (ch == '\r' || ch == '\n')
                return "";
            else
                return str;
        }
        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);
        if (last == '\n') {
            if (str.charAt(lastIdx - 1) == '\r')
                lastIdx--;
        } else if (last != '\r')
            lastIdx++;
        return str.substring(0, lastIdx);
    }

    /**
     * 去除字符串尾部指定的分隔符
     *
     * @param str String
     * @param separator 需要去除的分割符
     * @return 返回去除尾部指定分隔符的字符串
     */
    @Nullable
    public static String chomp(String str, String separator) {
        if (isEmpty(str) || separator == null)
            return str;
        if (str.endsWith(separator))
            return str.substring(0, str.length() - separator.length());
        else
            return str;
    }


    /**
     * 得到一个指定长度的字符串，长度不够在字符串右边补空格
     *
     * @param str  字符串
     * @param size 长度
     * @return
     */
    @Nullable
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * 得到一个指定长度的字符串，长度不够在字符串右边补充指定字符
     *
     * @param str     字符串
     * @param size    长度
     * @param padChar 填充的字符
     */
    @Nullable
    public static String rightPad(String str, int size, char padChar) {
        if (str == null)
            return null;
        int pads = size - str.length();
        if (pads <= 0)
            return str;
        if (pads > 8192)
            return rightPad(str, size, String.valueOf(padChar));
        else
            return str.concat(padding(pads, padChar));
    }

    /**
     * 得到一个指定长度的字符串，长度不够在字符串右边补充指定字符
     *
     * @param str    字符串
     * @param size   长度
     * @param padStr 填充的字符串
     */
    @Nullable
    public static String rightPad(String str, int size, String padStr) {
        if (str == null)
            return null;
        if (isEmpty(padStr))
            padStr = " ";
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0)
            return str;
        if (padLen == 1 && pads <= 8192)
            return rightPad(str, size, padStr.charAt(0));
        if (pads == padLen)
            return str.concat(padStr);
        if (pads < padLen)
            return str.concat(padStr.substring(0, pads));
        char[] padding = new char[pads];
        char[] padChars = padStr.toCharArray();
        for (int i = 0; i < pads; i++) {
            padding[i] = padChars[i % padLen];
        }

        return str.concat(new String(padding));
    }

    /**
     * 得到一个指定长度的字符串，长度不够在字符串左边补空格
     *
     * @param str  字符串
     * @param size 长度
     */
    @Nullable
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * 得到一个指定长度的字符串，长度不够在字符串左边补充指定字符
     *
     * @param str     字符串
     * @param size    长度
     * @param padChar 填充的字符
     * @return result
     */
    @Nullable
    public static String leftPad(String str, int size, char padChar) {
        if (str == null)
            return null;
        int pads = size - str.length();
        if (pads <= 0)
            return str;
        if (pads > 8192)
            return leftPad(str, size, String.valueOf(padChar));
        else
            return padding(pads, padChar).concat(str);
    }

    /**
     * 得到一个指定长度的字符串，长度不够在字符串左边补充指定字符
     *
     * @param str String
     * @param size   需要返回字符串的长度
     * @param padStr 要填充的内容，默认为空格" "
     * @return result
     */
    @Nullable
    public static String leftPad(String str, int size, String padStr) {
        if (str == null)
            str = "";
        if (isEmpty(padStr))
            padStr = " ";
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0)
            return str;
        if (padLen == 1 && pads <= 8192)
            return leftPad(str, size, padStr.charAt(0));
        if (pads == padLen)
            return padStr.concat(str);
        if (pads < padLen)
            return padStr.substring(0, pads).concat(str);
        char padding[] = new char[pads];
        char padChars[] = padStr.toCharArray();
        for (int i = 0; i < pads; i++) {
            padding[i] = padChars[i % padLen];
        }
        return (new String(padding)).concat(str);
    }

    /**
     * 填充字符
     */
    private static String padding(int repeat, char padChar)
            throws IndexOutOfBoundsException {
        if (repeat < 0)
            throw new IndexOutOfBoundsException(
                    "Cannot pad a negative amount: " + repeat);
        char[] buf = new char[repeat];
        Arrays.fill(buf, padChar);
        return new String(buf);
    }

    /**
     * 首字母大写
     *
     * @param str String
     * @return 返回首字母大写的字符串
     */
    @Nullable
    public static String capitalizeFirstLetter(String str) {
        if (isBlank(str)) {
            return str;
        }
        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
                : new StringBuilder(str.length())
                .append(Character.toUpperCase(c))
                .append(str.substring(1)).toString();
    }

    /**
     * 将字符串转成utf-8编码
     *
     * @param str String
     * @return 返回utf-8编码的字符串
     */
    @Nullable
    public static String utf8Encode(String str) throws UnsupportedEncodingException {
        if (!isEmpty(str) && str.getBytes("UTF-8").length != str.length()) {
            return URLEncoder.encode(str, "UTF-8");
        }
        return str;
    }

    /**
     * 字符串纯数字判断
     *
     * @return 纯数字组成返回true
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否是数字(小数or整数)
     *
     * @param str 目标字符串
     * @return 是浮点型则返回true
     */
    public static boolean isNumberDecimals(String str) {
        if (isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^[0-9]+$|^[0-9]+\\.?[0-9]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断是否是由字母组成
     *
     * @return 由字母组成返回true
     */
    public static boolean isLetter(CharSequence str) {
        if (str == null)
            return false;
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否由字母和数字组成
     *
     * @param str 为null返回false
     * @return 字符串含有数字or字符则返回true
     */
    public static boolean isLetterOrNumeric(String str) {
        if (str == null)
            return false;
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是中文字符
     *
     * @return 是中文字符则返回true
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判断字符串中是否含有中文
     *
     * @param str 为null时返回false
     * @return 含有中文字符返回true
     */
    public static boolean containsChinese(final String str) {
        if (str == null)
            return false;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 反转字符串
     * <li>如果str为""返回""， 为null返回null
     * <li>否则返回反转的字符串， 即"abc"返回"cba"
     */
    @Nullable
    public static String reverse(String str) {
        if (isEmpty(str))
            return str;
        else
            return (new StringBuilder(str)).reverse().toString();
    }

    /**
     * 统计某个sub字符串出现的次数
     *
     * @param str 目标字符串，为空返回0
     * @param sub sub子串，为空返回0
     * @return sub字符串在目标字符串中出现的次数
     */
    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub))
            return 0;
        int count = 0;
        for (int idx = 0; (idx = str.indexOf(sub, idx)) != -1; idx += sub
                .length()) {
            count++;
        }
        return count;
    }

    /**
     * 生成guid/uuid字符串
     */
    @NonNull
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

}
