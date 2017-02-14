$(document).ready(function () {
    $.getJSON("GetAllItem", function (data) {
        var tbody = $('#tbody');
        tbody.html("");
        var number = 0;
        for (var i = 0; i < data.documents.length; ++i) {
            number++;
            tbody.append(getTr(data.documents[i], number));
        }
    });
    windowDialog();
});

function windowDialog() {
    $('#dialogWindow').dialog({
        autoOpen: false,
        height: 250,
        width: 403,
        zIndex: 4,
        buttons: {
            Оплатил: function () {
                $.getJSON("PurchaseItem", {'id': $(".current").attr("id"), 'fullName':$("#fullName").val(), 'address':$("#address").val()},
                    function (data) {
                        if (data.success == true) {
                            $('#dialogWindow').dialog('close');
                            window.location = '../purchasesItem.jsp';
                        }
                    }
                );
            }
        }
    });
}

function getTr(element, number) {
    var tr = $("<tr>", {id: element.id});
    var td = $("<td>");
    tr.append(td);
    td.html(number);
    tr.append(getTd(element.name));
    tr.append(getTd(element.description));
    tr.append(getTd(element.price));
    tr.append(getTd(element.user));
    tr.append(getTd(createSpan()));
    return tr;
}

function createSpan() {
    var span = document.createElement("span");

    span.innerHTML = "Купить этот товар";
    span.style="cursor: pointer";
    $(span).on("click", function () {
        $(this).parent().parent().addClass("current");
        $('#dialogWindow').dialog('open');
    });
    return span;
}
