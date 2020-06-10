package com.skeleton.foundation.model.error;


/**
 * 错误信息
 *
 * 错误编号
 * 一共由8位数字进行表示：
 *
 * 前三位表示不同服务名称，统一分配数字
 * 后续两位表示模块名称，由服务负责人统一分配数字
 * 后续三位表示具体错误原因
 * 00401005按照上面的的规则，可划分为004，01，005。04标识user服务代号，01标识登录注册模块，005是业务错误如"该用户不存在"
 **/
public class ErrorCodes {

    //通用（000）
    public static final String RPC_ERROR = "10000001";
    public static final String INVALID_PARAMETERS_ERROR = "00001002";
    public static final String SYSTEM_BUSY_ERROR = "00001003";
    public static final String MISSING_PARAMETERS_ERROR = "00001004";

    //网关（001）
    public static final String ROUTE_ERROR = "00101001";

    //营销任务推送 (003)
    //素材 (00301)
    public static final String MATERIAL_ERROR = "00301001";
    //推送 (00302)
    public static final String PUSH_ERROR = "00302001";
    //自动回复配置 (00303)
    public static final String REPLY_CONFIG_ERROR = "00303001";
    //渠道 (00304)
    public static final String CHANNEL_ERROR = "00304001";
    //营销任务 (00305)
    public static final String MARKETING_TASK_ERROR = "00305001";

    //会员标签(004)
    //标签 (00401)
    public static final String LABEL_ERROR = "00401001";
    //标签分组 (00402)
    public static final String GROUP_ERROR = "00402001";
    //会员 (00403)
    public static final String MEMBER_ERROR = "00403001";

    // 统计分析相关(005)
    //会员画像-价值分析(00501)
    public static final String RFM_CONFIG_EXISTED = "00501001";
    public static final String RFM_CONFIG_NOT_EXISTED = "00501002";
    public static final String RFM_CONFIG_ERROR = "00501003";
    //会员画像-属性分析(00502)
    public static final String PROPERTY_ANALYSIS_CONFIG_ERROR = "00502001";
    public static final String PROPERTY_ANALYSIS_CONFIG_NOT_EXIST = "00502002";
    //会员画像-活跃分析(00503)
    public static final String ACTIVE_ANALYSIS_CONFIG_ERROR = "005003001";
    public static final String ACTIVE_ANALYSIS_CONFIG_NOT_EXIST = "005003002";
    //交易分析-交易趋势分析(00504)
    public static final String TRADE_TREND_CONFIG_ERROR = "00504001";
    public static final String TRADE_TREND_ANALYSIS_DATA_ERROR = "00504002";
    //交易分析-交易分布(00505)
    public static final String TRADE_DISTRIBUTION_CONFIG_ERROR = "00505001";
    public static final String TRADE_DISTRIBUTION_ANALYSIS_DATA_ERROR = "00505002";

    /**
     * 获取 Error 对象
     * @param message 错误信息
     * @return
     */
    public static ErrorObject build(String code,String message) {
        return new ErrorObject(code, message);
    }

}
