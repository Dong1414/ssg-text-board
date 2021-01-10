function ArticleDetail__Body__init() {
	if ( toastui == undefined ) {
		return;
	}

	var body = document.querySelector('.main-body');
	var initialValue = body.innerHTML.trim();

	var viewer = new toastui.Editor.factory({
		el : body,
		initialValue : initialValue,
		viewer : true,
		 plugins: [toastui.Editor.plugin.codeSyntaxHighlight]
	});
}

ArticleDetail__Body__init();
/* 스크롤 이벤트 */
$(window).on('scroll',function(){
    if($(window).scrollTop() >= 25){
        $(".topbutton").fadeIn(400);
    }else{
        $(".topbutton").fadeOut(400);
    }
    });
/* 스크롤 이벤트 끝 */
