<#import "/spring.ftl" as spring/>
<#macro login path isRegisterForm>
	<div class="row justify-content-center align-items-center" style="height: 75vh;">
		<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
			<div class="card card-signin my-5">
				<div class="card-body">
					<#if isRegisterForm>
						<h4 class="card-title text-center"><@spring.message "text.registration"/></h4>
					<#else>
						<h4 class="card-title text-center"><@spring.message "text.Sign in"/></h4>
					</#if>
				<form action="${path}" method="post">
				
						<div class="form-group row">
						<div class="col">
							<input type="text" name="email" value="<#if user??>${user.email}</#if>" 
								   class="form-control ${(emailError??)?string('is-invalid', '')}"
								   placeholder="<@spring.message "text.email"/>" />
							<#if emailError??>
              			    	<div class="invalid-feedback">
                  				 	${emailError}
               				    </div>
           					</#if>
    				    </div>
					</div>
					
					<div class="form-group row">
						<div class="col">
							<input type="password" name="password"
								class="form-control ${(passwordError??)?string('is-invalid', '')}"
								placeholder="<@spring.message "text.password"/>" />
							<#if passwordError??>
              					<div class="invalid-feedback">
               				  	   ${passwordError}
              					</div>
          					</#if>
     				    </div>
					</div>
					
					<#if isRegisterForm>
					    					
    				<div class="form-group row">
						<div class="col">
							<input type="text" name="username" value="<#if user??>${user.username}</#if>" 
								   class="form-control ${(usernameError??)?string('is-invalid', '')}"
								   placeholder="<@spring.message "text.name"/>" />
							<#if usernameError??>
              			    	<div class="invalid-feedback">
                  				 	${usernameError}
               				    </div>
           					</#if>
    				    </div>
					</div>
					
    					<div class="form-group row">
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<span id="selected" data-bind="label">+7</span><span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li class="option" style="cursor: pointer;"><span class="flag-icon flag-icon-ru"></span>+7</li>
								</ul>
							</div>
    					
							<div class="col">
								<input required type="text" name="phone"
									class="form-control"
									id="phone" placeholder="<@spring.message "text.telephone"/>" />
							</div>
    					</div>
           			<#else>
                    	<div class="custom-control custom-checkbox mb-3">
                			<input type="checkbox" class="custom-control-input" id="customCheck1">
                			<label class="custom-control-label" for="customCheck1"><@spring.message "text.remember me"/> (in progress)</label>
              			</div>
    				</#if>
    					<input type="hidden" name="_csrf" value="${_csrf.token}" />
    				<#if !isRegisterForm>
    					<a  href="/registration"><@spring.message "text.don`t have an account?"/></a><br>
    					<a  href="/recovery"><@spring.message "text.forgot your password?"/> (in progress)</a>
    					<#else>
    					<a href="/login"><@spring.message "text.already have an account?"/></a>
    					</#if>
    					<button class="btn btn-primary btn-block text-uppercase mt-2" type="submit"><#if isRegisterForm><@spring.message "text.create"/><#else><i class="fa fa-sign-in"></i> <@spring.message "text.sign in"/></#if></button>
				</form>
			</div>
		</div>
	</div>
</div>
</#macro>