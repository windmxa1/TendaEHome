/**
 * 订单数据请求(取消列表)
 */
var json = null;
var total = null;
var indexId = 0;
var pageSize = 20;
var pageNo = 1;
$(function() {
	// refreshData(20,1,10,json.length);
	builderUQTQueryMsg(getJsonArrayByPageSize(pageSize, pageNo));

	var totalPage = getTotllePage(pageSize);
	var totalRecords = total;
	// 生成分页控件 根据分页的形式在这里设置
	kkpager.init({
		pno : pageNo,
		// 总页码
		total : totalPage,
		// 总数据条数
		totalRecords : totalRecords,
		// 页面条数
		pageSize : pageSize
	});
	kkpager.generPageHtml();

});

function selectOrder() {
	var pageSize = 20;
	var pageNo = 1;
	builderUQTQueryMsg(getJsonArrayByPageSize(pageSize, pageNo));
	var totalPage = getTotllePage(pageSize);
	var totalRecords = total;
	// 生成分页控件 根据分页的形式在这里设置
	kkpager.init({
		pno : pageNo,
		// 总页码
		total : totalPage,
		// 总数据条数
		totalRecords : totalRecords,
		// 页面条数
		pageSize : pageSize
	});
	kkpager.generPageHtml();
};
/** ************************* 控制图片自适应开始******************************** */
function AutoResizeImage(maxWidth, maxHeight, objImg) {
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth == 0 && maxHeight == 0) {
		Ratio = 1;
	} else if (maxWidth == 0) {//
		if (hRatio < 1)
			Ratio = hRatio;
	} else if (maxHeight == 0) {
		if (wRatio < 1)
			Ratio = wRatio;
	} else if (wRatio < 1 || hRatio < 1) {
		Ratio = (wRatio <= hRatio ? wRatio : hRatio);
	}
	if (Ratio < 1) {
		w = w * Ratio;
		h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
}
/** ************************* 控制图片自适应开始******************************** */

/** ************************** 鼠标跟随显示超链接图片开始*************************** */
var c$ = {};
var w$ = function(s) {
	document.write(s);
}
var o$ = function(id) {
	return document.getElementById(id);
}
w$("<div id=\"ts\" style=\"position:absolute;background-color:#FFFFE6;font-size: 12px;padding: 3px; border: 1px solid #FFCC99;display:none\"></div>");
function moveon(o) {
	var evt = event || window.event;
	var x = evt.clientX + 5;
	var y = evt.clientY + 20;
	o$("ts").style.left = x + "px";
	o$("ts").style.top = y + "px";
	o$("ts").innerHTML = '<img src="' + o.innerHTML + '" alt="">';
	o$("ts").style.display = "";
}
c$.mout = function() {
	o$("ts").style.display = "none";
}
/** ************************* 鼠标跟随显示超链接图片结束*************************** */

/** *******************************列表分页************************************** */

/**
 * 获取总页数
 * 
 * @returns {number}
 */
var getTotllePage = function(pageSize) {
	var jsonCount = total;
	var shang = jsonCount / pageSize;
	var yushu = jsonCount % pageSize;
	if (yushu > 0) {
		shang++;
	}
	return shang;
}
/**
 * 获取分页后的数据
 * 
 * @param pageSize分页后的条目数
 * @param pageNo当前第几页
 * @returns {*}
 */
var getJsonArrayByPageSize = function(pageSize, pageNo) {
	if (indexId == 1) {
		$.ajax({
			type : "post",
			url : "back/tv/getTvByName",
			dataType : "json",
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			data : {
				'start' : (pageNo - 1) * pageSize,
				'limit' : pageSize,
				'name' : $('#seek_text').val()
			},
			success : function(data) {
				if (data.code == 100) {
					json = data.data.list;
					total = data.data.total;
				}
			},
			error : function(jqXHR) {
				alert("网络异常");
			}
		});
	} else {
		$.ajax({
			type : "post",
			url : "back/tv/getTvList",
			dataType : "json",
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			data : {
				'start' : (pageNo - 1) * pageSize,
				'limit' : pageSize,
				'type' : $('.selectType option:selected').val() || null
			},
			success : function(data) {
				if (data.code == 100) {
					json = data.data.list;
					total = data.data.total;
				}
			},
			error : function(jqXHR) {
				alert("网络异常");
			}
		});
	}
	var jsonCount = total;
	var shang = getTotllePage(pageSize);
	// var startIndex = (pageNo);
	// var endIndex = (shang == pageNo)? jsonCount:pageSize;
	return json.slice(0, 20);
}
/**
 * 重置搜索
 */
function reset() {
	indexId = 0;
	builderUQTQueryMsg(getJsonArrayByPageSize(pageSize, pageNo));
	var totalPage = getTotllePage(pageSize);
	var totalRecords = total;
	// 生成分页控件 根据分页的形式在这里设置
	kkpager.init({
		pno : pageNo,
		// 总页码
		total : totalPage,
		// 总数据条数
		totalRecords : totalRecords,
		// 页面条数
		pageSize : pageSize
	});
	kkpager.generPageHtml();
}
/**
 * 搜索节目
 */
function seek() {
	indexId = 1;
	builderUQTQueryMsg(getJsonArrayByPageSize(pageSize, pageNo));
	var totalPage = getTotllePage(pageSize);
	var totalRecords = total;
	// 生成分页控件 根据分页的形式在这里设置
	kkpager.init({
		pno : pageNo,
		// 总页码
		total : totalPage,
		// 总数据条数
		totalRecords : totalRecords,
		// 页面条数
		pageSize : pageSize
	});
	kkpager.generPageHtml();

};
/**
 * 刷新页面数据
 * 
 * @param pageSize
 *            每页显示条数
 * @param pageNum
 *            第几页
 */
function refreshData(pageSize, pageNo) {
	builderUQTQueryMsg(getJsonArrayByPageSize(pageSize, pageNo));

	var totalPage = getTotllePage(pageSize);
	var totalRecords = total;
	// 生成分页控件 根据分页的形式在这里设置
	kkpager.init({
		pno : pageNo,
		// 总页码
		total : totalPage,
		// 总数据条数
		totalRecords : totalRecords,
		// 页面条数
		pageSize : pageSize
	});
	kkpager.generPageHtml();

}

/**
 * 构建表格数据
 */
var builderUQTQueryMsg = function(UQTQueryMsg) {
	var UQT_detailTable = $('#UQT_detailTable');
	UQT_detailTable.empty();
	var th = '<tr><th scope="col"  class="dis_order">名字</th><th scope="col" class="match_type" >台标</th><th scope="col" class="match_type">直播地址</th><th scope="col" class="query_pro">修改时间</th><th scope="col" class="dis_dta">操作</th><th class="dis_hidden" style="display: none">隐藏属性</th></tr>';

	UQT_detailTable.append(th);
	var tr;
	$
			.each(
					UQTQueryMsg,
					function(i, eachData) {
						tr = $('<tr>');
						var id = eachData.id;
						var name = eachData.name;
						var imgUrl = eachData.imgUrl;
						var tvUrl = eachData.tvUrl;
						var createTime = eachData.createTime;
						var available = eachData.available;
						var trString = "<td class='dis_order'>"
								+ name
								+ "</td>"
								+ "<td class='match_type'>"
								+ "<a href='#' onmousemove='moveon(this);' onmouseout='c$.mout();'>"
								+ imgUrl + "  </a>" + "</td>"
								+ "<td class='match_type'>" + tvUrl + "</td>"
								+ "<td class='match_type'>" + createTime
								+ "</td>" + "<td class='dis_dta'>";
						if (available == 1) {
							trString = trString
									+ "<button class='btn btn-info' onclick='toggleTv("
									+ id
									+ ","
									+ available
									+ ")' style='height:30px;margin-right:10px'>隐藏</button>";
						} else {
							trString = trString
									+ "<button class='btn btn-warning' onclick='toggleTv("
									+ id
									+ ","
									+ available
									+ ")' style='height:30px;margin-right:10px'>显示</button>";
						}
						trString = trString
								+ "<button data-toggle='modal' id='modTv' data-target='#modOrder' style='height:30px;margin-right:10px' class='btn' data-order='"
								+ JSON.stringify(eachData)
								+ "'>修改</button><button class='btn' id='delTv' style='height:30px;margin-right:10px' data-oid='"
								+ id + "'>删除</button></td>";
						tr.append(trString);
						UQT_detailTable.append(tr);
					});
};

function toggleTv(id, available) {
	$.ajax({
		type : "post",
		url : "back/tv/updateTvAvailalbe",
		data : {
			available : available,
			id : id
		},
		async : false,
		cache : false,
		headers : {
			"token" : getCookie('token')
		},
		success : function(data) {
			if (data.code == 100) {
				location.reload();
				alert('操作成功');
			} else {
				alert(data.msg);
			}
		},
		error : function(data) {
			alert("error");
		}
	});
}
// 修改直播列表
$(document).on('click', '#modTv', function() {
	var order = $(this).data('order');
	$('#xg_id').val(order.id);
	$('#xg_name').val(order.name);
	$('#xg_available').val(order.available);
	$('#xg_tvUrl').val(order.tvUrl);
	$('#xg_createTime').val(order.createTime);
});

function toJson(x) {
	var o = {};
	var a = x.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return JSON.stringify(o);
}
// 添加电视节目
function tjtv() {
	var formData = new FormData(document.getElementById("tjtv_form"));
	$.ajax({
		type : "post",
		url : "back/tv/addTv",
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		dataType : "json",
		headers : {
			"token" : getCookie('token')
		},
		success : function(data) {
			if (data.code == 100) {
				$('#tjtv').modal('hide');
				location.reload();
				alert('添加成功');
			} else {
				alert(data.msg);
			}
		},
		error : function(data) {
			alert("error");
		}
	});

};
// 修改电视节目
$(document).on('click', '#modTv_btn', function() {
	var formData = new FormData(document.getElementById("modTv_form"));
	$.ajax({
		type : "post",
		url : "back/tv/updateTv",
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		beforeSend : function(request) {
			request.setRequestHeader("token", getCookie('token'));
		},
		success : function(data) {
			if (data.code == 100) {
				alert('修改成功');
				location.reload();
			} else {
				alert(data.msg);
			}
		},
		error : function(data) {
			alert("网络不给力,修改失败");
		}
	});
});

// 删除直播节目
$(document).on('click', '#delTv', function() {
	var oId = $(this).data('oid');
	if (confirm('确定要删除这个直播节目吗')) {
		$.ajax({
			type : "post",
			url : "back/tv/deleteTv",
			data : {
				'id' : oId
			},
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			success : function(data) {
				if (data.code == 100) {
					alert('删除成功');
					location.reload();
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("网络不给力,删除失败");
			}
		});
	}
});

/**
 * 选择左侧checkbox
 * 
 */
var optionCheckBoxes = function(data) {
	var checkType = $(data).is(':checked');
	var trs = $(data).parent().parent().parent().nextAll();

	if (checkType) {
		// 全部选择
		trs.each(function(trNode) {
			var currentCheck = $(this).find('>td span input[type="checkbox"]');
			// alert(currentCheck.is(':checked'));
			currentCheck.attr("checked", true);
		});
	} else {
		// 全部取消选择
		trs.each(function(trNode) {
			var currentCheck = $(this).find('>td span input[type="checkbox"]');
			// alert(currentCheck.is(':checked'));
			currentCheck.attr("checked", false);
		});
	}
}

function changeTime(ts) { // 时间戳转时间函数
	// var timestamp = new Date(parseInt(ts) *
	// 1000).toLocaleString().replace(/年|月/g, "-").substr(0, 8);
	var date = new Date(parseInt(ts) * 1000);
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var getHours = date.getHours(); // 时
	var getMinutes = date.getMinutes(); // 分
	var getSeconds = date.getSeconds(); // 秒
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}

	if (getHours >= 1 && getHours <= 9) {
		getHours = "0" + getHours;
	}
	if (getMinutes >= 1 && getMinutes <= 9) {
		getMinutes = "0" + getMinutes;
	}
	if (getSeconds >= 1 && getSeconds <= 9) {
		getSeconds = "0" + getSeconds;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1
			+ strDate + " " + getHours + seperator2 + getMinutes + seperator2
			+ getSeconds;
	// $('#eTime').val(currentdate.substr(0,10));
	return currentdate.substr(0, 10);

}

/**
 * Created by huipu on 2016/1/28. 分页插件
 */
var kkpager = {
	// divID
	pagerid : 'div_pager',
	// 当前页码
	pno : 1,
	// 总页码
	total : 1,
	// 总数据条数
	totalRecords : 0,
	// 是否显示总页数
	isShowTotalPage : true,
	// 是否显示总记录数
	isShowTotalRecords : true,
	// 是否显示页码跳转输入框
	isGoPage : true,
	// 页面条数默认大小
	pagesize : 20,
	// 链接前部
	hrefFormer : '',
	// 链接尾部
	hrefLatter : '',
	/** **链接算法*** */
	getLink : function(n) {
		// 这里的算法适用于比如：
		// hrefFormer=http://baidu.com/news/20131212
		// hrefLatter=.html
		// 那么首页（第1页）就是http://baidu.com/news/20131212.html
		// 第2页就是http://baidu.com/news/20131212_2.html
		// 第n页就是http://baidu.com/news/20131212_n.html
		if (n == 1) {
			return this.hrefFormer + this.hrefLatter;
		} else {
			return this.hrefFormer + '_' + n + this.hrefLatter;
		}
	},
	// 跳转框得到输入焦点时
	focus_gopage : function() {
		var btnGo = $('#btn_go');
		$('#btn_go_input').attr('hideFocus', true);
		btnGo.show();
		btnGo.css('left', '0px');
		$('#go_page_wrap').css('border-color', '#6694E3');
		btnGo.animate({
			left : '+=44'
		}, 50, function() {
			// $('#go_page_wrap').css('width','88px');
		});
	},

	// 跳转框失去输入焦点时

	blur_gopage : function() {
		setTimeout(function() {
			var btnGo = $('#btn_go');
			// $('#go_page_wrap').css('width','44px');
			btnGo.animate({
				left : '-=44'
			}, 100, function() {
				$('#btn_go').css('left', '0px');
				$('#btn_go').hide();
				$('#go_page_wrap').css('border-color', '#DFDFDF');
			});
		}, 400);
	},
	// 根据页数刷新页面数据
	gopage : function(data) {
		var currentPage = '';
		if (data.tagName == 'A') {
			// 点击的是a标签，这里写你的代码
			var tempPage = $(data).html();
			if (tempPage == '下一页') {
				currentPage = parseInt(this.pno) + 1;
			} else if (tempPage == '上一页') {
				currentPage = parseInt(this.pno) - 1;
			} else {
				currentPage = tempPage;
			}
		} else {
			// 手动输入要跳转的页数
			var str_page = $("#btn_go_input").val();
			if (isNaN(str_page)) {
				$("#btn_go_input").val(this.next);
				return;
			}
			var n = parseInt(str_page);
			if (n < 1 || n > this.total) {
				$("#btn_go_input").val(this.next);
				return;
			}
			currentPage = n;
		}
		refreshData(this.pagesize, currentPage);
		// 不管是点击页码还是手动输入页码，都要将下一页的页码选中
		// 此时，只需要重新加载本组件即可
	},
	changepageSize : function(data) {
		// 更改每页显示条数
		// 刷新数据时初始化页数为1，
		var pageSize = $(data).val();
		// alert('要初始化的页面条数为:'+pageSize);
		refreshData(pageSize, 1);
	},
	// 分页按钮控件初始化
	init : function(config) {

		// 页面条数初始化
		this.pagesize = config.pageSize;
		// 赋值
		this.pno = isNaN(config.pno) ? 1 : parseInt(config.pno);
		this.total = isNaN(config.total) ? 1 : parseInt(config.total);
		this.totalRecords = isNaN(config.totalRecords) ? 0
				: parseInt(config.totalRecords);
		if (config.pagerid) {
			this.pagerid = pagerid;
		}
		if (config.isShowTotalPage != undefined) {
			this.isShowTotalPage = config.isShowTotalPage;
		}
		if (config.isShowTotalRecords != undefined) {
			this.isShowTotalRecords = config.isShowTotalRecords;
		}
		if (config.isGoPage != undefined) {
			this.isGoPage = config.isGoPage;
		}
		this.hrefFormer = config.hrefFormer || '';
		this.hrefLatter = config.hrefLatter || '';
		if (config.getLink && typeof (config.getLink) == 'function') {
			this.getLink = config.getLink;
		}
		// 验证
		if (this.pno < 1)
			this.pno = 1;
		this.total = (this.total <= 1) ? 1 : this.total;
		if (this.pno > this.total)
			this.pno = this.total;
		this.prv = (this.pno <= 2) ? 1 : (this.pno - 1);
		this.next = (this.pno >= this.total - 1) ? this.total : (this.pno + 1);
		this.hasPrv = (this.pno > 1);
		this.hasNext = (this.pno < this.total);

		this.inited = true;
	},
	// 生成分页控件Html
	generPageHtml : function() {
		if (!this.inited) {
			return;
		}

		var str_prv = '', str_next = '';
		if (this.hasPrv) {
			str_prv = '<a href="javascript:void(0);"  onclick="kkpager.gopage(this);" title="上一页">上一页</a>';
			// str_prv = '<a href="javascript:void(0);""javascript:void(0);""' +
			// this.getLink(this.prv) + '" title="上一页">上一页</a>';
		} else {
			str_prv = '<span class="disabled">上一页</span>';
		}

		if (this.hasNext) {
			str_next = '<a href="javascript:void(0);" onclick="kkpager.gopage(this);"  title="下一页">下一页</a>';
			// str_next = '<a href="javascript:void(0);""javascript:void(0);""'
			// + this.getLink(this.next) + '" title="下一页">下一页</a>';
		} else {
			str_next = '<span class="disabled">下一页</span>';
		}

		var str = '';
		var dot = '<span>...</span>';
		var total_info = '';
		if (this.isShowTotalPage || this.isShowTotalRecords) {
			total_info = '<span class="normalsize">共';
			if (this.isShowTotalPage) {
				total_info += this.total + '页';
				if (this.isShowTotalRecords) {
					total_info += '&nbsp;/&nbsp;';
				}
			}
			if (this.isShowTotalRecords) {
				total_info += this.totalRecords + '条数据';
			}

			total_info += '</span>';
		}

		var pageSize = '<select id="select_pagesize" onchange="kkpager.changepageSize(this)">';

		if (this.pagesize == 10)
			pageSize += '<option selected="selected" value="10" >10</option>';
		else
			pageSize += '<option  value="10" >10</option>';
		if (this.pagesize == 15)
			pageSize += '<option selected="selected" value="15" >15</option>';
		else
			pageSize += '<option  value="15" >15</option>';
		if (this.pagesize == 20)
			pageSize += '<option selected="selected" value="20" >20</option>';
		else
			pageSize += '<option  value="20" >20</option>';
		// if(this.pagesize == 50)
		// pageSize += '<option selected="selected" value="50" >50</option>';
		// else
		// pageSize += '<option value="50" >50</option>';
		// if(this.pagesize == 75)
		// pageSize += '<option selected="selected" value="75" >75</option>';
		// else
		// pageSize += '<option value="75" >75</option>';
		// if(this.pagesize == 100)
		// pageSize += '<option selected="selected" value="100" >100</option>';
		// else
		// pageSize += '<option value="100" >100</option>';
		pageSize += '</select><span class="normalsize" >条/页</span>';

		var gopage_info = '';
		if (this.isGoPage) {
			gopage_info = '&nbsp;<span class="normalsize" >转到</span><span id="go_page_wrap" style="display:inline-block;width:44px;height:18px;border:1px solid #DFDFDF;margin:0px 1px;padding:0px;position:relative;left:0px;top:5px;">'
					+ '<input type="button" id="btn_go" onclick="kkpager.gopage(this);" style="width:44px;height:20px;line-height:20px;padding:0px;font-family:arial,宋体,sans-serif;text-align:center;border:0px;background-color:#0063DC;color:#FFF;position:absolute;left:0px;top:-1px;display:none;" value="确定" />'
					+ '<input type="text" id="btn_go_input" onfocus="kkpager.focus_gopage()" onkeypress="if(event.keyCode<48 || event.keyCode>57)return false;" onblur="kkpager.blur_gopage()" style="width:42px;height:23px;text-align:center;border:0px;position:absolute;left:0px;top:0px;outline:none;" value="'
					+ this.pno
					+ '" /></span><span class="normalsize" >页</span>';
		}

		// 分页处理
		if (this.total <= 8) {
			for ( var i = 1; i <= this.total; i++) {
				if (this.pno == i) {
					str += '<span class="curr">' + i + '</span>';
				} else {
					str += '<a href="javascript:void(0);" onclick="kkpager.gopage(this);" title="第'
							+ i + '页">' + i + '</a>';
				}
			}
		} else {
			if (this.pno <= 5) {
				for ( var i = 1; i <= 7; i++) {
					if (this.pno == i) {
						str += '<span class="curr">' + i + '</span>';
					} else {
						str += '<a href="javascript:void(0);" onclick="kkpager.gopage(this);" title="第'
								+ i + '页">' + i + '</a>';
					}
				}
				str += dot;
			} else {
				str += '<a href="javascript:void(0);" onclick="kkpager.gopage(this);" title="第1页">1</a>';
				str += '<a href="javascript:void(0);" onclick="kkpager.gopage(this);" title="第2页">2</a>';
				str += dot;

				var begin = this.pno - 2;
				var end = this.pno + 2;
				if (end > this.total) {
					end = this.total;
					begin = end - 4;
					if (this.pno - begin < 2) {
						begin = begin - 1;
					}
				} else if (end + 1 == this.total) {
					end = this.total;
				}
				for ( var i = begin; i <= end; i++) {
					if (this.pno == i) {
						str += '<span class="curr">' + i + '</span>';
					} else {
						str += '<a href="javascript:void(0);" onclick="kkpager.gopage(this);" title="第'
								+ i + '页">' + i + '</a>';
					}
				}
				if (end != this.total) {
					str += dot;
				}
			}
		}

		str = "&nbsp;" + pageSize + "&nbsp;" + str_prv + str + str_next
				+ total_info + gopage_info;
		$("#" + this.pagerid).html(str);
	}
};

function getParameter(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

/** *******************************列表分页************************************** */
