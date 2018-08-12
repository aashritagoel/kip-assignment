function getAjaxData() {
    $.ajax({
        url: 'https://reqres.in/api/users/10',
        dataType: 'json',
        async: false,
        type: "GET",
        success: function (data) {
            var row = $('<tr><td>' + data.data.id + '</td><td>' + data.data.first_name + '</td><td>' + data.data.last_name + '</td><td><img src="' + data.data.avatar + '" height ="60" width="60"</td></tr>');
            $('#myTable').append(row);
        },
        error: function (data) {
            console.log(data);
        }
    });
}
getAjaxData();
