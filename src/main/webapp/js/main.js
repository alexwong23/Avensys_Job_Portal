$(document).on('click', '#applyJob', function() {
    // change hidden input value when button clicked
    $("#hiddenJobID").val($(this).val())
});

$(document).on('click', '#removeJob', function() {
    // change hidden input value when button clicked
    $("#hiddenJobID").val($(this).val())
    // change form action to post to manager remove route
    $('#jobForm').attr('action', "/managerremovejob").submit();
});



