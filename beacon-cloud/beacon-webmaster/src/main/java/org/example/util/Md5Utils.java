package org.example.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5加密工具类
 * 使用MD5 + salt + 1024次迭代（与ShiroRealm的HashedCredentialsMatcher一致）
 */
public class Md5Utils {

    private static final int HASH_ITERATIONS = 1024;

    /**
     * MD5加密（带salt）
     * @param original 明文
     * @param salt 盐值
     * @return 密文
     */
    public static String encrypt(String original, String salt) {
        return new Md5Hash(original, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
    }
}
