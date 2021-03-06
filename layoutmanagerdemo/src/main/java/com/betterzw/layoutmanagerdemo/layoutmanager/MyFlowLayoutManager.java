package com.betterzw.layoutmanagerdemo.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 整理了 {@link FlowLayoutManager} 的代码，加了点注释，应该是没有修改原文件里的代码逻辑
 *
 * 页面加载后 adapter 会调用 13 次 onCreateViewHolder() 和 onBindViewHolder()
 * 页面加载后 RecyclerView 的子 view 有 13 个
 * 在滑动中获取到的 RecyclerView 的子 view 数有 13、14 个
 */
public class MyFlowLayoutManager extends LayoutManager {
    // 竖直偏移量。每次换行时，要根据这个 offset 判断
    private int mVerticalOffset;
    // 屏幕可见的第一个 view 的 Position
    private int mFirstVisiPos;
    // 屏幕可见的最后一个 view 的 Position
    private int mLastVisiPos;

    // key 是 view 的 position，value 是 view 的显示区域
    private SparseArray<Rect> mItemRects;

    public MyFlowLayoutManager() {
        // 如果 LayoutManager 要支持 WRAP_CONTENT 的布局，需要调用 setAutoMeasureEnabled(true)
        setAutoMeasureEnabled(true);
        mItemRects = new SparseArray<>();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 这个方法负责对 view 布局，是 LayoutManager 的入口。它会在如下情况被调用：
     * 1. 在 RecyclerView 初始化时，会被调用两次；
     * 2. 在调用 adapter.notifyDataSetChanged() 时，会被调用；
     * 3. 在调用 setAdapter 替换 Adapter 时,会被调用；
     * 4. 在 RecyclerView 执行动画时，它也会被调用。
     *
     * 即 RecyclerView 初始化、 数据源改变时都会被调用。
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            // 没有 Item，界面空着吧
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {
            // state.isPreLayout() 是支持动画的
            return;
        }
        // 在布局之前，将所有的子 View 先 Detach 掉，放入到 Scrap 缓存中
        detachAndScrapAttachedViews(recycler);

        // 初始化
        mVerticalOffset = 0;
        mFirstVisiPos = 0;
        mLastVisiPos = getItemCount();

        // 填充 childView
        fill(recycler, state, 0);
    }

    /**
     * 填充 childView 的核心方法，应该先填充，再移动。
     * 在填充时，预先计算 dy 的在内，如果 View 越界，回收掉。
     * 一般情况是返回 dy，如果出现 View 数量不足，则返回修正后的 dy.
     *
     * @param recycler
     * @param state
     * @param dy       RecyclerView 给我们的位移量,+,显示底端， -，显示头部
     * @return 修正以后真正的dy（可能剩余空间不够移动那么多了 所以return <|dy|）
     */
    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, int dy) {
        // 回收越界的子 View
        recyclerInvisibleView(dy, recycler);

        // 布局子 View 阶段
        if (dy >= 0) {
            return addChildViewWhenScrollDown(recycler, dy);
        } else {
            return addChildViewWhenScrollUp(recycler, dy);
        }
    }

    /**
     * 如果 dy 大于等于 0，表示 RecyclerView 往下滑了，这时从 mFirstVisiPos 开始往后遍历，
     * 把所有能在屏幕中显示的 view 并且没有添加进来的 view 都添加进来
     */
    private int addChildViewWhenScrollDown(RecyclerView.Recycler recycler, int dy) {
        int leftOffset = getPaddingLeft();
        int topOffset = getPaddingTop();
        int lineMaxHeight = 0;

        // RecyclerView 的子 view 的数量，未添加或者暂时 detach 的 view 不包含在内
        int childCount = getChildCount();

        // 遍历子 view 的开始位置
        int minPos = mFirstVisiPos;
        // 遍历子 view 的结束位置
        mLastVisiPos = getItemCount() - 1;
        if (childCount > 0) {
            // 如果 RecyclerView 的子 view 数量大于 0，说明从 mFirstVisiPos 开始的可见的 view 已经添加进 RecyclerView 里了，
            // 这时只需要在滑动过后的剩余空间里补上新的 view 即可。

            // 当前可见的最后一个 view
            View lastView = getChildAt(childCount - 1);
            // 当前可见的最后一个 view 的位置 + 1
            minPos = getPosition(lastView) + 1;
            topOffset = getDecoratedTop(lastView);
            leftOffset = getDecoratedRight(lastView);
            lineMaxHeight = Math.max(lineMaxHeight, getDecoratedMeasurementVertical(lastView));
        }
        // 顺序添加子 view 进 RecyclerView
        for (int i = minPos; i <= mLastVisiPos; i++) {
            // 找 recycler 要一个 view，我们不管它是从 scrap 里取，还是从 RecyclerViewPool 里取，亦或是 onCreateViewHolder 里拿。
            View child = recycler.getViewForPosition(i);
            // 添加子 view
            addView(child);
            // 测量子 view
            measureChildWithMargins(child, 0, 0);
            // 子 view 在水平方向占用的空间
            int decoratedMeasurementHorizontal = getDecoratedMeasurementHorizontal(child);
            // 子 view 在垂直方向占用的空间
            int decoratedMeasurementVertical = getDecoratedMeasurementVertical(child);
            if (leftOffset + decoratedMeasurementHorizontal <= getHorizontalSpace()) {
                // 如果上一个 view 的右侧位置加上要添加的子 view 的宽度小于当前行的宽度，说明这一行还能装得下新添加的子 view，不需要换行
                layoutDecoratedWithMargins(child, leftOffset, topOffset, leftOffset + decoratedMeasurementHorizontal, topOffset + decoratedMeasurementVertical);

                // 保存 Rect 供逆序 layout 用
                Rect rect = new Rect(leftOffset, topOffset + mVerticalOffset, leftOffset + decoratedMeasurementHorizontal, topOffset + decoratedMeasurementVertical + mVerticalOffset);
                mItemRects.put(i, rect);

                // 计算新的 leftOffset 和 lineMaxHeight
                leftOffset += decoratedMeasurementHorizontal;
                lineMaxHeight = Math.max(lineMaxHeight, decoratedMeasurementVertical);
            } else {
                // 如果当前行排列不下，需要另起一行
                // 更新 leftOffset、topOffset 和 lineMaxHeight
                leftOffset = getPaddingLeft();
                topOffset += lineMaxHeight;
                lineMaxHeight = 0;

                // 新起一行的时候要判断一下边界
                if (topOffset - dy > getHeight() - getPaddingBottom()) {
                    // 越界了就回收
                    removeAndRecycleView(child, recycler);
                    mLastVisiPos = i - 1;
                } else {
                    layoutDecoratedWithMargins(child, leftOffset, topOffset, leftOffset + decoratedMeasurementHorizontal, topOffset + decoratedMeasurementVertical);

                    // 保存 Rect 供逆序 layout 用
                    Rect rect = new Rect(leftOffset, topOffset + mVerticalOffset, leftOffset + decoratedMeasurementHorizontal, topOffset + decoratedMeasurementVertical + mVerticalOffset);
                    mItemRects.put(i, rect);

                    // 计算新的 leftOffset 和 lineMaxHeight
                    leftOffset += decoratedMeasurementHorizontal;
                    lineMaxHeight = Math.max(lineMaxHeight, decoratedMeasurementVertical);
                }
            }
        }
        // 添加完后，判断是否已经没有更多的 ItemView。此时如果屏幕仍有空白，则需要修正dy
        View lastChild = getChildAt(getChildCount() - 1);
        if (getPosition(lastChild) == getItemCount() - 1) {
            int gap = getHeight() - getPaddingBottom() - getDecoratedBottom(lastChild);
            if (gap > 0) {
                dy -= gap;
            }
        }
        return dy;
    }

    /**
     * 利用Rect保存子 view 边界
     * 正序排列时，保存每个子 view 的 Rect，逆序时，直接拿出来 layout。
     * 当 RecyclerView 往下滑了后再往上拉，要添加的子 view 应该都是在下滑过程中已经计算过显示区域的，
     * 所以在那个时候保存下来的子 view 的显示区域可以直接拿来用，不需要再重新计算一遍
     */
    private int addChildViewWhenScrollUp(RecyclerView.Recycler recycler, int dy) {
        int maxPos = getItemCount() - 1;
        mFirstVisiPos = 0;
        if (getChildCount() > 0) {
            View firstView = getChildAt(0);
            maxPos = getPosition(firstView) - 1;
        }
        for (int i = maxPos; i >= mFirstVisiPos; i--) {
            Rect rect = mItemRects.get(i);

            if (rect.bottom - mVerticalOffset - dy < getPaddingTop()) {
                mFirstVisiPos = i + 1;
                break;
            } else {
                View child = recycler.getViewForPosition(i);
                // 将 view 添加至 RecyclerView 中，childIndex 为 1，但是 view 的位置还是由 layout 的位置决定
                addView(child, 0);
                measureChildWithMargins(child, 0, 0);

                layoutDecoratedWithMargins(child, rect.left, rect.top - mVerticalOffset, rect.right, rect.bottom - mVerticalOffset);
            }
        }
        return dy;
    }

    /**
     * 在滑动过程中，需要回收没有显示在屏幕中的 View
     */
    private void recyclerInvisibleView(int dy, RecyclerView.Recycler recycler) {
        if (getChildCount() <= 0) {
            return;
        }
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (dy > 0) {
                // 如果 view 的下边界减去垂直方向的偏移量，小于 RecyclerView 顶端的位置，
                // 说明这个 view 已经滑到屏幕上方并且不可见，这时需要回收掉这个 view
                if (getDecoratedBottom(child) - dy < getPaddingTop()) {
                    removeAndRecycleView(child, recycler);
                    mFirstVisiPos++;
                    continue;
                }
            } else if (dy < 0) {
                // 如果 view 的上边界减去垂直方向的偏移量，小于 RecyclerView 底部的位置，
                // 说明这个 view 已经滑到屏幕下方并且不可见，这时需要回收掉这个 view
                if (getDecoratedTop(child) - dy > getHeight() - getPaddingBottom()) {
                    removeAndRecycleView(child, recycler);
                    mLastVisiPos--;
                    continue;
                }
            }
        }
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 位移 0 或者没有子 view，当然不移动
        if (dy == 0 || getChildCount() == 0) {
            return 0;
        }

        int realOffset = dy;//实际滑动的距离， 可能会在边界处被修复
        // 边界修复代码
        if (mVerticalOffset + realOffset < 0) {
            // 修复上边界
            realOffset = -mVerticalOffset;
        } else if (realOffset > 0) {
            // 修复下边界
            // 利用最后一个子View比较修正
            View lastChild = getChildAt(getChildCount() - 1);
            if (getPosition(lastChild) == getItemCount() - 1) {
                int gap = getHeight() - getPaddingBottom() - getDecoratedBottom(lastChild);
                if (gap > 0) {
                    realOffset = -gap;
                } else if (gap == 0) {
                    realOffset = 0;
                } else {
                    realOffset = Math.min(realOffset, -gap);
                }
            }
        }

        // 先填充，再位移。
        realOffset = fill(recycler, state, realOffset);

        // 累加实际滑动距离
        mVerticalOffset += realOffset;

        // 滑动
        offsetChildrenVertical(-realOffset);

        return realOffset;
    }

    /**
     * 获取某个childView在水平方向所占的空间
     */
    public int getDecoratedMeasurementHorizontal(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + params.leftMargin
                + params.rightMargin;
    }

    /**
     * 获取某个childView在竖直方向所占的空间
     */
    public int getDecoratedMeasurementVertical(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + params.topMargin
                + params.bottomMargin;
    }

    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }
}
