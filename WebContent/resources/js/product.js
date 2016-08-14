var filesUpload = [];
var productIdDelete;
var categoryIdDelete;

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
			        alert('Không thể chỉnh sửa sản phẩm ');
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
		if(isNaN(sizeImage) || sizeImage.length == 0)
			sizeImage = "0";
		sizeImage = parseInt(sizeImage);
		for(var i=0; i<countFile; i++){
			try{
				var fileTypes = filesUpload[i].name.split('.');
			    var type = fileTypes[fileTypes.length - 1];
			    var fileName = productId + "_" + (i + sizeImage);
			    
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
	try{
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
	}catch (e) {
		// TODO: handle exception
	}
	
}

function setDeleteCategory(categoryId){
	categoryIdDelete = categoryId;
}

function deleteCategory(){
	try{
		var url = host + "/SaleManager/product/deleteCategory";
		var formData = new FormData();
		formData.append('idDelete', categoryIdDelete);
		$.ajax(
			{	
			    type: 'POST', 
			    url: url, 
			    data: formData,
			    cache: false,
			    contentType: false,
	            processData: false,
			    success: function(data){         
			    	$("tr[id*=rowCategory" + categoryIdDelete  +"]").remove();
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown){
			        alert('Không thể xóa được khách hàng này,vui lòng thử lại ');
			    }
			}); 
	}catch (e) {
		// TODO: handle exception
	}
}

function addCategory(){
	try{
		var categoryName = $("input[name*='categoryName']").val();
		var description = $("textArea[name*='description']").val();
		var groupStatus = $("select[name*='groupStatus']").val();
		if(null == name || name.trim().length == 0){
			alert("Tên danh mục không được để trống!");
		}else{
			var formData = new FormData();
			formData.append('categoryName', categoryName);
			formData.append('description', description);
			formData.append('groupStatus', groupStatus);
			
			var url = host + "/SaleManager/product/addCategory";
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
					    		location.reload();
					    	}
					    	else{
					    		$("p[id*='errorAddProduct']").show();
					    		$("p[id*='errorAddProduct']").text(data);
					    	}
					    },
					    error: function(XMLHttpRequest, textStatus, errorThrown){
					        alert('Không thể thêm được danh mục sản phẩm,vui lòng thử lại ');
					    }
					});
		}
		 
	}catch (e) {
		// TODO: handle exception
	}
}

function showEditCategory(categoryId){
	try{
		hideEditCategory();
		$("p[id*=name" + categoryId + "]").hide();
		$("input[id*=inputName" + categoryId + "]").show();
		
		$("p[id*=des" + categoryId + "]").hide();
		$("textArea[id*=description" + categoryId + "]").show();
		
		$("p[id*=status" + categoryId + "]").hide();
		$("select[id*=status" + categoryId + "]").show();
		
		$("div[id*=action" + categoryId + "]").hide();
		$("div[id*=editAction" + categoryId + "]").show();
	}catch (e) {
		// TODO: handle exception
	}
}

function hideEditCategory(){
	try{
		$("p[id*='name']").show();
		$("input[id*='inputName']").hide();
		
		$("p[id*='des']").show();
		$("textArea[id*='description']").hide();
		
		$("p[id*='status']").show();
		$("select[id*='status']").hide();
		
		$("div[id*='action']").show();
		$("div[id*='editAction']").hide();
	}catch (e) {
		// TODO: handle exception
	}
}

function editCategoryAction(categoryId){
	try{
		var name = $("input[id*=inputName" + categoryId + "]").val();
		var description = $("textArea[id*=description" + categoryId + "]").val();
		var status = $("select[id*=status" + categoryId + "]").val();
		if(null == name || name.trim().length == 0){
			alert("Tên không được để trống!");
		}else{
			var url = host + "/SaleManager/product/updateCategory";
			var formData = new FormData();
			formData.append('name', name);
			formData.append('description', description);
			formData.append('status', status);
			formData.append('categoryId', categoryId);
			
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
					    		$("p[id*=name" + categoryId + "]").text(name);
								$("p[id*=des" + categoryId + "]").text(description);
								$("p[id*=status" + categoryId + "]").text(status);
								hideEditCategory();
					    	}
					    	else{
					    		$("p[id*='errorAddProduct']").show();
					    		$("p[id*='errorAddProduct']").text(data);
					    	}
					    },
					    error: function(XMLHttpRequest, textStatus, errorThrown){
					        alert('Không thể chỉnh sửa được danh mục sản phẩm,vui lòng thử lại ');
					    }
					});
			
		}
		
	}catch (e) {
		// TODO: handle exception
	}
}

function changeImageProductView(id, imageName){
	var src = "/SaleManager/product/ImageAction.action?imageId=" + imageName;
	$("img[class*='imageProductShow']").attr("src", src);
	$("img[id*='imgThumb']").removeClass('imgSelect');
	$("img[id*=imgThumb" + id +"]").addClass('imgSelect');
}