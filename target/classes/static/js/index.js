var index=new Vue({
    el: '#index',
    data: {
        oldPasswd: '',
        newPasswd:'',
        confirmPasswd:'',
        warnInfo:'',
        user: new Object(),
        layer:null
    },
    mounted:function()
    {
        this.initLayer();
        this.getUser();
    },
    methods: {
        initLayer:function(){
            var _this = this;
            var layer = _this.layer;
            layui.use('layer', function(){
                var layer = layui.layer;
                _this.layer=layer;
            });
        },
        getUser: function(){
            var _this = this;
            $.ajax({
                type:'post',
                url: "getCurrentUser",
                success: function(data){
                    _this.user = data;

                }});
        },
        updatePasswd: function(){
            var _this = this;
            var layer = layui.layer;
            _this.warnInfo = '';
            _this.oldPasswd = '';
            _this.newPasswd = '';
            _this.confirmPasswd = '';
            layer.open({
                title: '修改密码',
                type: 1,
                skin: 'node_layer',
                area: ['400px', '410px'],
                content: $('#updatePasswordDiv')
            });
        },
        updatePassword: function(){
            var _this = this;
            var oldPwd = _this.oldPasswd;
            var newPwd = _this.newPasswd;
            var confirmPwd = _this.confirmPasswd;
            if (oldPwd == '' || newPwd == '' || confirmPwd == ''){
                _this.warnInfo = "旧密码、新密码或者确认密码不能为空";
                return;
            }
            if (newPwd != confirmPwd){
                _this.warnInfo = "新密码和确认密码不一致";
                return;
            }
            if (newPwd.length < 6 || newPwd.length > 18){
                _this.warnInfo = "新密码长度6~18位";
                return;
            }
            _this.warnInfo = "";
            var param={'oldPwd':oldPwd,'newPwd':newPwd};
            $.ajax({
                type:'post',
                data:param,
                url: "updatePassword",
                success: function(data){
                    if(data=='success')
                    {
                        layer.close(layer.index);
                        layer.alert('修改密码成功',{icon: 6});
                    }else if(data=='errPwd')
                    {
                        _this.warnInfo='旧密码错误';
                    }else if(data=='error')
                    {
                        _this.warnInfo='修改密码失败';
                    }
                }});

        },
        logout: function() {
            $.ajax({
                type : "post",
                url: "logout",
                success: function(data){
                    window.location.href="login";
                }});
        },
        closeWin:function () {
            var _this = this;
            var layer = _this.layer;
            layer.close(layer.index);
        }
    }
});