$(document).ready(function() {
    // $("form button").click(function() {
    //     alert("click");
    //     $.ajax({
    //         url: "/crawler2/phones?websiteId=1",
    //         type: "POST",
    //         async: true,
    //         data: $("form").serialize()
    //     });
    // });


    // function populate_nav() {
    //     //首先将nav置空
    //     $("#nav-ul").empty();
    //
    //     $.ajax({
    //         url: "/crawler/brands",
    //         type: "GET",
    //         success: function(result) {
    //             if (result.success) {
    //                 $("<li></li>").addClass("active")
    //                     .append($("<a></a>").append("今日特卖"))
    //                     .appendTo("#nav-ul");
    //
    //                 $.each(result.brandList, function(index, brand) {
    //                     $("<li></li>").addClass("nav-phone-category")
    //                         .attr("brand-id", brand.brandId)
    //                         .append($("<a></a>").append(brand.brandName))
    //                         .appendTo("#nav-ul");
    //                 })
    //             } else {
    //                 alert(result.errorMsg);
    //             }
    //         }
    //     });
    // };
    //
    // $("#nav-ul").on("click", ".nav-phone-category", function() {
    //     var brandId = $(this).attr("brand-id");
    //     alert(brandId);
    //
    //     $.ajax({
    //         url: "/crawler/items?brandId=" + brandId,
    //         type: "GET",
    //         success: function(result) {
    //             alert("回调正常");
    //         }
    //     });
    // });
})