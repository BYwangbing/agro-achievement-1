// 删除
$(".deleteDemand").on('click', function () {
    var parent = $(this).parent().parent();
    var demandId = parent.children("td:nth-child(2)").text();
    var inform = "您确定要删除需求ID为 " + demandId + " 的所有信息吗？";
    if(confirm(inform) == true){
        $.ajax({
            type: "DELETE",
            url: "/admin/adminDeleteDemand/",
            data: {
                demandId:demandId
            },
            success: function (data) {
                if (data.code == 0) {
                    $('.cure').addClass('uu');
                    $('.cure').html("删除成功");
                    setTimeout(function () {
                        $('.cure').removeClass("uu")
                        $('.cure').html('');
                    }, 2000);
                    setTimeout(function () {

                    }, 1000);
                } else {

                }
            },
            error: function (msg) {
                $('.cure').addClass('uu');
                $('.cure').html(data.msg);
                setTimeout(function () {
                    $('.cure').removeClass("uu")
                    $('.cure').html('');
                }, 2000);
                setTimeout(function () {
                    location.reload(true);
                }, 1000);
                alert("网络错误");
            }
        })
    }
    else {

    }
});