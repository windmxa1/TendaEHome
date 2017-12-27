/**
 * 订单数据请求(取消列表)
 */
var json = null;
var total = null;
$(function() {
	// refreshData(20,1,10,json.length);
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
	$.ajax({
		type : "post",
		url : "back/staff/getStaffList",
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
	var jsonCount = total;
	var shang = getTotllePage(pageSize);
	// var startIndex = (pageNo);
	// var endIndex = (shang == pageNo)? jsonCount:pageSize;
	return json.slice(0, 20);
}

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
	var th = '<tr><th scope="col"  class="dis_order">员工编号</th><th scope="col" class="match_type" >姓名</th><th scope="col" class="match_type">工龄</th><th scope="col" class="query_pro">服务范围</th><th scope="col" class="match_type">账号</th><th scope="col" class="match_type">密码</th><th scope="col" class="dis_dta">操作</th><th class="dis_hidden" style="display: none">隐藏属性</th></tr>';

	UQT_detailTable.append(th);
	var tr;
	$
			.each(
					UQTQueryMsg,
					function(i, eachData) {
						tr = $('<tr>');
						var id = eachData.id;
						var staffNo = eachData.staffNo;
						var staffName = eachData.staffName;
						var year = eachData.year;
						var serviceVange = eachData.serviceVange;
						tr
								.append("<td class='dis_order'>"
										+ staffNo
										+ "</td>"
										+ "<td class='match_type'>"
										+ staffName
										+ "</td>"
										+ "<td class='match_type'>"
										+ year
										+ "</td>"
										+ "<td class='match_type'>"
										+ serviceVange
										+ "</td>"
										+ "<td class='match_type'>"
										+ eachData.username
										+ "</td>"
										+ "<td class='match_type'>"
										+ eachData.password
										+ "</td>"
										+ "<td class='dis_dta'><button data-toggle='modal' id='modStaff' data-target='#modOrder' style='height:30px;margin-right:10px' class='btn' data-order='"
										+ JSON.stringify(eachData)
										+ "'>修改</button><button class='btn' id='delStaff' style='height:30px;margin-right:10px' data-oid='"
										+ id + "'>删除</button></td>");
						UQT_detailTable.append(tr);
					});
};

// 修改员工信息
$(document).on('click', '#modStaff', function() {
	var order = $(this).data('order');
	$('#id').val(order.id);
	$('#staffNo').val(order.staffNo);
	$('#staffName').val(order.staffName);
	$('#year').val(order.year);
	$('#serviceVange').val(order.serviceVange);
	$('#username').val(order.username);
	$('#password').val(order.password);
	$('#isLeader').val(order.isLeader);
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

$(document).on('click', '#modOrder_btn', function() {
	var staffNo = $('#staffNo').val();
	var staffName = $('#staffName').val();
	var year = $('#year').val();
	var serviceVange = $('#serviceVange').val();
	var _reg = /^\d+$/;
	var password = $("#password").val();
	if (staffNo == '' || staffName == '' || year == '' || serviceVange == '') {
		alert("您有未填写的必填项，请补充完整后提交");
	} else if (!_reg.test(year)) {
		alert("工龄请填写数字");
	} else if (!_reg.test(staffNo)) {
		alert("工号请填写数字");
	} else {
		if(password!=''){
			$("#password").val(hex_md5(hex_md5(password+"szsiyann_dcl")));
		}
		var formData = new FormData(document.getElementById("modOrder_form"));
		$.ajax({
			type : "post",
			url : "back/staff/updateStaff",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			dataType : "json",
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
	}
});
// 添加员工信息
function tjyg() {
	var formData = new FormData(document.getElementById("tjyg_form"));
	var staffNo = $('#xg_staffNo').val();
	var staffName = $('#xg_staffName').val();
	var year = $('#xg_year').val();
	var serviceVange = $('#xg_serviceVange').val();
	var password = $("#xg_password").val();
	var _reg = /^\d+$/;
	if (staffNo == '' || staffName == '' || year == '' || serviceVange == '') {
		alert("您有未填写的必填项，请补充完整后提交");
	} else if (!_reg.test(year)) {
		alert("工龄请填写数字");
	} else if (!_reg.test(staffNo)) {
		alert("员工编号请填写数字");
	} else {
		if(password!=''){
			$("#password").val(hex_md5(hex_md5(password+"szsiyann_dcl")));
		}
		$.ajax({
			type : "post",
			url : "back/staff/addStaff",
			data : formData,
			dataType : "json",
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			beforeSend : function(request) {
				request.setRequestHeader("token", getCookie('token'));
			},
			success : function(data) {
				if (data.code == 100) {
					alert('添加成功');
					location.reload();
				} else {
					alert(data.msg);
				}
			},
			error : function(data) {
				alert("网络不给力,修改失败");
			}
		});
	}
}

// 删除员工信息
$(document).on('click', '#delStaff', function() {
	var oId = $(this).data('oid');
	if (confirm('确定要删除这个员工信息吗')) {
		$.ajax({
			type : "post",
			url : "back/staff/deleteStaff",
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
