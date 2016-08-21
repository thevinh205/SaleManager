var userNameCustomerDelete;
var host = "http://" + window.location.host;

$(document).ready(function() {
	initMenu();
	
	$('input.number').keyup(function(event) {

		  // skip for arrow keys
		  if(event.which >= 37 && event.which <= 40) return;

		  // format number
		  $(this).val(function(index, value) {
		    return value
		    .replace(/\D/g, "")
		    .replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		    ;
		  });
		});
});

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});
 $("#menu-toggle-2").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled-2");
    $('#menu ul').hide();
});

 function initMenu() {
  $('#menu ul').hide();
  $('#menu ul').children('.current').parent().show();
  //$('#menu ul:first').show();
  $('#menu li a').click(
    function() {
      var checkElement = $(this).next();
      if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
        return false;
        }
      if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
        $('#menu ul:visible').slideUp('normal');
        checkElement.slideDown('normal');
        return false;
        }
      }
    );
  }

function setDeleteCustomer(userName){
	userNameCustomerDelete = userName;
}

function deleteCustomer(){
	var url = host + "/SaleManager/customer/deleteCustomer";
	var formData = new FormData();
	formData.append('userNameDelete', userNameCustomerDelete);
	$.ajax(
		{	
		    type: 'POST', 
		    url: url, 
		    data: formData,
		    cache: false,
		    contentType: false,
            processData: false,
		    success: function(data){         
		    	$("tr[id*=rowCus" + userNameCustomerDelete  +"]").remove();
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Không thể xóa được khách hàng này,vui lòng thử lại ');
		    }
		}); 
	
}

function deleteEmployee(){
	var url = host + "/SaleManager/employee/deleteEmployee";
	var formData = new FormData();
	formData.append('userNameDelete', userNameCustomerDelete);
	$.ajax(
		{	
		    type: 'POST', 
		    url: url, 
		    data: formData,
		    cache: false,
		    contentType: false,
            processData: false,
		    success: function(data){         
		    	$("tr[id*=rowCus" + userNameCustomerDelete  +"]").remove();
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Không thể xóa được nhân viên này,vui lòng thử lại ');
		    }
		}); 
}

function showIconLoading(){
	$("#loadingIcon").show();
}

function hideIconLoading(){
	$("#loadingIcon").hide();
}
