let postsToDisplay = 5;

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/users/getCurrentUsername",
        success: (response) => {
        $("#current-username").html(response);
        }
    });
    getPosts();

});

function getPosts() {
    $.ajax({
        type: "GET",
        url: "/chat/all",
        success: function(response){
            drawChat(response);
        },
        error: function(response) {
            $("#infoMessage").html("<p class='alert alert-danger'>" + response.responseText + "</p>");
        }
    });
}

function drawChat(data) {
    let html = "";
    if(data) {
        if(postsToDisplay > data.length) {
            postsToDisplay = data.length;
        }
        html += "<a id='load-more'>Load previous messages</a>";
        for(let i=data.length-postsToDisplay; i<data.length; i++) {
                if(data[i].isCurrent) {
                    html += "<p class='username'><i>You</i></p>";
                } else {
                    html += "<p class='username'><i>" + data[i].author + "</i></p>";
                }
                if(data[i].isCurrent) {
                    html += "<div class='alert alert-light message current' role='alert'>";
                } else {
                    html += "<div class='alert alert-light message' role='alert'>";
                }
                html += "<p class='title'>" + data[i].title + "</p> "
                html += "<p>" + data[i].content + "</p>";
                html += "</div></br>";
        }
    }
    $("#chat").html(html);
}

$("#addbutton").on("click", function() {
    $.ajax({
            type: "POST",
            url: "/chat/submit",
            data: JSON.stringify(prepareData()),
            contentType: "application/json",
            success: function(response){
                location.reload();
            },
            error: function(response) {
                alert("could not load the chat");
            }
        });
});

function prepareData() {
    return {
        title: $("#title").val(),
        content: $("#content").val()
    }
}

$("#chat").on("click", "#load-more", () => {
    postsToDisplay += 5;
    getPosts();
})