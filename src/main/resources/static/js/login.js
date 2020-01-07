var login=new Vue({
    el: '#login',
    data: {
        userName: '',
        password:'',
        authCode:'',
        errorInfo:'',
        isNeedCode:false,
        codeShow:false,
        errorShow:false
    },
    methods: {
        login:function()
        {
            var _this = this;
            var userName = _this.userName;
            var password = _this.password;

            if(userName==''||password=='')
            {
                _this.errorInfo = '用户名或密码不允许为空.';
                return;
            }

            var regEn = /[`~#$%^&*()+<>?:"{},.\/;'[\]]/im,
                regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;

            if(regEn.test(userName) || regCn.test(userName))
            {
                _this.errorInfo = '用户名或密码中包含特殊字符.';
                return ;
            }
            _this.errorInfo = '';
            var param={'userName':userName,'password':password}
            /* let params = new URLSearchParams();
             params.append('userName', userName);
             params.append('passWord', passWord);*/
            $.ajax({
                type:'post',
                data:param,
                url : "submitLogin",
                success : function(data) {//请求的返回成功的方法
                    if(data == "error"){
                        _this.errorInfo = '用户名或密码错误';
                    }else if(data == "success"){
                        window.location.href = 'index';
                    }
                }
            });
        },
        emptyErrorInfo:function(){
            this.errorInfo = '';
        }
    }
});



