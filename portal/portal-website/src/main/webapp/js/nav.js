// JavaScript Document
 $(document).ready(function(){
							
$(".servemenu_1 li a").click(function(){
								 
		var c=$(this).parent().parent().attr('class');
		$("."+c+" li").children("ul").slideUp(500);
		$("."+c+" li").children("i").removeClass("serverr"); 
		if($(this).parent().children("ul").css("display")=="none")
		{
			$(this).parent().children("ul").slideDown(500);
			$(this).parent().children("i").addClass("serverr"); 
		}else{
			
			$(this).parent().children("ul").slideUp(500);
			
		}

});




});