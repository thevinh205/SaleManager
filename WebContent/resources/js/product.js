var filesUpload = [];
var productIdDelete;

function addNewProduct(){
	showIconLoading();
	var productId = $("input[name*='productId']").val();
	var productName = $("input[name*='productName']").val();
	var groupProduct = $("select[name*='groupProduct']").val();
	var sellPrice = $("input[name*='sellPrice']").val();
	var buyPrice = $("input[name*='buyPrice']").val();
	var inventory = $("input[name*='inventory']").val();
	var description = $("div[class*='nicEdit-main']").html();
	var typeAvatar = "jpg";
	if(null !=filesUpload && filesUpload.length > 0){
		var fileTypes = filesUpload[0].name.split('.');
		typeAvatar = fileTypes[fileTypes.length - 1];
	}
	
	var formData = new FormData();
	formData.append('productId', productId);
	formData.append('productName', productName);
	formData.append('groupProduct', groupProduct);
	formData.append('sellPrice', sellPrice);
	formData.append('buyPrice', buyPrice);
	formData.append('inventory', inventory);
	formData.append('description', description);
	formData.append('typeAvatar', typeAvatar);
	
	
	var url = host + "/SaleManager/product/addProductAction";
	uploadImage(productId);
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
		    		window.location.href = host + "/SaleManager/product/productList";
		    	}
		    	else{
		    		$("p[id*='errorAddProduct']").show();
		    		$("p[id*='errorAddProduct']").text(data);
		    		$('body').animate({ scrollTop: 0 }, "fast");
		    		hideIconLoading();
		    	}
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Không thể thêm mới sản phẩm ');
		    }
		}); 
}

function updateProduct(){
	showIconLoading();
	var productId = $("input[name*='productId']").val();
	var productName = $("input[name*='productName']").val();
	var groupProduct = $("select[name*='groupProduct']").val();
	var sellPrice = $("input[name*='sellPrice']").val();
	var buyPrice = $("input[name*='buyPrice']").val();
	var inventory = $("input[name*='inventory']").val();
	var description = $("div[class*='nicEdit-main']").html();
	var typeAvatar = "jpg";
	if(null !=filesUpload && filesUpload.length > 0){
		var fileTypes = filesUpload[0].name.split('.');
		typeAvatar = fileTypes[fileTypes.length - 1];
	}
	
	var formData = new FormData();
	formData.append('productId', productId);
	formData.append('productName', productName);
	formData.append('groupProduct', groupProduct);
	formData.append('sellPrice', sellPrice);
	formData.append('buyPrice', buyPrice);
	formData.append('inventory', inventory);
	formData.append('description', description);
	formData.append('typeAvatar', typeAvatar);
	var sizeImage = $("p[id*='sizeImage']").text();
	var countFile = filesUpload.length;
	var totalFile = sizeImage + countFile;
	formData.append('totalFile', totalFile);
	
	var url = host + "/SaleManager/product/editProductAction";
	uploadImage(productId);
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
			    		window.location.href = host + "/SaleManager/product/productList";
			    	}
			    	else{
			    		$("p[id*='errorAddProduct']").show();
			    		$("p[id*='errorAddProduct']").text(data);
			    		$('body').animate({ scrollTop: 0 }, "fast");
			    		hideIconLoading();
			    	}
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown){
			        alert('Không thể thêm mới sản phẩm ');
			    }
			}); 
}

var loadFile = function(event){
	$('#imgProductPreview').show(300);
	var files = event.target.files;
	var countFile = files.length;
	
	for(var i=0; i<countFile; i++){
	    var imgPreview = "<img class='imgPreview' src='" + URL.createObjectURL(event.target.files[i]) + "'/>";
	    $('#imgProductPreview').append(imgPreview).fadeIn('fast');
	    filesUpload.push(files[i]);
	}
}

function removeAllFilePreview(){
	$('#imgProductPreview').hide(500);
	$('#imgProductPreview').empty();
	filesUpload = [];
	$("p[id*='sizeImage']").text('0');
}

function uploadImage(productId){
	if(null != filesUpload && filesUpload.length > 0){
		var countFile = filesUpload.length;
		var sizeImage = $("p[id*='sizeImage']").text();
		sizeImage = parseInt(sizeImage);
		for(var i=0; i<countFile; i++){
			try{
				var fileTypes = filesUpload[i].name.split('.');
			    var type = fileTypes[fileTypes.length - 1];
			    var fileName = productId + "_" + (i + sizeImage) + "." + type;
			    
				var data = new FormData();
			    data.append("file", filesUpload[i]);
			    data.append("type", type);
			    data.append("fileName", fileName);
			    data.append("typeUpload", "product");
			    data.append("parentId", productId);
			    
			    jQuery.ajax({
			        url: 'uploadImage',
			        type: "POST",
			        data: data,
			        dataType: 'script',
			        cache: false,
			        processData: false,
			        contentType: false,
			        async: false,
			        success: function () {
			        },
			        error: function () {
			        },
			        complete: function (res) {
			        	
			        }
			    });
			}catch (e) {
				// TODO: handle exception
			}
		}
		removeAllFilePreview();
	}	
}

function setDeleteProduct(productId){
	productIdDelete = productId;
}

function deleteProduct(){
	var url = host + "/SaleManager/product/deleteProduct";
	var formData = new FormData();
	formData.append('productIdDelete', productIdDelete);
	$.ajax(
		{	
		    type: 'POST', 
		    url: url, 
		    data: formData,
		    cache: false,
		    contentType: false,
            processData: false,
		    success: function(data){         
		    	$("tr[id*=rowPro" + productIdDelete  +"]").remove();
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Không thể xóa được khách hàng này,vui lòng thử lại ');
		    }
		}); 
}