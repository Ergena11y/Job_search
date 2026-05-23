

(function () {
    'use strict';

    function escHtml(str) {
        return String(str)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;');
    }

    function formatTime(isoString) {
        if (!isoString) return '';
        try {
            return new Date(isoString).toLocaleTimeString('ru', { hour: '2-digit', minute: '2-digit' });
        } catch {
            return '';
        }
    }



    const chatBox  = document.getElementById('chatBox');
    const msgInput = document.getElementById('msgInput');
    const sendBtn  = document.getElementById('sendBtn');

    function appendMessage(msg, isMine) {
        const wrapper = document.createElement('div');
        wrapper.className = 'd-flex mb-2 ' + (isMine ? 'justify-content-end' : 'justify-content-start');

        const bubble = document.createElement('div');
        bubble.className = 'chat-bubble ' + (isMine ? 'chat-bubble--mine' : 'chat-bubble--theirs');
        bubble.innerHTML =
            '<div class="small">' + escHtml(msg.content || '') + '</div>' +
            '<div class="chat-bubble__time">' + formatTime(msg.timeStamp) + '</div>';

        wrapper.appendChild(bubble);
        chatBox.appendChild(wrapper);
        chatBox.scrollTop = chatBox.scrollHeight;
    }


    const socket      = new SockJS('/ws');
    const stompClient = Stomp.over(socket);
    stompClient.debug = null;

    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/chat/' + CHAT_RESPONDED_ID, function (frame) {
            const msg = JSON.parse(frame.body);
            appendMessage(msg, msg.senderRole === CHAT_MY_ROLE);
        });
    });



    function sendMsg() {
        const text = msgInput.value.trim();
        if (!text || !stompClient.connected) return;
        stompClient.send('/app/chat/' + CHAT_RESPONDED_ID, {}, text);
        msgInput.value = '';
    }

    sendBtn.addEventListener('click', sendMsg);
    msgInput.addEventListener('keydown', function (e) {
        if (e.key === 'Enter') sendMsg();
    });



    window.addEventListener('load', function () {
        chatBox.scrollTop = chatBox.scrollHeight;
    });

})();