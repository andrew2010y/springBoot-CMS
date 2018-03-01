/* 用户列表 -- js */
layui.config({
    base : "../../static/layui/lay/modules/"
}).use(['table','layer','projectName'], function(){
    var table = layui.table,
        layer = layui.layer;
    var projectName = layui.projectName;

    //获取url参数
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        } else {
            return null;
        }
    }

    var vc2areacode = getQueryString("id");
    var  vc2areaname = getQueryString("name");
    var styleOne = "",
        styleOnly ="";
    //console.log(getQueryString("id"));
    //拼接代理商类型
    $.ajax({
        url: projectName+'/area-server/getUserPositionAll',
        type: 'get',
        success: function(res) {
            var data = res.data;
            styleOne = "<p><input name='vc2sdkagent' type='radio' value='"+ data[0].numuserpositionid+"'/>"+data[0].content+"</p>"+
                        "<p><input name='vc2sdkagent' type='radio' value='"+ data[1].numuserpositionid+"'/>"+data[1].content+"</p>"+
                        "<p><input name='vc2sdkagent' type='radio' value='"+ data[2].numuserpositionid+"'/>"+data[2].content+"</p>";
            styleOnly = "<p  style='color:lightgray;'><input name='vc2sdkagent' type='radio' value='"+ data[0].numuserpositionid+"' disabled='disabled' />"+data[0].content+"</p>"+
                        "<p  style='color:lightgray;'><input name='vc2sdkagent' type='radio' value='"+ data[1].numuserpositionid+"' disabled='disabled'/>"+data[1].content+"</p>"+
                        "<p  style='color:lightgray;'><input name='vc2sdkagent' type='radio' value='"+ data[2].numuserpositionid+"' disabled='disabled' />"+data[2].content+"</p>";

        },
        error: function() {
            console.log('获取数据失败！');
        }
    });

    //表格渲染
    table.render({
        elem: '#userList',
        // height: 500,
        url: projectName+'/game-user/list',
        //url:'../../static/json/usersList.json',
        page:{
            layout: ['prev', 'page', 'next', 'skip','count'],
        },
        limit: 20,
        where: {
            username: $('#userName').val()
        },
        cols: [[ //表头
            {field: 'vc2sdkusername', title: '用户名', align: 'center', valign: 'middle' },
            {
                field: 'vc2sdkusername', title: '区服', align: 'center', valign: 'middle',
                templet: function(res){
                   if(res.vc2sdkusername){
                       res.vc2sdkusername = vc2areaname;
                   }else{
                       res.vc2sdkusername = ""
                   }
                    return res.vc2sdkusername;
                }
            },
            {fixed: 'right',title: '操作', align: 'center', valign: 'middle', toolbar: '#barTool'}
        ]]
    });


    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        //console.log(data);
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var vc2typ = data.vc2type;
        console.log(vc2typ);
        var numuserpositionid = data.numuserpositionid;
        var url = projectName+"/area-server/"+vc2areacode+"/" +data.numuserid+ "/appoint";
        //console.log(url);

        //信息模态框
        if(vc2typ == "") {

            var html =
                "<div>指定当前用户(用户名:"+
                data.vc2sdkusername+")为该服务器的何种类型代理商？"+
                styleOne +
                "<p style='font-size:12px;'>变更代理商后，代理商的收益将从第二天开始计算。</p>"+
                "</div>";

            // var x = this;

            layer.open({

                title: '指定用户'
                ,content: html
                ,btn: ['确定', '取消']
                ,btn1: function(index, layero){
                    //更新数据
                    var ab = $("input[name='vc2sdkagent']:checked").val();
                    var cd = $("input[name='vc2sdkagent']:checked").parent("p").text();
                    // alert(cd);
                    if(!cd){
                        layer.msg("请选择代理商类型，再提交！");
                    }else{

                        $.ajax({
                            url: url,
                            type: 'post',
                            data:{
                                numuserpositionid: ab
                            },
                            success: function(data) {
                                //console.log(data.code);
                                if(data.code == 0){
                                    table.reload('userList', {
                                        where: {
                                            username: $('#userName').val()
                                        }
                                    });
                                }


                            },
                            error: function() {
                                console.log('发送失败');
                            }
                        });
                    }
                    layer.close(index);
                    window.location.href = './gameArea.html';
                }
                ,cancel: function(index, layero){
                    if(confirm('确定要关闭么?')){ //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }

            });
        } else {


            var html =
                "<div>是否指定当前用户(用户名:"+
                data.vc2sdkusername+")为该服务器的代理商?" +
                "<p style='font-size:12px;'>该用户的代理商类型已确定，</p>"+
                styleOnly +
                "<p style='font-size:12px;'>变更代理商后，代理商的收益将从第二天开始计算。</p>"+
                "</div>";



            layer.open({

                title: '指定用户'
                ,content: html
                ,btn: ['确定', '取消'],
                success:function(index, layero) {
                    $("input[value=" +numuserpositionid+ "]").attr('checked',true);

                }
                ,btn1: function(index, layero){

                    var ab = $("input[name='vc2sdkagent']:checked").val();
                    var cd = $("input[name='vc2sdkagent']:checked").parent("p").text();
                    //alert(cd);
                    if(!cd){
                        layer.msg("请选择代理商类型，再提交！");
                    }else {
                        $.ajax({
                            url: url,
                            type: 'post',
                            data:{
                                numuserpositionid: data.numuserpositionid
                            },
                            success: function(data) {
                                if(data.code == 0){
                                    table.reload('userList', {
                                        where: {
                                            username: $('#userName').val()
                                        }
                                    });
                                }
                            },
                            error: function() {
                                console.log('发送失败');
                            }
                        });
                    }

                    layer.close(index);
                    window.location.href = './gameArea.html';

                }
                ,cancel: function(index, layero){
                    if(confirm('确定要关闭么?')){ //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }

            });
        }
    });

    //点击返回上一级
    $('#return').click(function() {
        window.location.href = "./gameArea.html";
    });
    //点击查询，重新加载数据
    var active = {
        reload: function(){
            //执行重载
            table.reload('userList', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    username: $('#userName').val()
                }
            });
        }
    };

    $('#query').on('click', function(){

        var type = $(this).data('type');
        //console.log(type);
        active[type] ? active[type].call(this) : '';
    });

    //session 超时处理
    $(document).ajaxSuccess(function(event,xhr,options){
        //console.log("ajaxSuccess",xhr.responseJSON.code)
        if(xhr.responseJSON.code==-1){
            // console.log("跳转登录页面")
            window.parent.location.href = "../login.html";
        }

    });

});

