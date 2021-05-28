<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "/spring.ftl" as spring/>

<@c.page>
<@l.login "/registration" true />
<script>
    $.fn.setCursorPosition = function(pos) {
  if ($('#phone').get(0).setSelectionRange) {
    $('#phone').get(0).setSelectionRange(pos, pos);
  } else if ($('#phone').get(0).createTextRange) {
    var range = $('#phone').get(0).createTextRange();
    range.collapse(true);
    range.moveEnd('character', pos);
    range.moveStart('character', pos);
    range.select();
  }
};
  $(function() {

    function maskPhone() {
      var code = $('#selected').text();
      switch (code) {
        case "+7":
          $("#phone").mask("+7(999) 999-99-99");
          $('#phone').click(function(){
    $(this).setCursorPosition(3);  // set position number
  });
          break;
      }    
    }
    maskPhone();
      $('.option').on('click', function() {
    $('#selected').text($(this).text());
    maskPhone();
    });
  }); 
</script>
</@c.page>