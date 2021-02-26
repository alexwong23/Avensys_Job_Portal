$(document).on('click', '#seekerApplyJob', function() {
    // change hidden input value when button clicked
    $("#hiddenJobID").val($(this).val());
});

$(document).on('click', '#managerAcceptJob', function() {
    // change hidden input value when button clicked
    $("#hiddenSeekerID").val($(this).val());
});

 //todo: not going to do
$(document).on('click', '#managerRejectJob', function() {
    // change hidden input value when button clicked
    $("#hiddenSeekerID").val($(this).val());
    // change form action to post to manager accept job route
    $('#applicationForm').attr('action', "/jobseek/manageracceptjob").submit();
});

 //todo: not tested yet
$(document).on('click', '#seekerRemoveJob', function() {
    // change hidden input value when button clicked
    $("#hiddenJobID").val($(this).val())
    // change form action to post to manager remove route
    $('#jobForm').attr('action', "/seekerremovejob").submit();
});








