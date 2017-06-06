var mainUrl = '';
//取Cookie的值  
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg)) return unescape(arr[2]);
	else return null;
}
$(function() {
	if(getCookie('token') == null) {
		if(confirm("登录已失效，是否跳转到重新登录？")) {
			window.parent.location.href = "login.html";
		}
	};
//	 $('#myTab li:eq(2) a').tab('show');
	pageInitModule.setWidth();
	pageInitModule.setSidebar();
	pageInitModule.setCarousel();
})
$(window).resize(function() {
	pageInitModule.setWidth();
})
$(window).scroll(function() {
	pageInitModule.setScrollToTop();
});

//写入到Cookie  
//name:cookie名称  value:cookie值   
function SetCookie(name, value) {  
    var Days = 30;  
    var exp = new Date();  
    exp.setTime(exp.getTime() + 7*24*60*60*1000);//过期时间7天
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();  
}; 

//取Cookie的值  
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg)) return unescape(arr[2]);
	else return null;
};

/*
 * init page when page load
 */
var pageInitModule = (function(mod) {
	mod.setCarousel = function() {
		try {
			$('.carousel').hammer().on('swipeleft', function() {
				$(this).carousel('next');
			});
			$('.carousel').hammer().on('swiperight', function() {
				$(this).carousel('prev');
			});
		} catch(e) {
			console.log("you mush import hammer.js and jquery.hammer.js to let the carousel can be touched on mobile");
		}
	};
	mod.setWidth = function() {
		if($(window).width() < 768) {
			$(".sidebar").css({
				left: -80
			});
			$(".all").css({
				marginLeft: 0
			});
		} else {
			$(".sidebar").css({
				left: 0
			});
			$(".all").css({
				marginLeft: 80
			});
		}
	};
	mod.setScrollToTop = function() {
		var top = $(window).scrollTop();
		if(top < 60) {
			$('#goTop').hide();
		} else {
			$('#goTop').show();
		}
	};
	mod.setSidebar = function() {
		$('[data-target="sidebar"]').click(function() {
			var asideleft = $(".sidebar").offset().left;
			if(asideleft == 0) {
				$(".sidebar").animate({
					left: -80
				});
				$(".all").animate({
					marginLeft: 0
				});
			} else {
				$(".sidebar").animate({
					left: 0
				});
				$(".all").animate({
					marginLeft: 80
				});
			}
		});
		$(".has-sub>a").click(function() {
			$(this).parent().siblings().find(".sub-menu").slideUp();
			$(this).parent().find(".sub-menu").slideToggle();
		})
		var _strcurrenturl = window.location.href.toLowerCase();
		$(".navbar-nav a[href],.sidebar a[href]").each(function() {
			var href = $(this).attr("href").toLowerCase();
			var isActive = false;
			$(".breadcrumb>li a[href]").each(function(index) {
				if(href == $(this).attr("href").toLowerCase()) {
					isActive = true;
					return false;
				}
			})
			if(_strcurrenturl.indexOf(href) > -1 || isActive) {
				$(this).parent().addClass("active");
				if($(this).parents("ul").attr("class") == "sub-menu") {
					$(this).parents("ul").slideDown();
					$(this).parents(".has-sub").addClass("active");
				}
			}
		})
	}
	return mod;
})(window.pageInitModule || {});