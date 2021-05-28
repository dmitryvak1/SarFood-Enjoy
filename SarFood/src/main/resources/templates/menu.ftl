<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>

<div class="row filters">
	<div class="col-6 col-sm">
		<form method="post">
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
     			   <select name="categoryId" onchange="submit()">
     				 		<option value="" hidden selected><#if type??><@spring.message type/><#else><@spring.message "text.dishes"/></#if></option>
     				 		<option value="0"><@spring.message "text.dishes"/></option>
						<#list categories as category>
     						<option value="${category.id}"><@spring.message category.type/></option>
  						</#list>
   				   </select>
		</form>
    </div>
    
	<div class="col-6 col-sm">
		<form method="post">
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
     			   <select name="filterId" onchange="submit()">
     				 		<option value="" hidden selected><@spring.message filters/></option>
     						<option value="0"><@spring.message "text.from cheap"/></option>
     						<option value="1"><@spring.message "text.from expensive"/></option>
     						<option value="2"><@spring.message "text.newest"/></option>
   				   </select>
		</form>
    </div>
</div>

<div class="goods">
	<ul class="goods_grid">
		<#list page.content as product>
			<li	class="goods_cell">
				<div class="goods_tile">
					<div id="product${product.id}" class="product">
						<div class="product-image">
							<img src="static/${product.link}" alt="">
						</div>	
					<div class="desc">
						<div class="cell">
							<p>
								<span class="title">Details</span> ${product.messagekey}
							</p>
						</div>
					</div>
					<div class="name pt-2" style:"white-space: pre-line;">${product.content}</div>
					<div class="price"> ${product.price} Р</div>
					<a class="btn buy-btn mt-1" data-id-product="${product.id }" style="background:#247b2d; color:white; cursor: pointer;"><@spring.message "text.add to cart"/></a>
				</div>
			</li>
		</#list>
	</ul>
</div>

<div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog" style="font-size:1.5rem;">
	<div class="modal-dialog">
		<form id="addProduct">
			<div class="modal-content" style="width:120%; margin-top: 6rem;">
				<div class="modal-header justify-content-center">
					<h4 class="modal-title"><@spring.message "text.add product to shopping cart"/></h4>
				</div>
				<div class="modal-body row">
					<div class="col-xs-12 col-sm-6">
						<h4 class="name text-center pb-4">Name</h4>
							<div class="list-group hidden-xs adv-chars">
							</div>
							<div class="list-group">
								<span class="list-group-item"><small><@spring.message "text.price"/>: </small><span class="price">0</span></span> 
								<span class="list-group-item"><small><@spring.message "text.count"/>: </small><input type="number" class="count" value="1" min="1" max="50"></span> 
								<span class="list-group-item"><small><@spring.message "text.total cost"/>: </small><span class="cost">0</span></span>
							</div>
							
					</div>
					<div class="col-xs-12 col-sm-6">
							<img class="product-image" style="max-width:100%" src="">
							<div class="desc pt-3"> There should be a description of the dish (in progress) </div>
							
					</div>
				</div>
			<div class="modal-footer">
				<button id="addToCart" type="submit" class="btn" style="background:#247b2d; color:white;"><@spring.message "text.add to cart"/></button>
				<button type="button" class="btn btn-default" data-dismiss="modal" style="background:red; color:white;"><@spring.message "text.close"/></button>
			</div>
		</div>
		</form>
	</div>
</div>

</@c.page>
