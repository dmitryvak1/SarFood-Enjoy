<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">

<@c.page>

	<#if CURRENT_SHOPPING_CART??>
	<#if known>
	<#else>
		<div class="alert alert-warning hidden-print my-5" role="alert"><@spring.message "text.shopping cart alert"/></div>
	</#if>
<div id="shoppingCart" style="background:white;">
	
	<table class="table table-bordered">
	<thead>
		<tr>
			<th><@spring.message "text.dish"/></th>
			<th><@spring.message "text.price"/></th>
			<th><@spring.message "text.count"/></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<#list CURRENT_SHOPPING_CART.items as item>
			<tr id="product${item.product.id }" class="item">
				<td class="name">${item.product.content}</td>
				<td class="price">Р${item.product.price}</td>
				<td class="count">${item.count}</td>
				<td class="hidden-print">
				<form action="/shopping-cart" method="post">
				<input type="hidden" name="_csrf" value="${_csrf.token}" />
				<input type="hidden" name="productId"" value="${item.product.id }" />
					<button class="btn btn-danger"><@spring.message "text.remove"/></a>
					</form>
				</td>
			</tr>
	</#list>
	<tr>
			<td colspan="2" class="text-right"><strong><@spring.message "text.total cost"/>:</strong></td>
			<td class="total">Р${CURRENT_SHOPPING_CART.totalCost}</td>
		</tr>
	</tbody>
</table>
</div>
<form action="/order" method="post">
<div style="display: inline-block;">
		<#if known>
				<input type="hidden" name="_csrf" value="${_csrf.token}" />
    			<button class="btn btn-primary" type="submit"><@spring.message "text.make order"/></button>
	<#else>
		<a href="/login" class="btn btn-primary btn-block" ><@spring.message "text.sign in"/></a>
	</#if>
</form>
</div>
<#else><div class="alert alert-danger mt-4" role="alert">Shoping cart is empty!</div>
</#if>

</@c.page>