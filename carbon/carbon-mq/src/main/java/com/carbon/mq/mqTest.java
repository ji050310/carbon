package com.carbon.mq;

import cn.hutool.json.JSONObject;
import com.carbon.domain.common.constant.RocketMqName;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class mqTest {
        public static void main(String[] args) throws InterruptedException, MQClientException {
            String data="{\"rawData\":{\"summary\":\"支付成功\",\"create_time\":\"2022-09-15T16:30:58+08:00\",\"resource\":{\"ciphertext\":\"2PF628ZvXdIFHsPKZxw7cFawbPfTfPkISfZv0Oomk96pSo2nJBqdfkTcUg/NrK6oIdxBs4yfQf6Qy2mqkMEIt7uRITj+xXvFEHJp+8PorFjN/jVQMGKTIymqlpy64K0hI7tQSv4eABCG3e3b6D/BSbR5rWDj3r/2A1kfDqhGnCrWfkawPkftoNz1TxtUPWxtOTlDZfC6ZrXSzYflV4OMh4QVSeAZa1lNIJWHxCedU0FL0psoB3BP25oATsWKi2G0Q/Art6Xx1qD6jR+Wsw7t8hC79JeEi1D5Rg7xw2ukxwKX99dCYDpF+xDQyrnAlM9qAJxeHNNrrezgo8dMOwBE/ElMCbZnL/rNlEbhUXro7KnD4DR70jsZOlrIX7drrvkgsg8Rk2Reotw98OVms5qNeB8MM44/N3Aeyyi28LeSDRg7l2osGuUjb6jio05303GStJvwQ7nxzsG1LU6neWibCCSfiRTPqBe0LRG6tWb6ttURmew5X3fYycT8GyUC2LyjnLevCtIJcI6bx6/myJNr1r2UUY0ZpUesKopSld0Sv3rbLGnHJOVm\",\"nonce\":\"pBdOD8yj6HiD\",\"associated_data\":\"transaction\",\"original_type\":\"transaction\",\"algorithm\":\"AEAD_AES_256_GCM\"},\"resource_type\":\"encrypt-resource\",\"event_type\":\"TRANSACTION.SUCCESS\",\"id\":\"ec602883-1257-5ee9-ba8e-dbffdcad175c\"},\"result\":{\"transaction_id\":\"4200001566202209151010205989\",\"amount\":{\"payer_total\":1,\"total\":1,\"currency\":\"CNY\",\"payer_currency\":\"CNY\"},\"mchid\":\"1619787489\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"OTHERS\",\"success_time\":\"2022-09-15T16:30:58+08:00\",\"payer\":{\"openid\":\"ovDIA5spc342jC4DfACxFS6oAFVQ\"},\"out_trade_no\":\"5vbuw73h\",\"appid\":\"wxaff84490105352ea\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"JSAPI\",\"attach\":\"\"}}";

            JSONObject jsonObject=new JSONObject(data);
            JSONObject rawData=(JSONObject)jsonObject.get("rawData");
            JSONObject result=(JSONObject)jsonObject.get("result");
            System.out.println(rawData);
            System.out.println(result);
            System.out.println(result.get("transaction_id"));

        }


}
