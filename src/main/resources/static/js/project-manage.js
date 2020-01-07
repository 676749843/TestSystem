var projectView=new Vue({
    el: '#main',
    data: {
        projectInfo:[],
        layer:null,
        searchCondition:
            {
                //项目名称
                projectName:"",
                //起始索引
                startIndex:0,
                //展示长度
                showLength:10
            },
        addProjectInfo:
            {
                projectDesc:"",
                projectName:""
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
            _this.initTable(true);
        },
        initTable:function (isPage) {
            var _this = this;
            $.ajax({
                url: 'selProjectAll',
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
                url: 'countProjectAll',
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
        addNewProjectLayer:function () {
            var _this = this;
            var layer = _this.layer;
            layer.open({
                title: '添加新项目',
                type: 1,
                offset:['60px','400px'],
                skin: 'node_layer',
                area: ['400px', '300px'],
                content: $('#addNewProject'),
                cancel: function(index, layero){

                }
            });
        },
        addNewProject:function () {
            var _this = this;
            var layer = _this.layer;
            $.ajax({
                url: 'addProjectInfo',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.addProjectInfo),
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
        }

    }
});



