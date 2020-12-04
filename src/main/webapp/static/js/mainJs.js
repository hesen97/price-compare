$(document).ready(function() {
    init();
    function init() {
        var websiteId = 1;
        var searchStr = $("#search-input").val();
        var accuracyLevel = $("#search-select").val();
        show_phones_on_panel(websiteId, searchStr, accuracyLevel);
    }

    function show_phones_on_panel(websiteId, searchStr, accuracyLevel) {
        $.ajax({
            url: "/crawler2/phones?websiteId=" + websiteId + "&searchStr=" + searchStr + "&accuracyLevel=" + accuracyLevel,
            type: "GET",
            success: function(result) {
                if (result.success) {
                    build_panel(result.phoneList, searchStr);
                } else {
                    alert(result.errorMsg);
                }
            }
        });
    }


    function build_panel(phoneList, searchStr) {
        //首先置空
        $("#show-panel").empty();
        $("<div></div>").addClass("panel-heading")
            .append("<i>" + searchStr + "</i>" + " 的搜索结果:").appendTo("#show-panel");
        var rowEle = $("<div></div>").addClass("row");

        $.each(phoneList, function(index, phone) {
            if (index != 0 && index % 6 == 0) {
                rowEle.appendTo("#show-panel");
                rowEle = $("<div></div>").addClass("row");
            }

            var imgEle = $("<img>").attr("src", phone.imageUrl)
                .attr("height", "200").attr("width", "200");

            var priceDivEle = $("<div></div>").addClass("phone-price")
                .append($("<em></em>").append("￥")).append($("<i></i>").append(phone.price));

            var aEle = $("<a></a>").attr("href", phone.detailUrl).attr("target", "_blank")
                .append($("<span></span>").addClass("phone-title").append(phone.title))
                .append(priceDivEle);

            rowEle.append($("<div></div>").addClass("col-md-2").append(imgEle).append(aEle));
        });

        if (rowEle.children().length > 0) {
            rowEle.appendTo("#show-panel");
        }
    }

    $("#website-TM").click(function() {
        $(this).parent().children().removeClass("active");
        $(this).addClass("active");

        var websiteId = 1;
        var searchStr = $("#search-input").val();
        var accuracyLevel = $("#search-select").val();
        show_phones_on_panel(websiteId, searchStr, accuracyLevel);
    })

    $("#website-JD").click(function() {
        $(this).parent().children().removeClass("active");
        $(this).addClass("active");

        var websiteId = 2;
        var searchStr = $("#search-input").val();
        var accuracyLevel = $("#search-select").val();
        show_phones_on_panel(websiteId, searchStr, accuracyLevel);
    })
});