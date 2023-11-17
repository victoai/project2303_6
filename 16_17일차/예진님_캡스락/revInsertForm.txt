$(function(){
	//input을 datepicker로 설정
	$("#datepicker").datepicker({
		minDate: 1,
		changeYear: true,
		changeMonth: true,
		dateFormat: 'yy-mm-dd',
		beforeShowDay: function(date){
		let day = date.getDay();
		return [(day != 0 && day != 6)];
		}
	});
	
	$("input[name='rev_location']").on("change", function(){
		getTimeByLocationAndDate();
	})
	
	$("input[name='rev_date']").on("change", function(){
		getTimeByLocationAndDate();
	})
});

function chkCharCode(event) {
  let regExp = /[^ㄱ-ㅎ가-힣]/g;
  let ele = event.target;
  if (regExp.test(ele.value)) {
	alert("한글로 입력해주세요");
    ele.value = ele.value.replace(regExp, '');
  }
};

function save(){
		//alert("hi");
		let frm = document.frm;
		let rev_num = frm.rev_num;
		let rev_name = frm.rev_name;
		let rev_location = frm.rev_location;
		let rev_date = frm.rev_date;
		let rev_time = frm.rev_time;
		let rev_program = frm.rev_program;
		
		
		if(rev_num.value == ""){
			alert("예약번호를 입력해주세요");
			rev_num.focus();
		}
		else if(rev_name.value == ""){
			alert("이름를 입력해주세요");
			rev_name.focus();
		}
		else if(rev_location.value == ""){
			alert("위치를 선택해주세요");
			rev_location.focus();
		}
		else if(rev_date.value == ""){
			alert("날짜를 선택해주세요");
			rev_date.focus();
		}
		else if(rev_time.value == ""){
			alert("시간을 선택해주세요");
			rev_time.focus();
		}
		else if(rev_program.value == ""){
			alert("프로그램을 입력해주세요");
			rev_program.focus();
		}
		else{
			frm.submit();
			alert("예약 완료");
		}
	}
	
	function getTimeByLocationAndDate() {
		$.ajax(
			 {
				 type:"post",
				 url:"/Medi3/revInsertTimeController.do",
				 data: {
					revLocation: $("input[name='rev_location']:checked").val(),
					revDate: $("input[name='rev_date']").val()
					},
				dataType: "json",
					success: function( data){
						$('#rev_time').prop('disabled', false);
						$("#rev_time > option").each(function() {
					    $(this).css('display', 'block');
					});
	
					for(i=0;i < data.length;i++){
						$("#rev_time > option[value='"+data[i]['revTime']+"']").css('display', 'none');
					};
				 }
				 ,
				 error: function(){
					alert("error");	 
				 },		 
				 		 
			}
		 );
	}
	
	function change(){
	//alert("hi");
	if($("input:radio[id=hong]").is(":checked")){
		$("#hong1").show();
		$("#hong2").show();
		$("#gang1").hide();
		$("#gang2").hide();
		}
	else if($("input:radio[id=gang]").is(":checked")){
		$("#hong1").hide();
		$("#hong2").hide();
		$("#gang1").show();
		$("#gang2").show();
		}
	}