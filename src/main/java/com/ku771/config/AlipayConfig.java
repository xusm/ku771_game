package com.ku771.config;

public class AlipayConfig {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		public static String app_id = "2016092300579250";
		
		// 商户私钥，您的PKCS8格式RSA2私钥
	    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDZoNuXWxnuiRrfVq0HQeq5AfcKTFki+mbir937d2dU1zmojSgX+kQNp+EWEvbXkSTNJWN0ycDmVyxa6EOaDC/ZyC+4bGivwKvv4tsljEr2zqBbWq7N9eCdp3cC8PBPUHEljVThREM/dc/Bk1RPU5Z82YzBN5uZT0JUdwglP4aQB2VKedXnGYMyvjtXMi8WTjg3Q4dlFrtRQQUpAa8a50EmGK7jrg1+pFYFsJeNlXlZgmLJJEEkmxuojq3POqdqsaej6xekeFiv4dP7pBtX8XCQhoec9X0Y25gB3/kdZ9eAruCqyN8y8WmJgxM/EwCt0BUFA1+LIZQ5d2Mk2pNgl/+PAgMBAAECggEBAIkkMB0yQKYr6i5BvansJRFPJrFrqWQ8bad+ciGsfgZd1qzbvx9VC5ubZmF1s+3mPk20uPb4yMGev4nOAgPKmEDiQQgz8MiIXxE9YSJJRVhAn5bSD4Qk5NYUmQ/Wa2lf5N1oMcv/K22LPU/cGu4eW5/zrBp6g2haJQ97Lzvhy1do13Tx4OeB979uaLjzg1VzDplfw18C41c9wGUl5NcriL1tsVV7LpPDEE243HZmOY8nVSCKubIDROz/2bR10furEVusZdzdg7RioOLWR+hsBakb/DKzkWl9/b2qpZH/2paMSV3WcUfZDcvU/K+lFfGuxAMjwu8fmny/ozYDV2jSftECgYEA/V72LbL7t/DlVA2XQD3/gs3PaGCKmfQoWGRqZBPbdBrWoa0fnGr1iYuAIVLb+XoBxd/fl5xiHb1rBTqrGmKkuHZuQFsMjwBvXtaRSYj787SgSZTCar4JdvhdBG/tdFgLhLreBaWUXlsG5ExYTm2ddR6sN8UzMyAErzxjSt3OIjcCgYEA2+LzqYiuB6YN8EX9aocdT5h8sHi6W6fIrtUtSgqiAIyXVbMY8e33STCyT524QPqN3sXslV6Pt92I13i2g17v9h8wU74b84b0hFjrGyjSZxBEpxurymZg/sRFCq3UKmamkhHTUAhE5ua8jCXZ8kwR7z/4BG3NC4yLcSgOEfSEQWkCgYEAsFToa0GHIaqLT4Xt343GRxhI4lFd5GshsPKhgGi5cAJFoVtFBq0BmOn3EDoEUYlxtm5pw6/YMO5kjs0Xy1MfZCk37Z3wDY219IDLuCuXfTIV41+eNn9vQtbylWX4EeI8kfEjFzb3HPuovjXLtzlePeZM4LtjOI9t8y7Jw46+KN0CgYAsbqjkGi1oeEKaydnSe+kNW3fexRaLTNU9lvAm5Q0lZUNzWtRwlrCAhqmRcwJ3hunCGWacLFaIRejP+aGP3FBLALRoR6MoS53/XGRtwYr6rovg5LOLG7L/6DM3XDbiNon0PEviATnDGt8AxulKj9qT6O46HuHvMoA+5mQpdqp++QKBgQDcXDtbpV6HJWj8kSYNfbfMYjbqPoX2bgNmvFrPewEWOKmt9iP4y5Y4qWY+uLW3hyrsx6hM7AcrKuACORop91sC4qZ5doG8ky3iVEe/RTanSt/wU25DeyJ7ADQYDR55nwZ94QV/sZzC6kGEZJFaAJ0/IiFJfLW8whDFd8YafmU5lQ==";
		
		// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA04BdWjc+3cjRwKXf8oV4s2YceTSyZVd1zc236v/n+qAoeJSkCRAviWjnEy9XlTkn64CBPIfrfnbU2MFL26n3yi5UDi+p2t+Vby1SVHCDdQVw/bj0cddELu+M5rgy/xJcG1LKbtiLEXeOia6fOL7K2xQMfz24InbJBMncJvG2h28onrr1OfhF3RBw83D1Vd0FlTuBm2JHbVKo4qK4OBsltQxQpzk649ZSxo7tfGuNtNT1rEc+qxSb8b4jW8FRjiAN/WJIU4axh3UPwBmhRNIvUfbWhOVn3awapcbSlqGtr+I+jq+yuKacarvnUtoStXfpC0NgxkNSBBAuXi83LZdKAwIDAQAB";

		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "http://localhost:8080/ku771/notify_url.jsp";

		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String return_url = "http://localhost:8080/tradingcenter/update_trading_record.do";

		// 签名方式
		public static String sign_type = "RSA2";
		
		// 字符编码格式
		public static String charset = "utf-8";
		
		// 支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

		//参数类型
	    public static String param_type = "json";
	   

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
