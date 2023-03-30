package com.example.free_pre_android

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // 알림 메시지를 받았을 때 처리할 로직 작성
        // 예상 월경일까지 남은 일수가 5일 미만인 경우에만 알림 메시지를 보냅니다.
        //if (diff < 5) {
        //    sendNotification("예상 월경일 ${diff}일 남았습니다.")
        //}
    }

    private fun sendNotification(messageBody: String) {
        // 알림 메시지를 보내는 코드 작성
        // ...
    }
}