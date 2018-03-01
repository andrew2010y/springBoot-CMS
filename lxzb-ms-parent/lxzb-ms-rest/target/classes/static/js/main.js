/* 用户账单列表 -- js */
layui.config({
    base : "../static/layui/lay/modules/"
}).use(['table','layer','laydate','projectName'], function(){
    var table = layui.table,
        layer = layui.layer,
        laydate = layui.laydate;
    var projectName = layui.projectName;

    //时间格式化(获取当前时间)
    function formatTime () {
        var date = new Date();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        var nowMonth = date.getMonth() + 1;
        var strDate = date.getDate();
        var seperator = "-";
        var seperatorT = ":";

        if (nowMonth >= 1 && nowMonth <= 9) {
            nowMonth = "0" + nowMonth;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        if (hours >= 0 && hours <= 9) {
            hours = "0" + hours;
        }
        if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
        }
        var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate + " "
            + hours + seperatorT + minutes + seperatorT + seconds;

        return nowDate;

    }

    //日期控制(结束时间>开始时间)
    function time(){

        var mytime= formatTime();     //获取当前时间
        //console.log(mytime);

        //开始时间id="beginTime",结束时间id="endTime";
        var beginTime = {
            elem: '#beginTime',
            type:'datetime',
            min: '2000-09-10 00:00:00',
            max: mytime,
            show: true,
            closeStop: '#beginTime'

        };
        var endTime = {
            elem: '#endTime',
            type:'datetime',
            min: '2000-09-10 00:00:00',
            max: mytime,
            show: true,
            closeStop: '#endTime'
        };
        $('#beginTime').on('click', function(e){

            if($('#endTime').val() != null && $('#endTime').val() != undefined && $('#endTime').val() != ''){
                beginTime.max = $('#endTime').val();
            }
            laydate.render(beginTime);
        });
        $('#endTime').on('click', function(e){
            if($('#beginTime').val() != null && $('#beginTime').val() != undefined && $('#beginTime').val() != ''){
                endTime.min = $('#beginTime').val();
            }
            laydate.render(endTime);
        });
        if(endTime.min >beginTime.max) {
            layer.msg("失败");
            return;
        }
    }

    time();

    //表格渲染
    table.render({
        elem: '#userBills',
        // height: 500,
        url: projectName+'/user-bill/list',
        page:{
            layout: ['prev', 'page', 'next', 'skip','count'],
        },
        limit: 20,
        where: {
            vc2ordercode: $('#orderNumber').val(),
            vc2sdkuserid: $('#userID').val(),
            datorderStart: $('#beginTime').val(),
            datorderEnd: $('#endTime').val(),
            vc2areacode: $('#server').val()
        },
        cols: [[ //表头
            {field: 'numchargerecordeid', title: '编号', align: 'center', valign: 'middle'},
            {field: 'vc2ordercode', title: '充值订单号', align: 'center', valign: 'middle'},
            {field: 'vc2sdkusername', title: '用户名', align: 'center', valign: 'middle' },
            {field: 'vc2areacode', title: '所属服务器', align: 'center', valign: 'middle'},
            {field: 'vc2sdkrecommandusername', title: '推荐人用户名', align: 'center', valign: 'middle'},
            {field: 'numchargenum', title: '到账金额(元)', align: 'center', valign: 'middle'},
            {field: 'datorder', title: '充值时间', align: 'center', valign: 'middle',width: 150}
        ]]
    });

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var agentId = data.agentId;
        var url = projectName+"/area-server/"+data.vc2areacode+"/" +data.numuserid+ "/appoint";

        //console.log(obj);

        //信息模态框
        if(agentId == "") {
            var html =
                "<div>是否指定当前用户(userID:"+
                data.vc2sdkuserid+")为该服务器代理商？</div>";

            // var x = this;

            layer.open({

                title: '指定用户'
                ,content: html
                ,btn: ['确定', '取消']
                ,btn1: function(index, layero){
                    //更新数据
                    obj.update({
                        agentId: data.numuserid
                    });

                    $.ajax({
                        url: url,
                        type: 'post',
                        // data:{
                        //     oldUserId: data.agentId
                        // },
                        success: function(data) {
                            //console.log(data.code);
                            if(data.code == 0){
                                table.reload('userBills', {
                                    where: {
                                        vc2ordercode: $('#orderNumber').val(),
                                        vc2sdkuserid: $('#userID').val(),
                                        datorderStart: $('#beginTime').val(),
                                        datorderEnd: $('#endTime').val(),
                                        vc2areacode: $('#server').val()
                                    }
                                });

                                // if(data.numuserid == data.agentId){
                                //     console.log(data.numuserid,data.agentId);
                                //     $(x).text("已指定");
                                //     $(x).attr("class","layui-btn-disabled");
                                //     $(x).prop("disabled",true);
                                // }
                            }


                        },
                        error: function() {
                            console.log('发送失败');
                        }
                    });
                    layer.close(index);
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
                "<div>当前指定服务器已有代理商(userID:"+
                data.vc2sdkagentuserid+")，是否替换为当前所选用户(userID:"+
                data.vc2sdkuserid+")为新的服务器代理商？</div>";


            layer.open({

                title: '指定用户'
                ,content: html
                ,btn: ['确定', '取消']
                ,btn1: function(index, layero){

                    obj.update({
                        agentId: data.numuserid
                    });

                    $.ajax({
                        url: url,
                        type: 'post',
                        data:{
                            oldUserId: data.agentId
                        },
                        success: function(data) {
                            if(data.code == 0){
                                table.reload('userBills', {
                                    where: {
                                        vc2ordercode: $('#orderNumber').val(),
                                        vc2sdkuserid: $('#userID').val(),
                                        datorderStart: $('#beginTime').val(),
                                        datorderEnd: $('#endTime').val(),
                                        vc2areacode: $('#server').val()
                                    }
                                });
                            }
                        },
                        error: function() {
                            console.log('发送失败');
                        }
                    });

                    layer.close(index);
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

    //点击查询，重新加载数据
    var active = {
        reload: function(){
            //执行重载
            table.reload('userBills', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    vc2ordercode: $('#orderNumber').val(),
                    vc2sdkusername: $('#vc2sdkusername').val(),
                    datorderStart: $('#beginTime').val(),
                    datorderEnd: $('#endTime').val(),
                    vc2areacode: $('#server').val()
                }
            });
        }
    };

    //userID输入内容的判断(输入15位以内的字段)
    $('#userID').keyup(function() {

        var userID = $("#userID").val();
        if(userID != "" && userID.length > 16) {

            this.value = userID.slice(0,15);
            layer.msg("请重新输入用户userID",{icon: 5, time: 2000});
            return;

        }
    });

    $('#query').on('click', function(){
        //判定开始时间不能大于结束时间；
        var endTime = $('#endTime').val();
        var beginTime = $('#beginTime').val();
        if(endTime < beginTime){
            layer.msg("开始时间不能大于结束时间");
            return;
        }
        //console.log(endTime,beginTime);
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

