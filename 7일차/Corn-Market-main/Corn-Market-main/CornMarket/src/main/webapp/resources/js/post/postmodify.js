// 나눔버튼 클릭시 0원으로 값 바뀜
$(document).ready(function () {
    $("#boardcheckbox").change(function () {
        if ($("#boardcheckbox").is(":checked")) {
            $("#inputonchange").val("0");
        }
    });

});

// input 태그 숨기고 label 선택시 파일업로드 창 실행하게 하기.

const imageTag = document.getElementById("chooseFile");
imageTag.addEventListener('change', function () {
    //console.log('파일선택');
    /*
    while (onnode.hasChildNodes()) {
        onnode.removeChild(onnode.firstChild);
    }
    */
    //loadImg(this);


});


/** 파일 유효성 검사 */
function getImageFiles(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;
    const imagePreview = document.querySelector('.image-preview');
    //console.log(typeof files, files);


    if ([...files].length >= 6) {
        alert('이미지는 최대 5개까지 업로드가 가능합니다.');
        return;
    }

    /** 파일 타입 유효성검사 */
    [...files].forEach(file => {
        if (!file.type.match("image/.*")) {
            alert('이미지 파일만 업로드가 가능합니다.');
            return;
        }

        /** 파일 갯수 유효성검사 */
        if ([...files].length < 6) {
            uploadFiles.push(file);
            const reader = new FileReader();
            reader.onload = (e) => {
                const preview = createElement(e, file);
                imagePreview.appendChild(preview);
            };
            reader.readAsDataURL(file);
        }
    });

}

function createElement(e, file) {
    //let fullname = document.getElementById("chooseFile").files[i].name;
    const li = document.createElement('li');
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('data-file', file.name);
    li.appendChild(img);
    var name = document.getElementById('fileName');
    name.textContent = file.name;


    img.style.width = "700px";
    img.style.height = "300px";
    img.style.objectFit = "contain";

    return li;

}

const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.upload');

//upload.addEventListener('click', () => realUpload.click());
realUpload.addEventListener('change', getImageFiles);

/** 이미지 삭제 */
// 등록 이미지 삭제 ( input file reset )

function resetInputFile($input, $preview) {
    var agent = navigator.userAgent.toLowerCase();
    if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) {
        // ie 일때
        $input.replaceWith($input.clone(true));
        $preview.empty();
    } else {
        //other
        $input.val("");
        $preview.empty();
    }
}

$(".btn-delete").click(function (event) {
    var $input = $(".real-upload");
    var $preview = $('#preview');
    resetInputFile($input, $preview);
    //console.log('삭제완료');
});

//게시판 작성 form 제출
function registerCheck() {
 	checkRegister();
}

// 유효성 검사
function checkRegister() {
	
    let title = $('#registertitle').val();
    let cate = $('#registercate').val();
    let price = $('#inputonchange').val();
    let content = $('#registercontent').val();

    if (title == null || title == "") {
        alert('제목을 입력해주세요.');
        $('#registertitle').focus();
    }
    else if (cate == null || cate == "") {
        alert('카테고리를 선택해주세요.');
        $('#registercate').focus();
    }
    else if (price == null || price == "") {
        alert('가격을 입력해주세요.');
        $('#inputonchange').focus();
    }
    else if (content == null || content == "") {
        alert('내용을 입력해주세요.');
        $('#registercontent').focus();
    }
    else {
        frmregister.submit();
        alert('등록이 완료되었습니다.');
    }
};