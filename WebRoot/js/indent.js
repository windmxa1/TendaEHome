/**
 * 订单模块
 */
var json = null;
var total = null;
var pageSize = 20;
var pageNo = 1;
var indexState = -1;
// 加载默认列表（全部订单）
var type = 0;
$(function() {
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
	$(".dropdown").hover(function() {
		$(this).toggleClass("open");
	});
	$('.navli a').click(function() {
		$(".navli").removeClass("active");
		$(this).parent().addClass("active");
		type = $(this).data("type");
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
});
// 切换订单状态
function indent(v) {
	indexState = v;
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

// 按订单号搜索
function seek() {
	indexState = -2;
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
// 导出生态城PDF
function getOrderPDF(o, s) {
	$(".dropdown").removeClass('open');
	$.ajax({
		type : "post",
		url : "back/orders/getOrdersPDF",
		data : {
			address : o,
			state : s
		},
		dataType : "json",
		async : false,
		cache : false,
		headers : {
			"token" : getCookie('token')
		},
		success : function(data) {
			if (data.code == 100) {
				location.href = data.data.pdfUrl;
			} else {
				alert(data.msg);
			}
		},
		error : function(jqXHR) {
			alert("网络异常");
		}
	})
}
// 时间戳转换
function getLocalTime(nS) {
	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-")
			.replace(/日/g, " ");
}

// 申请退款
function popRefund(o) {
	$("#as-close").trigger("click");
	$('#r_orderId').val(o.id);
	$('#r_payWay').val(o.payWay);
	$('#r_total').val(o.total);
	$('#refundmodal').modal();

}
function doRefund() {
	var data = new FormData($('#refund_form')[0]);
	$.ajax({
		type : "post",
		url : "back/refund/doRefund",
		dataType : "json",
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		data : data,
		headers : {
			"token" : getCookie('token')
		},
		success : function(data) {
			if (data.code == 100) {
				alert("申请成功!");
				location.reload();
			} else {
				alert(data.msg);
			}
		},
		error : function(jqXHR) {
			alert("网络异常");
		}
	});
}
// 售后处理
function doAfterSale(odata) {
	$("#labelRefund").html("退款单编号");
	$.ajax({
		type : "post",
		url : "back/refund/getAfterSaleByOrder",
		dataType : "json",
		async : false,
		cache : false,
		data : {
			orderId : odata.id
		},
		headers : {
			"token" : getCookie('token')
		},
		success : function(data) {
			if (data.code == 100) {
				var as = data.data.afterSale;
				$('#as_afterSaleId').val(as.afterSaleId);
				$('#as_orderId').val(as.orderId);
				$('#as_refundId').val(as.refundId);
				$('#as_reason').val(as.reason);
				$('#as_state').val(odata.afterSaleState);// 售后处理状态从订单上得到
				$('#as_handleResult').val(as.handleResult);
				$('#as_time').val(getLocalTime(as.time));
				if (as.refundId == 0) {
					$("#labelRefund").append(
							"<button type='button' class='btn btn-info' onclick='popRefund("
									+ JSON.stringify(odata)
									+ ")'>申请退款</button>");
				} else {
					$("#labelRefund").append(
							"<a href='#refundDiv' onclick='refundDetail("
									+ as.refundId
									+ ")' data-toggle='collapse'>详情</a>");
				}
			}
		},
		error : function(jqXHR) {
			alert("网络异常");
		}
	});
}
// 获取退款详情
function refundDetail(refundId) {
	$.ajax({
		type : "post",
		url : "back/refund/getRefundById",
		dataType : "json",
		async : false,
		cache : false,
		data : {
			id : refundId
		},
		headers : {
			"token" : getCookie('token')
		},
		success : function(data) {
			if (data.code == 100) {
				var r = data.refund;
				$("#refundDiv").append(
						"退款单号" + r.refundId + " 退款金额" + r.refundFee + "<br />"
								+ "退款状态" + r.state + " 退款描述" + r.description);
			} else {
				alert(data.msg);
			}
		},
		error : function(jqXHR) {
			alert("网络异常");
		}
	});
}

// 修改售后单
function updateAfterSale() {
	var data = new FormData($('#aftersale_form')[0]);
	$.ajax({
		type : "post",
		url : "back/refund/updateAfterSale",
		dataType : "json",
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		headers : {
			"token" : getCookie('token')
		},
		data : data,
		success : function(data) {
			if (data.code == 100) {
				alert("修改成功");
				location.reload();
			} else {
				alert(data.msg);
			}
		},
		error : function(jqXHR) {
			alert("网络异常");
		}
	})
}
// 修改订单
function updateIndent(s, v) {
	var order_data = {};
	var urlPath = "";
	switch (s) {
	case 0:
		if (!confirm("确定要取消订单？")) {
			return;
		}
		urlPath = "back/orders/cancelOrder";
		order_data = {
			id : v.id
		};
		break;
	case 4:
		if (!confirm("确定订单已完成？")) {
			return;
		}
		urlPath = "back/orders/finishOrder";
		order_data = {
			id : v.id
		};
		break;
	case 5:
		if (!confirm("确定要取消该订单并退款吗？")) {
			return;
		}
		urlPath = "back/orders/refundOrder";
		order_data = {
			id : v.id,
			total : v.total
		};
		break;
	case 6:
		if (!confirm("确定已经完成该订单的退款处理了吗？")) {
			return;
		}
		urlPath = "back/orders/finishRefund";
		order_data = {
			refundId : v.refundId
		}
		break;
	}
	$.ajax({
		type : "post",
		url : urlPath,
		dataType : "json",
		async : false,
		cache : false,
		headers : {
			"token" : getCookie('token')
		},
		data : order_data,
		success : function(data) {
			if (data.code == 100) {
				location.reload();
			} else {
				alert(data.msg);
			}
		},
		error : function(jqXHR) {
			alert("网络异常");
		}
	});
};
var order_id_detail;
// 详情
function details(v) {
	order_id_detail = v;
	orderDetailsF.init();
	// ddxq.window.refreshData();
	// window.location.href = "details.html";
};
/** ******************************商品列表分页************************************** */
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
	if (indexState == -2) { // 搜索订单
		$.ajax({
			type : "post",
			url : "back/orders/getOrderByOrderNum",
			dataType : "json",
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			data : {
				orderNum : $('#seek_text').val()
			},
			success : function(data) {
				if (data.code == 100) {
					var list = [];
					list.push(data.data.list);
					json = list;
					total = data.data.total;
				}
			},
			error : function(jqXHR) {
				alert("网络异常");
			}
		})
	} else if (indexState == -1) { // 全部订单
		$.ajax({
			type : "post",
			url : "back/orders/getOrdersList",
			dataType : "json",
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			data : {
				'start' : (pageNo - 1) * pageSize,
				'limit' : pageSize,
				'type' : type
			},
			success : function(data) {
				if (data.code == 100) {
					json = data.data.list;
					total = data.data.total
				}
			},
			error : function(data) {
				alert("error");
			}
		});
	} else if (indexState == 7) {
		$.ajax({
			type : "post",
			url : "back/refund/getAfterSaleOrder",
			dataType : "json",
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			data : {
				'start' : (pageNo - 1) * pageSize,
				'limit' : pageSize,
				'type' : type
			},
			success : function(data) {
				if (data.code == 100) {
					json = data.data.list;
					total = data.data.total
				}
			},
			error : function(data) {
				alert("error");
			}
		});
	} else { // 按订单状态搜索
		$.ajax({
			type : "post",
			url : "back/orders/getOrdersList",
			dataType : "json",
			async : false,
			cache : false,
			headers : {
				"token" : getCookie('token')
			},
			data : {
				'start' : (pageNo - 1) * pageSize,
				'limit' : pageSize,
				"state" : indexState,
				'type' : type
			},
			success : function(data) {
				if (data.code == 100) {
					json = data.data.list;
					total = data.data.total
				}
			},
			error : function(data) {
				alert("error");
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
	var th = '<tr><th scope="col" class="eng_name" style="width:90px">订单编号</th><th scope="col" class="query_pro" >地址</th><th class="match_type" scope="col" style="width:30px">订单状态</th><th scope="col"  class="dis_order">时间</th><th scope="col"  class="dis_order">支付方式</th><th scope="col"  class="dis_dta">操作</th><th class="dis_hidden" style="display: none">隐藏属性</th></tr>';

	UQT_detailTable.append(th);
	var tr;
	$
			.each(
					UQTQueryMsg,
					function(i, eachData) {
						tr = $('<tr>');
						var id = eachData.id;
						var orderNum = eachData.orderNum;
						var status = eachData.status;
						var address = eachData.address;
						var createTime = eachData.createTime;
						var afterSaleState = eachData.afterSaleState;
						var payWay = "";
						switch (eachData.payWay) {
						case 0:
							payWay = "微信支付";
							break;
						case 1:
							payWay = "支付宝支付";
							break;
						default:
							payWay = "无";
							break;
						}
						var trString = "<td class='eng_name'>"
								+ "<button class='btn btn-sm' data-target='#order_"+id+"' data-toggle='collapse' type='button'>"
								+"<span class='glyphicon glyphicon-triangle-bottom' aria-hidden='true'></span></button>"
								+ orderNum
								+ "</td>"
								+ "<td class='query_pro'>"
								+ address
								+ "</td>"
								+ "<td class='match_type'>"
								+ status
								+ "</td>"
								+ "<td class='match_type' title='"
								+ createTime
								+ "'>"
								+ createTime
								+ "</td>"
								+ "<td class='match_type'>"
								+ payWay
								+ "</td>"
								+ "<td class='dis_dta' style='text-align:left;'>"
								+ "<button class='btn' onclick='details("
								+ id
								+ ")' data-toggle='modal' data-target='#ddxq' style='height:30px;margin-right:10px'  >订单详情</button>";
						if (indexState == 7) {
							trString = trString
									+ "<button class='btn' onclick='doAfterSale("
									+ JSON.stringify(eachData)
									+ ")' data-toggle='modal' data-target='#aftersale' style='height:30px; margin-right:10px' >售后处理</button>";
						} else {
							switch (eachData.state) {
							case 0:
								trString = trString
										+ "<button class='btn btn-warning' disabled='disabled' style='height:30px; margin-right:10px' >取消订单</button>";
								break;
							case 2:
								trString = trString
										+ "<button class='btn btn-warning' style='height:30px; margin-right:10px' onclick='updateIndent("
										+ 5 + "," + JSON.stringify(eachData)
										+ ")' >取消并退款</button>";
								break;
							case 1:
								trString = trString
										+ "<button class='btn btn-warning' style='height:30px; margin-right:10px' onclick='updateIndent("
										+ 0 + "," + JSON.stringify(eachData)
										+ ")' >取消订单</button>";
								break;
							case 3:
								trString = trString
										+ "<button class='btn btn-info' style='height:30px; margin-right:10px' onclick='updateIndent("
										+ 4 + "," + JSON.stringify(eachData)
										+ ")' >完成订单</button>";
								break;
							case 5:
								trString = trString
										+ "<button class='btn btn-info' style='height:30px; margin-right:10px' onclick='updateIndent("
										+ 6 + "," + JSON.stringify(eachData)
										+ ")' >完成退款</button>";
								break;
							default:
								trString = trString
										+ "<button class='btn btn-info' disabled='disabled' style='height:30px; margin-right:10px'>完成订单</button>";
								break;
							}
						}
						trString = trString
								+ "</td>"
								+ "<td class='dis_hidden' style='display: none'></td>";
						tr.append(trString);

						UQT_detailTable.append(tr);
						tr = $('<tr id="order_'+id+'" class="collapse">');
						tr.append('<td colspan="6">订单总价为：'+eachData.total+' 配送的员工为: '+eachData.staffName+' 选择的厨师为: '+eachData.franchiseeName+' 备注: '+eachData.remarks+'</td></tr>');
						UQT_detailTable.append(tr);
					});
}

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

		if (this.pagesize == 5)
			pageSize += '<option selected="selected" value="5" >5</option>';
		else if (this.pagesize == 10)
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

/** *******************************订单列表分页************************************** */
