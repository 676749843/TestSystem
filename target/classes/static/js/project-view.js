var projectManage=new Vue({
    el: '#main',
    data: {
        projectNum: 0,
        projectFinishedNum:0,
    },
    mounted: function(){
        this.init();
    },
    methods: {
        init:function()
        {
            var _this = this;
            _this.initProjectNum();
           // _this.drawNumPie();
            _this.dtsNumTop5();
            _this.testcaseNumTop5();
            _this.scriptType();
           _this.testCaseType();
        },
        initProjectNum:function(){
            var _this = this;
            $.ajax({
                url: 'projectViewNum',
                type:'post',
                success: function(data){
                    _this.projectNum=data[0];
                    _this.projectFinishedNum=data[1];
                }});
        },
        dtsNumTop5:function () {
            var _this = this;
            $.ajax({
                url: 'dtsNumTop5',
                type:'post',
                success: function(data){
                    debugger;
                    _this.drawDtsNumTop5(data)
                }});
        },
        testcaseNumTop5:function () {
            var _this = this;
            $.ajax({
                url: 'testcaseNumTop5',
                type:'post',
                success: function(data){
                    debugger;
                    _this.drawTestNumTop5(data)
                }});
        },
        scriptType:function () {
            var _this = this;
            $.ajax({
                url: 'scriptType',
                type:'post',
                success: function(data){
                    debugger;
                    _this.drawScriptType(data)
                }});
        },
        testCaseType:function () {
            var _this = this;
            $.ajax({
                url: 'testCaseType',
                type:'post',
                success: function(data){
                    debugger;
                    _this.drawTestCaseType(data)
                }});
        },

        drawDtsNumTop5:function (data) {
            var xData=[];
            var seriesData=[];
            for(var p in data){//遍历json数组时，这么写p为索引，0,1
                xData.push(p)
                seriesData.push(data[p]);
            }
            var myChart = echarts.init(document.getElementById('dtsTop5'));
            var option = {
                color: ['#3398DB'],
                title: {
                    text: '缺陷数目排行Top5'
                },
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : xData,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'缺陷数目',
                        type:'bar',
                        barWidth: '20px',
                        data:seriesData
                    }
                ]
            };

            myChart.setOption(option);
        },


        drawTestNumTop5:function (data) {
            var xData=[];
            var seriesData=[];
            for(var i=0;i<data.length;i++)
            {
                xData.push(data[i].projectName);
                var  allNum1 = data[i].testCaseNum;
                var finishNum1 = data[i].testCaseFinishNum;
                var rate1=0;
                if(allNum1!=0)
                {
                    rate1 = finishNum1/allNum1;
                }
                seriesData.push(rate1);
            }

            for(var p in data){//遍历json数组时，这么写p为索引，0,1
                xData.push(p)
                seriesData.push(data[p]);
            }
            var myChart = echarts.init(document.getElementById('testcaseTop5'));
            var option = {
                color: ['#3398DB'],
                title: {
                    text: '用例完成情况排行Top5'
                },
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : xData,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'用例完成率',
                        type:'bar',
                        barWidth: '20px',
                        data:seriesData
                    }
                ]
            };

            myChart.setOption(option);
        },

        drawScriptType:function(data)
        {
            var xData=[];
            var seriesData=[];
            for(var p in data){//遍历json数组时，这么写p为索引，0,1
                if(p==0)
                {
                    xData.push("界面用例脚本")
                    var val =  {value:data[p], name:'界面用例脚本'};
                    seriesData.push(val);
                }else if(p==1)
                    {
                        xData.push("接口用例脚本");
                        var val =  {value:data[p], name:'接口用例脚本'};
                        seriesData.push(val);
                    }else if(p==-1)
                {
                    xData.push("未指明");
                    var val =  {value:data[p], name:'未指明'};
                    seriesData.push(val);
                }


            }
            var myChart = echarts.init(document.getElementById('completeRatePie'));

            // 指定图表的配置项和数据
            var option = {
                title : {
                    text: '脚本属性占比',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: xData
                },
                series : [
                    {
                        name: '脚本属性占比',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:seriesData,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        drawTestCaseType:function(data)
        {
            var xData=[];
            var seriesData=[];
            for(var p in data){//遍历json数组时，这么写p为索引，0,1

                    xData.push(p)
                    var val =  {value:data[p], name:p};
                    seriesData.push(val);

            }
            var myChart = echarts.init(document.getElementById('numPie'));

            // 指定图表的配置项和数据
            var option = {
                title : {
                    text: '用例类型占比',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: xData
                },
                series : [
                    {
                        name: '用例类型占比',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:seriesData,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

    }
});



