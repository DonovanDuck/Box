package com.TB.push;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

public class PushTest {

	public static void main(String[] args) throws PushClientException, PushServerException {
		// TODO Auto-generated method stub
		String apiKey = "1Rab8Er6A2PG2B9c4T4ubiRV";
		String secretKey = "pesNHKnHWRLcc5y4MlApL2lAwdbmU7Gv";  
		PushKeyPair pair = new PushKeyPair(apiKey,secretKey);
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);
		
		pushClient.setChannelLogHandler (new YunLogHandler () {
		    @Override
		    public void onHandle (YunLogEvent event) {
		        System.out.println(event.getMessage());
		    }
		});
		
		PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
			    addChannelId("4516889113908580579").
			    addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600 x 5.
			    addMessageType(1).                  //设置消息类型,0表示消息,1表示通知,默认为0.
			    addMessage("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}").
			    addDeviceType(3);          //设置设备类型，3 for android, 4 for ios.
		
		PushMsgToSingleDeviceResponse response = pushClient.
				pushMsgToSingleDevice(request);
		System.out.println("msgId: " + response.getMsgId()
		+ ",sendTime: " + response.getSendTime());
		
//		try{
//		    //设置请求参数，创建请求实例
//		    //执行Http请求，获取返回结果
//		    //Http请求结果打印
//		} catch (Exception e) {
//		    /*ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
//		    *'true' 表示抛出, 'false' 表示捕获。
//		    */
//		    if (BaiduPushConstants.ERROROPTTYPE) { 
//		        throw e;
//		    } else {
//		        e.printStackTrace();
//		    }
//		    if (BaiduPushConstants.ERROROPTTYPE) {
//		        throw e;
//		    } else {
//		        System.out.println(String.format(
//		                "requestId: %d, errorCode: %d, errorMessage: %s",
//		                e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
//		    }
		
		
		
		
		
		
		
	}
	
	

}
