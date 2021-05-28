<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-md navbar-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/menu"><@spring.message "text.menu"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/contacts"><@spring.message "text.contacts"/></a>
            </li>
            <#if isAdmin>
            <li class="nav-item active">
                <a class="nav-link" href="/user" style="white-space: nowrap;"><@spring.message "text.users"/></a>
            </li>
            </#if>
             <#if isAdmin>
            <li class="nav-item active">
                <a class="nav-link" href="/orders-list" style="white-space: nowrap;"><@spring.message "text.orders"/></a>
            </li>
            </#if>
        </ul>
    </div>
    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto" href="/menu">SarFood enjoy</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <div class="navbar-collapse collapse w-100 order-2 dual-collapse2">
        <ul  class="navbar-nav ml-auto">
				<li class="dropdown" id="currentShoppingCart" style="cursor: pointer;">
					<a class="nav-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="white-space: nowrap; text-decoration:none; color:white;">
						<span id="count" data-cart-items="${(CURRENT_SHOPPING_CART.totalCount)!0}">
							<i class="fe fe-shopping-cart"></i> 
               			</span>
					</a>
					<div class="dropdown-menu shopping-cart-desc" style="text-align:center;">
						<span class="total-cost">Р${(CURRENT_SHOPPING_CART.totalCost)!0}</span><br>
						<a href="/shopping-cart" class="btn" style="background:#ed5f00;"><@spring.message "text.view cart"/></a>
					</div>
				</li>
                 <#if known>
                <li class="nav-item active dropdown" style="cursor: pointer;">
            <a class="nav-link dropdown-toggle waves-effect waves-light" id="navbarDropdownMenuLink-4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
              <i class="fe fe-user"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
              <a class="dropdown-item waves-effect waves-light" href="/user/profile"><@spring.message "text.my account"/></a>
              <a class="dropdown-item waves-effect waves-light" href="/orders-list"><@spring.message "text.my orders"/></a>
              <form action="/logout" method="post">
  				  <input type="hidden" name="_csrf" value="${_csrf.token}" />
  				  <button class="dropdown-item waves-effect waves-light btn" style="outline: none !important; box-shadow:none;" type="submit"><@spring.message "text.sign out"/></button>
				</form>
            </div>
          </li>
            <#else>
           	   <li class="nav-item active">
                <a class="nav-link" href="/login" style="white-space: nowrap;"><@spring.message "text.sign in"/></a>
         	   </li>
                </#if>
           <li class="pl-2 pt-1">

 		   	</li>
        </ul>
    </div>
</nav>