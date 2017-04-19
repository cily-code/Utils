# 工具类总结
<ul>
  <li>utils_base:所有工具类的基础包，包括HttpUtils、Logs、RandomUtils、StrUtils、TimerUtils、TimeUtils、UUIDUtils</li>
  <li>utils_secret:加密工具，包括AES工具和MD5工具</li>
  <li>utils_app:android开发基础类，定义了一系列常用的工具类和方法</li>
  <li>utils_app_rx:android开发所用的OkHttp + Retrofit + RxJava</li>
</ul>
<h3>RxBus（一）</h3>
<p>&nbsp;&nbsp;&nbsp;&nbsp;如果已经在项目里使用了RxJava了，可以考虑实现RxBus来替代EventBus等库，在实现相同功能的前提下，减少代码量。由于本人正式在项目里，使用RxJava时间比较晚，所以，Rx系列的文章，许多具体实现是参考了其他的blog，并加上自己的理解。但由于无法考证哪位是原作者，在此就不添加作者链接了，但还是对大牛们表示感谢。关于具体如何实现RxBus，在此不做具体描述，因为已经有很多的blog，都详细的讲解如何去实现RxBus，如果不清楚，可以自行搜索RxBus的实现或参考我的代码。本blog更多的是关注在如何在项目里去使用RxBus，以及关于Event方面的内容。</p>
<img src="https://github.com/cily-code/Utils/blob/master/images/rxbus_1.png" />
<p>&nbsp;&nbsp;&nbsp;&nbsp;RxBus的代码暂时封装的比较简单，后期根据实际情况，可能会做进一步的封装，以便于使用更简单。在post方法里，可以发送任何对象，但我考虑的是代码统一维护，所以强制规定了只能发送Event类型的对象。本期的做法是定义一个没有任何成员变量、成员方法的基类，如果需要使用，则自定义一个Event子类，这样，在业务层处理Event的时候，所有的对象都是Event的子类。这种做法比较简单，也比较清晰，但在实际使用过程中，发现需要自定义N多个子类Event，繁琐。后面在使用Handler + Message的时候，这种模式，不就是我所需要的吗？于是就打算学习一个Message的做法，自行封装一个统一的Event。目前的Event定义了两个成员变量what和obj，所有的Event都发送Event对象，业务层处理的时候，都是处理Event对象，不再处理不同的子Event了；根据what区分是哪个事件，根据obj区分具体的事件内容</p>
<img src="https://github.com/cily-code/Utils/blob/master/images/rxbus_event_1.png" />
<p>&nbsp;&nbsp;&nbsp;&nbsp;但这只是一个最初的版本，只解决了需要重复定义Event的问题，后续我将参考Message的源码，加入对象池，以便于Event对象的复用</p>