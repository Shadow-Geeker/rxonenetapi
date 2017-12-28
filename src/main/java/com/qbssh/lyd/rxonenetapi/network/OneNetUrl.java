package com.qbssh.lyd.rxonenetapi.network;

/**
 * OneNet api base url address.
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/20/2017.
 */

public final class OneNetUrl {

    final static String DEFAULT = "http://api.heclouds.com/";

    public final static String BEI_JING = "http://api.bj.cmcconenet.com/";
    public final static String LIAO_NIN = "http://api.ln.cmcconenet.com/";
    public final static String JIANG_SU = "http://api.js.cmcconenet.com/";
    public final static String ZHE_JIANG = "http://api.zj.cmcconenet.com/";
    public final static String FU_JIAN = "http://api.fj.cmcconenet.com/";
    public final static String SHAN_DONG = "http://api.sd.cmcconenet.com/";
    public final static String HU_NAN = "http://api.hn.cmcconenet.com/";
    public final static String GUANG_DONG = "http://api.gd.cmcconenet.com/";
    public final static String GUANG_XI = "http://api.gx.cmcconenet.com/";
    public final static String SI_CHUAN = "http://api.sc.cmcconenet.com/";

    // TODO:
    public static String getBaseUrl(int location) {
        return DEFAULT;
    }

}
