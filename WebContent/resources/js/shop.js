var userNameEmpAction;
var positionEmp;
var indexRow;
var prodIdAction;

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
				    	if(null == data){
					    	$("button[id*='btnCancel']").click();
					    	$("input[name*='idProdAdd']").val("");
					    	$("input[name*='countProduct']").val("");
				    	}
				    	else{
				    		$("p[id*='msgAddProd']").text(data);
				    	}
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

function setEmpDelete( usernameDelete, position, empName, index){
	userNameEmpAction = usernameDelete;
	positionEmp = position;
	indexRow = index;
	$("strong[id*='nameEmpDelete']").text(empName);
	$("strong[id*='positionNameDelete']").text(position);
}

function deleteEmployeeOfShop(){
	try{
		if(null != userNameEmpAction){
			formData = new FormData();
			formData.append('usernameDelete', userNameEmpAction);
			formData.append('positionEmp', positionEmp);
			var url = "/SaleManager/shop/deleteEmployeeInShop"
				$.ajax({	
				    type: 'POST', 
				    url: url, 
				    data: formData,
				    cache: false,
				    contentType: false,
		            processData: false,
				    success: function(data){ 
				    	$("tr[id*=rowCus" + indexRow + "]").remove();
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown){
				        alert('Không thể xóa nhân viên khỏi shop ');
				    }
				}); 
		}
	}catch (e) {
		// TODO: handle exception
	}
}

function changeTab(tabName){
	try{
		if(null != tabName){
			var url = "/SaleManager/shop/" + tabName;
				$.ajax({	
				    type: 'POST', 
				    url: url, 
				    cache: false,
				    contentType: false,
		            processData: false,
				    success: function(data){ 
				    	$("div[id*='contentTab']").html(data);
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown){
				    }
				}); 
		}
	}catch (e) {
		// TODO: handle exception
	}
}

function setDeleteProdShop(productId, productName){
	prodIdAction = productId;
	$("strong[id*='prodNameDelete']").text(productName);
	
} 

function deleteProductInShop(){
	try{
		if(null != prodIdAction){
			$("tr[id*=rowPro" + prodIdAction + "]").remove();
			var formData = new FormData();
			formData.append('productIdDelete', prodIdAction);
			var url = "/SaleManager/shop/deleteProductInShop";
				$.ajax({	
				    type: 'POST', 
				    url: url, 
				    data: formData,
				    cache: false,
				    contentType: false,
		            processData: false,
				    success: function(data){ 
				    	$("tr[id*=rowPro" + prodIdAction + "]").remove();
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown){
				        alert('Không thể xóa sản phẩm khỏi shop ');
				    }
				}); 
		}
	}catch (e) {
		// TODO: handle exception
	}
}

function searchOrder(){
	try{
		showIconLoading();
		var idOrderSearch = $("input[name*='idOrderSearch']").val();
		var nameCusSearch = $("input[name*='nameCusSearch']").val();
		var nameEmpSearch = $("input[name*='nameEmpSearch']").val();
		var startDate = $("input[name*='startDate']").val();
		var endDate = $("input[name*='endDate']").val();
		var formData = new FormData();
		formData.append('idOrderSearch', idOrderSearch);
		formData.append('nameCusSearch', nameCusSearch);
		formData.append('nameEmpSearch', nameEmpSearch);
		formData.append('startDate', startDate);
		formData.append('endDate', endDate);
		var url = "/SaleManager/shop/searchOrder";
		$.ajax({	
		    type: 'POST', 
		    url: url, 
		    cache: false,
		    data: formData,
		    contentType: false,
            processData: false,
		    success: function(data){ 
		    	$("div[id*='contentListOrder']").html(data);
		    },
		    complete:function(data){
		    	hideIconLoading();
		    }
		}); 
	}catch (e) {
		// TODO: handle exception
	}
}

function createOrder(){
	try{
		
	}catch (e) {
		// TODO: handle exception
	}
}