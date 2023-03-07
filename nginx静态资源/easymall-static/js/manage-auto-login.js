function manage_auto_login(){
    $.ajax({
       url:"http://www.easymall.com/user/manage_auto_login",
       dataType:"json",
       type:"GET",
       success:function (result) {
           if(result.status==200){
               window.location.href="manage.html";
               return;
           }else{
               alert(result.msg+"");
               window.location.href="manage-login.html";
               return;
           }
       },
       erro:function () {
           alert("后台自动登录请求发送失败");
       }
    });
}