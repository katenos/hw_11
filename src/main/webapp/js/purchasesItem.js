$(document).ready(function () {
    $.getJSON("GetPurchasesItem", function (data) {
        var tbody = $('#tbody');
        tbody.html("");
        var number = 0;
        for (var i = 0; i < data.documents.length; ++i) {
            number++;
            tbody.append(getTr(data.documents[i], number));
        }
    });
});

function getTr(element, number) {
    var tr = $("<tr>", {id: element.id});
    var td = $("<td>");
    tr.append(td);
    td.html(number);
    tr.append(getTd(element.name));
    tr.append(getTd(element.description));
    tr.append(getTd(element.user_pur_name));
    tr.append(getTd(element.price));
    tr.append(getTd(element.user_fio));
    tr.append(getTd(element.user_address));
    if(element.status==true){
        tr.append(getTd("Доставлено"));
    }
    else{
        tr.append(getTd("В ожидании"));
    }
    return tr;
}
