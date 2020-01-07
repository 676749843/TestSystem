var projectManage=new Vue({
    el: '#main',
    data: {
        reportInfo:[],
        layer:null,
        projectSelect:[],
        scriptShowInfo:[],
        testcaseInfo:[],
        isUpdate:false,
        consoleWindow:"",
        testcaseType:0,
        testcaseShowType:0,
        scriptRun:
            {
                consoleWindow:"",
                scriptParam:""
            },
        testCaseId:-1,
        scriptInfo:
            {
                projectId:1,
                testCaseId:-1,
                testcaseType:-1,
                scriptName:"",
                scriptDesc:"",
                scriptContent:""
            },
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

                        _this.initScriptShow();
                    }

                }});
        },
        initScriptShow:function()
        {
            var _this = this;
            var proId = _this.searchCondition.projectId;
            $.ajax({
                url: 'selByScriptProId',
                data:{"proId":proId},
                type:'post',
                success: function(data){
                    _this.scriptShowInfo =data;
                    if(data.length>0)
                    {
                        _this.scriptInfo=data[0];
                        _this.isUpdate=true;
                    }
                }});
        },

        check:function () {
            var _this = this;
            var script = _this.scriptText;
            $.ajax({
                url: 'testJavaScript',
                data:{"scriptContent":_this.scriptInfo.scriptContent,"scriptName":_this.scriptInfo.scriptName},
                type:'post',
                success: function(data){
                    var text ="";
                    for(var i=0;i<data.length;i++)
                    {
                        text=text+(i+1)+"   "+data[i]+"\n";
                    }
                    _this.scriptRun.consoleWindow=text;
                }});
        },
        runScript:function () {
            var _this = this;
            var script = _this.scriptText;

            $.ajax({
                url: 'runScript',
                data:{"scriptContent":_this.scriptInfo.scriptContent,"scriptName":_this.scriptInfo.scriptName,"scriptParam":_this.scriptRun.scriptParam},
                type:'post',
                success: function(data){
                    var text ="";
                    for(var i=0;i<data.length;i++)
                    {
                        text=text+(i+1)+"   "+data[i]+"\n";
                    }
                    _this.scriptRun.consoleWindow=text;
                }});
        },
        scriptShow:function(item)
        {
            var _this = this;
            _this.scriptInfo=item;
            _this.isUpdate=true;
        },
        addNewScriptClick:function()
        {
            var _this = this;
            _this.scriptInfo= {
                id:-1,
                projectId:1,
                testCaseId:-1,
                testcaseType:-1,
                scriptName:"",
                scriptDesc:"",
                scriptContent:""
            };
                _this.scriptRun=
            {
                consoleWindow:"",
                    scriptParam:""
            };
            _this.isUpdate=false;
        },
        addNewScript:function () {
            var _this = this;
            var layer = _this.layer;
            var url="updateScript";
            if(!_this.isUpdate)
            {
                url="addScript";
                _this.scriptInfo.projectId =  _this.searchCondition.projectId
            }
            $.ajax({
                url: url,
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.scriptInfo),
                success: function(data){
                    if( url=="addScript")
                    {
                        if(data!=null)
                        {
                            layer.closeAll();
                            layer.alert("操作成功");
                            _this.scriptInfo=data;
                            _this.initScriptShow();
                        }else
                            {
                                layer.alert("操作失败")
                            }

                            return;
                    }



                    //需要初始化分页
                    if(data=="success")
                    {
                        layer.closeAll();
                        layer.alert("操作成功");
                        _this.initScriptShow();
                    }else
                    {
                        layer.alert("操作失败")
                    }
                }});
        },
        restParam:function () {
            var _this = this;
            _this.scriptRun.scriptparam="";
        },
        resetScriptInfo:function (item) {
            var _this = this;

        },

        connetTestCaseLayer:function () {
            var _this = this;
            var layer = _this.layer;
            var layer = _this.layer;
            if(_this.scriptInfo.id==-1)
            {
                layer.alert("请先保存");
                return;
            }


            _this.searchCondition.startIndex=0;
            _this.searchCondition.showLength=10;
            _this.initTable(true,true);
        },
        find:function()
        {
            var _this = this;
            _this.initTable(true,false);
        },
        initTable:function (isPage,isAlert) {
            var _this = this;
            var url="";
            if(_this.testcaseType==0)
            {
                url="selTestCaseView"
            }else
                {
                    url='selTestCaseInterface';
                }
            $.ajax({
                url: url,
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    _this.testcaseInfo = data;
                        debugger;
                    if(_this.scriptInfo.testCaseId!=-1)
                    {

                        _this.testCaseId=_this.scriptInfo.testCaseId;
                    }
                    if(data.length>0  )
                    {
                        if(_this.scriptInfo.testCaseId==-1)
                        {
                            _this.testCaseId=data[0].id;
                        }
                    }
                    if(_this.testcaseType !=_this.scriptInfo.testcaseType)
                    {
                        _this.testCaseId=-1;
                    }
                    _this.testcaseShowType=_this.testcaseType;

    if(isAlert)
    {
        layer.open({
            title: '关联用例',
            type: 1,
            offset:['60px','400px'],
            skin: 'node_layer',
            area: ['500px', '600px'],
            content: $('#connetTestCase'),
            cancel: function(index, layero){

            }
        });
    }


                    //需要初始化分页
                    if(isPage)
                    {
                        _this.selectPageNum();
                    }
                }});
        },
        selectPageNum:function(){
            var _this = this;
            var url="";
            if(_this.testcaseType==0)
            {
                url="countTestCaseView"
            }else
            {
                url='countTestCaseInterface';
            }
            $.ajax({
                url: url,
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
        connetTestCaseSure:function () {
            var _this = this;
            var layer = _this.layer;
            if(_this.testCaseId==-1)
            {
                layer.alert("请选择用例");
                return;
            }

            _this.scriptInfo.testCaseId=_this.testCaseId;
            _this.scriptInfo.testcaseType=_this.testcaseShowType;
            $.ajax({
                url: 'updateScript',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.scriptInfo),
                success: function(data){




                    //需要初始化分页
                    if(data=="success")
                    {
                        layer.closeAll();
                        layer.alert("操作成功");
                        _this.initScriptShow();
                    }else
                    {
                        layer.alert("操作失败")
                    }
                }});
        }

    }
});



