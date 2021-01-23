 $(function () {
    // change hidden input value when button clicked
    $("#deleteItem").click(function(){
        $("#hiddenItem").val($(this).val())
    });
});


