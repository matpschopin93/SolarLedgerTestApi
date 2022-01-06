# SolarLedgerTestApi

Hi,
I created a spring boot app that can it be started with : 

mvn spring-boot:run

and builded with :
mvn clean install

The api doesnt't handle the concorrency, but i tried to use Java Stream as request.

I hope it will like you.

In the folder test/resource there are some examples of request Body in Json Format used by JunitTest.

I didn't implemented Services classes (maybe i shoulded put the Java Stream there?) cause of the business logical was easy,
so I think the Repository class was enougth to work directly into the Controller.

Best Regards

Matteo