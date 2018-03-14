# 自定义View相关整理

# Path相关设置

path = new Path();
//抗锯齿
path.setAntiAlias(true)
paint.setColor(Color.BLACK);
paint.setStrokeWidth(3);
//实心或者空心
paint.setStyle(Paint.Style.STROKE);
//设置虚线效果
paint.setPathEffect(new DashPathEffect(new float[] {15, 5}, 0));

# invalidate和postInvalidate的区别

- 调用invalidate，它会告诉系统当前视图已更改，并且应该尽快重新绘制,只能在UIThread中调用
- 调用postInvalidate方法, 它从非UIThread中通知系统，视图尽快在UIThread的下一个事件回调中重绘

# getSuggestedMinimumWidth 和getMinimumWidth区别

如果View没有设置背景，那么返回android:minWidth这个属性所指定的值，这个值可以为0；如果View设置了背景，则返回android:minWidth和背景的最小宽度这两者中的最大值
