<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#import "parts/pager.ftl" as p>
<@c.page>
<#if CURRENT_MESSAGE??>
</#if>

	<section class="profile" style="background: white;">

		<div class="row">
			<div class="col-12 text-center name"><@spring.message "text.my account"/></div>
		</div>

		<div class="row">
			<div class="col-12 col-md-3">
				<div class="mb-5 mb-md-0">
					<div class="list-group list-group-flush-x">
						<a class="item dropright-toggle is-active" href="/orders-list">
							<@spring.message "text.orders"/> </a> <a
							class="item dropright-toggle" href="/user/profile">
							<@spring.message "text.personal info"/> </a>
					</div>
				</div>

				<form action="/logout" method="post">
					<input type="hidden" name="_csrf" value="${_csrf.token}" />
					<div class="list-group list-group-flush-x exit">
						<button class="btn item dropright-toggle" type="submit"><@spring.message "text.sign out"/></button>
					</div>
				</form>

			</div>

			<div class="col-12 col-md-9 col-lg-8 offset-lg-1">
				<div class="card card-lg mb-5 mr-2 border">
					<div class="card-body p-2">
					
					<#list page.content as order>
						<div class="card card-sm">
							<div class="card-body bg-light p-2">
								<div class="row">

									<div class="col-6 col-lg-3">
										<h6 class="mb-0 text-muted"><@spring.message "text.order"/> №:</h6>
										<p class="text-bold">${order.id}</p>
									</div>

									<div class="col-12 col-lg-3">
										<h6 class="mb-0 text-muted"><@spring.message "text.order date"/>:</h6>
										<p class="text-bold">
											${order.created}
										</p>
									</div>

									<div class="col-6 col-lg-3">
										<h6 class="mb-0 text-muted"><@spring.message "text.order amount"/>:</h6>
										<p class="text-bold">Р ${order.totalCost}</p>
									</div>

									<div class="col-6 col-lg-3">
										<a class="btn btn-sm btn-block btn-outline-dark"
											href="/order/${order.id}"><@spring.message "text.order details"/></a>
									</div>

								</div>
							</div>
						</div>
						</#list>
						
					</div>
				</div>
					<@p.pager url page />
			</div>
			
		</div>
	</section>
</@c.page>