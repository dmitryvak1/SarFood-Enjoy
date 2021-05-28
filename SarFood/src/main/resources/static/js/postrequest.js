$( document ).ready(function() {
  
  // SUBMIT FORM
    $("#addProduct").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    ajaxPost();
  });
    
    
    function ajaxPost(){
      
      // PREPARE FORM DATA
      var formData = {
    		  idProduct : $('#addProductPopup').attr('data-id-product'),
    		  count : $('#addProductPopup .count').val()
      }
      
      // DO POST
      $.ajax({
      type : "POST",
      contentType : "application/json",
      url : window.location + "/save",
      data : JSON.stringify(formData),
      dataType : 'json',
      success : function(data) {
        	$('#count').attr('data-cart-items', data.totalCount);
			$('#currentShoppingCart .total-cost').text('Р' + data.totalCost);
			$('#addProductPopup').modal('hide');
      },
      error : function(e) {
        alert("Error!")
        console.log("ERROR: ", e);
      }
    });
      
      // Reset FormData after Posting
      resetData();
 
    }
})