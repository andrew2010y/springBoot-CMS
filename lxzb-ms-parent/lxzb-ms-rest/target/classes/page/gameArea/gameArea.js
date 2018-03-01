
layui.config({
    base : "../../static/layui/lay/modules/"
}).use(['table','form','laydate','projectName'], function(){
    var table = layui.table;	 //table表格
    var form = layui.form;

    var projectName = layui.projectName;

    var createTable ={  //table封装
        data:[],
        requestData:{}, //请求数据
        vc2areacode:"",//区服ID
        vc2type:"", //下拉选框的value值
        tableOptions:{},//table 选项
        dataArr:[          	 //表头内容
            {field:'vc2areacode',title: '区服ID'},
            {field:'vc2areaname',title: '区服名称'},
            {field:'vc2sdkusername',title: '服务器代理商用户名', },
            {
                field:'vc2type',title: '代理商类型',
                templet: function(res){
                    // console.log("templet",res.vc2type)
                    switch (res.vc2type) {
                        case "SPECIAL":res.vc2type="特殊代理商";break;
                        case "SMALL":res.vc2type="小代理商";break;
                        case "BIG":res.vc2type="大代理商";break;
                        default :res.vc2type="";break;
                    }
                    return res.vc2type;
                }
            },
            {field:'datcreate',width:150,title:'开服时间'},
            {
                field:'vc2sdkusername',title:'操作',templet: function(res){

                    if(res.vc2sdkusername==""){
                        return '<a href="./userList.html?id='+res.vc2areacode+'&name='+encodeURI(res.vc2areaname)+'" style="color:#e91515">指定代理商</a>'
                    }else{
                        res.vc2sdkusername="";
                        return  res.vc2sdkusername;
                    }

            }}
        ],
        init:function(url,type){
            this.getData();
            table.render(this.returnOpt(url,type));
        },
        getData:function (type) { //获取查询条件
            var that = this;
            var data = "";
            this.vc2areacode = $("#areaID").val(); //获取区服ID
            this.vc2type = $("#gameSelectValue option:selected").val();  //获取状态值
            if(this.vc2type==1){
                this.vc2type = "";
            }
            // console.log($("#selectValue select:option").val())
            this.requestData = {
                "vc2areacode":that.vc2areacode,
                "vc2type":that.vc2type
            }

            return this.requestData;
        },
        returnOpt:function(url,type) {
            // console.log("地址",url)
            //table options 赋值
            var that = this;
            this.tableOptions = {
                elem: '#gameArea',
                method: 'get',
                even:true,
                where:that.requestData,
                url:projectName+url, //数据接口
                page:{ //开启分页 支持传入 laypage 组件的所有参数
                    layout: ['prev', 'page', 'next', 'skip','count'], //自定义分页布局
                },
                cols:[that.dataArr],//表头
                limit:20,
                done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。

                }
            };

            return  this.tableOptions;

        }

    }

    // table 数据第一次初始化
    // createTable.init('/page/gameArea/gameArea.json');
    createTable.init('/game-suit/list');


    //监听下拉选框
    form.on('select(required)', function(data){
        // console.log(data.value); //得到被选中的值
    });



    form.on('submit(formSearch)', function(data){ //查询按钮

        var areaidValue = $("#areaID").val().length;
        console.log("查询按钮",areaidValue);
        if( areaidValue <= 20 ){
            createTable.init('/game-suit/list');
        }else{
            layer.msg("区服ID的位数不大于20位");
            return;
        }


    });


    //session 超时处理

    $(document).ajaxSuccess(function(event,xhr,options){
        // console.log("ajaxSuccess",xhr.responseJSON.code);
        if(xhr.responseJSON.code==-1){
            // console.log("跳转登录页面")
            window.parent.location.href = "../../login.html";
        }

    });


});
















