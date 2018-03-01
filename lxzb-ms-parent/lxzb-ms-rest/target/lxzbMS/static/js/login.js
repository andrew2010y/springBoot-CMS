


layui.config({
    base : "static/layui/lay/modules/"
}).use(['form','layer','projectName'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    var projectName = layui.projectName;

    // console.log(projectName);

    //登录按钮事件
    form.on("submit(login)",function(data){
        var data = data.field;
        var username = data.username;
        var password = data.password;
        //console.log(username, password);

        $.ajax({
            url: projectName+'/system/login',
            method:"post",
            data:{
                "username":username,
                "password":password,
            },
            beforeSend: function(){

                // layer.msg("登录过程中，请稍等");
            },
            success: function(data) {
                console.log(data);
                // layer.close(index);
                // setTimeout(function () {
                if(data.code === 0) {
                    //存储用户名
                    localStorage.setItem("username",username);
                    window.location.href = "./index.html";
                } else{
                    layer.msg(data.msg,{icon: 5, time: 2000});
                }
                // },1000)

            },
            error: function() {
                console.log('获取接口数据失败！');
            }
        });

        return false;
    })
})
