<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="row">


            <div class="col-md-3">
                <div class="card shadow-sm mb-3">
                    <div class="card-body text-center">
                        <#if interlocutor.avatar?? && interlocutor.avatar != "">
                            <img src="/${interlocutor.avatar}"
                                 class="rounded-circle mb-2"
                                 style="width:80px;height:80px;object-fit:cover;" alt="avatar">
                        <#else>
                            <div class="rounded-circle bg-primary d-inline-flex align-items-center
                        justify-content-center mb-2"
                                 style="width:80px;height:80px;">
              <span class="text-white fw-bold fs-3">
                ${interlocutor.name?substring(0,1)?upper_case!'?'}
              </span>
                            </div>
                        </#if>
                        <h6 class="mb-1">${interlocutor.name!''} ${interlocutor.surname!''}</h6>
                        <span class="badge bg-warning text-dark small">
            ${interlocutor.accountType!''}
          </span>
                        <hr>
                        <p class="small text-muted mb-1">
                            <i class="bi bi-envelope me-1"></i>${interlocutor.email!''}
                        </p>
                        <#if interlocutor.phoneNumber?? && interlocutor.phoneNumber != "">
                            <p class="small text-muted mb-0">
                                <i class="bi bi-telephone me-1"></i>${interlocutor.phoneNumber}
                            </p>
                        </#if>
                    </div>
                </div>

                <div class="card shadow-sm">
                    <div class="card-body">
                        <p class="small text-muted mb-1 fw-bold">Вакансия:</p>
                        <p class="small mb-0">${vacancy.name!''}</p>
                        <hr class="my-2">
                        <p class="small text-muted mb-1 fw-bold">Резюме:</p>
                        <p class="small mb-0">${ra.resume.name!''}</p>
                    </div>
                </div>
            </div>

            <!-- Правая панель: чат -->
            <div class="col-md-9">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white d-flex align-items-center">
                        <a href="javascript:history.back()" class="text-white me-3">
                            <i class="bi bi-arrow-left"></i>
                        </a>
                        <span class="fw-bold">
            ${interlocutor.name!''} ${interlocutor.surname!''}
          </span>
                    </div>

                    <!-- Блок сообщений -->
                    <div class="card-body p-3"
                         id="chatBox"
                         style="height:420px;overflow-y:auto;background:#f8f9fa;">
                        <#list messages as msg>
                            <#assign isMine = (msg.senderRole!'') == user.accountType>
                            <div class="d-flex mb-2 ${isMine?string('justify-content-end','justify-content-start')}">
                                <div class="px-3 py-2 rounded-3 shadow-sm"
                                     style="max-width:70%;
                                             background:${isMine?string('#0d6efd','#ffffff')};
                                             color:${isMine?string('#fff','#212529')};">
                                    <div class="small">${msg.content!''}</div>
                                    <div class="text-end" style="font-size:0.7rem;opacity:0.7;margin-top:2px;">
                                        ${msg.timeStamp!''}
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>

                    <!-- Поле ввода -->
                    <div class="card-footer bg-white p-2">
                        <div class="input-group">
                            <input type="text" id="msgInput" class="form-control"
                                   placeholder="Введите сообщение..." autocomplete="off">
                            <button class="btn btn-primary" id="sendBtn">
                                <i class="bi bi-send-fill"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- SockJS + STOMP -->
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
    <script>
        const respondedId = ${respondedId};
        const myRole      = '${user.accountType!''}';
        const myName      = '${user.name!''} ${user.surname!''}';

        const socket = new SockJS('/ws');
        const stompClient = Stomp.over(socket);
        stompClient.debug = null; // отключаем лог

        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/chat/' + respondedId, function (frame) {
                const msg = JSON.parse(frame.body);
                appendMessage(msg, msg.senderRole === myRole);
            });
        });

        document.getElementById('sendBtn').addEventListener('click', sendMsg);
        document.getElementById('msgInput').addEventListener('keydown', function (e) {
            if (e.key === 'Enter') sendMsg();
        });

        function sendMsg() {
            const input = document.getElementById('msgInput');
            const text  = input.value.trim();
            if (!text || !stompClient.connected) return;
            stompClient.send('/app/chat/' + respondedId, {}, text);
            input.value = '';
        }

        function appendMessage(msg, isMine) {
            const box = document.getElementById('chatBox');
            const wrapper = document.createElement('div');
            wrapper.className = 'd-flex mb-2 ' + (isMine ? 'justify-content-end' : 'justify-content-start');

            const bubble = document.createElement('div');
            bubble.className = 'px-3 py-2 rounded-3 shadow-sm';
            bubble.style.maxWidth = '70%';
            bubble.style.background = isMine ? '#0d6efd' : '#ffffff';
            bubble.style.color      = isMine ? '#fff'    : '#212529';

            const time = msg.timeStamp ? new Date(msg.timeStamp).toLocaleTimeString('ru', {hour:'2-digit', minute:'2-digit'})
                : '';

            bubble.innerHTML =
                '<div class="small">' + escHtml(msg.content) + '</div>' +
                '<div class="text-end" style="font-size:0.7rem;opacity:0.7;margin-top:2px;">' + time + '</div>';

            wrapper.appendChild(bubble);
            box.appendChild(wrapper);
            box.scrollTop = box.scrollHeight;
        }

        function escHtml(str) {
            return str.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
        }

        window.addEventListener('load', function () {
            const box = document.getElementById('chatBox');
            box.scrollTop = box.scrollHeight;
        });
    </script>
</@main.layout>