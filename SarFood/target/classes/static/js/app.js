$(function() {
	var init = function() {
		initBuyBtn();
		$('#addProductPopup .count').change(calculateCost);
	};
	var showAddProductPopup = function() {
		var idProduct = $(this).attr('data-id-product');
		var product = $('#product' + idProduct);
		$('#addProductPopup').attr('data-id-product', idProduct);
		$('#addProductPopup .product-image').attr('src', product.find('.product-image img').attr('src'));
		$('#addProductPopup .name').text(product.find('.name').text());
		var price = product.find('.price').text();
		$('#addProductPopup .price').text(price);
		$('#addProductPopup .category').text(product.find('.category').text());
		$('#addProductPopup .producer').text(product.find('.producer').text());
		$('#addProductPopup .count').val(1);
		$('#addProductPopup .cost').text(price);
		$('#addProductPopup').modal({
			show : true
		});
	};
	var initBuyBtn = function() {
		$('.buy-btn').click(showAddProductPopup);
	};
	var calculateCost = function() {
		var priceStr = $('#addProductPopup .price').text();
		var price = parseFloat(priceStr.replace('Р', ' '));
		var count = parseInt($('#addProductPopup .count').val());
		var min = parseInt($('#addProductPopup .count').attr('min'));
		var max = parseInt($('#addProductPopup .count').attr('max'));
		if (count >= min && count <= max) {
			var cost = price * count;
			$('#addProductPopup .cost').text(cost + ' Р');
		} else {
			$('#addProductPopup .count').val(1);
			$('#addProductPopup .cost').text(priceStr);
		}
	};
	var refreshTotalCost = function() {
		var total = 0;
		$('#shoppingCart .item').each(
				function(index, value) {
					var count = parseInt($(value).find('.count').text());
					var price = parseFloat($(value).find('.price').text()
							.replace('Р', ' '));
					var val = price * count;
					total = total + val;
				});
		$('#shoppingCart .total').text('Р' + total);
	};
	
	init();
});