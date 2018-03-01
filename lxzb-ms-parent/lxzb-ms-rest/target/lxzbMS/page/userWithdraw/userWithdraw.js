
layui.config({
    base : "../../static/layui/lay/modules/"
}).use(['table','form','laydate','projectName'], function(){
    var laydate = layui.laydate; //时间插件
    var table = layui.table;	 //table表格
    var form = layui.form;
    var dateTimeMax = getTime(); //获取当前时间
    
    var projectName = layui.projectName;

    var createTable ={  //table封装
        data:[],
        requestData:{}, //请求数据
        numuserid:"",//用户ID
        datcreateStart:"",//开始时间
        datcreateEnd:"",//结束时间
        numtransactionnumMin:"",//最小金额
        numtransactionnumMax:"",//最大金额
        vc2status:"", //下拉选框的value值
        tableOptions:{},//table 选项
        dataArr:[          	 //表头内容
            {type:'checkbox'},
            {field:'vc2transactionid',width:300,title: '提现订单号'},
            {field:'numuserid',title: '提现用户Userid'},
            {field:'numcash',title: '提现额度(元)', },
            {field:'numtransactionnum',title: '应到账额度(元)'},
            {field:'numtax',title: '扣税额度(元)'},
            {field:'datcreate',width:150,title: '订单生成时间'},
            {
                field:'vc2status', title: '订单状态',
                templet: function(res){
                    // console.log("templet",res.vc2status)
                    switch (res.vc2status) {
                        case "ORDER_CREATE":res.vc2status="未审核";break;
                        case "ORDER_CHECK_REFUSE":res.vc2status="已拒绝";break;
                        case "ORDER_TRADE_SUCCESS":res.vc2status="支付成功";break;
                        case "ORDER_TRADE_FAIL":res.vc2status="支付失败";break;
                        default :res.vc2status="";break;
                    }
                    return res.vc2status;
                }
            },
            {fixed: 'right',title: '提现申请处理',toolbar:'#barDemo'}
        ],
        init:function(url,type){
            this.getData();
            table.render(this.returnOpt(url,type));
        },
        getData:function (type) { //获取查询条件
            var that = this;
            var data = "";
            this.numuserid = $("#userID").val(); //获取用户ID
            this.datcreateStart = $("#beginTime").val(); //获取开始时间
            this.datcreateEnd = $("#endTime").val(); //获取结束时间
            this.numtransactionnumMin = $("#sumMin").val();  //获取金额区间最小值
            this.numtransactionnumMax = $("#sumMax").val();  //获取金额区间最大值
            this.vc2status = $("#selectValue option:selected").val();  //获取状态值

            // console.log($("#selectValue select:option").val())
            this.requestData = {
                "numuserid":that.numuserid,
                "datcreateStart":that.datcreateStart,
                "datcreateEnd":that.datcreateEnd,
                "numtransactionnumMin":that.numtransactionnumMin,
                "numtransactionnumMax":that.numtransactionnumMax,
                "vc2status":that.vc2status
            }

            if (type===1) {

                data = "?numuserid="+this.numuserid+"&datcreateStart="+this.datcreateStart+"&datcreateEnd="+this.datcreateEnd+"&numtransactionnumMin="+this.numtransactionnumMin+"&numtransactionnumMax="+this.numtransactionnumMax+"&vc2status="+this.vc2status;
                return data;

            } else {
                return this.requestData;
            }




        },
        returnOpt:function(url,type) {
            // console.log("地址",url)
            //table options 赋值
            var that = this;
            this.tableOptions = {
                elem: '#userWithdraw',
                method: 'get',
                even:true,
                where:that.requestData,
                url:projectName+url, //数据接口
                page:{ //开启分页 支持传入 laypage 组件的所有参数
                    layout: ['prev', 'page', 'next', 'skip','count'], //自定义分页布局
                },
                // page:true,
                cols:[that.dataArr],//表头
                limit:20,
                done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    that.data.push(res.data);
                    // setFieldWidth(that.tableOptions);
                    // console.log("success",res.code);
                    if (type===1) { //这是代表未审核状态
                        $("#layCheck").prop('checked',false);
                        form.render();
                        $("#layButton").addClass("layui-btn-disabled");
                        $("#layButton").prop("disabled",true);
                        if (res.code==0 && res.data.length >0) {
                            $(".allCheck").show().find(".totalSum").html(res.sum);
                        } else {
                            $(".allCheck").hide()
                        }

                    }

                }
            };

            return  this.tableOptions;

        }

    }

    /* table 数据第一次初始化 */
    // createTable.init(localhost+'/alipay-withdrawal/list',1);
    createTable.init('/alipay-withdrawal/list',1);


    //监听下拉选框
    form.on('select(required)', function(data){
        // console.log(data.value); //得到被选中的值
        createTable.selectValue = data.value;
        if ( data.value!="ORDER_CREATE" ) {

            createTable.dataArr = [          	 //表头内容
                // {checkbox: true},
                {field:'vc2transactionid',width:300,title: '提现订单号'},
                {field:'numuserid',title: '提现用户Userid'},
                {field:'numcash',title: '提现额度(元)', },
                {field:'numtransactionnum', title: '应到账额度(元)'},
                {field:'numtax', title: '扣税额度(元)'},
                {field:'datcreate', width:150,title: '订单生成时间',},
                {
                    field:'vc2status', title: '订单状态',
                    templet: function(res){
                        // console.log("templet",res.vc2status)
                        switch (res.vc2status) {
                            case "ORDER_CREATE":res.vc2status="未审核";break;
                            case "ORDER_CHECK_REFUSE":res.vc2status="已拒绝";break;
                            case "ORDER_TRADE_SUCCESS":res.vc2status="支付成功";break;
                            case "ORDER_TRADE_FAIL":res.vc2status="支付失败";break;
                            default :res.vc2status="";break;
                        }
                        return res.vc2status;
                    }
                }

            ];
            // console.log("这不是第一个选项",createTable.dataArr)
        } else{

            createTable.dataArr = [          	 //表头内容
                {type:'checkbox'},
                {field:'vc2transactionid',width:300,title: '提现订单号'},
                {field:'numuserid',title: '提现用户Userid'},
                {field:'numcash',title: '提现额度(元)', },
                {field:'numtransactionnum',title: '应到账额度(元)'},
                {field:'numtax',title: '扣税额度(元)'},
                {field:'datcreate',width:150,title: '订单生成时间'},
                {
                    field:'vc2status', title: '订单状态',
                    templet: function(res){
                        // console.log("templet",res.vc2status)
                        switch (res.vc2status) {
                            case "ORDER_CREATE":res.vc2status="未审核";break;
                            case "ORDER_CHECK_REFUSE":res.vc2status="已拒绝";break;
                            case "ORDER_TRADE_SUCCESS":res.vc2status="支付成功";break;
                            case "ORDER_TRADE_FAIL":res.vc2status="支付失败";break;
                            default :res.vc2status="";break;
                        }
                        return res.vc2status;
                    }
                },
                {fixed: 'right',title: '提现申请处理',toolbar:'#barDemo'}
            ]

        }
    });



    form.on('submit(formSearch)', function(data){ //查询按钮

        var timeMin = $("#beginTime").val();
        var timeMax = $("#endTime").val();
        var userNum = $("#userID").val();

        // selectRange("#sumMin","#sumMax","请选择金额区间范围","请重新选择金额区间范围");

        // console.log("提现userid",userNum);

        if(/^\d{0,15}$/.test(userNum)){
            // console.log("true user")
        }else{
            // console.log("false user");
            layer.msg("提现用户Userid是小于15位的数字");
            return;
        }


        var minNum = $("#sumMin").val();
        var maxNum = $("#sumMax").val();
        // console.log( "最小值",minNum,"最大值",maxNum );
        /*if(  minNum =="" || maxNum =="" ){
            // console.log("都是空值")
            if( minNum == "" && maxNum == ""){

            }else{
                layer.msg("请选择金额区间范围");
                return;
            }

        }*/


        maxNum = parseInt(maxNum);
        minNum = parseInt(minNum);

        if(maxNum<minNum){ //金额排行区间最大值和最小值进行比较
            layer.msg("初始金额不能大于结束金额");
            return;
        }


        //比较日期大小
        if(  timeMin =="" || timeMax =="" ){
            // console.log("都是空值")
            if( timeMin == "" && timeMax == ""){

            }else{
                layer.msg("请重新选择时间范围");
                return;
            }
        }else{ //验证开始和结束时间
            if (timeMin.length > 0 && timeMax.length > 0) {
                var startDateTemp = timeMin.split(" ");
                var endDateTemp = timeMax.split(" ");

                var arrStartDate = startDateTemp[0].split("-");
                var arrEndDate = endDateTemp[0].split("-");

                var arrStartTime = startDateTemp[1].split(":");
                var arrEndTime = endDateTemp[1].split(":");

                var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0], arrStartTime[1], arrStartTime[2]);
                var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1], arrEndTime[2]);

                // console.log(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0], arrStartTime[1], arrStartTime[2]);
                console.log("开始时间",allStartDate.getTime(),"结束时间",allEndDate.getTime())

                if (allStartDate.getTime() > allEndDate.getTime()) {

                    layer.msg("开始时间不能等于大于结束时间");
                    return;
                }

            }
        }

        var selectOpt = $("#selectValue option:selected").val();
        if (selectOpt!="ORDER_CREATE") {
            $(".allCheck").hide();
            createTable.init('/alipay-withdrawal/list');
        } else {
            createTable.init('/alipay-withdrawal/list',1); //这是未审核状态
        }
        // console.log("查询按钮");
    });


    form.on('submit(formExport)', function(data){ //导出按钮

        var data = createTable.getData(1);
        // console.log("导出按钮",data);
        window.open(projectName+'/alipay-withdrawal/download'+data);
    });

    table.on('checkbox(test)', function(obj){ //table 复选按钮

        var	checkList =$('.layui-table-box input[type="checkbox"]');

        for(var i=0;i<checkList.length;i++){

            if ( $( checkList[i] ).prop('checked')  ) {

                // console.log($( checkList[i] ).prop('checked'))
                $("#layCheck").prop('checked','checked');
                form.render('checkbox');
                $("#layButton").removeClass("layui-btn-disabled");
                $("#layButton").prop("disabled",false);

            } else {

                $("#layCheck").prop('checked',false);
                form.render();
                $("#layButton").addClass("layui-btn-disabled");
                $("#layButton").prop("disabled",true);
                return;
            }
        }


    });


    form.on('checkbox(formAllCheck)', function(data){ //全选按钮
        // console.log("全选按钮",createTable.data[0]);
        var checkBoolean = data.elem.checked;
        if (checkBoolean) {
            // console.log("全选按钮true",$('input[type="checkbox"]'));
            var	child =$('input[type="checkbox"]');
            child.each(function(index, item){
                item.checked = data.elem.checked;
                // console.log(item)
            });
            form.render('checkbox');
            $("#layButton").removeClass("layui-btn-disabled");
            $("#layButton").prop("disabled",false);

        } else {
            // console.log("全选按钮false",data.elem.checked);
            $('input[type="checkbox"]').prop('checked',false);
            form.render();

            $("#layButton").addClass("layui-btn-disabled");
            $("#layButton").prop("disabled",true);
        }
    });

    form.on('submit(formRatify)', function(data){ //一键批准
        console.log("一键批准")
        layer.open({
            title:'一键批准',
            skin:'lay-popup',
            content: '是否一键批准所勾选的所有用户提现申请？如不确定请仔细查看后确认',
            btn: ['确定', '取消'],
            btn1: function(index, layero){
                //按钮【按钮一】的回调
                // console.log("yes",createTable.data)
                
                var ratifyAllData = '';
                createTable.data[0].forEach(function (ele,i) {
                	if (i==0) {
                		ratifyAllData += 'numthirdbindingcashid='+ele.numthirdbindingcashid;	
                	}else{
                		ratifyAllData += '&numthirdbindingcashid='+ele.numthirdbindingcashid;
                	}
                })
                createTable.data = [];
                // console.log( "yeS框",arrId );
                $("#layCheck").prop('checked',false);
                form.render();
                $("#layButton").addClass("layui-btn-disabled");
                $("#layButton").prop("disabled",true);
               
                //发送 单个审核批准数据
                ajax('/alipay-withdrawal/audit-pass',"post",ratifyAllData);
                //重新请求 未审核数据
                createTable.init('/alipay-withdrawal/list',1);
                layer.close(index);
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
                console.log("no")
                //return false 开启该代码可禁止点击该按钮关闭
            },
            cancel: function(){
                //右上角关闭回调

                // return false ;//开启该代码可禁止点击该按钮关闭
            }
        });
    })

    //监听工具条
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        // console.log(obj)
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'del'){ //删除
            layer.confirm('真的拒绝么', function(index){
                obj.del()
                layer.close(index);
                // console.log(obj.data);
                var refuseID = obj.data.numthirdbindingcashid;

                var refuseData = {
                    numthirdbindingcashid:"",
                }

                refuseData.numthirdbindingcashid=refuseID;

                //发送 审核拒绝数据
                ajax('/alipay-withdrawal/audit-refuse',"post",refuseData);
                //重新请求 未审核数据
                createTable.init('/alipay-withdrawal/list',1);

            });
        } else if(layEvent === 'edit'){ //批准
            // console.log(obj.data.numthirdbindingcashid);
            var ratifyID = obj.data.numthirdbindingcashid;
            obj.del()
            //向服务端发送删除指令
            var ratifyData = {
                numthirdbindingcashid:[]
            };

            ratifyData.numthirdbindingcashid.push(ratifyID);
            //发送 单个审核批准数据
            ajax('/alipay-withdrawal/audit-pass',"post",'numthirdbindingcashid='+ratifyID);
            //重新请求 未审核数据
            createTable.init('/alipay-withdrawal/list');


        }
    });


    //金额区间只能输入数字
    var numberSort = {
        init:function () {
            this.changeNum("#sumMin");
            this.changeNum("#sumMax");
        },
        changeNum:function (id) { //金额区间为数字
            var that = this;
            $(id).keyup(function (e) {
                var value = e.target.value.replace(/[^\d]/,"");
                $(this).val(value);
                // console.log( $(this).val() )
                if( $(this).val().length==1 && $(this).val()==0 ){
                    // console.log("开头有0",$(this).val())
                    $(this).val("");
                }

            })
        }

    }

    numberSort.init();


    //日期控制(结束时间>开始时间)
    function time(){

        var mytime= getTime();     //获取当前时间
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
        $('#beginTime').on('focus', function(e){

            if($('#endTime').val() != null && $('#endTime').val() != undefined && $('#endTime').val() != ''){
                beginTime.max = $('#endTime').val();
            }
            laydate.render(beginTime);
        });
        $('#endTime').on('focus', function(e){
            if($('#beginTime').val() != null && $('#beginTime').val() != undefined && $('#beginTime').val() != ''){
                endTime.min = $('#beginTime').val();
            }

            laydate.render(endTime);
        });

    }

    time();

    //session 超时处理

    $(document).ajaxSuccess(function(event,xhr,options){
        // console.log("ajaxSuccess",xhr.responseJSON.code);
        if(xhr.responseJSON.code==-1){
            // console.log("跳转登录页面")
            window.parent.location.href = "../../login.html";
        }

    });

    //ajax 简单调用
    function ajax(url,method,data) {
        $.ajax({
            url:projectName+url,
            method:method,
            data:data,
            dataType:"json",
            success:function(data) {
                console.log("sucess",data)
            }
        })
    }



});



function getTime() {  //时间方法
    var hours = new Date().getHours();
    var minutes = new Date().getMinutes();
    var seconds = new Date().getSeconds();
    var years = new Date().getFullYear();
    var months = new Date().getMonth() + 1;
    var days = new Date().getDate();

    if (months<10) {
        months = "0"+months;
    } else {
        months =months;
    }

    if (days<10) {
        days = "0"+days;
    } else {
        days =days;
    }

    if (hours<10) {
        hours = "0"+hours;
    } else {
        hours =hours;
    }

    if (minutes<10) {
        minutes = "0"+minutes;
    } else {
        minutes =minutes;
    }

    if (seconds<10) {
        seconds = "0"+seconds;
    } else {
        seconds =seconds;
    }

    // console.log(years,months,days)
    return years+"-"+months+"-"+days+"  "+hours+":"+minutes+":"+seconds;
}





function selectRange(minID,maxID,messageFir,message) {

    var minNum = $(minID).val();
    var maxNum = $(maxID).val();
    // console.log( "最小值",minNum,"最大值",maxNum );
    if(  minNum =="" || maxNum =="" ){
        // console.log("都是空值")
        if( minNum == "" && maxNum == ""){
        }else{
            layer.msg(messageFir);
            return;
        }

    }

    maxNum = parseInt(maxNum);
    minNum = parseInt(minNum);

    if(maxNum<=minNum){ //排行区间最大值和最小值进行比较
        layer.msg(message);
        return;
    }
}





   



	
	
	