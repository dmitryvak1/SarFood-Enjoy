<#import "/spring.ftl" as spring/>
<meta charset="UTF-8">
<html>

    <body>

        <h1><@spring.message "greeting"/></h1>

        <a href="?lang=ua">
            <@spring.message "lang.ua"/>
        </a>
        <a href="?lang=en">
            <@spring.message "lang.eng"/>
        </a>
        <a href="?lang=ru">
            <@spring.message "lang.ru"/>
        </a>

    </body>
</html>