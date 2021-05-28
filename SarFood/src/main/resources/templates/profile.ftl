<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
<section class="profile" style="background:white;" >

        <div class="row">
          <div class="col-12 text-center name">
            <@spring.message "text.my account"/>
          </div>
        </div>
        
        <div class="row">
          <div class="col-12 col-md-3">

            <div class="mb-5 mb-md-0">
              <div class="list-group list-group-flush-x">
                <a class="item dropright-toggle" href="/orders-list">
                   <@spring.message "text.orders"/>
                </a>
                <a class="item dropright-toggle is-active" href="/user/profile">
                   <@spring.message "text.personal info"/>
                </a>
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

            <form class="px-3">
              <div class="row">
                <div class="col-12 col-md-6">

                  <div class="form-group">
                    <label for="accountName">
                      <@spring.message "text.name"/>:
                    </label>
                    <input class="form-control" id="accountFirstName" type="text" placeholder="First Name" value="${username!''}" required="">
                  </div>

                </div>
                <div class="col-12">

                  <div class="form-group">
                    <label for="accountLastName">
                    <@spring.message "text.telephone"/>:
                    </label>
                    <input class="form-control" id="accountLastName" type="text" placeholder="Telephone Number" value="${phone!''}" required="">
                  </div>

                </div>
                <div class="col-12">

                  <div class="form-group">
                    <label for="accountEmail">
                      <@spring.message "text.email"/>:
                    </label>
                    <input class="form-control" id="accountEmail" type="email" placeholder="Email Address" value="${email!''}" required="">
                  </div>

                </div>
                <div class="col-12 col-md-6">

                  <div class="form-group">
                    <label for="accountPassword">
                      <@spring.message "text.current password" />:
                    </label>
                    <input class="form-control" id="accountPassword" type="password" placeholder="  <@spring.message "text.current password" />" required="">
                  </div>

                </div>
                <div class="col-12 col-md-6">

                  <div class="form-group">
                    <label for="AccountNewPassword">
                      <@spring.message "text.new password" />:
                    </label>
                    <input class="form-control" id="AccountNewPassword" type="password" placeholder="<@spring.message "text.new password" />" required="">
                  </div>

                </div>
                <div class="col-12">

                  <button class="btn btn-dark" type="submit"><@spring.message "text.save" /></button>

                </div>
              </div>
            </form>

          </div>
        </div>
    </section>
</@c.page>