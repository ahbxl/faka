package com.card.test;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

public class test {

    public static void main(String[] args) {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradePrecreateResponse response = Payment.FaceToFace()
                    .preCreate("Apple iPhone11 128G", "2234567891", "0.01");
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println(response.qrCode);
                System.out.println("调用成功");
            } else {
                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = "2019041663920099";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCki/TXPWTKnU6og+RYt7aGZb2sHS1+OJv93VPY5tYnOxOrHfbv+vrPuPwxXUIfjMZZ8E86RQi3OaX2uIsLKz/C8W5LBsDSgfwx9tW28UPxSDCz5TL4WYNINbH2oH/Ktz3wjz9E9Mw58CdfUN1eGIx3FOaR1qGScEWQvMXZ+AH8NJMqXD9dtk3v5kIucfjviNxfzgvnkEl5oK1mENg70cldcvxvpBAJHYAcm/DgY1eGHmyXPVbivRwMK95NOJfwOIqePt5m/tGh8PsjJq4PmQYMIbsV4JVquW3WvufwVULftmJIxgGmn2wnp56OTfpeIz6FIf5sI01BWXlqcB/whe23AgMBAAECggEBAJDa3Lo3Q+nSvhI/ueAYKsj5/BXuwcPiVgEQvOWvoUt8CH6VrHPrQK1pLirO7I0VwvAeS8ECUP/r8hzWrSEx+7nEDNJjqZAn+jqKD+4zSxq38JgN5+cV9iq1imuRM67jzdVeZdZwovFGZ4LTj21sswuKDMjKaAaga71VUd/nIuhAeSFEzCUUNHeeLcs3offnZeQPDQho0JEbstvNK31vJ5Wi3EXyww01GEuzj7e6n5pcmVZnJCJwkAgT+CnhUtsU+vXANv0nJYxk/vsLMOv8AKBkfymbA5/+UpTxYiaPMPF/Y0ucmMbqDKtDOWaUfwOH8Z7MHTLlgtse5JdqkyPQaaECgYEA9dgAIe9IibLn+bcq8DQZ6PgdnwZKGJpNgxPgIgwG9/fia81ZgzRf5n4qegpQQ04HcUFlhf9QS9BVvRnRAmE5OLJkGlzIkeCarSlI8UukdzrqLeUCCLNegXKh9jmYQlgivJxZdUI+6accB5K+z7MsmHwXmQk6Xdpd+XLEPg8DFGkCgYEAq1gsQgVmDD74K9S1s5ofJy5Ef7I0e201K4MvjWEmGr19h59yYgVhbsUOb/GnmFrJrZvcJzYqu4SdjeUrjfFKfrLE56NQBGe9G8X6C//zePU05CRR9LO5zBCa5M+JSwk6+Hehcm/G5UlEXqYXHZGvVm240Eq3EitdgpWD6x4QLR8CgYEArjXfeiyfQXy25/0Ff2vcRHu2Od9yGFcXwGAQPvA46/ULzQqaeOBfaO1U2mB+mgMsQPRvpfHTi7XZXAR0WPReYTAAwbGoK792vGh6GQtaQ2dLVTRoKZQ5zw9VeC8+Fx1vmCpPNkm4XUifEzrI3lQAsRmWe0rPwnwghcjvCW8H5qkCgYBe2a2OUpy+FLGe2d5H8P5j2xnyMn0ZAkhXsQRG2EuWq+Tsidhw6JeaER2/3F8xWLiiEVCaqEKZkX0CSqWEqtjZMC8OJ3qpJaAq2rpjoClwgRTpYi24LzGgBGUqWrSexBpP5zGJPtU0og1l872CEd6lEpInQ+T5+uWF3yyqn/bGcwKBgAoI/8EFxTXZ6JFJgvBC4eZCS+L5vq6MPvN852c19a/3fLrn/sY3IS0GIoUdQICDFsUDLXJUVQ6iH+yq/iYNhCiHGdNwnSXfRm1ON7pOcF3k99lhDBMwiMTxpFmibfyVq+GeiB6xtERo3YDPIpV9l5h2EBmzWcIlwttVQ7M8cG1g";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        // config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
        // config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
        // config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiZsrvN/juodTD1buoOKebWyn7HTEK1rGYnGqG/1DQaLjNnX8fI/ugLamkOTHhOEs43g0Q728jpcTEkkJSL+yM62x7x7aT1Tz+7pmBYqfG4C5mEVEVXUnMW59Aq+vKdb6tCUX1ol54q2t2hMhHWRF1RifSX5kEROAza1bg4J24MPXqYt9r3NM/zl7RFWTuc9HIUDMKEnzlCW3lMw9BAvYWq6sGIoGRhGCby43nrrsLfBEpFHkI3C6HRMwbrP2zXTIAieimo4tYx0yzlwTONr8li+7clqJyicYxP3qw1GS6XbHtEFZ4H8lyupVRDYIjczORn5/Owt/tqQPxStZqNtQhwIDAQAB";

        //可设置异步通知接收服务地址（可选）
        // config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "BNuD0FNyyPabIcASj2eb3Q==";

        return config;
    }
}