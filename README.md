# VideoListToDetail Base Google ExoPlayer
### 视频列表无缝切换到详情，基于谷歌ExoPlayer
> 功能：
> 列表视频播放，点击无缝切换到详情，播放视频不停止，不卡屏
> 
> 原理：
> 1.使用同一个ExoPlayer，对齐设置不同的绘制View达到视频可以不停止播放的效果。
> 2.使用Google安卓5.0以上支持的共享元素切换方式，实现切换不同Acitivty可以无缝显示效果。

<video id="video" controls="" preload="none" poster="http://media.w3.org/2010/05/sintel/poster.png">
      <source id="mp4" src="http://media.w3.org/2010/05/sintel/trailer.mp4" type="video/mp4">
      <source id="webm" src="http://media.w3.org/2010/05/sintel/trailer.webm" type="video/webm">
      <source id="ogv" src="http://media.w3.org/2010/05/sintel/trailer.ogv" type="video/ogg">
      <p>Your user agent does not support the HTML5 Video element.</p>
    </video>