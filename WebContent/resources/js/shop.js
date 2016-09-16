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

function searchProductAdd(){
	try{
		showIconLoading();
		var idProdSeach =  $("input[name*='idProdSearch']").val();
		var nameProdSeach =  $("input[name*='nameProdSearch']").val();
		var groupProductSearch =  $("select[name*='groupProductSearch']").val();
		
		var formData = new FormData();
		formData.append('idProdSearch', idProdSeach);
		formData.append('nameProdSearch', nameProdSeach);
		formData.append('groupProductSearch', groupProductSearch);
		
		var url = "/SaleManager/shop/searchProductOrder";
		$.ajax({	
		    type: 'POST', 
		    url: url, 
		    cache: false,
		    data: formData,
		    contentType: false,
            processData: false,
		    success: function(data){ 
		    	$("div[id*='idTableProdSearch']").html(data);
		    },
		    complete:function(data){
		    	hideIconLoading();
		    }
		}); 
	}catch (e) {
		// TODO: handle exception
	}
}

function selectPageProductAdd(page){
	try{
		showIconLoading();
		var idProdSeach =  $("input[name*='idProdSearch']").val();
		var nameProdSeach =  $("input[name*='nameProdSearch']").val();
		var groupProductSearch =  $("select[name*='groupProductSearch']").val();
		var pageCurrent = $("p[name*='pageCurrent']").text();
		
		var formData = new FormData();
		formData.append('idProdSeach', idProdSeach);
		formData.append('nameProdSeach', nameProdSeach);
		formData.append('groupProductSearch', groupProductSearch);
		formData.append('pageCurrent', pageCurrent);
		formData.append('page', page);
		
		var url = "/SaleManager/shop/listProductOrderSearch";
		$.ajax({	
		    type: 'POST', 
		    url: url, 
		    cache: false,
		    data: formData,
		    contentType: false,
            processData: false,
		    success: function(data){ 
		    	$("div[id*='idTableProdSearch']").html(data);
		    },
		    complete:function(data){
		    	hideIconLoading();
		    }
		}); 
	}catch (e) {
		// TODO: handle exception
	}
}

var listProdAddId = [];
function addProductOrder(productId){
	try{
		showIconLoading();		
		var countProduct = $("input[id*=countProduct" + productId + "]").val();
		if(countProduct > 0){
			if(listProdAddId.length == 0 || listProdAddId.toString().indexOf(productId) == -1){
				listProdAddId[listProdAddId.length] = (productId + "_" + countProduct); 
				var formData = new FormData();
				formData.append('productIdAdd', productId);
				formData.append('countProduct', countProduct);
				
				var url = "/SaleManager/order/addProductOrder";
				$.ajax({	
				    type: 'POST', 
				    url: url, 
				    cache: false,
				    data: formData,
				    contentType: false,
		            processData: false,
				    success: function(data){ 
				    	$("div[id*='popupTableListProdOrder']").append(data);
				    },
				    complete:function(data){
				    	$("button[id*='btnCancelAddProd']").click();
				    	hideIconLoading();
				    }
				}); 
			}
			else{
				for (var i = 0; i < listProdAddId.length; i++) {
				    if (listProdAddId[i].indexOf(productId) != -1) {
				        var productIdAndCount = listProdAddId[i].split("_");
				        if(productIdAndCount.length > 1){
				        	var countCurrent = parseInt(productIdAndCount[productIdAndCount.length-1]) + parseInt(countProduct);
				        	listProdAddId[i] = productId + "_" + countCurrent;
				        	$("span[id*=countProdShow" + productId + "]").text(countCurrent);
				        }
				    }
				}
				$("button[id*='btnCancelAddProd']").click();
		    	hideIconLoading();
			}
			
			var priceProductSelect = $("span[id*=priceProductSelect" + productId + "]").text();
			var productMoney = $("span[id*='productMoney']").text();
			var totalMoney = $("span[id*='totalMoney']").text();
			if(priceProductSelect.trim().length>0 && productMoney.trim().length>0 && totalMoney.trim().length>0){
				priceProductSelect = priceProductSelect.trim().replace(/\,/g, "");
				productMoney = productMoney.trim().replace(/\,/g, "");
				totalMoney = totalMoney.trim().replace(/\,/g, "");
				
				productMoney = parseInt(productMoney) + (parseInt(priceProductSelect) * parseInt(countProduct));
				totalMoney = parseInt(totalMoney) + (parseInt(priceProductSelect) * parseInt(countProduct));
				$("span[id*='productMoney']").text(addCommas(productMoney));
				$("span[id*='totalMoney']").text(addCommas(totalMoney));
			}
		}
		else{
			hideIconLoading();
			$("p[id*='msgAddProd']").text("Số lượng sản phẩm phải lớn hơn 0 !");		
		}
	}catch (e) {
		// TODO: handle exception
	}
}

function deleteProdOrder(productId){
	try{
		for (var i = 0; i < listProdAddId.length; i++) {
		    if (listProdAddId[i].indexOf(productId) != -1) {
		    	listProdAddId.splice(i,1);
		    }
		}
		var priceProdShow = $("span[id*=priceProdShow" + productId + "]").text();
		var countProdShow = $("span[id*=countProdShow" + productId + "]").text();
		var productMoney = $("span[id*='productMoney']").text();
		var totalMoney = $("span[id*='totalMoney']").text();
		if(priceProdShow.trim().length>0 && countProdShow.trim().length>0 && productMoney.trim().length>0 && totalMoney.trim().length>0){
			priceProdShow = priceProdShow.trim().replace(/\,/g, "");
			productMoney = productMoney.trim().replace(/\,/g, "");
			totalMoney = totalMoney.trim().replace(/\,/g, "");
			
			productMoney = parseInt(productMoney) - (parseInt(priceProdShow) * parseInt(countProdShow));
			totalMoney = parseInt(totalMoney) - (parseInt(priceProdShow) * parseInt(countProdShow));
			$("span[id*='productMoney']").text(addCommas(productMoney));
			$("span[id*='totalMoney']").text(addCommas(totalMoney));
		}
		
		$("table[id*=tableProd" + productId + "]").remove();
	}catch (e) {
		// TODO: handle exception
	}
}

function createOrder(){
	try{
		showIconLoading();
		var listProductString = "";
		for(var i =0; i<listProdAddId.length; i++){
			listProductString += (listProdAddId[i] + ";");
		}
		var customerName = $("input[name*='customerName']").val();
		var email = $("input[name*='cusEmail']").val();
		var phoneNumber = $("input[name*='cusPhoneNumber']").val();
		var cusAddress = $("textarea[name*='cusAddress']").val();
		var shipperId = $("select[name*='shipperId']").val();
		
		var formData = new FormData();
		formData.append('customerName', customerName);
		formData.append('email', email);
		formData.append('phoneNumber', phoneNumber);
		formData.append('cusAddress', cusAddress);
		formData.append('shipperId', shipperId);
		formData.append('listProductString', listProductString);
		
		var url = "/SaleManager/order/createOrder";
		$.ajax({	
		    type: 'POST', 
		    url: url, 
		    cache: false,
		    data: formData,
		    contentType: false,
            processData: false,
		    success: function(data){ 
		    	if(null == data){
		    		$("button[id*='btnCancel']").click();
		    		notifyMessage("Tạo đơn hàng thành công !");
		    	}
		    	else{
		    		$("p[id*='errorCreateOrder']").show().text(data);
		    	}
		    },
		    complete:function(data){
		    	hideIconLoading();
		    }
		}); 
	}catch (e) {
		// TODO: handle exception
	}
}