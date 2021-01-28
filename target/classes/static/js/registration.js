$("#register").on("click", function() {
    $.ajax({
          type: "POST",
          url: "/users/register",
          data: JSON.stringify(prepareData()),
          contentType: "application/json",
          success: function(response){
                window.location.href = "/";
          },
          error: function(response) {
                console.log(response);
          }
    });

});

function prepareData() {
    return {
        username: $("#username").val(),
        password: document.getElementById("password").value,
        email: $("#email").val()
    }
}