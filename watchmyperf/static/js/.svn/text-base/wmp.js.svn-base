
var wmp_month_names = ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", 
                      "Juillet", "Août", "Septembre", "Octobre", "Novembre", 
                      "Décembre"];
var wmp_day_names_min = ["Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa"];

var club_names = ["D",
                  "B 3",
                  "B 5",
                  "B 7",
                  "F 3",
                  "F 4",
                  "F 5",
                  "F 6",
                  "F 7",
                  "F 8",
                  "F 9",
                  "P",
                  "SW"
                  ];
var club_img = ["/img/none.png",
                  "/img/b3.png",
                  "/img/b5.png",
                  "/img/b7.png",
                  "/img/f3.png",
                  "/img/f3.png",
                  "/img/f5.png",
                  "/img/f6.png",
                  "/img/f7.png",
                  "/img/f8.png",
                  "/img/f9.png",
                  "/img/none.png",
                  "/img/none.png"
                  ];


$.fn.assistedInput = function(data) {
	return this.each(function() {
		var input = $(this);
		var selectoptions = $("<div></div>").insertAfter(input);
		selectoptions.css({
			position: "absolute",
			left: 2 + input.position().left,
			display: "none"
			});
		for(var i=0 ; i<data.length ; i++) {
			var value = data[i].value;
			var img = data[i].img;
			var item = $("<div class='selectitems'><img src='"+img+"'/></div>").appendTo(selectoptions);
			var handler = function(x){
				return function() {
					input.val(x);
					selectoptions.hide();
				}
			};
			item.bind('click', handler(value) );
		}
		$(".selectitems").mouseover(function(){
			$(this).addClass("hoverclass");
		});
		$(".selectitems").mouseout(function(){
			$(this).removeClass("hoverclass");
		});
		input.click(function(){
			if( selectoptions.is(':hidden') )
				selectoptions.show();
			else
				selectoptions.hide();
		});
	});
}


/*
$.fn.assistedInput = function(data) {
	return this.each(function() {  
		obj = $(this);  
		obj.after("<div id='selectoptions' style='position:absolute;display:none'></div>");
		for(i=0 ; i<data.length ; i++) {
			var value = data[i].value;
			var img = data[i].img;
			$("#selectoptions").append("<div class='selectitems'><img src='"+img+"' title='"+value+"'/></div>")
		}
		$(".selectitems > img").click(function(){
			obj.val( this.title );
			$("#selectoptions").hide();
		});
		$(".selectitems").mouseover(function(){
			$(this).addClass("hoverclass");
		});
		$(".selectitems").mouseout(function(){
			$(this).removeClass("hoverclass");
		});
		obj.click(function(){
			if( $("#selectoptions").is(':hidden') )
				$("#selectoptions").show();
			else
				$("#selectoptions").hide();
		});
	});  
}
*/


$.fn.customSelect = function( first_value ) {
	return this.each(function() {  
		var select = $(this);
		var hidden_input = $("<input type='hidden' value ='' name='" + this.name + "'/>").insertBefore(select);
		this.name = ""; // hack to avoid double send
		var select_replacement = $("<div><img src='" + select[0][0].title + "'/></div>").insertAfter(select);
		var pos = select.position();
		select_replacement.css({
			position: "absolute",
			left: pos.left,
			top: pos.top,
			"z-index": 999
		});
		var option_holder = $("<div></div>").insertAfter(select_replacement);
		option_holder.css({
			display:"none",
			position: "absolute",
			left: 2 + pos.left
		});
		select.find('option').each(function(i){
			var img_src = this.title;
			var value = this.value;
			var option_replacement = $("<div title='" + value + "'><img src='" + img_src + "'/></div>").appendTo(option_holder);
			option_replacement.addClass("selectitems");
			option_replacement.bind( 'click', function(){
				hidden_input.val( value );
				var img = select_replacement[0].firstChild;
				img.src = img_src;
				option_holder.hide();
			});
		});
		select_replacement.click(function(){
			option_holder.show();
		});
		$(".selectitems").mouseover(function(){
			$(this).addClass("hoverclass");
		});
		$(".selectitems").mouseout(function(){
			$(this).removeClass("hoverclass");
		});
	})  
}


/*
$.fn.customSelect = function() {
	return this.each(function() {  
		obj = $(this);  
		obj.after("<div id=\"selectoptions\"></div>");
		obj.find('option').each(function(i){ 
			$("#selectoptions").append("<div id=\"" + $(this).attr("value") + "\" class=\"selectitems\"><img src=\"" + this.title + "\" /><span>" + $(this).html() + "</span></div>");
		});
		obj.before("<input type=\"hidden\" value =\"\" name=\"" + this.name + "\" class=\"customselect\"/><div id=\"iconselect\">" + this.title + "</div><div id=\"iconselectholder\"> </div>").remove();
		$("#iconselect").click(function(){
			$("#iconselectholder").toggle("fast");
		});
		$("#iconselectholder").append( $("#selectoptions")[0] );
		$(".selectitems").mouseover(function(){
			$(this).addClass("hoverclass");
		});
		$(".selectitems").mouseout(function(){
			$(this).removeClass("hoverclass");
		});
		$(".selectitems").click(function(){
			$(".selectedclass").removeClass("selectedclass");
			$(this).addClass("selectedclass");
			var thisselection = $(this).html();
			$(".customselect").val(this.id);
			$("#iconselect").css("background-image", "none");
			$("#iconselect").html(thisselection);
			$("#iconselectholder").toggle("fast")
		});
	})  
}
*/