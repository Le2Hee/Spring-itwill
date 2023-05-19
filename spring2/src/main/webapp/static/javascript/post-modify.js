/**
 * post-modify.js
 */

document.addEventListener('DOMContentLoaded', function() {
    
    const inputId = document.querySelector('input#id');
    const inputTitle = document.querySelector('input#title');
    const textareaContent = document.querySelector('textarea#content');
    const inputAuthor = document.querySelector('input#author');
    
    const form = document.querySelector('#modifyForm');
    
    const buttonUpdate = document.querySelector('button#btnUpdate');
    const buttonDelete = document.querySelector('button#btnDelete');
    
    buttonDelete.addEventListener('click', (e) => {
        e.preventDefault;
        
        const id = inputId.value;
        const result = confirm(`${id}를 삭제하시겠습니까?`);
        
        if(result) {
            form.action = 'delete';
            form.method = 'post';
            form.submit();
        }
        
    });
    
    buttonUpdate.addEventListener('click', (e) => {
        e.preventDefault;
        
        const id = inputId.value;
        const title = inputTitle.value;
        const content = textareaContent.value;
        
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return;
        }
        
        const result = confirm(`${id}번 post를 업데이트할까요?`);
        
        if (result) {
            form.action = 'update';
            form.method = 'post';
            form.submit();
        }
        
        
    });
    
});