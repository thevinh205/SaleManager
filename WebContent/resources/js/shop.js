function addProductToShop(){
	try{
		var productId = $("input[name*='idProdAdd']").val();
		var countProduct = $("input[name*='countProduct']").val();
		if(null == productId || productId.trim().lenght == 0){
			
		}
		else if(null == countProduct || countProduct.trim().lenght == 0){
			
		}
		else{
			var formData = new FormData();
			formData.append('productIdAdd', productId);
			formData.append('countProduct', countProduct);
			
			var url = host + "/SaleManager/shop/addProductToShop";
			$.ajax(
				{	
				    type: 'POST', 
				    url: url, 
				    data: formData,
				    cache: false,
				    contentType: false,
		            processData: false,
				    success: function(data){ 
				    	$("button[id*='btnCancel']").click();
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown){
				        alert('Không thể thêm mới sản phẩm tới shop ');
				    }
				}); 
		}
	}catch (e) {
		// TODO: handle exception
	}
}

function addEmployeeToShop(){
	try{
		var empUserName = $("select[name*='empAdd']").val();
		var empPosition = $("select[name*='empPosition']").val();
		var formData = new FormData();
		formData.append('empUserName', empUserName);
		formData.append('empPosition', empPosition);
		
		var url = host + "/SaleManager/shop/addEmployeeToShop";
		$.ajax({	
				    type: 'POST', 
				    url: url, 
				    data: formData,
				    cache: false,
				    contentType: false,
		            processData: false,
				    success: function(data){ 
				    	if(null == data)
				    		$("button[id*='btnCancel']").click();
				    	else{
				    		$("p[id*='msgAddEmp']").text(data);
				    	}
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown){
				        alert('Không thể thêm nhân viên cho shop ');
				    }
				}); 
	}catch (e) {
		// TODO: handle exception
	}
}