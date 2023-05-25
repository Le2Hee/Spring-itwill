/**
 * reply.js
 * 댓글 등록, 목록 검색, 수정, 삭제
 * /post/detail.jsp에 포함
 */

document.addEventListener('DOMContentLoaded', () => {
    // 댓글 개수 표시 영역(span)
    const replyCountSpan = document.querySelector('span#replyCount');
    
    // 댓글 목록 표시 영역(div)
    const replies = document.querySelector('div#replies');
    
    // 댓글 삭제 버튼의 이벤트 리스너 (콜백) 함수
    const deleteReply = (e) => { // 이벤트 리스너이기 때문에 아규먼트에 e가 들어간다.
//        alert(JSON.stringify(e));
        console.log(e.target); // e.target : 이벤트가 발생한 타켓. 여기서는 삭제 버튼. 
        
        if (!confirm('정말 삭제할까요?')) {
            return;
        }
        
        // 삭제할 댓글 아이디.
        const id = e.target.getAttribute('data-id');
        
        // 삭제 요청 URL
        const reqUrl = `/spring2/api/reply/${id}`;
        
        // 삭제 요청을 Ajax 방식으로 보내기.
        axios.delete(reqUrl) // delete는 보내는 방식 (get, post, ...)
            .then((reponse) => {
                
                console.log(reponse);
                alert('댓글 삭제 성공');
                getRepliesWithPostId(); // 댓글 목록 업데이트 갱신
                
            })
            .catch((error) => {
                
                console.log(error);
                alert('댓글 삭제 실패');
            });
    };
    
    // 댓글 수정 모달 객체 생성               HTML 엘리먼트, 옵션(기능)
    const modal = new bootstrap.Modal('div#replyUpdateModal', {backdrop: false});
    
    // 모달 엘리먼트 찾기
    const modalInput = document.querySelector('input#modalReplyId');
    const modalTextarea = document.querySelector('textarea#modalReplyText');
    const modalBtnUpdate = document.querySelector('button#modalBtnUpdate');
    
    // 댓글 수정 버튼의 이벤트 리스너 (콜백) 함수
    const showUpdateModel = (e) => { 
        // e: 이벤트 객체, e.target: 이벤트가 발생한 타겟, 여기서는 수정 버튼
        const id = e.target.getAttribute('data-id');
        const reqUrl = `/spring2/api/reply/${id}`;
        
        axios.get(reqUrl) // 서버로 GET 방식의 Ajax 요청을 보냄.
            .then((response) => { // 성공 응답이 왔을 때 실행할 콜백을 등록
                                  // response안에 데이터가 reply data가 된다.
                const {id, replyText} = response.data;
                // response에 포함된 데이터 객체 안에 있는 
                //                  키값인 id를 데이터를 replyText에 넣어준다.
                // Reply dto 클래스에 있는 변수와 동일
                
                // 찾은 id, replyText를 input과 area에 채워준다.
                modalInput.value = id;
                modalTextarea.value = replyText;
                
                modal.show(); // 모달 보여주기
            })
            .catch((error) => console.log(error)); // 실패 응답이 왔을 때 실행할 콜백을 등록
        
    };
    
    const updateReply = (e) => {
        // 수정할 댓글 아이디
        const id = modalInput.value;
        
        // 수정할 댓글 내용
        const replyText = modalTextarea.value;
        
        // PUT 방식의 Ajax 요청을 보내기.
        const reqUrl = `/spring2/api/reply/${id}`;
        const data = { replyText }; // { key: value }, { replyText: replyText }
        // 변수이름과 동일한 경우는 간단화 가능한다.
        
        // 요청에 대한 성공, 실패 콜백
        axios.put(reqUrl, data)
            .then((response) => {
                console.log(response);
                alert(`댓글 업데이트 성공(${response.data})`);
                getRepliesWithPostId(); // 갱신하기
            })
            .catch((error) => console.log(error))
            .finally(() => modal.hide());
        
    };
    
    // 모달에서 [저장] 버튼 이벤트 리스너 등록.
    modalBtnUpdate.addEventListener('click', updateReply);
    
    // 댓글 목록 HTML을 작성하고 replies에 추가하는 함수.
    // argument data : Ajax 요청의 응답으로 전달받은 데이터.
    const makeReplyElements = (data) => {
        // 댓글 개수 업데이트
        replyCountSpan.innerHTML = data.length; // 배열 길이(원소 개수)
        
        replies.innerHTML = ''; // <div>의 컨텐트를 지워버리는 것.
        
        let htmlStr = ''; // 문자열을 계속 덮붙이기 때문에 변한다. -> let
        
        // for (let i = 0; i < data.length; i++) {}
        // for (let x in data) {} -> in은 배열에서 인덱스를 iteration
        for (let reply of data) { // of은 배열에서 원소를 iteration
            console.log(reply);
            
            // Timestamp 타입 값을 날짜/시간 타입 문자열로 변환.
            const modified = new Date(reply.modifiedTime).toLocaleString();
                
            // 댓글 한개를 표시할 HTML 코드.
            htmlStr += `
                <div class="card">
                    <div>
                        <span class="d-none">${reply.id}</span>
                        <span class="fw-bold">${reply.writer}</span>
                        <span class="text-secondery">${modified}</span>
                    </div>
                    <div>
                        ${reply.replyText}
                    </div>
                    <div>
                        <button class="btnDelete btn btn-outline-danger" data-id="${reply.id}">
                        삭제
                        </button>
                        <button class="btnModify btn btn-outline-success" data-id="${reply.id}">
                        수정
                        </button>
                    </div>
                </div>
            `;
            
        }
        
        // 작성된 HTML코드를 replies <div> 영역 안에 포함.
        replies.innerHTML = htmlStr;
        
        // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 등록.
        const deleteButtons = document.querySelectorAll('button.btnDelete');
        
        for (let btn of deleteButtons) {
            btn.addEventListener('click', deleteReply);
        }
        
        // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 등록
        const modifyButtons = document.querySelectorAll('button.btnModify');
        
        for (let btn of modifyButtons) {
            btn.addEventListener('click', showUpdateModel);
        }
        
    }; 
    
    const getRepliesWithPostId = async () => { // async 문법
        
        // 댓글 목록을 요청하기 위한 포스트 번호(아이디)
        const postId = document.querySelector('input#id').value;
        
        // 댓글 목록을 요청할 URL
        const reqUrl = `/spring2/api/reply/all/${postId}`;
        
        // Ajax 요청을 보내고 응답을 기다림.
        try {
            const response = await axios.get(reqUrl);
            console.log(response);
            // 댓글 개수 업데이트 & 댓글 목록 보여주기
            makeReplyElements(response.data);
        } catch (error) {
            console.log(error);
        }
        
    };
    
    // 부트스트랩 Collapse 객체를 생성 -> false: 초기 상태는 화면에서 안보이는 상태.
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', { toggle: false });

    // 버튼 아이콘 이미지
    const toggleBtnIcon = document.querySelector('img#toggleBtnIcon');

    const btnToggleReply = document.querySelector('button#btnToggleReply');

    // 댓글 목록/ 목록보이기/ 숨기기 토글 버튼에 이벤트 리스노를 등록
    btnToggleReply.addEventListener('click', () => {
        bsCollapse.toggle();

        if (toggleBtnIcon.alt === 'toggle-off') {
            toggleBtnIcon.src = '/spring2/static/icons/toggle2-on.svg';
            toggleBtnIcon.alt = 'toggle-on';

            // 댓글 전체 목록을 서버에 요청하고, 응답이 오면 화면 갱신.
            getRepliesWithPostId();

        } else {
            toggleBtnIcon.src = '/spring2/static/icons/toggle2-off.svg';
            toggleBtnIcon.alt = 'toggle-off';
            
        }
    });
    
    // 댓글 등록 버튼
    const btnAddReply = document.querySelector('button#btnAddReply');

    const createReply = (e) => {
        const postId = document.querySelector('input#id').value; // id에 들어가있는 값을 읽음.
        const replyText = document.querySelector('textarea#replyText').value;
        const writer = document.querySelector('input#writer').value;

        if (replyText === '') { // 댓글 내용에 아무것도 없을 때
            alert('댓글 내용을 입력하세요.');
            return;
        }

        //Object 선언
        const data = { postId, replyText, writer, }; // 변수 이름과 값을 똑같이 사용하겠다.
        // 다를 때 : postId = postId, 라고 선언해야된다.

        axios.post('/spring2/api/reply', data) // axios라이브러리를 이용해서 post요청을 보내겠다, 
            //         요청을 보낼 주소, 보낼 데이터
            .then((response) => { // post요청을 보낼때 성공callback 함수 등록
                alert(`댓글 등록 성공(${response.data})`);
                
                // 성공callback: 아규먼트 전달하면 어떤 결과가 나오면 된다.
                // console.log(response);

                // 댓글 입력 창의 내용을 지움.
                document.querySelector('textarea#replyText').value = '';

                getRepliesWithPostId();
            })

            .catch((error) => {// 요청을 보냈을 때 에러가 났을때 실행하는 함수 등록
                // 에러callback: 야규먼트 전달하면 어떤 결과가 나오면 된다.
                console.log(error);
            });

        // .finally(); // callback이든 에러가 나든 무조건 실행하는 함수. 필요없으면 안써도됨.

    };


    btnAddReply.addEventListener('click', createReply);

});
