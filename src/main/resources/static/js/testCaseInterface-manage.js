var projectView=new Vue({
    el: '#main',
    data: {
        projectInfo:[],
        layer:null,
        projectSelect:[],
        dtsInfo:[],
        userDeverInfo:[],
        searchCondition:
            {
                //项目名称
                projectId:"",
                interfacName:"",
                //起始索引
                startIndex:0,
                //展示长度
                showLength:10
            },
        dtsSearchCondition:
            {
                //项目名称
                projectId:"",
                testCaseId:"",
                testCaseType:1,
                //起始索引
                startIndex:0,
                //展示长度
                showLength:10
            },
        addNewTestCase:
            {
                interfacName:"",
                interfaceParam:"",
                testcaseExcept:"",
                testcaseComments:"",
                projectId:-1
            },
        editDostate:
            {
                testcaseName:"",
                testcaseDesc:"",
                testcaseExcept:"",
                testcaseComments:"",
                id:-1,
                testcaseDostate:""
            },
        newDTS:
            {
                dtsTitile:"",
                testcaseType:1,
                dtsDesc:"",
                dtsLevel:"一般",
                dtsSolver:""
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
                    _this.initTable(true);
                }});
        },
        search:function () {
            var _this = this;
            _this.initTable(true);
        },
        initTable:function (isPage) {
            var _this = this;
            $.ajax({
                url: 'selTestCaseInterface',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    _this.projectInfo = data;
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
                url: 'countTestCaseInterface',
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
        addNewTestCaseLayer:function () {
            var _this = this;
            var layer = _this.layer;
            layer.open({
                title: '添加新用例',
                type: 1,
                offset:['60px','400px'],
                skin: 'node_layer',
                area: ['500px', '600px'],
                content: $('#addNewTestCase'),
                cancel: function(index, layero){

                }
            });
        },
        addNewTestCaseButton:function () {
            var _this = this;
            _this.addNewTestCase.projectId = _this.searchCondition.projectId;
            var layer = _this.layer;
            $.ajax({
                url: 'addNewTestCaseView',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.addNewTestCase),
                success: function(data){

                    //需要初始化分页
                    if(data=="success")
                    {
                        layer.closeAll();
                        layer.alert("新增成功")
                        _this.initTable(true);
                    }else
                    {
                        layer.alert("新增失败")
                    }
                }});
        },

        editTestcaseViewLayer:function () {
            var _this = this;
            var layer = _this.layer;
            layer.open({
                title: '修改信息',
                type: 1,
                offset:['60px','400px'],
                skin: 'node_layer',
                area: ['500px', '600px'],
                content: $('#addNewTestCase'),
                cancel: function(index, layero){

                }
            });
        },
            addNewTestCaseButton:function () {
                var _this = this;
                _this.addNewTestCase.projectId = _this.searchCondition.projectId;
                var layer = _this.layer;
                $.ajax({
                    url: 'addNewTestCaseInterface',
                    contentType: "application/json",
                    type:'post',
                    data:JSON.stringify(_this.addNewTestCase),
                    success: function(data){

                        //需要初始化分页
                        if(data=="success")
                        {
                            layer.closeAll();
                            layer.alert("新增成功")
                            _this.initTable(true);
                        }else
                        {
                            layer.alert("新增失败")
                        }
                    }});
            },

        uploadTestCaseViewByExcel:function () {
            $("#fileUpload").click();
        },
        addNewTestCaseView:function () {
            var _this = this;


            var projectId = _this.searchCondition.projectId;
            debugger;
            var fileVale = $("#fileUpload").val();
            if(fileVale == "" || fileVale == null)
            {
                layer.alert("请选择文件");
                return;
            }
            $.ajaxFileUpload
            (
                {
                    url: 'uploadTestCaseViewByExcel', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    data:{"projectId":_this.searchCondition.projectId},
                    fileElementId: 'fileUpload', //文件上传域的ID
                    dataType: 'text', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if(data=='success')
                        {
                            layer.closeAll();
                            layer.alert("新增成功")
                            _this.initTable(true);
                        }

                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {

                    }
                }
            )
        },
        editTestcaseView:function (item) {
            var _this = this;
            var layer = _this.layer;
            _this.editDostate=item;
            // _this.editDostate.testcaseName=item.testcaseName;
            // _this.editDostate.testcaseDesc=item.testcaseDesc;
            // _this.editDostate.testcaseExcept=item.testcaseExcept;
            // _this.editDostate.testcaseRemark=item.testcaseRemark;
            // _this.editDostate.id=item.id;
            // _this.editDostate.id=item.id;
            // _this.editDostate.testcaseDostate=item.testcaseDostate;
            // _this.editDostate.projectId = _this.searchCondition.projectId;
            layer.open({
                title: '修改执行过',
                type: 1,
                offset:['60px','400px'],
                skin: 'node_layer',
                area: ['500px', '650px'],
                content: $('#editDostate'),
                cancel: function(index, layero){

                }
            });
        },
        delTestcaseView:function (item) {
            debugger;
            var _this = this;
            layer.confirm("确定删除该用例以及该用例所关联的缺陷吗？", {
                skin: 'confirm_layer',
                btn: ['确定', '取消'],
                closeBtn: 0 //按钮
            }, function() {
                $.ajax({
                    url: 'delTestcaseViewInterface',
                    contentType: "application/json",
                    type:'get',
                    data:{"id":item.id},
                    success: function(data){

                        //需要初始化分页
                        if(data=="success")
                        {
                            layer.closeAll();
                            layer.alert("删除成功")
                            _this.initTable(true);
                        }else
                        {
                            layer.alert("删除失败")
                        }
                    }});
            });
        },
        editDostateButton:function () {
            var _this = this;
            var layer = _this.layer;
            $.ajax({
                url: 'editDostateInterface',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.editDostate),
                success: function(data){

                    //需要初始化分页
                    if(data=="success")
                    {
                        layer.closeAll();
                        layer.alert("修改成功")
                        _this.initTable(false);
                    }else
                    {
                        layer.alert("新增失败")
                    }
                }});
        },





        applyBug:function (item) {
            var _this = this;

            _this.newDTS.testcaseId=item.id;
            _this.dtsSearchCondition.testCaseId=item.id;
            _this.initDtsTable(true,true);
            var layer = _this.layer;
        },
        initDtsTable:function (isPage,isAlert) {
            var _this = this;
            _this.dtsSearchCondition.projectId=_this.searchCondition.projectId;
            $.ajax({
                url: 'selDTSByProId',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.dtsSearchCondition),
                success: function(data){
                    _this.dtsInfo = data;

                    if(isAlert)
                    {
                        layer.open({
                            title: '查看缺陷',
                            type: 1,
                            skin: 'node_layer',
                            offset:'10px',
                            area: ['1000px', '600px'],
                            content: $('#dtsCreate'),
                            cancel: function(index, layero){

                            }
                        });
                    }

                    //需要初始化分页
                    if(isPage)
                    {
                        _this.selectDTSPageNum();
                    }
                }});
        },
        selectDTSPageNum:function(){
            var _this = this;
            $.ajax({
                url: 'countDTSNumByProId',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.dtsSearchCondition),
                success: function(data){
                    _this.initDTSPage(data);
                }});
        },
        initDTSPage:function(count) {
            var _this = this;
            layui.use('laypage', function(){
                var laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    elem: 'dtsPage' //注意
                    ,count: count //数据总数，从服务端得到
                    ,limit:10,
                    jump: function(obj, first){
                        if(first!=true)
                        {
                            _this.dtsSearchCondition.startIndex = obj.curr-1;
                            _this.initDtsTable(false,false);
                        }

                    }
                });
            });
        },
        addDTSLayer:function () {
            var _this = this;
            _this.newDTS.projectId=_this.dtsSearchCondition.projectId;
            $.ajax({
                url: 'selDeUser',
                type:'post',
                success: function(data){
                    _this.userDeverInfo=data;
                    if(data.length>0)
                    {
                        _this.newDTS.dtsSolver=data[0].userName;
                    }

                    layer.open({
                        title: '增加缺陷',
                        type: 1,
                        skin: 'node_layer',
                        offset:'10px',
                        area: ['500px', '600px'],
                        content: $('#addNewDTS'),
                        cancel: function(index, layero){

                        }
                    });

                }});


        },
        addDTS:function () {
            var _this = this;
            var layer = _this.layer;
            debugger;
            _this.newDTS.prjectId=_this.searchCondition.projectId;
            $.ajax({
                url: 'addDTS',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.newDTS),
                success: function(data){

                    //需要初始化分页
                    if(data=="success")
                    {
                        layer.close(layer.index);
                        layer.alert("新增成功")
                        _this.initDtsTable(true);
                    }else
                    {
                        layer.alert("新增失败")
                    }
                }});
        },
        delDTS:function (item) {
            debugger;
            var _this = this;
            layer.confirm("确定删除该缺陷吗？", {
                skin: 'confirm_layer',
                btn: ['确定', '取消'],
                closeBtn: 0 //按钮
            }, function() {
                $.ajax({
                    url: 'delDTS',
                    contentType: "application/json",
                    type:'get',
                    data:{"id":item.id},
                    success: function(data){

                        //需要初始化分页
                        if(data=="success")
                        {
                            layer.close(layer.index);
                            layer.alert("删除成功")
                            _this.initDtsTable(true);
                        }else
                        {
                            layer.alert("删除失败")
                        }
                    }});
            });
        },


    }
});



