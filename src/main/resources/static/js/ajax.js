function estimate(mark) {
    $.ajax({
        url:'/article/estimate',
        type:'POST',
        data:{
            'mark':mark
        }
    }).done(function () {

    }).fail(function () {

    });
}

function loadArticles() {
    $.ajax({
        url: '/article/load',
        type: 'POST',
        success: function (data) {
            let contentTableHTML = "<div class=\"other-container col-lg-10\">\n" +
                "    <div class=\"col-md-4\">\n" +
                "        <div class=\"card mb-4 box-shadow\">\n" +
                "            <img class=\"card-img-top\"\n" +
                "                 data-src=\"holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Картинка\"\n" +
                "                 alt=\"Картинка [100%x225]\" style=\"height: 225px; width: 100%; display: block;\"">"";
            for (let i = 0; i < data.length; i++) {
                contentTableHTML += "";
                contentTableHTML += "src=\" " + data[i].image + " data-holder-rendered=\"true\">";
                contentTableHTML += "<div class=\"card-body\">\n" +
                    "                <p class=\"card-text\">" + data[i].description + "</p>\n" +
                    "                <div class=\"d-flex justify-content-between align-items-center\">\n" +
                    "                    <div class=\"btn-group\">\n" +
                    "                        <div class=\"bd-example bd-example-padded-bottom\">\n" +
                    "                            <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\" data-toggle=\"modal\"\n" +
                    "                                    data-target=\"#gridSystemModal1\">\n" +
                    "                                Прочитать\n" +
                    "                            </button>\n" +
                    "                            <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">В избранное</button>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <small class=\"text-muted\">";
                for (let i = 0; i < data.rating){
                    contentTableHTML += "<i class=\"fa fa-star\"></i>"
                }
                contentTableHTML += "</small>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div id=\"gridSystemModal1\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridModalLabel\"\n" +
                    "         aria-hidden=\"true\">\n" +
                    "        <div class=\"modal-dialog\" role=\"document\">\n" +
                    "            <div class=\"modal-content\">\n" +
                    "                <div class=\"modal-header\">\n" +
                    "                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span\n" +
                    "                            aria-hidden=\"true\">&times;</span></button>\n" +
                    "                    <h4 class=\"modal-title\" id=\"gridModalLabel1\">" + data[i].title+ "</h4>\n" +
                    "                </div>\n" +
                    "                <div class=\"modal-body\">\n" +
                    "                    <div class=\"container-fluid bd-example-row\">\n" +
                    "                        <div class=\"row\">\n" +
                    "                            <div class=\"col-md-4\"> ";
                contentTableHTML += ""+data[i].text+"</div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "\n" +
                    "                <div class=\"modal-footer\">\n" +
                    "\n" +
                    "                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n" +
                    "\n" +
                    "                    <div id=\"reviewStars-input\">\n" +
                    "                        <input id=\"star-4\" type=\"radio\" name=\"reviewStars\" onclick='estimate(1)'/>\n" +
                    "                        <label title=\"gorgeous\" for=\"star-4\"></label>\n" +
                    "\n" +
                    "                        <input id=\"star-3\" type=\"radio\" name=\"reviewStars\" onclick='estimate(2)'/>\n" +
                    "                        <label title=\"good\" for=\"star-3\"></label>\n" +
                    "\n" +
                    "                        <input id=\"star-2\" type=\"radio\" name=\"reviewStars\" onclick='estimate(3)'/>\n" +
                    "                        <label title=\"regular\" for=\"star-2\"></label>\n" +
                    "\n" +
                    "                        <input id=\"star-1\" type=\"radio\" name=\"reviewStars\" onclick='estimate(4)'/>\n" +
                    "                        <label title=\"poor\" for=\"star-1\"></label>\n" +
                    "\n" +
                    "                        <input id=\"star-0\" type=\"radio\" name=\"reviewStars\" onclick='estimate(5)'/>\n" +
                    "                        <label title=\"bad\" for=\"star-0\"></label>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>";
            }
            let contentTableDiv = document.getElementById('inner'+cityId);
            contentTableDiv.innerHTML = contentTableHTML;
        }
    }).done(function (data) {

    }).fail(function () {

    });
}
function ate(dish_id) {
    $.ajax({
        url:'/ate/dish',
        type:'POST',
        data:{
            'dish_id':dish_id
        }
    }).done(function () {
        document.getElementById(dish_id).style.background = rgba(0,0,0,.6);
    }).fail(function () {

    });
}


