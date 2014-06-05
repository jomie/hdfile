
$(function(){
		var curToggleBtn = null;
		var unToggleDo = false;
		$("body").bind("mouseover", function(e){
			if (unToggleDo && curToggleBtn != null)
				curToggleBtn.dropdown('toggle');
			unToggleDo = false;
		});
		$("a[local_name='toggle-file']").bind("mouseover", function(e){
			curToggleBtn = $(this);
  		$(this).dropdown('toggle');
		});
		$("a[local_name='toggle-file']").bind("mouseleave", function(){
			unToggleDo = true;
			//curToggleBtn = $(this);
  		//$(this).dropdown('toggle');
		});
		$("ul[local_name='toggle_ul']").bind("mouseover", function(){
  		unToggleDo = false;
		});
		$("ul[local_name='toggle_ul']").bind("mouseleave", function(){
  		unToggleDo = true;
  		//curToggleBtn.dropdown('toggle');
		});
});