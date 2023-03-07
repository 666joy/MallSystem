//饼图
$(document).ready(function() {

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	// 数据加载完之前先显示一段简单的loading动画
	myChart.showLoading();
	// 指定图表的配置项和数据
	var names = [];
	var values = [];
	// 数据加载完之前先显示一段简单的loading动画
	myChart.showLoading();
	$.ajax({
		type : "post",
		async : "true", // 异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : "http://www.easymall.com/products/EchartsShow", // 请求发送到dataActiont处
		data : {},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			// 请求成功时执行该函数内容，result即为服务器返回的json对象
			if (result) {
				for (var i = 0; i < result.length; i++) {
					names.push(result[i].name);
					values.push(result[i].value);
				}
				myChart.hideLoading(); // 隐藏加载动画
				myChart.setOption(// 加载数据图表
				{
					title : {
						text : '商品销量',
						x : 'center'
					},
					// 工具箱
					// 保存图片
					toolbox : {
						show : true,
						feature : {
							saveAsImage : {
								show : true
							}
						}
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						left : 'left',
						data : names
					},
					calculable : true,
					series : [ {
						name : '销量',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : result,
						itemStyle : {
							emphasis : {
								shadowBlur : 10,
								shadowOffsetX : 0,
								shadowColor : 'rgba(0, 0, 0, 0.5)'
							}
						}
					} ]
				});
			}
		},
		error : function(errorMsg) {
			// 请求失败时执行该函数
			alert("图表请求数据失败!");
			myChart.hideLoading();
		}
	});// end ajax

});// 刷新方法结束
// 折线图
$(document).ready(function() {
	// 初始化ehcharts实例
	var lineChart = echarts.init(document.getElementById("main2"));
	// 数据加载完之前先显示一段简单的loading动画
	lineChart.showLoading();
	// 指定图表的配置项和数据
	var names = []; // 类别数组（实际放x轴坐标值）
	var nums = []; // 销量数组（Y坐标值）
	$.ajax({
		type : "post",
		async : "true",// 异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : "http://www.easymall.com/products/EchartsShow",
		data : {},
		dataType : "json",
		success : function(result) {
			if (result) {
				for (var i = 0; i < result.length; i++) {
					names.push(result[i].name);
					nums.push(result[i].value);
				}
				lineChart.hideLoading();// 隐藏加载动画
				lineChart.setOption({// 加载数据图表
					title : {
						text : '折线图',
					},
					// 工具箱
					// 保存图片
					toolbox : {
						show : true,
						feature : {
							saveAsImage : {
								show : true
							}
						}
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c}"
					},
					// 图例-每一条数据的名字叫销量
					legend : {
						data : [ '销量' ]
					},
					// x轴
					xAxis : {
						type : 'category',
						boundaryGap : true,
						data : names,
						axisLabel : { // interval设置成 0 强制显示所有标签     
							interval : 0,
							rotate : 30
						// 可以通过旋转解决标签显示不下的问题
						}
					},
					// y轴没有显式设置，根据值自动生成y轴
					yAxis : {
						type : 'value'
					},
					// 数据-data是最终要显示的数据
					series : [ {
						name : '销量',
						type : 'line',
						data : nums
					} ]
				})
			}
		},
		error : function(errorMsg) {
			alert("图表请求数据失败");
			lineChart.hideLoading();
		}
	})
})
