$(document).ready(function () {
    $.getJSON("GetUserItem", function (data) {
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
    return tr;
}

function getTd(childElement) {
    var td = $("<td>");
    td.append(childElement);
    return td;
}