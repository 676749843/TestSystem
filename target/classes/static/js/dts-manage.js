var projectManage=new Vue({
    el: '#main',
    data: {
        searchCondition:
            {
                //项目名称
                projectId:"",
                dtsName:"",
                //起始索引
                startIndex:0,
                //展示长度
                showLength:10
            },
        layer:null,
        projectSelect:[],
        dtsInfo:[],
        userDeverInfo:[],
        editDTS:{
            dtsTitile:"",
            dtsDesc:"",
            dtsLevel:"",
            dtsSolver:"",
            dtsState:""
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
                    _this.initDtsTable(true);
                }});
        },
        initDtsTable:function (isPage) {
            var _this = this;
            $.ajax({
                url: 'selDTSByProIdWithoutType',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.searchCondition),
                success: function(data){
                    _this.dtsInfo = data;
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
                data:JSON.stringify(_this.searchCondition),
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
                            _this.searchCondition.startIndex = obj.curr-1;
                            _this.initDtsTable(false);
                        }

                    }
                });
            });
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
        projectChange:function () {
            var _this = this;
            _this.searchCondition.dtsName="";
            _this.initDtsTable(true);
        },
        editDTSLayer:function (item) {
            var _this = this;

            _this.editDTS.dtsTitile =item.dtsTitile;
            _this.editDTS.dtsDesc =item.dtsDesc;
            _this.editDTS.dtsLevel =item.dtsLevel;
            _this.editDTS.dtsSolver =item.dtsSolver;
            _this.editDTS.dtsState =item.dtsState;
            _this.editDTS.id=item.id;
            $.ajax({
                url: 'selDeUser',
                type:'post',
                success: function(data){
                    _this.userDeverInfo=data;
                    layer.open({
                        title: '修改缺陷',
                        type: 1,
                        skin: 'node_layer',
                        offset:'10px',
                        area: ['500px', '600px'],
                        content: $('#editDTS'),
                        cancel: function(index, layero){

                        }
                    });
                }});


        },
        editDTSButton:function () {
            var _this = this;
            $.ajax({
                url: 'editDTS',
                contentType: "application/json",
                type:'post',
                data:JSON.stringify(_this.editDTS),
                success: function(data){

                    //需要初始化分页
                    if(data=="success")
                    {
                        layer.closeAll();
                        layer.alert("修改成功")
                        _this.initDtsTable(true);
                    }else
                    {
                        layer.alert("修改失败")
                    }
                }});
        }


    }
});



