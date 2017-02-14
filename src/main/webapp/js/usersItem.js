$(document).ready(function () {
    getUserItem();
    $('#dialogWindow').dialog({
        autoOpen: false,
        height: 250,
        width: 403,
        zIndex: 4,
        buttons: {
            Добавить: function () {
                var name = $("#purchaseName").val();
                var description = $("#purchaseDescription").val();
                var price = parseInt($("#purchaseStartPrice").val());
                if (name !== undefined && price !== NaN) {
                    $.getJSON("AddItem", {
                            name: $("#purchaseName").val(),
                            description: $("#purchaseDescription").val(),
                            price: $("#purchaseStartPrice").val()
                        }, function () {
                            getUserItem();
                            $('#dialogWindow').dialog('close');
                        }
                    );
                }
            }
        }
    });
});

function getUserItem() {
    $.getJSON("GetUserItem", function (data) {
        var tbody = $('#tbody');
        tbody.html("");
        var number = 0;
        for (var i = 0; i < data.documents.length; ++i) {
            number++;
            tbody.append(getTr(data.documents[i], number));
        }
    });
}

function addItem() {
    $('#dialogWindow').dialog('open');
}

function getTr(element, number) {
    var tr = $("<tr>", {id: element.id});
    var td = $("<td>");
    tr.append(td);
    td.html(number);
    tr.append(getTd(element.name));
    tr.append(getTd(element.description));
    tr.append(getTd(element.price));
    return tr;
}