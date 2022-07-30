package com.aj2814.homeAutomation.services.deviceControllers.common;

public class B64Converter {
    public static byte[] encode(byte[] src)
    {
        return java.util.Base64.getMimeEncoder().encode(src);
    }
    public static String encodeToString(byte[] src)
    {
        return java.util.Base64.getMimeEncoder().encodeToString(src);
    }
    public static byte[] decode(byte[] src){
        return java.util.Base64.getMimeDecoder().decode(src);
    }
    public static byte[] decode(String src){
        return java.util.Base64.getMimeDecoder().decode(src);
    }
}
