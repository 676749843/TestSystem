var projectManage=new Vue({
    el: '#main',
    data: {
        reportInfo:[],
        layer:null,
        projectSelect:[],
        dtsInfo:[],
        reportType:0,
        userDeverInfo:[],
        searchCondition:
            {
                //项目名称
                projectId:"",
                //起始索引
                startIndex:0,
                //展示长度
                showLength:10
            }
    },
    mounted: function(){
        var _this = this;
        _this.init();
        layui.use('layer', function(){
            var layer = layui.layer;
            _this.layer=layer;
        });
    },
    methods: {
        init:function()
        {
            var _this = this;
            _this.initProjectSelect();
        },
        initProjectSelect:function(){
            var _this = this;
            $.ajax({
                url: 'initProjectSelect',
                type:'post',
                success: function(data){
                    _this.projectSelect = data;
                    if(data.length>0)
                    {
                        _this.searchCondition.projectId =_this.projectSelect[0].id;
                    }
                    _this.reportTypeChange();
                }});
        },
        initTable:function (isPage) {
            var _this = this;
            $.ajax({
                url: 'getReportTableInfo',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    _this.reportInfo = data;
                    //需要初始化分页
                    if(isPage)
                    {
                        _this.selectPageNum();
                    }
                }});
        },
        selectPageNum:function(){
            var _this = this;
            $.ajax({
                url: 'countProjectDetail',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    _this.initPage(data);
                }});
        },
        initPage:function(count) {
            var _this = this;
            layui.use('laypage', function(){
                var laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    elem: 'page' //注意
                    ,count: count //数据总数，从服务端得到
                    ,limit:10,
                    jump: function(obj, first){
                        if(first!=true)
                        {
                            _this.searchCondition.startIndex = obj.curr-1;
                            _this.initTable(false);
                        }

                    }
                });
            });
        },
        projectSelectChange:function () {
            var _this=this;
        },
        reportTypeChange:function () {
            var _this=this;
            var reportType=_this.reportType;
            if(reportType==0)
            {
                _this.drawReportPie();
            }else if(reportType==1)
            {
                _this.drawReportBar();
            }else if(reportType==2)
            {
                _this.initTable(true);
            }
        },
        drawReportPie:function () {

            var _this = this;
            $.ajax({
                url: 'getTestCaseTypeInfo',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    debugger
                    var jsonData=[];
                    for(var item in data){
                        //item 表示Json串中的属性，如'name'
                        var jValue=data[item];//key所对应的value
                        var name="未执行"
                        if(item==0)
                        {
                            name="未执行"
                        }else if(item==1)
                        {
                            name="执行成功"
                        }else if(item==2)
                        {
                            name="执行失败"
                        }else if(item==3)
                        {
                            name="无效用例"
                        }
                        var obj= {value:jValue, name:name};
                        jsonData.push(obj);

                    }


                    var myChart = echarts.init(document.getElementById('reportPie'));

                    // 指定图表的配置项和数据
                    var option = {
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                }, //区域缩放，区域缩放还原
                                dataView: {
                                    readOnly: false
                                }, //数据视图
                                magicType: {
                                    type: ['line', 'bar']
                                },  //切换为折线图，切换为柱状图
                                restore: {},  //还原
                                saveAsImage: {}   //保存为图片
                            }
                        },
                        legend: {
                            orient: 'vertical',
                            x: 'left',
                            data:['未执行','执行成功','执行失败','无效用例']
                        },
                        series: [
                            {
                                name:'访问来源',
                                type:'pie',
                                radius: ['50%', '70%'],
                                avoidLabelOverlap: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: 'center'
                                    },
                                    emphasis: {
                                        show: true,
                                        textStyle: {
                                            fontSize: '30',
                                            fontWeight: 'bold'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data:jsonData
                            }
                        ]
                    };


                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);



                }});





        },
        drawReportBar:function () {

            var _this = this;
            $.ajax({
                url: 'getTestCaseTypeInfo',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    debugger
                    var jsonData=[];
                    for(var item in data){
                        //item 表示Json串中的属性，如'name'
                        var jValue=data[item];//key所对应的value
                        var name="未执行"
                        if(item==0)
                        {
                            name="未执行"
                        }else if(item==1)
                        {
                            name="执行成功"
                        }else if(item==2)
                        {
                            name="执行失败"
                        }else if(item==3)
                        {
                            name="无效用例"
                        }
                        var obj= {value:jValue, name:name};
                        jsonData.push(jValue);

                    }


                    var myChart = echarts.init(document.getElementById('reportBar'));

                    // 指定图表的配置项和数据
                    var option = {
                        xAxis: {
                            type: 'category',
                            data:['未执行','执行成功','执行失败','无效用例']
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: jsonData,
                            type: 'bar'
                        }]
                    };


                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);



                }});





        }

    }
});



