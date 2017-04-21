function filterFunction() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("filterInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("itemsTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function deleteRow(button, category) {
    var row = button.parentNode.parentNode;
    var data = {
        category: category,
        id: row.cells[2].innerText
    };
    post(data, "/admin/delete/");
    document.getElementById("itemsTable").deleteRow(row.rowIndex);
}

function post(data, url) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        beforeSend: function(request) {
            var csrfToken = $("meta[name='_csrf']").attr("content");
            return request.setRequestHeader('X-CSRF-Token', csrfToken);
        }
    })
}