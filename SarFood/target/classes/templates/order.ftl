<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
<#if CURRENT_MESSAGE??>
<div class="alert alert-success hidden-print" role="alert">${CURRENT_MESSAGE}</div>
</#if>

<div id="order" style="background:white;">
	<table class="table table-bordered">
	<thead>
		<tr>
			<th><@spring.message "text.dish"/></th>
			<th><@spring.message "text.price"/></th>
			<th><@spring.message "text.count"/></th>
		</tr>
	</thead>
	<tbody>
	<#list order.orderItems as item>
			<tr id="product${item.menuItem.id}" class="item">
				<td class="name">${item.menuItem.content}</td>
				<td class="price">Р${item.menuItem.price}</td>
				<td class="count">${item.count}</td>
			</tr>
	</#list>
	<tr>
			<td colspan="2" class="text-right"><strong><@spring.message "text.total cost"/>:</strong></td>
			<td class="total">Р${order.totalCost}</td>
		</tr>
	</tbody>
</table>
</div>
</@c.page>